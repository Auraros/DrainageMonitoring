package com.myutil;
import java.sql.Date;
//import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.io.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.util.Properties;
import java.util.HashMap;//导入;
;import java.net.*;

public class EFileReader{

private String fileName="";
//reade a txt file and return the string
public static String readFile(String fileName){
//this.fileName=fileName;
StringBuilder strBuilder=new StringBuilder(); 

File f=null;
FileReader fre=null;
BufferedReader bre=null;
try{
	f=new File(fileName);
	fre=new FileReader(f);
	bre=new BufferedReader(fre);
	String str="";
	while((str=bre.readLine())!=null) //判断最后一行不存在，为空
	{
	//System.out.println(str);
	strBuilder.append(str);
	}
}catch(IOException e){
	e.printStackTrace();

}finally{
	try{
		bre.close();
		fre.close();
	}catch(IOException e){
		e.printStackTrace();
	}
	System.out.println("EFileReader:"+strBuilder.toString());
	return strBuilder.toString();	
}
}

public static void main(String args[]){
	System.out.println("- - - test EFileReader- - - -");
	
	String jsonStr=EFileReader.readFile("TestJson.json");
	System.out.println("json str:");
	System.out.println(jsonStr);
}
	
}