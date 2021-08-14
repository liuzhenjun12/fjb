package com.fjb.quartz.task;


import org.quartz.SchedulerException;
import org.springframework.stereotype.Component;
import java.io.IOException;


/**
 * 定时任务调度测试
 *
 * @author fjb
 */
@Component("ryTask")
public class RyTask
{
    public void yuyue(String jobName,String bankNo,String bankName,String kaoshiTime) throws IOException, SchedulerException {

    }



}
