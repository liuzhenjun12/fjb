package com.ruoyi.common.utils.http;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.core.domain.entity.SysZhuce;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.bean.BeanUtils;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.constant.Constants;

/**
 * 通用http发送方法
 *
 * @author ruoyi
 */
public class HttpUtils
{
    private static final Logger log = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param)
    {
        return sendGet(url, param, Constants.UTF8);
    }

    public static String requestGet(String url) throws IOException {
        String  str = Request.Get(url)
                .execute()
                .returnContent()
                .asString(Charset.forName("UTF-8"));
        log.info("str - {}", str);
        return str;
    }

    /**
     * 向指定 URL 发送GET方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @param contentType 编码类型
     * @return 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param, String contentType)
    {
        StringBuilder result = new StringBuilder();
        BufferedReader in = null;
        try
        {
            String urlNameString = url + "?" + param;
            log.info("sendGet - {}", urlNameString);
//            System.setProperty("http.proxyHost", "127.0.0.1");
//            System.setProperty("http.proxyPort", "8888");
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
            connection.connect();
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), contentType));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendGet ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendGet SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendGet IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendGet Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (Exception ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try
        {
            String urlNameString = url;
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null)
            {
                result.append(line);
            }
            log.info("recv - {}", result);
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        }
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static String sendSSLPost(String url, String param)
    {
        StringBuilder result = new StringBuilder();
        String urlNameString = url + "?" + param;
        try
        {
            log.info("sendSSLPost - {}", urlNameString);
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
            URL console = new URL(urlNameString);
            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = "";
            while ((ret = br.readLine()) != null)
            {
                if (ret != null && !"".equals(ret.trim()))
                {
                    result.append(new String(ret.getBytes("ISO-8859-1"), "utf-8"));
                }
            }
            log.info("recv - {}", result);
            conn.disconnect();
            br.close();
        }
        catch (ConnectException e)
        {
            log.error("调用HttpUtils.sendSSLPost ConnectException, url=" + url + ",param=" + param, e);
        }
        catch (SocketTimeoutException e)
        {
            log.error("调用HttpUtils.sendSSLPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        }
        catch (IOException e)
        {
            log.error("调用HttpUtils.sendSSLPost IOException, url=" + url + ",param=" + param, e);
        }
        catch (Exception e)
        {
            log.error("调用HttpsUtil.sendSSLPost Exception, url=" + url + ",param=" + param, e);
        }
        return result.toString();
    }

    private static class TrustAnyTrustManager implements X509TrustManager
    {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
        {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers()
        {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        @Override
        public boolean verify(String hostname, SSLSession session)
        {
            return true;
        }
    }

    public static String SendFilePost(String url,Object obj) throws IllegalAccessException, IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        try {
        HttpPost post = new HttpPost(url);
        post.setHeader("accept","*/*");
        post.setHeader("Accept-Encoding","gzip, deflate, br");  //像header这些自己去设置吧
        RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(15000).build();
        post.setConfig(defaultRequestConfig);
        RequestConfig requestConfig=RequestConfig.custom().setProxy(new HttpHost("127.0.0.1", 8888)).build();
        post.setConfig(requestConfig);
        System.out.println("executing request " + post.getURI());
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
        Map<String, String> reqParam= BeanUtils.objectToMap(obj);
        for(Map.Entry<String,String> param : reqParam.entrySet()){
//            multipartEntityBuilder.addPart(param.getKey(),param.getValue());
        }
        HttpEntity reqEntity = multipartEntityBuilder.build();
        post.setEntity(reqEntity);
        CloseableHttpResponse response = httpclient.execute(post);
        System.out.println("got response");
        try {
            if(response != null && response.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity entity = response.getEntity();
                result = entityToString(entity);
                log.info("recv - {}", result);
            }
            return result;
        } finally {
            response.close();
        }
    } finally {
    // 关闭连接,释放资源
    try {
        httpclient.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
   }
  }
    private static int TIME_OUT = 10 * 1000;   //超时时间
    private static String CHARSET = "utf-8"; //设置编码

    public static String httpSendFile(File file,String imageName,String RequestURL,Object obj,String sessionId){
        String result = "";
        String BOUNDARY = UUID.randomUUID().toString();  //边界标识   随机生成
        String PREFIX = "--------------------", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";   //内容类型
        URL url = null;
        String params = "";
        try {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "8888");
            url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);  //允许输入流
            conn.setDoOutput(true); //允许输出流
            conn.setUseCaches(false);  //不允许使用缓存
            conn.setRequestMethod("POST");  //请求方式
            conn.setRequestProperty("Cookie", sessionId);
