package com.cshttprequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;

public class ClientClass {
  public static void main(String args[]) throws IOException, JSONException {
	try {
     URL url = new URL("http://localhost:8080/ServerProgram/aliens/alien");
     StringBuilder postData;
     while(true) {
      postData=new StringBuilder();
      
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
      LocalDateTime curr_date_time = LocalDateTime.now();
      
      long freespace = new File("/").getFreeSpace();
      long memorySize=new File("/").getTotalSpace();
      long usedRamSpace=memorySize-freespace; 
      float rup=(float)(usedRamSpace*100)/memorySize; //Used Memory In Percent    
      rup = (float) (Math.round(rup * 100.0) / 100.0);
      
      long diskSize= ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
      long feeSize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getFreePhysicalMemorySize();
      long usedDiskSpace=diskSize-feeSize;     
      float dup=(float)(usedDiskSpace*100)/diskSize;  // Used Disk In Percent  
      dup = (float) (Math.round(dup * 100.0) / 100.0);
      
      double CPU=((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemCpuLoad(); 
      CPU=CPU*100;
      CPU =Math.round(CPU * 100.0) / 100.0;
      
      String finalData=rup+" "+dup+" "+CPU+" "+curr_date_time;
      
      //postData.append("111 222 333 2021-03-06T19:50:50");
      postData.append(finalData);
      
      byte [] postDataBytes=postData.toString().getBytes("UTF-8");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("POST");
      con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      con.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      con.setDoOutput(true);
      con.getOutputStream().write(postDataBytes);
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
      String inputLine;
      StringBuffer response=new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }
      System.out.println(response.toString());
      in.close();
      postData.setLength(0);
      Thread.sleep(4000);
     }//end of loop
	}//end of try
	catch(Exception e) {
		System.out.println("Ex. "+e);
	}
  }//end of main
}//end of class
