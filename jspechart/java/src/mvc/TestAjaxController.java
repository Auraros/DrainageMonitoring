package com.mvc.controller;
import com.myutil.*;
//import com.mvc.dao.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
//import com.jsp2.Employee;
//import com.jsp2.EmployeeDao;
//import com.jsp2.EmployeeDBDao;
//import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.*;
//import com.redis.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.util.Properties;
import java.util.HashMap;//导入;
import java.net.*;
public class TestAjaxController implements Controller{

//private static RedisLocker redisLocker=new RedisLocker();
//private static EchartLogger logger=null;
/*static{
	
logger=EchartLogger.getLogger(TestAjaxController.class);
//System.out.println("- - - - - -- testing: EchartAptController: web_home:"+System.getProperty("web_home"));
logger.debug("- - - - - -- testing: TestAjaxController: web_home:"+System.getProperty("web_home"));

}*/

@Override
public ModelAndView handleRequest(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
			
	
		List<AjaxEntity> valueList=new ArrayList<AjaxEntity>();
		/*for(int i=0;i<10;i++){
			String createTime=EDateCalculator.time2DateStr(System.currentTimeMillis());
			String updateTime=EDateCalculator.time2DateStr(System.currentTimeMillis());
			valueList.add(new AjaxEntity(Integer.toString(i),"title"+i,"true",createTime,updateTime));
			createTime=null;
			updateTime=null;
		}*/
		
		try{
		   response.setContentType("text/html");
		   response.setCharacterEncoding("utf-8");
		   PrintWriter out=response.getWriter();
		   	//convert to json	
		   String jsonStr=testAjaxFormJson(valueList);
			
			/*
			write the values into file for debuging purpose, disable it.
			*/
			/*
			String dateTime=EDateCalculator.time2DateStr(System.currentTimeMillis());
			List<String> toFileAptValueList=new ArrayList<String>();
			toFileAptValueList.add(dateTime+":"+jsonStr);
			EFileWriter.writeStringToFile(toFileAptValueList,"echartAptJson-aptvalue.txt",true);
			*/
			System.out.println("- - - - - -- testing:TestAjaxController1: web_home:"+System.getProperty("web_home"));

			
			
			//test
			System.out.println("- -- - -test reading json data file- -- - ");
			jsonStr=readDataFomTextFile("JsonData.json");
			System.out.println(jsonStr);//readDataFomTextFile("JsonData.json"));
			response.setCharacterEncoding("utf-8");
			//写入到前台         	
			response.getWriter().write(jsonStr);
			/*
				out.println(jsonStr);
				out.flush();
				out.close();
			*/
	}catch (Exception ex){
		   ex.printStackTrace();
	}   
	    //指定视图 will clash with  the out.print or response.getWriter above 
        //modelAndView.setViewName("/WEB-INF/jsp/EchartlistMsg.jsp");
		//System.out.println("4-EchartAptController: debug2...");
        //return modelAndView;
		return null;
    }
/*public Map<String,Integer> convertEnumMap(EnumMap<Month,Integer> enumMap){
	Map<String,Integer> map=new HashMap<String,Integer>();
	for (Month e:Month.values()){  
			//System.out.println(e.toString());
			Integer monthValue=enumMap.get(e);
			map.put(e.toString(),monthValue);
			//System.out.println(e+":"+monthValue);
	}
		
		return map;
	
}*/

public String testAjaxFormJson(List<AjaxEntity> valueList){
	
	/*columns: [
    { name: 'id', caption: 'ID', sort: true, ordering: 1, type: 'Link', typeInfo: { base_url: '/home/', key: 'id' } },
    { name: 'title', caption: '标题', sort: true, type: 'Link', typeInfo: { base_url: '/home/', key: 'id' } },
    { name: 'available', caption: '对外可用', type: 'Boolean' },
    { name: 'create_time', caption: '创建时间', sort: true, type: 'Datetime' },
    { name: 'update_time', caption: '更新时间', sort: true, type: 'Datetime' },
  ],*/
	
StringBuilder strBuilder=new StringBuilder(); 
	
String head="{";
String tail="}";
String quote="\"";
strBuilder.append(head);
Iterator<AjaxEntity> it = valueList.iterator();
int i=0;
while(it.hasNext()) {
		//System.out.println(it.next());
	AjaxEntity ajaxEntity=it.next();
	strBuilder.append(head);
	strBuilder.append(quote+"id"+quote+":"+quote+ajaxEntity.id+quote);
	strBuilder.append(",");
	strBuilder.append(quote+"available"+quote+":"+quote+ajaxEntity.available+quote);
	strBuilder.append(",");
	strBuilder.append(quote+"title"+quote+":"+quote+ajaxEntity.title+quote);
	strBuilder.append(",");
	strBuilder.append(quote+"create_time"+quote+":"+quote+ajaxEntity.create_time+quote);
	strBuilder.append(",");
	strBuilder.append(quote+"update_time"+quote+":"+quote+ajaxEntity.update_time+quote);


	
	strBuilder.append(tail);
	i++;
	if(i<valueList.size()){
			strBuilder.append(",");
	}

}
strBuilder.append(tail);
return strBuilder.toString();
	
}

public String readDataFomTextFile(String fileName){
		
		URL url = TestAjaxController.class.getClassLoader().getResource("");
		//System.out.println("redis loadProperties: 3: "+url);
		
		String libpath =  url.getPath();
		//System.out.println("redis loadProperties: 4: "+libpath);
		String libpath2="";
		try{	
			libpath2 = URLDecoder.decode(libpath, "UTF-8");
			//System.out.println("Redis loadProperties: 5: "+libpath2);
			//trim the first / andanything before it
			int intIndex = libpath2.indexOf("/");
			if(intIndex == - 1){
			//System.out.println("没有找到字符串 /");
			}else{
			//System.out.println("/字符串位置 " + intIndex);
			libpath2=libpath2.substring(intIndex+1);
			//System.out.println("redis loadRedisProperties: 6: "+libpath2);
			}
			
		}catch(UnsupportedEncodingException e){
			//System.out.println("redis loadRedisProperties: exception1...");
			e.printStackTrace();
			libpath2="";
		}
		//LoadProperties l = new LoadProperties();
		//l.loadProperties();
		
		String finalFilePath=libpath2+fileName;
		System.out.println("finalPath:"+finalFilePath);
		
		String fileData=EFileReader.readFile(finalFilePath);
		return fileData;
}


public static void main(String[] args){

	TestAjaxController controller=new TestAjaxController();

	List<AjaxEntity> valueList=new ArrayList<AjaxEntity>();
		/*for(int i=0;i<10;i++){
			String createTime=EDateCalculator.time2DateStr(System.currentTimeMillis());
			String updateTime=EDateCalculator.time2DateStr(System.currentTimeMillis());
			valueList.add(new AjaxEntity(Integer.toString(i),"title"+i,"true",createTime,updateTime));
			createTime=null;
			updateTime=null;
		}*/
	//String jsonStr=controller.testAjaxFormJson(valueList);
	//System.out.println("jsonStr:"+jsonStr);
	
	System.out.println("- - - -- - -  -test reading data from file- - - - -");
	
	String jsonStr=controller.readDataFomTextFile("JsonData.json");
	
	System.out.println(" JsonData:"+jsonStr);
	
} 


}

class AjaxEntity{
public String id="0";
public String title="";
public String available="true";
//java.util.Date uDate=new java.util.Date(System.currentTimeMillis());
public String create_time="";//EDateCalculator.time2DateStr(System.currentTimeMillis());
public String update_time="";//EDateCalculator.time2DateStr(System.currentTimeMillis());

public AjaxEntity(String id,String title,String available,String create_time,String update_time)
{
	this.id=id;
	this.title=title;
	this.available=available;
	this.create_time=create_time;
	this.update_time=update_time;
	
}
/*name: 'id', caption: 'ID', sort: true, ordering: 1, type: 'Link', typeInfo: { base_url: '/home/', key: 'id' } },
    { name: 'title', caption: '标题', sort: true, type: 'Link', typeInfo: { base_url: '/home/', key: 'id' } },
    { name: 'available', caption: '对外可用', type: 'Boolean' },
    { name: 'create_time', caption: '创建时间', sort: true, type: 'Datetime' },
    { name: 'update_time'
*/
	
}