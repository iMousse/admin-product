package com.admin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-01
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class DownUtils {

    public static String getFileName(String agent,String filename) throws UnsupportedEncodingException {
        if (agent.contains("MSIE")) {

            //如果是IE浏览器,是这种编码格式
            filename = URLEncoder.encode(filename, "UTF-8");
            
        } else {

            // 其它浏览器
            filename = new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        }

        return filename;
    }
}
