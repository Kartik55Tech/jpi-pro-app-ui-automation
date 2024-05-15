package com.extentreport.Features;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class F3_Log_DifferentTypesOfInformation {

	public static void main(String[] args) throws IOException 
	{
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter("logDifferentInfo.html");
		
		extentReports.attachReporter(sparkReporter);
		
		//Log different type of information to extent reports
		
		// #1 Text (Bold,Italic)
		extentReports.createTest("Test 1")
		.log(Status.INFO,"info1")
		.log(Status.INFO,"<b>info2</b>")
		.log(Status.INFO,"<i>info1</i>")
		.log(Status.INFO,"<b><i>info1</i></b>");
		
		
		// #2 XML 
		String xmlData = "<menu id=\"file\" value=\"File\">\r\n"
				+ "  <popup>\r\n"
				+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\r\n"
				+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\r\n"
				+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\r\n"
				+ "  </popup>\r\n"
				+ "</menu>\r\n"
				+ "";
		
		extentReports.createTest("XML based Test")
		//.log(Status.INFO, xmlData);
		//.info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML))
		.info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));
		
		//#3 JSON
		String jsonData = "{\"menu\": {\r\n"
				+ "  \"id\": \"file\",\r\n"
				+ "  \"value\": \"File\",\r\n"
				+ "  \"popup\": {\r\n"
				+ "    \"menuitem\": [\r\n"
				+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n"
				+ "    ]\r\n"
				+ "  }\r\n"
				+ "}}";
		
		extentReports.createTest("JSON based Test")
		//.log(Status.INFO, jsonData);
		.info(MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));
		
		
		// #4 : Collection Data
		
		List<String> listData = new ArrayList<String>();
		listData.add("John");
		listData.add("Danny");
		listData.add("Steve");
		
		Map<Integer, String> mapData = new HashMap<Integer, String>();
		mapData.put(101, "QA1");
		mapData.put(102, "QA2");
		mapData.put(103, "QA3");
		
		Set<Integer> setData = mapData.keySet();
		
		extentReports.createTest("List based Test")
		.info(MarkupHelper.createOrderedList(listData))
		.info(MarkupHelper.createUnorderedList(listData));
		
		extentReports.createTest("Set based Test")
		.info(MarkupHelper.createOrderedList(setData))
		.info(MarkupHelper.createUnorderedList(setData));
		
		extentReports.createTest("Map based Test")
		.info(MarkupHelper.createOrderedList(mapData))
		.info(MarkupHelper.createUnorderedList(mapData));
		
		
		// #5 Highlight any message
		
		extentReports.createTest("Highlight test")
		.info("This is not highlighted text.")
		.info(MarkupHelper.createLabel("This is highlighted text.", ExtentColor.TEAL));
		
		// #6 Exception
		
		try {
			 @SuppressWarnings("unused")
			int i =5/0 ;
		} catch (Exception e) {
			extentReports.createTest("Exception Test1")
			.info(e);
		}
		
		Throwable t = new RuntimeException("This is a custom exception.");
		extentReports.createTest("Exception Test1")
		.info(t);
		
		
		extentReports.flush();
		
		//Automatically opens the located report file.
		Desktop.getDesktop().browse(new File("logDifferentInfo.html").toURI());
		

	}

}
