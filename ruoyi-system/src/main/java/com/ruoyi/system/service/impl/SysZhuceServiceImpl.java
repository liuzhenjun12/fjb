package com.ruoyi.system.service.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.chengji.CjR;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysZhuce;
import com.ruoyi.common.core.domain.pici.PiciList;
import com.ruoyi.common.core.domain.pici.PiciResult;
import com.ruoyi.common.core.domain.result.Re;
import com.ruoyi.common.core.domain.zuce.ZcRow;
import com.ruoyi.common.core.domain.zuce.Zrow;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.system.domain.SysBmb;
import com.ruoyi.system.mapper.SysBmbMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysZhuceMapper;
import com.ruoyi.system.service.ISysZhuceService;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-28
 */
@Service
public class SysZhuceServiceImpl implements ISysZhuceService
{
    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    private SysZhuceMapper sysZhuceMapper;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private SysBmbMapper sysBmbMapper;
    /**
     * 查询【请填写功能名称】
     *
     * @param zcId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysZhuce selectSysZhuceById(Long zcId)
    {
        return sysZhuceMapper.selectSysZhuceById(zcId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysZhuce> selectSysZhuceList(SysZhuce sysZhuce)
    {
        return sysZhuceMapper.selectSysZhuceList(sysZhuce);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSysZhuce(SysZhuce sysZhuce)
    {
        sysZhuce.setCreateTime(DateUtils.getNowDate());
        return sysZhuceMapper.insertSysZhuce(sysZhuce);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param sysZhuce 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysZhuce(SysZhuce sysZhuce)
    {
        sysZhuce.setUpdateTime(DateUtils.getNowDate());
        return sysZhuceMapper.updateSysZhuce(sysZhuce);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param zcIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysZhuceByIds(Long[] zcIds)
    {
        return sysZhuceMapper.deleteSysZhuceByIds(zcIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param zcId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysZhuceById(Long zcId)
    {
        return sysZhuceMapper.deleteSysZhuceById(zcId);
    }

    @Override
    public AjaxResult importZc(SysZhuce sysZhuce, String operName, Long userId) throws IOException {
        String session="";
        if (redisCache.hasKey(Constants.SYS_SESSION+sysZhuce.getDeptId())){
            session=redisCache.getCacheObject(Constants.SYS_SESSION+sysZhuce.getDeptId());
        }else {
            session= HttpUtils.getSession("http://221.226.21.180/examinationRY/manager.jsp");
            redisCache.setCacheObject(Constants.SYS_SESSION+sysZhuce.getDeptId(),session,50, TimeUnit.MINUTES);
        }
        /*** 登录*/
        SysDept shi = sysDeptMapper.selectDeptById(sysZhuce.getDeptId());
        String args="operatoraccount="+shi.getJigouCode()+"&password=123456&roleID=10&sessionUserID=0";
        Boolean login=OkHttpUtils.loginPost(args,session);
        if(!login){
            return AjaxResult.error("登录访问失败");
        }
        /*** 查询注册日期列表*/
        String pi=OkHttpUtils.wwwFormPost("","http://221.226.21.180/examinationRY/examManage.html?serviceType=getPeopleBankSelectedExamListBySecondBank",session,"POST");
        if (StringUtils.isBlank(pi)){
            return AjaxResult.error("访问日期列表失败");
        }
        PiciResult piciResult=JSON.parseObject(pi,PiciResult.class);
        if (piciResult.getResp().getResultList().isEmpty()){
            return AjaxResult.error("日期列表是空的");
        }
        List<PiciList> lists=piciResult.getResp().getResultList();
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd" );
        /*** 循环日期列表，获取注册列表*/
        for(PiciList l:lists){
            if(l.getDate().equals(sdf.format(sysZhuce.getKaoshiTime()))){
            getZhuceList(session,l.getCertificateType(),"serviceType=getExamineeDatas&state=待审核&examID="+l.getId()+"&cidSearch=&page=1&rows=2000",sysZhuce,operName,"0",l.getAuditDeadlineDateTime(),l.getId());
            getZhuceList(session,l.getCertificateType(),"serviceType=getExamineeDatas&state=审核通过&examID="+l.getId()+"&cidSearch=&page=1&rows=2000",sysZhuce,operName,"1",l.getAuditDeadlineDateTime(),l.getId());
            getZhuceList(session,l.getCertificateType(),"serviceType=getExamineeDatas&state=拒绝通过&examID="+l.getId()+"&cidSearch=&page=1&rows=2000",sysZhuce,operName,"2",l.getAuditDeadlineDateTime(),l.getId());
            }
        }
        return AjaxResult.success("操作成功");
    }

    /**
     * 审核
     * @param is
     * @param operName
     * @return
     */
    @Override
    public AjaxResult appver(String ids, boolean is, String operName,Long deptId,String exmaId,String pici) throws ParseException, IOException {
        String session="";
        if (redisCache.hasKey(Constants.SYS_SESSION+deptId)){
            session=redisCache.getCacheObject(Constants.SYS_SESSION+deptId);
        }else {
            session= HttpUtils.getSession("http://221.226.21.180/examinationRY/manager.jsp");
            redisCache.setCacheObject(Constants.SYS_SESSION+deptId,session,50, TimeUnit.MINUTES);
        }
        /*** 登录*/
        SysDept shi = sysDeptMapper.selectDeptById(deptId);
        String args="operatoraccount="+shi.getJigouCode()+"&password=123456&roleID=10&sessionUserID=0";
        Boolean login=OkHttpUtils.loginPost(args,session);
        if(!login){
            return AjaxResult.error("登录访问失败");
        }
        String b="",status="";
        if(is){
            b= URLEncoder.encode("审核通过","UTF-8");
            status="1";
        }else {
            b= URLEncoder.encode("拒绝通过","UTF-8");
            status="2";
        }
        ids=ids.replaceAll(",","%2C");
        String str="serviceType=updateExamineeState&state="+b+"&examID="+exmaId+"&ids=%5B"+ ids+"%5D";
        String sult=OkHttpUtils.wwwFormPost(str,"http://221.226.21.180/examinationRY/examSecondBank.action",session,"POST");
        if(StringUtils.isBlank(sult)){
            return AjaxResult.error("审核失败，服务器访问异常");
        }
        System.out.println(sult+"==>返回结果");
        if(sult.indexOf("success")>-1){
//            Long[] attr = new Long[at.length];
//            for (int idx = 0; idx < at.length; idx++) {
//                attr[idx] = Long.parseLong(at[idx]);
//            }
//         int i=sysZhuceMapper.updateStatus(status,attr,pici,deptId);
            return AjaxResult.success("审核成功");
        }else {
            return AjaxResult.error("审核失败");
        }
    }

    /**
     * 核对注册表与报名表
     * @param pici
     * @param operName
     * @param deptId
     * @return
     */
    @Override
    public AjaxResult hedui(String pici, String operName, Long deptId) {
        SysZhuce zhuce=new SysZhuce();
        zhuce.setPici(pici);
        zhuce.setDeptId(deptId);
        List<SysZhuce> zhuces=sysZhuceMapper.selectSysZhuceList(zhuce);
        if(zhuces.isEmpty()){
            return AjaxResult.error("注册表没有数据");
        }
        SysBmb bmb=new SysBmb();
        bmb.setPici(pici);
        bmb.setDeptId(deptId);
        List<SysBmb> bmbs=sysBmbMapper.selectSysBmbList(bmb);
        if(bmbs.isEmpty()){
            return AjaxResult.error("报名表没有数据");
        }
        int successNum = 0;
        int failureNum = 0;
        boolean is=false;
        for(SysBmb b1:bmbs){
            is=false;
            String sfz=b1.getIdcard().replace(" ","");
            for(SysZhuce z1:zhuces){
                String sf=z1.getCid().replace(" ","");
                if(sfz.equals(sf)){
                    String status="";
                    if("0".equals(z1.getStatus())){
                        status="等待审核";
                    }else if("1".equals(z1.getStatus())){
                        status="通过审核";
                    }else {
                        status="驳回审核";
                    }
                    b1.setSfwc("Y");
                    b1.setFucha(status);
                    b1.setUpdateBy(operName);
                    successNum++;
                    is=true;
                    sysBmbMapper.updateSysBmb(b1);
                    continue;
                }
            }
            if(!is){
                    failureNum++;
                    b1.setSfwc("N");
                    b1.setFucha("核对未报名成功");
                    b1.setUpdateBy(operName);
                    sysBmbMapper.updateSysBmb(b1);
            }
        }
        return AjaxResult.success(failureNum==0?"全部核对成功":"报名成功"+successNum+"人,报名失败"+failureNum+"人");
    }

    private void getZhuceList( String session, String certificateType, String s,SysZhuce sysZhuce, String operName,String status,String daoTime,String id) throws IOException {
     String su=OkHttpUtils.wwwFormPost(s,"http://221.226.21.180/examinationRY/examSecondBank.html",session,"POST");
     if(!StringUtils.isBlank(su)){
         Zrow r=JSON.parseObject(su,Zrow.class);
         if(!r.getRows().isEmpty()){
             List<ZcRow> rows=r.getRows();
             for(ZcRow w:rows){
                 sysZhuce.setUsername(w.getUsername());
                 sysZhuce.setCid(w.getCid());
                 sysZhuce.setRemark(id);
                 List<SysZhuce> zhuces=sysZhuceMapper.selectSysZhuceList(sysZhuce);
                 if(zhuces.isEmpty()) {
                     SysZhuce z = new SysZhuce();
                     z.setAlternatename(w.getAlternateName());
                     z.setAncestors(sysZhuce.getAncestors());
                     z.setCid(w.getCid());
                     z.setDeptId(sysZhuce.getDeptId());
                     z.setEmail(w.getEmail());
                     z.setExamname(w.getExamName());
                     z.setFilename(w.getFileName());
                     z.setId(w.getId());
                     z.setIdcardtype(w.getIdcardType());
                     z.setKaoshiTime(sysZhuce.getKaoshiTime());
                     if (certificateType.indexOf("首次") > -1) {
                         z.setKaoshiType("首次");
                     } else {
                         z.setKaoshiType("证书");
                     }
                     z.setDaoTime(daoTime);
                     z.setPhone(w.getPhone());
                     z.setPici(sysZhuce.getPici());
                     z.setUsername(w.getUsername());
                     z.setSex(w.getSex());
                     z.setStatus(status);
                     z.setRemark(id);
                     z.setCreateBy(operName);
                     this.insertSysZhuce(z);
                 }else {
                     if(!status.equals(zhuces.get(0).getStatus())){
                         SysZhuce zhuce=zhuces.get(0);
                         zhuce.setDaoTime(daoTime);
                         zhuce.setStatus(status);
                         zhuce.setUpdateBy(operName);
                         this.updateSysZhuce(zhuce);
                     }
                 }
             }
         }
     }
    }
}
