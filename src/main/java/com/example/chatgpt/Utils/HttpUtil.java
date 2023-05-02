package com.example.chatgpt.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 请求资源接口
 */
public class HttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient();
    public static final String url="http://127.0.0.1:8000?text=";
    /**
     * 向指定URL发起Get请求，参数为params
     * @param url
     * @param params
     * @return
     */
    public static String sendGet(String url, Map<String,String> params) throws Exception,IOException
    {
        StringBuffer result = new StringBuffer();// 查询结果
        BufferedReader in = null;
        OutputStreamWriter out = null;
        try {
            String paramsString=handleParams(params);//处理参数为字符串
            System.out.println("***************请求参数*******************");
            System.out.println("param:"+paramsString);
            System.out.println("***************请求参数*******************");
            HttpURLConnection conn=getConnObject(url,"GET",paramsString); //根据请求方式和url获得相应的conn对象
            if(conn==null){
                logger.error("url处理异常！");
                return "";
            }
            conn.connect();
            // 获取URLConnection对象对应的输出流
            /*out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // flush输出流的缓冲
            out.flush();*/
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader( new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e){
            logger.error(url+"响应异常",e);
            throw new IOException();
        } catch (Exception e) {
            logger.error("GET请求错误！",e);
        } finally{ //使用finally块来关闭输出流、输入流
            try{
                /*if(out!=null){
                    out.close();
                }*/
                if(in!=null){
                    in.close();
                }
            } catch(IOException ex){
                logger.error("服务器异常",ex);
            }
        }
        return result.toString();
    }

    /**
     * 向指定URL发起post请求，参数为params
     * @param url
     * @param params
     * @return
     */
    public static String sendPost(String url, Map<String,String> params)
    {
        return "";
    }
    /**
     * 根据请求方式和url获得相应的conn对象
     * @param url
     * @param method
     * @return
     */
    private static HttpURLConnection getConnObject(String url,String method,String params) throws Exception
    {
        HttpURLConnection conn=null;
        try {
            if(method.equals("GET")) {
                if(params!=""){
                    url=url+"?"+params;
                }
                URL realUrl = new URL(url);
                conn =(HttpURLConnection) realUrl.openConnection();
                conn.setRequestMethod("GET");
            }else if(method.equals("POST")) {
                URL realUrl = new URL(url);
                conn =(HttpURLConnection) realUrl.openConnection();
                conn.setRequestMethod("POST");
            }else {
//                throw new ServiceException(BizExceptionEnum.REST_API_ERROR);//接口处理异常
            }
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //设置连接超时和读取超时
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            //conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        }catch (MalformedURLException e){
            logger.error("无效url！",e);
        }catch (IOException e){
            logger.error("url资源获取异常！",e);
        }catch (Exception e){
            logger.error("不能处理的请求方式！");
        }
        return conn;
    }

    /**
     * Handle request params
     * @param params
     * @return
     */
    private static String handleParams(Map<String,String>params)
    {
        // 发送请求参数
        if (params != null) {
            StringBuilder param = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if(param.length()>0){
                    param.append("&");
                }
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());
            }
            System.out.println(param.toString());
            return param.toString();
        }else {
            return "";
        }
    }

    /**
     * 发起请求获取相应结果
     *
     * @param url url
     * @return resp
     */
    public static String get(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = OKHTTP_CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
            return Strings.EMPTY;
        } catch (IOException e) {
            return Strings.EMPTY;
        }
    }



}
