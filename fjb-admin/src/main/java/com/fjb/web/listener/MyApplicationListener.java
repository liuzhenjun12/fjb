package com.fjb.web.listener;

import com.fjb.common.constant.Constants;
import com.fjb.common.core.redis.RedisCache;
import com.fjb.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private RedisCache redisCache;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        /**
         * 初始化Session
         */
//        String session=HttpUtils.getSession("http://221.226.21.180/examinationRY/register.jsp");
//        redisCache.setCacheObject(Constants.SYS_SESSION,session,300, TimeUnit.MINUTES);
    }
}
