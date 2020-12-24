package com.ruoyi.common.utils.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.domain.PhoteSubmitVo;
import com.ruoyi.common.core.domain.PostResult;
import com.ruoyi.common.utils.StringUtils;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OkHttpUtils {
    public static PostResult OkHttpOpst(PhoteSubmitVo v, File file){
        PostResult result=new PostResult();
        result.setResult("failed");
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("cardtype"," 居民身份证")
                .addFormDataPart("idcard",v.getIdcard())
                .addFormDataPart("bankname","")
                .addFormDataPart("username",v.getName())
                .addFormDataPart("name",v.getName())
                .addFormDataPart("province",v.getProvince())
                .addFormDataPart("city",v.getCity())
                .addFormDataPart("jigou",v.getJigou())
                .addFormDataPart("peopleBankNo","")
                .addFormDataPart("bank",v.getBank())
                .addFormDataPart("bankno",v.getBankno())
                .addFormDataPart("examdate",v.getExamdate())
                .addFormDataPart("pbexamdate",v.getPbexamdate())
                .addFormDataPart("session","0")
                .addFormDataPart("sex",v.getSex())
                .addFormDataPart("certificateID","")
                .addFormDataPart("examtype",v.getExamtype())
                .addFormDataPart("email","532125082@qq.com")
                .addFormDataPart("qq","")
                .addFormDataPart("phone","18899859112")
                .addFormDataPart("cultural",v.getIdcard()+".jpg",
                        RequestBody.create(MediaType.parse("application/octet-stream"), file)).build();
        Request request = new Request.Builder()
                .url("http://221.226.21.180/examinationRY/register.action")
                .method("POST", body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().toString());
            if(StringUtils.isEmpty(response.body().toString())){
                result.setReason("服务器异常");
                return result;
            }else {
                if(response.body().toString().length()>100){
                    result.setReason("服务器异常");
                    return result;
                }else {
                    Gson gson = new Gson();
                    List<PostResult> persons = gson.fromJson(response.body().toString(), new TypeToken<List<PostResult>>() {}.getType());
                    if(persons.isEmpty()){
                        result.setReason("服务器异常");
                        return result;
                    }else {
                        result.setReason(persons.get(0).getReason());
                        result.setResult(persons.get(0).getResult());
                        return result;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("发生异常==>"+e.getMessage());
           result.setReason(e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String S="[{\"reason\":\"注册失败！该考生理论已合格不允许重新报考！\",\"result\":\"failed\"}]";
        PostResult result=new PostResult();
        Gson gson = new Gson();
        List<PostResult> persons = gson.fromJson(S, new TypeToken<List<PostResult>>() {}.getType());
        if(persons.isEmpty()){
            result.setReason("服务器异常");
            result.setResult("failed");
        }else {
            result.setReason(persons.get(0).getReason());
            result.setResult(persons.get(0).getResult());
        }
       // OkHttpOpst();
        System.out.println(result.toString());
    }
}
