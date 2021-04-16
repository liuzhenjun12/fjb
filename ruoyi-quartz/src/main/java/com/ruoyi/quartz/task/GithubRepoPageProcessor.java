package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.quartz.domain.SysJob;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.domain.SysGuangao;
import com.ruoyi.system.mapper.SysGuangaoMapper;
import com.ruoyi.system.service.ISysGuangaoService;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class GithubRepoPageProcessor implements PageProcessor {

    private Site site = Site.me()
            .setDomain("jianshu.com")
            .setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Safari/537.36");
    ;
    @Override
    public void process(Page page) {
        String list=page.getHtml().xpath("//textarea[@id='hotsearch_data']/text()").toString();
        List<Selectable> ss=  page.getHtml().regex("\"pure_title\": \"(.*?)\"").nodes();
        if(!ss.isEmpty()){
            for(Selectable s:ss){
                try {
                    int i= SpringUtils.getBean(ISysGuangaoService.class).checkName(s.toString());
                    System.out.println(i);
                    if(i>0){
                        continue;
                    }
                    SysGuangao g=new SysGuangao();
                    g.setName(s.toString());
                    g.setLaizi("百度热榜");
                    g.setCreateBy("admin");
                    g.setCreateTime(DateUtils.getNowDate());
                    String ba= URLEncoder.encode( s.toString(), "UTF-8" );
                    g.setUrl("https://www.baidu.com/s?cl=3&tn=baidutop10&fr=top1000&wd="+ba+"&rsv_idx=2&rsv_dl=fyb_n_homepage&hisfilter=1");
                    System.out.println(ba);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
//        System.out.println(list+"====================="+ss.size());
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void main(String[] args) {
        Spider.create(new GithubRepoPageProcessor()).addUrl("https://www.baidu.com").thread(5).run();
//        List<SysJob> jobs=SpringUtils.getBean(ISysJobService.class).selectJobList(new SysJob());
       // System.out.println(jobs.size());
    }
}
