package com.fjb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class FjbApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(FjbApplication.class, args);
        System.out.println("(♥f‿j)ﾉﾞ  反假系统启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                "//////////////////////////////////////////////////////////////////// \n" +
                "//                          _ooOoo_                               // \n" +
                "//                         o8888888o                              // \n" +
                "//                         88 .  .88                              // \n" +
                "//                         (| ^_^ |)                              // \n" +
                "//                          `=---='                              // \n" +
                "//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        // \n" +
                "//             佛祖保佑       永不宕机      永无BUG                // \n" +
                "//////////////////////////////////////////////////////////////////// ");
    }
}
