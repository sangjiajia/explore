/**
 * Copyright (c) 2016 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */


package explore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class TomcatStatusCatch {

	
	
	public static void main(String[] args) {
		String result = "";
		Document document = null;//引入org.dom4j包
		try {
			result = getHtmlContext("http://localhost:8080/manager/status?XML=true", "tomcat3", "s3cret");
			document = DocumentHelper.parseText(result);//将字符串转化为XML的Document
		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println(document.asXML());
		
	}
	/**
	  * @Description: 获取指定URL的内容
	  * @param tempurl url地址
	  * @param username tomcat 管理用户名
	  * @param password tomcat 管理用户密码
	  * @return
	  * @throws IOException
	  */
	public static String getHtmlContext(String tempurl, String username, String password) throws IOException {
		URL url = null;
		BufferedReader breader = null;
		InputStream is = null;
		StringBuffer resultBuffer = new StringBuffer();
		try {
			url = new URL(tempurl);
			String userPassword = username + ":" + password;
			String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());//在classpath中添加rt.jar包，在%java_home%/jre/lib/rt.jar

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			is = conn.getInputStream();
			breader = new BufferedReader(new InputStreamReader(is));
			String line = "";
			while ((line = breader.readLine()) != null) {
				resultBuffer.append(line);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} finally {
			if(breader != null)
				breader.close();
			if(is != null)
				is.close();
		}
		return resultBuffer.toString();
	}
}
