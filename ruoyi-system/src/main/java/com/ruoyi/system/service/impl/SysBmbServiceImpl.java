package com.ruoyi.system.service.impl;

import java.io.*;
import java.nio.charset.Charset;
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
import com.ruoyi.common.core.domain.PhoteSubmitVo;
import com.ruoyi.common.core.domain.PostResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.*;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.system.domain.vo.BankExamVo;
import com.ruoyi.system.domain.vo.IdcardVo;
import com.ruoyi.system.domain.vo.SubmitVo;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
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
     *
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
            System.out.println(bmb.toString());
            try
            {
                if(StringUtils.isEmpty(bmb.getName())||StringUtils.isEmpty(bmb.getIdcard())){
                    {
                        throw new CustomException("姓名或者身份证号不能为空！");
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
        String session="";
        if (redisCache.hasKey(Constants.SYS_SESSION+userid)){
            session=redisCache.getCacheObject(Constants.SYS_SESSION+userid);
        }else {
            session=HttpUtils.getSession("http://221.226.21.180/examinationRY/register.jsp");
            redisCache.setCacheObject(Constants.SYS_SESSION+userid,session,300, TimeUnit.MINUTES);
        }
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
                        s.setExamtype("临柜");
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
                        String submit =submitZhengshu(s);
                        if(submit=="Y"){
                            t.setSfwc("Y");
                            t.setFucha(t.getFucha()+";录入成功");
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            successNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 证书审验录入成功");
                        }else {
                            t.setFucha(submit);
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 证书审验录入失败:"+submit);
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
                    p.setExamtype("临柜");

                    File folder = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+t.getIdcard()+".jpg");
                    if(folder.exists()){
                        PostResult result= OkHttpUtils.OkHttpOpst(p,folder);
                        if("failed".equals(result.getResult())){
                            t.setFucha(result.getReason());
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":"+result.getReason());
                            continue;
                        }else {
                            t.setFucha("报名成功");
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            successNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":报名成功");
                            continue;
                        }
                    }else {
                        String sfjxm=t.getName()+t.getIdcard();
                        String sfjxm1=t.getName()+"+"+t.getIdcard();
                        File folder1 = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+sfjxm+".jpg");
                        File folder2 = new File("D:/jinchu/uploadPath/avatar/"+t.getAvatarUrl()+"/"+sfjxm1+".jpg");
                        if(folder1.exists()){
                            PostResult result= OkHttpUtils.OkHttpOpst(p,folder1);
                            if("failed".equals(result.getResult())){
                                t.setFucha(result.getReason());
                                t.setUpdateBy(operName);
                                this.updateSysBmb(t);
                                failureNum++;
                                successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":"+result.getReason());
                                continue;
                            }else {
                                t.setFucha("报名成功");
                                t.setUpdateBy(operName);
                                this.updateSysBmb(t);
                                successNum++;
                                successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":报名成功");
                                continue;
                            }
                        }else if(folder2.exists()){
                            PostResult result= OkHttpUtils.OkHttpOpst(p,folder2);
                            if("failed".equals(result.getResult())){
                                t.setFucha(result.getReason());
                                t.setUpdateBy(operName);
                                this.updateSysBmb(t);
                                failureNum++;
                                successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":"+result.getReason());
                                continue;
                            }else {
                                t.setFucha("报名成功");
                                t.setUpdateBy(operName);
                                this.updateSysBmb(t);
                                successNum++;
                                successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + ":报名成功");
                                continue;
                            }
                        }else {
                            t.setFucha("没有相片,无法注册");
                            t.setUpdateBy(operName);
                            this.updateSysBmb(t);
                            failureNum++;
                            successMsg.append("<br/>" + t.getId() + "、姓名 " + t.getName() + " 没有相片,无法注册");
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

    private String submitShouCi(PhoteSubmitVo p, String filePath) throws IOException{
        System.out.println(p.toString());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addTextBody("parentId", "");
        builder.addTextBody("parentIds", "");
        builder.addTextBody("cardtype", p.getCardtype());
        builder.addTextBody("idcard", p.getIdcard());
        builder.addTextBody("bankname", "");
        builder.addTextBody("username", p.getName());
        builder.addTextBody("name", p.getName());
        builder.addTextBody("province", p.getProvince());
        builder.addTextBody("city", p.getCity());
        builder.addTextBody("jigou", p.getJigou());
        builder.addTextBody("peopleBankNo","");
        builder.addTextBody("peopleBankName", "");
        builder.addTextBody("bank",p.getBank());
        builder.addTextBody("bankNumber", p.getBank());
        builder.addTextBody("bankno",p.getBankno());
        builder.addTextBody("examdate",p.getExamdate().toString());
        builder.addTextBody("pbexamdate",p.getExamdate().toString());
        builder.addTextBody("session", "0");
        builder.addTextBody("examtype", p.getExamtype());
        builder.addTextBody("sex", p.getSex());
        builder.addTextBody("email",p.getEmail());
        builder.addTextBody("qq", p.getQq());
        builder.addTextBody("phone", p.getPhone());
        builder.addTextBody("certificateID","");
        builder.addTextBody("targetImg",p.getIdcard());
        ContentType contentType2 = ContentType.create("multipart/form-data", Charset.forName("utf-8"));
        InputStream inputStream  = new FileInputStream(new File(filePath));

        builder.addBinaryBody("cultural", inputStream, contentType2, filePath);
        HttpEntity entity = builder.build();
        HttpPost httpPost = new HttpPost("http://221.226.21.180/examinationRY/photeSubmit.action");

        httpPost.setEntity(entity);
        httpPost.addHeader("UTOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmYWlyeWxhbmQiLCJleHAiOjE1ODgwODEzOTE3MDYsInR5cGUiOiJvdXRzaWRlIiwidXNlcklkIjoiMTI0OTQ5NjQ4Mzc0NzEzNTQ4OSIsImlhdCI6MTU4NzQ3NjU5MTcwNn0.EBqIx8WVsoFHz94bF2fy9T2R1nichhHCO8_GQ0Coa6Q");

        System.out.println("创建post请求并装载好打包数据");
        // 4. 创建HttpClient对象，传入httpPost执行发送网络请求的动作
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println("发送post请求并获取结果");
        // 5. 获取返回的实体内容对象并解析内容
        HttpEntity resultEntity = response.getEntity();
        String responseMessage = "";
        try{
            System.out.println("开始解析结果");
            if(resultEntity!=null){
                InputStream is = resultEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseMessage = sb.toString();
                System.out.println("解析完成，解析内容为"+ responseMessage);
            }
            EntityUtils.consume(resultEntity);
        }finally{
            if (null != response){
                response.close();
            }
        }
        return responseMessage;
    }

    /**
     * 证书审验提交注册
     * @param s
     * @return
     * @throws IOException
     */
    private String submitZhengshu(SubmitVo s) throws IOException {
        System.out.println(s.toString());
        Map<String, String> map = new HashMap<>();
        map.put("imgShow", s.getImgShow());
        map.put("jigou", s.getJigou());
        map.put("username", s.getUsername());
        map.put("sex", s.getSex());
        map.put("examtype", s.getExamtype());
        map.put("cardtype", s.getCardtype());
        map.put("idcard", s.getIdcard());
        map.put("examdateText", s.getExamdateText());
        map.put("examdate", s.getExamdate().toString());
        map.put("pbexamdateText", s.getPbexamdateText());
        map.put("sessionText", s.getSessionText());
        map.put("sessionID", s.getSessionID());
        map.put("certificateType",s.getCertificateType());
        map.put("certificateID", s.getCertificateID());
        map.put("bankno", s.getBankno());
        map.put("province", s.getProvince());
        map.put("city", s.getCity());
        map.put("peopleBankName", s.getPeopleBankName());
        map.put("bank", s.getBank());
        map.put("email",s.getEmail());
        map.put("qq", s.getQq());
        map.put("phone", s.getPhone());
        CloseableHttpResponse response = null;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String encoding = "utf-8";
            //创建post请求对象
            HttpPost httpPost = new HttpPost("http://221.226.21.180/examinationRY/register.action");
            //装填请求参数
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            //设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(list, encoding));
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            response = httpClient.execute(httpPost);
            System.out.println(response.getStatusLine()+"===============");
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(result+"------------");
            if(result.indexOf("success")>-1){
                return "Y";
            }else {
                if(result.indexOf("无法在该机构注册此考试")>-1){
                    return "报名通道已关闭";
                }
                if(result.length()>100){
                    return result.substring(0,100);
                }
                return result;
            }
        } catch (IOException e) {
            log.error("POST请求发出失败，请求的地址为{}，参数为{}，错误信息为{}", "", JSON.toJSON(map), e.getMessage(), e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }else {
                    return "N";
                }
            } catch (IOException e) {
                log.error("POST请求response关闭异常，错误信息为{}", e.getMessage(), e);
            }
        }
        return null;
    }


    private String submitZhengshu_1(SubmitVo s,String filePath) throws IOException {
        System.out.println(s.toString());
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF-8"));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        ContentType contentType = ContentType.create("application/json", Charset.forName("utf-8"));
        builder.addTextBody("jigou", s.getJigou());
        builder.addTextBody("username", s.getUsername());
        builder.addTextBody("sex", s.getSex());
        builder.addTextBody("examtype", s.getExamtype());
        builder.addTextBody("cardtype", s.getCardtype());
        builder.addTextBody("idcard", s.getIdcard());
        builder.addTextBody("examdateText", s.getExamdateText());
        builder.addTextBody("examdate", s.getExamdate().toString());
        builder.addTextBody("pbexamdateText", s.getPbexamdateText());
        builder.addTextBody("sessionText", s.getSessionText());
        builder.addTextBody("sessionID", s.getSessionID());
        builder.addTextBody("certificateType",s.getCertificateType());
        builder.addTextBody("certificateID", "");
        builder.addTextBody("bankno", s.getBankno());
        builder.addTextBody("province", s.getProvince());
        builder.addTextBody("city", s.getCity());
        builder.addTextBody("peopleBankName", s.getPeopleBankName());
        builder.addTextBody("bank", s.getBank());
        builder.addTextBody("email",s.getEmail());
        builder.addTextBody("qq", s.getQq());
        builder.addTextBody("phone", s.getPhone());
        ContentType contentType2 = ContentType.create("multipart/form-data", Charset.forName("utf-8"));
        InputStream inputStream  = new FileInputStream(new File(filePath));

        builder.addBinaryBody("img", inputStream, contentType2, filePath);
        HttpEntity entity = builder.build();
        HttpPost httpPost = new HttpPost("http://221.226.21.180/examinationRY/register.action");
        httpPost.setEntity(entity);
        httpPost.addHeader("UTOKEN", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmYWlyeWxhbmQiLCJleHAiOjE1ODgwODEzOTE3MDYsInR5cGUiOiJvdXRzaWRlIiwidXNlcklkIjoiMTI0OTQ5NjQ4Mzc0NzEzNTQ4OSIsImlhdCI6MTU4NzQ3NjU5MTcwNn0.EBqIx8WVsoFHz94bF2fy9T2R1nichhHCO8_GQ0Coa6Q");
//        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");

        System.out.println("创建post请求并装载好打包数据");
        // 4. 创建HttpClient对象，传入httpPost执行发送网络请求的动作
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println("发送post请求并获取结果");
        // 5. 获取返回的实体内容对象并解析内容
        HttpEntity resultEntity = response.getEntity();
        String responseMessage = "";
        try{
            System.out.println("开始解析结果");
            if(resultEntity!=null){
                InputStream is = resultEntity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuffer sb = new StringBuffer();
                String line = "";
                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                responseMessage = sb.toString();
                System.out.println("解析完成，解析内容为"+ responseMessage);
            }
            EntityUtils.consume(resultEntity);
        }finally{
            if (null != response){
                response.close();
            }
        }
        return responseMessage;
    }

}
