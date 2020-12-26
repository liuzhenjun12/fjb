package com.ruoyi.system.service.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.*;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.result.Re;
import com.ruoyi.common.core.domain.result.Ree;
import com.ruoyi.common.core.domain.result.Row;
import com.ruoyi.common.core.domain.result.Rows;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.system.domain.vo.BankExamVo;
import com.ruoyi.system.domain.vo.IdcardVo;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysBmbMapper;
import com.ruoyi.system.domain.SysBmb;
import com.ruoyi.system.service.ISysBmbService;

/**
 * 报名Service业务层处理
 *
 * @author ruoyi
 * @date 2020-11-29
 */
@Service
public class SysBmbServiceImpl implements ISysBmbService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysBmbMapper sysBmbMapper;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private RedisCache redisCache;
    /**
     * 查询报名
     *
     * @param id 报名ID
     * @return 报名
     */
    @Override
    public SysBmb selectSysBmbById(Long id)
    {
        return sysBmbMapper.selectSysBmbById(id);
    }

    /**
     * 查询报名列表
     *JSESSIONID=BB9E1A372D9191F45284800C259FD84E; Path=/examinationRY; HttpOnly
     * @param sysBmb 报名
     * @return 报名
     */
    @Override
    public List<SysBmb> selectSysBmbList(SysBmb sysBmb)
    {
        return sysBmbMapper.selectSysBmbList(sysBmb);
    }

    /**
     * 新增报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    @Override
    public int insertSysBmb(SysBmb sysBmb)
    {
        sysBmb.setCreateTime(DateUtils.getNowDate());
        return sysBmbMapper.insertSysBmb(sysBmb);
    }

    /**
     * 修改报名
     *
     * @param sysBmb 报名
     * @return 结果
     */
    @Override
    public int updateSysBmb(SysBmb sysBmb)
    {
        sysBmb.setUpdateTime(DateUtils.getNowDate());
        return sysBmbMapper.updateSysBmb(sysBmb);
    }

    /**
     * 批量删除报名
     *
     * @param ids 需要删除的报名ID
     * @return 结果
     */
    @Override
    public int deleteSysBmbByIds(Long[] ids)
    {
        return sysBmbMapper.deleteSysBmbByIds(ids);
    }

    /**
     * 通过id数组查询
     * @param ids
     * @return
     */
    @Override
    public List<SysBmb> selectSysBmbByIds(Long[] ids) {
        return sysBmbMapper.selectSysBmbByIds(ids);
    }

    /**
     * 删除报名信息
     *
     * @param id 报名ID
     * @return 结果
     */
    @Override
    public int deleteSysBmbById(Long id)
    {
        return sysBmbMapper.deleteSysBmbById(id);
    }

    /**
     *
     * @param bmbList 报名表
     * @param isUpdateSupport 是否更新
     * @param operName 导入人
     * @param kaoshirq 考试日期
     * @param deptId 机构名称
     * @param pici 批次
     * @param avatarUrl 相片地址
     * @param ancestors
     * @return
     */
    @Override
    public String importBmb(List<SysBmb> bmbList, boolean isUpdateSupport, String operName, String kaoshirq, Long deptId, String pici, String avatarUrl, String ancestors) {
        if (StringUtils.isNull(bmbList) || bmbList.size() == 0)
        {
            throw new CustomException("导入报名表数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        for (SysBmb bmb : bmbList)
        {
            try
            {
                if(StringUtils.isEmpty(bmb.getIdcard())){
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、身份证号是空的 " );
                        continue;
                    }
                }
                if(StringUtils.isEmpty(bmb.getName())){
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + failureNum + "、姓名是空的 " );
                        continue;
                    }
                }
                SysBmb bmb1=new SysBmb();
                bmb1.setName(bmb.getName());
                bmb1.setIdcard(bmb.getIdcard());
                bmb1.setDeptId(deptId);
                bmb1.setPici(pici);
                // 验证是否存在这个用户
                List<SysBmb> u = sysBmbMapper.selectSysBmbList(bmb1);
                if (u.isEmpty())
                {
                    if(StringUtils.isEmpty(bmb.getExamType())){
                      bmb.setExamType("临柜");
                    }
                    bmb.setKaoshiTime(sdf.parse(kaoshirq));
                    bmb.setPici(pici);
                    bmb.setDeptId(deptId);
                    bmb.setAvatarUrl(avatarUrl);
                    bmb.setAncestors(ancestors);
                    bmb.setShichao("W");
                    bmb.setLiluen("W");
                    bmb.setKaoshiType("3");
                    bmb.setCreateBy(operName);
                    this.insertSysBmb(bmb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + bmb.getName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    bmb.setId(u.get(0).getId());
                    bmb.setUpdateBy(operName);
                    this.updateSysBmb(bmb);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、账号 " + bmb.getName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、账号 " + bmb.getName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " +bmb.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public String luru(List<SysBmb> list, String operName,Long userid) {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new CustomException("没有数据需要录入！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        IdentityCodeUtils idcard = new IdentityCodeUtils();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        for(SysBmb t:list){
            if("Y".equals(t.getSfwc())){
                continue;
            }
            try {
                /**
                 * 1.判断身份证是否合法
                 */
                if (!idcard.Verify(t.getIdcard())) {
                    t.setFucha("身份证号不合法");
                    t.setUpdateBy(operName);
                    this.updateSysBmb(t);
                    failureNum++;
                    successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 身份证号不合法");
                    continue;
                }
                /**
                 * 2.通过身份证获取信息，如果获取不到，证明是首次
                 */
                String sfzGetName= HttpUtils.sendGet("http://221.226.21.180/examinationRY/loadExamineeInfo.action","idcard="+t.getIdcard());
                String kaoshidate=sdf.format(t.getKaoshiTime());
                String cid_sex = t.getIdcard().substring(16,17);
                /**
                 * 获取地区和银行的机构代码
                 */
                SysDept shi = sysDeptMapper.selectDeptById(t.getDeptId());
                SysDept diqu = sysDeptMapper.selectDeptById(shi.getParentId());
                SysDept sheng=sysDeptMapper.selectDeptById(diqu.getParentId());
                if(sfzGetName.length()>2){
                    /**
                     * 判断是否有机位
                     */
                    t.setKaoshiType("2");
                    String jiwei=HttpUtils.sendGet("http://221.226.21.180/examinationRY/loadPeopleBankExamList.action","bankNO="+shi.getJigouCode()+"&type=2");
                    if (jiwei.length()>2){
                        if(jiwei.indexOf(kaoshidate)==-1){
                            t.setFucha(kaoshidate+"没有机位");
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 证书审验没有机位");
                            break;
                        }
                        SubmitVo s = new SubmitVo();
                        s.setProvince(sheng.getDeptName());
                        s.setJigou(diqu.getJigouCode());
                        s.setBankno(shi.getJigouCode());
                        s.setUsername(t.getName());
                        s.setIdcard(t.getIdcard());
                        s.setCardtype("居民身份证");
                        if(Integer.parseInt(cid_sex)%2==0){
                            s.setSex("女");
                        }else {
                            s.setSex("男");
                        }
                        s.setExamtype(t.getExamType());
                        s.setSessionText("该测评不需要选择场次");
                        s.setSessionID("0");
                        s.setCity(diqu.getDeptName());
                        s.setBank(shi.getDeptName());
                        s.setEmail("532125082@qq.com");
                        s.setQq("532125082");
                        s.setPhone("18899859112");
                        s.setPeopleBankName("北京金储自动化技术有限公司（"+diqu.getDeptName()+"）");
                        s.setPbexamdateText(kaoshidate);
                        s.setExamdateText(kaoshidate);
                        Gson gs = new Gson();
                        List<BankExamVo> persons = gs.fromJson(jiwei, new TypeToken<List<BankExamVo>>() {}.getType());
                        for(BankExamVo v:persons){
                            if(kaoshidate.equals(v.getDate())){
                                s.setExamdate(v.getId());
                            }
                        }
                        Gson gson = new Gson();
                        List<IdcardVo> depart =gson.fromJson(sfzGetName, new TypeToken<List<IdcardVo>>() {}.getType());
                        if (!t.getName().equals(depart.get(0).getName())){
                            t.setFucha("系统姓名为:"+depart.get(0).getName());
                            s.setUsername(depart.get(0).getName());
                        }
                        if(StringUtils.isNotEmpty(depart.get(0).getCertificateCode())){
                            s.setCertificateID(depart.get(0).getCertificateCode());
                        }
                        s.setCertificateType("证书审验集中考试");
                        String gFilePath="http://221.226.21.180/examinationRY/upload/"+t.getIdcard()+".jpg";
                        if(ImageUtils.isImagesTrue(gFilePath)){
                            s.setImgShow("/examinationRY/upload/"+t.getIdcard()+".jpg");
                        }else {
                            t.setFucha(t.getFucha()+";没有相片，需要登录上传");
                            s.setImgShow("/examinationRY/upload/default.jpg");
                        }
                        PostResult result =OkHttpUtils.submitZhengshu(s);
                        if("failed".equals(result.getResult())){
                            t.setFucha(result.getReason());
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":"+result.getReason());
                            continue;
                        }else {
                            t.setSfwc("Y");
                            t.setFucha("报名成功");
                            t.setExamId(s.getExamdate().toString());
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            successNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":报名成功");
                            continue;
                        }
                    }else {
                        t.setFucha("证书审验没有机位");
                        t.setUpdateBy(operName);
                        this.updateSysBmb(t);
                        failureNum++;
                        successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 证书审验没有机位");
                        break;
                    }
                }else {
                    t.setKaoshiType("1");
                    String ky=HttpUtils.requestGet("http://221.226.21.180/examinationRY/checkQualification.action?Name="+t.getName()+"&IdNumber="+t.getIdcard());
                    if(ky.indexOf("0")==-1){
                        t.setFucha("不满足参加该场测评的资格");
                        t.setUpdateBy(operName);
                        this.updateSysBmb(t);
                        failureNum++;
                        successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 不满足参加该场测评的资格");
                        continue;
                    }

                String jiwei=HttpUtils.sendGet("http://221.226.21.180/examinationRY/loadPeopleBankExamList.action","bankNO="+shi.getJigouCode()+"&type=1");
                if (jiwei.length()>2) {
                    if (jiwei.indexOf(kaoshidate) == -1) {
                        t.setFucha(kaoshidate + "没有机位");
                        t.setUpdateBy(operName);
                        this.updateSysBmb(t);
                        failureNum++;
                        successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 首次考试没有机位");
                        break;
                    }
                    PhoteSubmitVo p=new PhoteSubmitVo();
                    Gson gs = new Gson();
                    List<BankExamVo> persons = gs.fromJson(jiwei, new TypeToken<List<BankExamVo>>() {}.getType());
                    for(BankExamVo v:persons){
                        if(kaoshidate.equals(v.getDate())){
                            p.setExamdate(v.getId().toString());
                            p.setPbexamdate(v.getId().toString());
                        }
                    }
                    p.setIdcard(t.getIdcard());
                    p.setName(t.getName());
                    p.setProvince(sheng.getDeptName());
                    p.setCity(diqu.getDeptName());
                    p.setJigou(diqu.getJigouCode());
                    p.setBank(shi.getJigouCode());
                    p.setBankname(shi.getJigouCode());
                    p.setBankno(shi.getJigouCode());
                    if(Integer.parseInt(cid_sex)%2==0){
                        p.setSex("女");
                    }else {
                        p.setSex("男");
                    }
                    p.setExamtype(t.getExamType());
                    File folder =null;
                    folder = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+t.getIdcard()+".jpg");
                    if(!folder.exists()){
                        String sfjxm=t.getName()+t.getIdcard();
                        folder = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+sfjxm+".jpg");
                        if(!folder.exists()){
                            String sfjxm1=t.getName()+"+"+t.getIdcard();
                            folder = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+sfjxm1+".jpg");
                        }
                    }
                    if(!folder.exists()){
                        t.setFucha("没有相片,无法注册");
                        t.setUpdateBy(operName);
                        this.updateSysBmb(t);
                        failureNum++;
                        successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 没有相片,无法注册");
                        continue;
                    }else {
                        PostResult result= OkHttpUtils.OkHttpOpst(p,folder);
                        if("failed".equals(result.getResult())){
                            t.setFucha(result.getReason());
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":"+result.getReason());
                            continue;
                        }else {
                            t.setSfwc("Y");
                            t.setFucha("报名成功");
                            t.setExamId(p.getExamdate());
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            successNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":报名成功");
                            continue;
                        }
                    }
                }else {
                    t.setFucha("首次考试没有机位");
                    t.setUpdateBy(operName);
                    this.updateSysBmb(t);
                    failureNum++;
                    successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 首次考试没有机位");
                    break;
                }
                }
            }catch (Exception e)
                {
                    failureNum++;
                    String msg = "<br/>" + t.getId() + "、姓名 " +t.getName() + " 录入失败：";
                    successMsg.append(msg + e.getMessage());
                    log.error(msg, e);
                }
        }
        successMsg.insert(0, "恭喜您，数据已全部录入完毕！共 成功" + successNum + " 条，失败"+failureNum+" 条，数据如下：");
        return successMsg.toString();
    }

    /**
     * 是否完成状态修改npk
     * @param sysBmb
     * @return
     */
    @Override
    public int updateSfwcStatus(SysBmb sysBmb) {
        return sysBmbMapper.updateSysBmb(sysBmb);
    }

    @Override
    public AjaxResult updateAvatar(SysBmb bmb, String operName, Long userId) throws IOException {
        if(StringUtils.isEmpty(bmb.getExamId())){
            return AjaxResult.error("只有录入成功后才能修改照片");
        }
        File folder =null;
        folder = new File("D:/jinchu/uploadPath/avatar/"+bmb.getAvatarUrl()+"/"+bmb.getIdcard()+".jpg");
        if(!folder.exists()){
            String sfjxm=bmb.getName()+bmb.getIdcard();
            folder = new File("D:/jinchu/uploadPath/avatar/"+bmb.getAvatarUrl()+"/"+sfjxm+".jpg");
            if(!folder.exists()){
                String sfjxm1=bmb.getName()+"+"+bmb.getIdcard();
                folder = new File("D:/jinchu/uploadPath/avatar/"+bmb.getAvatarUrl()+"/"+sfjxm1+".jpg");
            }
        }
        if(!folder.exists()){
            return AjaxResult.error("未找到照片");
        }
        String session="";
        if (redisCache.hasKey(Constants.SYS_SESSION+userId)){
            session=redisCache.getCacheObject(Constants.SYS_SESSION+userId);
        }else {
            session=HttpUtils.getSession("http://221.226.21.180/examinationRY/");
            redisCache.setCacheObject(Constants.SYS_SESSION+userId,session,5, TimeUnit.MINUTES);
        }
        /*** 登录*/
        String args="operatoraccount="+bmb.getName()+"&password="+bmb.getIdcard()+"&roleID=8&sessionUserID=0";
        String url="http://221.226.21.180/examinationRY/userLogin.action";
        String su=OkHttpUtils.wwwFormPost(args,url,session,"POST");
        if(StringUtils.isEmpty(su)){
            return AjaxResult.error("登录访问失败");
        }
        Re json = JSON.parseObject(su,Re.class);
        if(!"success".equals(json.getResp().getResultMsg())){
            return AjaxResult.error("用户名或者密码错误");
        }
        /*** 获取场次列表*/
//        String cc=OkHttpUtils.formHtml("http://221.226.21.180/examinationRY/examManage.html?serviceType=queryExamineeExam",session,"POST");
//        if(StringUtils.isEmpty(cc)){
//            throw new CustomException("访问失败！");
//        }
//        Ree ee=JSON.parseObject(cc,Ree.class);
        /*** 通过场次id获取信息*/
        String exid="examID="+bmb.getExamId();
        String cs=OkHttpUtils.wwwFormPost(exid,"http://221.226.21.180/examinationRY/exam.html?serviceType=getExamineeInfo",session,"POST");
        if(StringUtils.isEmpty(cs)){
            return AjaxResult.error("访问场次信息失败");
        }
        Rows rows=JSON.parseObject(cs,Rows.class);
         if("已分配".equals(rows.getRows().get(0).getState())){
             return AjaxResult.error("已分配状态下不能修改");
         }
        Row row=rows.getRows().get(0);
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        UpdateResult u=new UpdateResult();
        u.setId(row.getId().toString());
        u.setExamDateOld(sdf.format(bmb.getKaoshiTime()));
        u.setExamIDOld(bmb.getExamId());
        u.setUsername(bmb.getName());
        u.setSex(row.getSex());
        u.setExamtype(row.getCertificateType());
        u.setIdcardType("居民身份证");
        u.setParentId(row.getParentId());
        u.setCid(row.getCid());
        u.setBankNO(row.getBankNO());
        u.setBankNOShow(row.getBankNO());
        u.setProvince(row.getProvince());
        u.setCity(row.getCity());
        u.setCityNew(row.getCity());
        u.setJigou(row.getJigou());
        u.setJigous(row.getParentId());
        u.setBankNew(row.getBankNO());
        u.setExamdate(bmb.getExamId());
        u.setPbexamdate(bmb.getExamId());
        u.setEmail(row.getEmail());
        u.setPhone(row.getPhone());
        String sult=OkHttpUtils.updateTu(u,folder,session);
        if(StringUtils.isEmpty(sult)){
            return AjaxResult.error("修改失败");
        }
        if(sult.indexOf("success")==-1){
            return AjaxResult.error("修改失败");
        }
        bmb.setFucha("录入成功");
        bmb.setUpdateBy(operName);
        sysBmbMapper.updateSysBmb(bmb);
        return AjaxResult.success("修改成功");
    }

}
