package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysYuyue;
import com.ruoyi.common.core.domain.yuyue.*;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.mapper.SysYuyueMapper;
import com.ruoyi.system.service.ISysYuyueService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务调度测试
 *
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{
    @Autowired
    private RedisCache redisCache;


    public void yuyue(String jobName,String bankNo,String bankName,String kaoshiTime) throws IOException, SchedulerException {
        /*** 查看任预约表中是否已经存在预约日期*/
        SysYuyue yue=new SysYuyue();
        yue.setBankNo(bankNo);
        yue.setKaoshiTime(kaoshiTime);
        List<SysYuyue> yuyueList= SpringUtils.getBean(ISysYuyueService.class).selectSysYuyueList(yue);
        if(!yuyueList.isEmpty()){
            /*** 如果存在暂停预约*/
            closeTask(jobName);
            System.out.println("已存在机位，结束任务");
            return;
        }
        String session="";
        if (redisCache.hasKey(Constants.SYS_SESSION+bankNo)){
            session=redisCache.getCacheObject(Constants.SYS_SESSION+bankNo);
        }else {
            session= HttpUtils.getSession("http://221.226.21.180/examinationRY/");
            redisCache.setCacheObject(Constants.SYS_SESSION+bankNo,session,50, TimeUnit.MINUTES);
        }
        System.out.println("session==>"+redisCache.getCacheObject(Constants.SYS_SESSION+bankNo));
        /*** 登录*/
        String args="operatoraccount="+bankNo+"&password=123456&roleID=10&sessionUserID=0";
        Boolean login= OkHttpUtils.loginPost(args,session);
        if(!login){
            throw new CustomException("登录失败！");
        }
        /*** 获取测评中心预约日期列表*/
        String sult=OkHttpUtils.wwwFormPost("","http://221.226.21.180/examinationRY/examManage.html?serviceType=getPeopleBankSelectedExamList",session,"POST");
        if(StringUtils.isBlank(sult)){
            throw new CustomException("访问失败！");
        }else {
            Pic pic= JSON.parseObject(sult,Pic.class);
            if(pic.getResp().getResultList().isEmpty()){
              yyuejiwei(kaoshiTime,session,bankNo,bankName);
            }else {
                List<PicList> list=pic.getResp().getResultList();
                Boolean is=false;
                for(PicList p:list){
                    if(kaoshiTime.equals(p.getDate())){
                     is=true;
                    }
                }
                if(!is){
                    yyuejiwei(kaoshiTime,session,bankNo,bankName);
                }
            }
        }
    }

    private void yyuejiwei(String kaoshiTime,String session,String bankNo,String bankName) throws IOException {
        /*** 如果没有数据从测评中心获取*/
        String yu=OkHttpUtils.wwwFormPost("serviceType=getExamsListForDatagrid","http://221.226.21.180/examinationRY/examManage.html",session,"POST");
        if(StringUtils.isBlank(yu)){
            throw new CustomException("获取预约列表失败！");
        }
        Yue yue1=JSON.parseObject(yu,Yue.class);
        List<YueList> yues=yue1.getRows();
        List<YueList> emp=new ArrayList<>();
        for(YueList Y:yues){
            if(kaoshiTime.equals(Y.getDate())&&"N".equals(Y.getCheckFlag())){
                emp.add(Y);
            }
        }
        if(emp.isEmpty()){
            throw new CustomException(kaoshiTime+"没有公布！");
        }
        YueYue yueYue=new YueYue();
        yueYue.setExamSelected(emp);
        String yao=JSON.toJSONString(yueYue);
        System.out.println(yao+"=========yao");
        /*** 从测评中心添加预约日期*/
        String daoyue=OkHttpUtils.yuyuePost(yao,session);
        System.out.println(daoyue+"=========daoyue");
        if(StringUtils.isBlank(daoyue)){
            throw new CustomException(kaoshiTime+"添加预约日期失败！");
        }
        Pic pic1=JSON.parseObject(daoyue,Pic.class);
        if (pic1.getResp().getResult()>1){
            for(YueList E:emp){
                System.out.println(E.getId()+","+E.getCertificateType());
                Pay pay=new Pay();
                YueTa yueTa=new YueTa();
                String AM="serviceType=getPeopleBankSessionsForSelect&segmentID=AM&examID="+E.getId();
                String su1=OkHttpUtils.wwwFormPost(AM,"http://221.226.21.180/examinationRY/examManage.html",session,"POST");
                if(StringUtils.isBlank(su1)){
                    throw new CustomException(E.getId()+"获取上午预约列表失败！");
                }
                String PM="serviceType=getPeopleBankSessionsForSelect&segmentID=PM&examID="+E.getId();
                String su2=OkHttpUtils.wwwFormPost(PM,"http://221.226.21.180/examinationRY/examManage.html",session,"POST");
                if(StringUtils.isBlank(su2)){
                    throw new CustomException(E.getId()+"获取下午预约列表失败！");
                }
                String NM="serviceType=getPeopleBankSessionsForSelect&segmentID=NM&examID="+E.getId();
                String su3=OkHttpUtils.wwwFormPost(NM,"http://221.226.21.180/examinationRY/examManage.html",session,"POST");
                if(StringUtils.isBlank(su3)){
                    throw new CustomException(E.getId()+"获取晚上预约列表失败！");
                }
                Shi shi1=JSON.parseObject(su1,Shi.class);
                List<ShiList> shiLists1=shi1.getRows();
                if(shiLists1.isEmpty()){
                    throw new CustomException(E.getId()+"获取上午预约列表失败！");
                }
                Shi shi2=JSON.parseObject(su2,Shi.class);
                List<ShiList> shiLists2=shi2.getRows();
                if(shiLists2.isEmpty()){
                    throw new CustomException(E.getId()+"获取下午预约列表失败！");
                }
                Shi shi3=JSON.parseObject(su3,Shi.class);
                List<ShiList> shiLists3=shi3.getRows();
                if(shiLists3.isEmpty()){
                    throw new CustomException(E.getId()+"获取晚上预约列表失败！");
                }
                int num=0;
                pay.setExamID(E.getId());
                for(ShiList L1:shiLists1){
                    if(L1.getSegRemainCount()>=150){
                        L1.setSegOrderCount("150");
                    }else {
                        L1.setSegOrderCount(L1.getSegRemainCount()+"");
                    }
                    num+=Integer.parseInt(L1.getSegOrderCount());
                    pay.setAmOrderCountEdit(L1.getSegOrderCount());
                    if(L1.getSesRemainCount()>=50){
                        L1.setSesOrderCount("50");
                    }else {
                        L1.setSesOrderCount(L1.getSesRemainCount()+"");
                    }
                }
                yueTa.setSessionAM(shiLists1);
                for(ShiList L2:shiLists2){
                    if(L2.getSegRemainCount()>=150){
                        L2.setSegOrderCount("150");
                    }else {
                        L2.setSegOrderCount(L2.getSegRemainCount()+"");
                    }
                    num+=Integer.parseInt(L2.getSegOrderCount());
                    pay.setPmOrderCountEdit(L2.getSegOrderCount());
                    if(L2.getSesRemainCount()>=50){
                        L2.setSesOrderCount("50");
                    }else {
                        L2.setSesOrderCount(L2.getSesRemainCount()+"");
                    }
                }
                yueTa.setSessionPM(shiLists2);
                for(ShiList L3:shiLists3){
                    if(L3.getSegRemainCount()>=150){
                        L3.setSegOrderCount("150");
                    }else {
                        L3.setSegOrderCount(L3.getSegRemainCount()+"");
                    }
                    num+=Integer.parseInt(L3.getSegOrderCount());
                    pay.setNmOrderCountEdit(L3.getSegOrderCount());
                    if(L3.getSesRemainCount()>=50){
                        L3.setSesOrderCount("50");
                    }else {
                        L3.setSesOrderCount(L3.getSesRemainCount()+"");
                    }
                }
                yueTa.setSessionNM(shiLists3);
                pay.setSessionChangeData(JSON.toJSONString(yueTa));
                String ok=OkHttpUtils.yuyueOk(pay,session);
                if(StringUtils.isBlank(ok)){
                    throw new CustomException(kaoshiTime+"添加预约日期失败！");
                }
                if(ok.indexOf("success")>-1){
                    SysYuyue yuyue=new SysYuyue();
                    yuyue.setKaoshiTime(E.getDate());
                    yuyue.setBankNo(bankNo);
                    yuyue.setApperTime(E.getAuditDeadlineDateTime());
                    yuyue.setTerminalTime(E.getTerminalTime());
                    yuyue.setBankName(bankName);
                    if(E.getCertificateType().indexOf("证书")>-1){
                        yuyue.setCertifType("证书审验");
                    }else {
                        yuyue.setCertifType("首次考试");
                    }
                    yuyue.setCountNum(num);
                    yuyue.setUseNum(0);
                    yuyue.setExamId(E.getId().toString());
                    SpringUtils.getBean(ISysYuyueService.class).insertSysYuyue(yuyue);
                }else {
                    throw new CustomException(kaoshiTime+"添加预约日期失败！");
                }
            }
        }else {
            throw new CustomException(kaoshiTime+"添加预约日期失败！");
        }
    }

    private void closeTask(String jobName) throws SchedulerException {
        SysJob job=new SysJob();
        job.setJobName(jobName);
        List<SysJob> jobs=SpringUtils.getBean(ISysJobService.class).selectJobList(job);
        if(!jobs.isEmpty()){
            for(SysJob j:jobs){
                SpringUtils.getBean(ISysJobService.class).pauseJob(j);
            }
        }
    }

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {

        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }
}
