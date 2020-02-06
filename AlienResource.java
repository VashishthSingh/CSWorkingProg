package com.serpro.ServerProgram;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("aliens")
public class AlienResource { 
	@POST
	@Path("alien")
	public String geData(String data) {
		System.out.println("getData method Called..");
		System.out.println("Data : "+data);
		String ar[]=data.split(" ");
		for(String s:ar)
		 System.out.println(s);
		Ripository.getDataFromClient(data);
		return(data);
	}
}
