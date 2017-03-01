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

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.SocketException;

import org.apache.commons.net.telnet.TelnetClient;

public class TelnetTest {

	public static void main(String[] args) throws SocketException, IOException {
		
		 TelnetClient telnetClient = new TelnetClient();
		 telnetClient.connect("localhost", 20883);
		 InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
         PrintStream pStream = new PrintStream(telnetClient.getOutputStream());  //写命令的流
         pStream.println("status -l");
         pStream.flush(); //将命令发送到telnet Server
         byte[] b = new byte[2048];
         StringBuffer sBuffer = new StringBuffer(300);
             //读取Server返回来的数据，直到读到登陆标识，这个时候认为可以输入用户名
         int   size = inputStream.read(b);
             if(-1 != size) {
                 sBuffer.append(new String(b,0,size));
             }
         System.out.println(sBuffer.toString());
	}
}
