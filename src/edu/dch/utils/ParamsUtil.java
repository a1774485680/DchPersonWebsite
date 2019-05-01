package edu.dch.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class ParamsUtil {
	/**
     * 将url参数转换成map
     * @param param aa=11&bb=22&cc=33
     * @return
     */
   	public static Map<String, String> getUrlParams(String param) {
        Map<String, String> map = new HashMap<>(0);
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        for (int i = 0; i < params.length; i++) {
            int index = params[i].indexOf("=");
            if (index != -1) {
                map.put(params[i].substring(0, index), params[i].substring(index+1));
            }
        }
        return map;
    }
 
    /**
     * 将map转换成url
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map<String, Object> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }
 
    /**
     * 以流的方式接收参数, 并转成字符串
     * @param request
     * @return
     */
    public static String getStreamToString(HttpServletRequest request) {
 
        try {
            InputStream inputStream;
            inputStream = request.getInputStream();
 
            //建立接收流缓冲，准备处理
            StringBuffer requestBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            //读入流，并转换成字符串
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                requestBuffer.append(readLine);
 
            }
            reader.close();
            return java.net.URLDecoder.decode(requestBuffer.toString(), "utf-8");
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
