package com.wangtao.blog.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.wangtao.blog.common.constant.enums.ErrorEnum;
import com.wangtao.blog.common.exception.HttpUtilException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:HttpClientUtils
 * @author: KarlWang
 * @Description: TODO(HTTP工具类) 
 * @date:2017年9月12日 下午7:18:15
 * @see com.wangtao.blog.common.util.HttpClientUtils
 */
public final class HttpClientUtils
{
    private final static Logger LOGGER = Logger.getLogger(HttpClientUtils.class);

    /**
     * <p>Title: HttpClientUtils</p> 
     * <p>Description:TODO(私有化构造函数) </p>
     */
    private HttpClientUtils(){}

    
    /**
     * @Title: getFileByteArrayByHttpGet 
     * @Description: TODO(HttpGet方式获取文件字节数组) 
     * @param url
     * @return byte[]
     * @throws HttpUtilException
     */
    public static byte[] getFileByteArrayByHttpGet(String url) throws HttpUtilException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
        	if(StringUtils.isBlank(url)) {
        		throw new HttpUtilException(ErrorEnum.PARAMETER_EMPTY_ERROR.getErrorMsg());
        	}
            HttpGet get = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(get);
            try {
                if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    HttpEntity entity = response.getEntity();
                    return EntityUtils.toByteArray(entity);
                }
                return null;
            } finally{
                response.close();
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new HttpUtilException(e.getMessage());
        } finally{
            try {
                httpClient.close();
            } catch (IOException e) {
                throw new HttpUtilException(e.getMessage());
            }
        }
    }

    /**
     * @Title: doPost 
     * @Description: TODO(post提交参数) 
     * @param url
     * @param parames
     * @param body
     * @param contentType
     * @return String
     * @throws HttpUtilException
     */
    public String doPost(String url, Map<String,String> parames,String body,String contentType) throws HttpUtilException
    {
    	CloseableHttpClient httpClient = HttpClients.createDefault();
    	try{
    		HttpPost post = new HttpPost(url);
    		HttpEntity entity = new StringEntity(body, "utf-8");
    		post.setEntity(entity);
    		CloseableHttpResponse response =  httpClient.execute(post);
    		try{
    			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) 
    			{
    				HttpEntity repEntity = response.getEntity();
    				return EntityUtils.toString(repEntity);
    			}
    			return null;
    		}finally{
    			response.close();
    		}
    	} catch(IOException e) {
    		LOGGER.error(e.getMessage());
    		throw new HttpUtilException(e.getMessage());
    	}finally{
    		try {
				httpClient.close();
			} catch (IOException e) {
				throw new HttpUtilException(e.getMessage());
			}
    	}
    }
    
    public static String postForm(String url, String textName, String textParam, String fileName, byte[] fileByte)
    {
        Map<String,String> textParams = new HashMap<>(1);
        textParams.put(textName, textParam);
        Map<String,byte[]> byteParams = new HashMap<>(1);
        byteParams.put(fileName, fileByte);
        return postForm(url, textParams, byteParams);
    }

    public static String postForm(String url, Map<String,String> textParams, Map<String,byte[]> byteParams)
    {
        MultipartEntityBuilder me = MultipartEntityBuilder.create();
        // 设置文本参数
        if(null != textParams && textParams.size() > 0)
        {
            for (Map.Entry<String,String> textParam:textParams.entrySet())
            {
                me.addPart(textParam.getKey(),new StringBody(textParam.getValue(),ContentType.MULTIPART_FORM_DATA));
            }
        }
        // 设置文件流参数
        if(null != byteParams && byteParams.size() > 0)
        {
            for (Map.Entry<String,byte[]> byteParam:byteParams.entrySet()) {
                String fileName = byteParam.getKey();
                me.addPart(fileName, new ByteArrayBody(byteParam.getValue(),ContentType.MULTIPART_FORM_DATA, fileName));
            }
        }
        return postForm(url,me,"multipart/form-data");
    }

    private static String postForm(String url, MultipartEntityBuilder me, String contentType) throws HttpUtilException
    {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader(HTTP.CONTENT_TYPE, contentType);
        HttpEntity reqEntity = me.build();
        post.setEntity(reqEntity);
        // post.setConfig(); 设置超时时间
        try {
            HttpResponse response = client.execute(post);
            if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                HttpEntity repEntity = response.getEntity();
                return EntityUtils.toString(repEntity);
            }
            return null;
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new HttpUtilException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504944675199&di=cc15b1f6700411192e2eaf48065daea5&imgtype=0&src=http%3A%2F%2Fwww.rizhi123.com%2Fueditor%2Fphp%2Fupload%2Fimage%2F20170116%2F1484533362694503.jpg";
        byte [] byteArray = HttpClientUtils.getFileByteArrayByHttpGet(url);
        for(int i = 0; i < byteArray.length; i++) {
        	System.out.print(byteArray[i]);
        	System.out.print("  ");
        	if((i%5) == 0)
        		System.out.print("\n");
        }
        System.out.println("ok!");
    }

    /*private interface ContentType
    {
        String urlencoded = "application/x-www-form-urlencoded";
        String json = "application/json";
        String xml = "application/xml";
        String plain = "text/plain";
    }*/
}
