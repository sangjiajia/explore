package explore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class HandlebarTest{

    public static void main(String[] args){
       
        List<Integer> list = new ArrayList<Integer>();
        
        List<Integer> list2 = new ArrayList<Integer>();
        for (int i=0;i<10000;i++){
        	try {
        		long now =System.currentTimeMillis();
                String result = "";
                try {
                	String handlebarUrl = "http://localhost:8080/brand/mustache-foo.htm";
                	String jspUrl = "http://localhost:8080/brand/query.htm";
                    result = getHtmlContext(jspUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("total use:"+(System.currentTimeMillis()-now));
                list.add(Integer.valueOf((System.currentTimeMillis()-now)+""));
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
            
        }
        int total=0;
        for (Integer integer : list){
            total+=integer;
        }
        System.out.println("平均用时："+total/100);
        
        int total2=0;
        for (Integer integer2 : list2){
            total2+=integer2;
        }
        System.out.println("渲染用时："+total2/100);

    }
    
    public static String getHtmlContext(String tempurl) throws IOException {
        URL url = null;
        BufferedReader breader = null;
        InputStream is = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            url = new URL(tempurl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
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
