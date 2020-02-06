package com.serpro.ServerProgram;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Ripository {
	public static void getDataFromClient(String data) {
		String arrayData[]=data.split(" ");
		float ramUti=Float.parseFloat(arrayData[0]);
		float diskUti=Float.parseFloat(arrayData[1]);
		double cpuUti=Double.parseDouble(arrayData[2]);
		String currDateTime=arrayData[3];
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=(Connection) DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/report?useSSL=false","root","password");     
			PreparedStatement stmt=(PreparedStatement) con.prepareStatement("insert into MyTable(ramUti,diskUti,cpuUti,readTime) values(?,?,?,?)");  
			stmt.setFloat(1,ramUti);
			stmt.setFloat(2,diskUti);
			stmt.setDouble(3,cpuUti);
			stmt.setString(4,currDateTime);
			stmt.executeUpdate();  
		}catch(Exception e){ System.out.println(e);}  
	}
}
