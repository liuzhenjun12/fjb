package com.ruoyi.common.utils.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ruoyi.common.core.domain.PhoteSubmitVo;
import com.ruoyi.common.core.domain.PostResult;
import com.ruoyi.common.core.domain.SubmitVo;
import com.ruoyi.common.core.domain.UpdateResult;
import com.ruoyi.common.core.domain.result.Re;
import com.ruoyi.common.utils.StringUtils;
import okhttp3.*;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OkHttpUtils {
    /**
     * 首次考试录入
     * @param v
     * @param file
     * @return
     */
    public static PostResult OkHttpOpst(PhoteSubmitVo v, File file){
        PostResult result=new PostResult();
        result.setResult("failed");
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8888");
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
            String sult=response.body().string();
            if(StringUtils.isEmpty(sult)){
                result.setReason("服务器异常");
                return result;
            }else {
                if(sult.length()>100){
                    result.setReason("服务器异常");
                    return result;
                }else {
                    Gson gson = new Gson();
                    List<PostResult> persons = gson.fromJson(sult, new TypeToken<List<PostResult>>() {}.getType());
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
    /**
     * 证书审验提交注册
     * @param s
     * @return
     * @throws IOException
     */
    public static PostResult submitZhengshu(SubmitVo s) throws IOException {
        Map<String, String> map = new HashMap<>();
        PostResult result=new PostResult();
        result.setResult("failed");
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
            String resu = EntityUtils.toString(response.getEntity(), "utf-8");
            if(StringUtils.isEmpty(resu)){
                result.setReason("服务器异常");
                return result;
            }else {
                if(resu.length()>100){
                    result.setReason("服务器异常");
                    return result;
                }else {
                    Gson gson = new Gson();
                    List<PostResult> persons = gson.fromJson(resu, new TypeToken<List<PostResult>>() {}.getType());
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
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                System.out.println("发生异常==>"+e.getMessage());
                result.setReason(e.getMessage());
            }
        }
        return result;
    }

    /**
     * 发送x-www-form-urlencoded格式请求
     * @param args
     * @param url
     * @param session
     * @param method
     * @return
     * @throws IOException
     */
    public static String wwwFormPost(String args,String url,String session,String method) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, args);
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Cookie", session)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 发送text/plain请求
     * @param url
     * @param session
     * @param method
     * @return
     * @throws IOException
     */
    public static String formHtml(String url,String session,String method) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method(method, body)
                .addHeader("Cookie", session)
                .build();
        Response response = client.newCall(request).execute();
        return  response.body().string();
    }

    /**
     * 修改头像
     * @return
     */
    public static String updateTu(UpdateResult u,File file,String session) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("examIDOld",u.getExamIDOld())
                .addFormDataPart("examDateOld",u.getExamDateOld())
                .addFormDataPart("username",u.getUsername())
                .addFormDataPart("id",u.getId())
                .addFormDataPart("sex",u.getSex())
                .addFormDataPart("examtype","临柜")
                .addFormDataPart("idcardType","居民身份证")
                .addFormDataPart("parentId",u.getParentId())
                .addFormDataPart("cid",u.getCid())
                .addFormDataPart("certificateID","")
                .addFormDataPart("bankNO",u.getBankNO())
                .addFormDataPart("bankNOShow",u.getBankNOShow())
                .addFormDataPart("bankname","")
                .addFormDataPart("province",u.getProvince())
                .addFormDataPart("city",u.getCity())
                .addFormDataPart("cityNew",u.getCityNew())
                .addFormDataPart("jigou",u.getJigou())
                .addFormDataPart("jigous",u.getJigous())
                .addFormDataPart("bank","")
                .addFormDataPart("bankNew",u.getBankNew())
                .addFormDataPart("examdate",u.getExamdate())
                .addFormDataPart("pbexamdate",u.getPbexamdate())
                .addFormDataPart("peopleBankNo","")
                .addFormDataPart("peopleBankName","")
                .addFormDataPart("email","532125082@qq.com")
                .addFormDataPart("qq","")
                .addFormDataPart("parentId","")
                .addFormDataPart("parentIds","")
                .addFormDataPart("phone","18899859112")
                .addFormDataPart("cultural",u.getCid()+".jpg",
                RequestBody.create(MediaType.parse("application/octet-stream"), file))
                .addFormDataPart("serviceType","modifyExamineeInfo")
                .addFormDataPart("examType",u.getExamtype())
                .build();
        Request request = new Request.Builder()
                .url("http://221.226.21.180/examinationRY/examConvert.html")
                .method("POST", body)
                .addHeader("Cookie", session)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static void main(String[] args) throws IOException {
        String jsonStr="{\"resp\":{\"result\":{\"token\":null,\"user\":{\"account\":\"潘晓灵\",\"branchid\":null,\"id\":\"140540\",\"idnumber\":\"350103199409143521\",\"latestlogintime\":\"0\",\"loginStatus\":\"1\",\"password\":\"3d49e8a7819bdbf64a596d8e49342a60\",\"retrylogin\":\"0\",\"roledesc\":\"考生\",\"roleid\":\"8\",\"rolename\":\"考生\",\"status\":\"0\",\"updatetime\":\"1543305243963\",\"userArea\":null,\"userBank\":\"B0014B235010001\",\"username\":\"潘晓灵\",\"userno\":\"1543305243963\"}},\"resultCode\":\"10000\",\"resultList\":null,\"resultMsg\":\"success\"},\"roleID\":\"8\"}";
        Re json = JSON.parseObject(jsonStr,Re.class);
        System.out.println(json.getResp().getResultMsg());
    }
}
