package com.ruoyi.common.utils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 判断身份证内容并加载信息
 */
public class IdentityGetName {
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    public static String getStr(String sfz,String bukao) throws IOException {
        String s="";
        if("Y".equals(bukao)){
            s="http://221.226.21.180/examinationRY/loadExamineeInfo.action?idcard="+sfz+"&type=1";
        }else {
            s="http://221.226.21.180/examinationRY/loadExamineeInfo.action?idcard="+sfz;
        }
        String  str = Request.Get(s)
                .execute()
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        if(str.length()>2){
            System.out.println(sfz+"证书审验");
        }else {
            System.out.println(sfz+"：首次");
        }
        return str;
    }

    /**
     * 判断首次考试是否有资格
     * @param name
     * @param sfz
     * @return
     * @throws IOException
     */
    public static boolean checkQualification(String name,String sfz) throws IOException {
        String  str = Request.Get("http://221.226.21.180/examinationRY/checkQualification.action?Name="+name+"&IdNumber="+sfz)
                .execute()
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        System.out.println(str+"============");
        if(str.indexOf("0")!=-1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否有机位
     * @param bankNO
     * @param type
     * @return
     * @throws IOException
     */
    public static String loadPeopleBankExamList(String bankNO,Integer type) throws IOException {
        String  str = Request.Get("http://221.226.21.180/examinationRY/loadPeopleBankExamList.action?bankNO="+bankNO+"&type="+type)
                .execute()
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        return str;
    }



    public static void main(String[] args) throws IOException {
        String [] a=new String[] {"350102197508110324",""};
       getStr("350102197508110324","");
       // checkQualification("朱叶敏","350301199510270726");
        //loadPeopleBankExamList("B0009L235030002",1);

    }
}