//            conn.setRequestProperty("Charset", CHARSET);  //设置编码
            conn.setRequestProperty("Accept","*/*");
            conn.setRequestProperty("Accept-Encoding","gzip,deflate,br");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + PREFIX+BOUNDARY);
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            StringBuffer sb = new StringBuffer();
            Map<String, String> reqParam= BeanUtils.objectToMap(obj);
            for(Map.Entry<String,String> param : reqParam.entrySet()){
                sb = null;
                sb = new StringBuffer();
                sb.append(PREFIX).append(BOUNDARY).append(LINE_END);
                sb.append("Content-Disposition: form-data; name=\"")
                        .append(param.getKey())
                        .append("\"")
                        .append(LINE_END)
                        .append(LINE_END);
                sb.append(param.getValue()).append(LINE_END);
                params = sb.toString();
                dos.write(params.getBytes());
            }
            sb = new StringBuffer();
            sb.append(PREFIX);
            sb.append(BOUNDARY);
            sb.append(LINE_END);
            /**
             * 这里重点注意：
             * name里面的值为服务器端需要key   只有这个key 才可以得到对应的文件
             * filename是文件的名字，包含后缀名的   比如:abc.png
             */
            sb.append("Content-Disposition: form-data; name=\"")
                    .append("cultural")
                    .append("\"")
                    .append(";filename=\"")
                    .append(imageName)
                    .append("\"\n");
            sb.append("Content-Type: image/jpeg");
            sb.append(LINE_END).append(LINE_END);
            dos.write(sb.toString().getBytes());
            InputStream is = new FileInputStream(file);
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            is.close();
            dos.write(LINE_END.getBytes());
            byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
            dos.write(end_data);
            dos.flush();
            /**
             * 获取响应码  200=成功
             * 当响应成功，获取响应的流
             */

            int res = conn.getResponseCode();
            System.out.println("res=========" + res);
            if (res == 200) {
                InputStream input = conn.getInputStream();
                StringBuffer sb1 = new StringBuffer();
                int ss;
                while ((ss = input.read()) != -1) {
                    sb1.append((char) ss);
                }
                result = sb1.toString();
            }
        } catch (MalformedURLException e) {
            log.error("调用HttpUtils.httpSendFile MalformedURLException, url=" + RequestURL , e);
        } catch (IOException e) {
            log.error("调用HttpUtils.httpSendFile IOException, url=" + RequestURL , e);
        } catch (IllegalAccessException e) {
            log.error("调用HttpUtils.httpSendFile IllegalAccessException, url=" + RequestURL , e);
        }

        return result;
    }

    public static String entityToString(HttpEntity entity) throws IOException {
        String result = null;
        if(entity != null)
        {
            long lenth = entity.getContentLength();
            if(lenth != -1 && lenth < 2048)
            {
                result = EntityUtils.toString(entity,"UTF-8");
            }else {
                InputStreamReader reader1 = new InputStreamReader(entity.getContent(), "UTF-8");
                CharArrayBuffer buffer = new CharArrayBuffer(2048);
                char[] tmp = new char[1024];
                int l;
                while((l = reader1.read(tmp)) != -1) {
                    buffer.append(tmp, 0, l);
                }
                result = buffer.toString();
            }
        }
        return result;
    }

    public static String getSession(String urlStr) {
        URL url= null;
        OutputStream out=null;
        InputStream in=null;
        try {
            url = new URL(urlStr);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            con.setRequestProperty("Accept-Encoding","gzip,deflate");
            con.setRequestProperty("connection", "keep-alive");
            Map hfs=con.getHeaderFields();
            Set<String> keys=hfs.keySet();
            for(String str:keys){
                List<String> vs=(List)hfs.get(str);
                System.out.print(str+":");
                for(String v:vs){
                }
            }
            String cookieValue=con.getHeaderField("Set-Cookie");
            int i=cookieValue.indexOf(";");
            String sessionId=cookieValue.substring(0, i);
            return sessionId;
    }   catch (Exception e) {
    e.printStackTrace();
    return null;
}finally{
    try {
        if(in!=null)
            in.close();
        if(out!=null)
            out.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    }

    public static void main(String[] args) throws IOException {
        //sendGet("http://221.226.21.180/examinationRY/loadExamineeInfo.action","idcard=441521199604278541");
       // requestGet("http://221.226.21.180/examinationRY/checkQualification.action?Name=何海玲&IdNumber=350181199701271968");
        //sendGet("http://221.226.21.180/examinationRY/loadPeopleBankExamList.action","bankNO=B0017B235010001&type=2");
       getSession("http://221.226.21.180/examinationRY/manager.jsp");
//        String S="JSESSIONID=5BDEE54F9BD92B8EB92FD5CA624D96C7; Path=/examinationRY; HttpOnly";
//      int i=S.indexOf(";");
//      System.out.println(i);
//        System.out.println(S.substring(0,i));
        String S="{\"rows\":[{\"alternateName\":\"平安银行股份有限公司福州分行\",\"cid\":\"350103199409143521\",\"email\":\"532125082@qq.com\",\"examName\":\"2021年01月08日临柜-首次考试\",\"fileName\":\"350103199409143521.jpg\",\"id\":3181347,\"idcardType\":\"居民身份证\",\"phone\":\"18899859112\",\"qq\":\"\",\"sex\":\"女\",\"username\":\"潘晓灵\"}],\"total\":1}";
        TableDataInfo info= JSON.parseObject(S,TableDataInfo.class);
        List<SysZhuce> zc= (List<SysZhuce>) info.getRows();
        for(SysZhuce s:zc){
            System.out.println(s);
        }
    }
}
