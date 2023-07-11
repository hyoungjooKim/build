package project.spring.build.service;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import project.spring.build.component.ApiDTO;

@Service
public class ApiExplorer {
	// http://www.cid.or.kr/job/openApi/service/getJobOpenInfoList.do
	public List apimethod() throws Exception {
		StringBuilder urlBuilder = new StringBuilder("http://www.cid.or.kr/job/openApi/service/getJobOpenInfoList.do"); /*URL*/
	    urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=공공api키"); /*Service Key*/
	    urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("12", "UTF-8"));
	    URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		
		String tag = sb.toString();
		//System.out.println(tag);
		DocumentBuilderFactory factory  =  null;
		  DocumentBuilder builder    =  null;
		  Document document = null;
		  List infolist = null;
		   try {
		  factory  =  DocumentBuilderFactory.newInstance();
		  builder    =  factory.newDocumentBuilder();
		  document = builder.parse(new InputSource(new StringReader(tag)));
		  
		  NodeList numlist = document.getElementsByTagName("cmpnyJoSeq"); // 채용정보번호
		  NodeList companylist = document.getElementsByTagName("cmpnyNm"); // 채용정보회사
		  NodeList titlelist = document.getElementsByTagName("empmnSj"); // 채용정보홍보명 : Title
		  NodeList wagelist = document.getElementsByTagName("wageAmount"); // 희망임금조건(원)
		  NodeList careerlist = document.getElementsByTagName("careerYear"); // 최저경력(년)
		  NodeList arealist = document.getElementsByTagName("workArea"); // 근무지역
		  NodeList area1list = document.getElementsByTagName("workAreaAdres1"); // 근무지역 1
		  NodeList area2list = document.getElementsByTagName("workAreaAdres2"); // 근무지역 2
		  NodeList insertdatelist = document.getElementsByTagName("rgsde"); // 해당 정보 등록일
		  NodeList LHlist = document.getElementsByTagName("lhCntrwkAt"); // LH공사여부
		  
		  infolist = new ArrayList();
		  
		  for(int i = 0 ; i < numlist.getLength(); i++) {
			   
			   Node num =  numlist.item(i).getChildNodes().item(0);
			   Node company =  companylist.item(i).getChildNodes().item(0);
			   Node title =  titlelist.item(i).getChildNodes().item(0);
			   Node wage =  wagelist.item(i).getChildNodes().item(0);
			   Node career =  careerlist.item(i).getChildNodes().item(0);
			   Node area =  arealist.item(i).getChildNodes().item(0);
			   Node area1 =  area1list.item(i).getChildNodes().item(0);
			   Node area2 =  area2list.item(i).getChildNodes().item(0);
			   Node insertdate =  insertdatelist.item(i).getChildNodes().item(0);
			   Node LH =  LHlist.item(i).getChildNodes().item(0);

			   /* 해당 코드는 api 자체 구동을 위해 사용된 코드 실제 작업에 사용하는 코드가 아님.
			    * 조건) 메소드가 main일 것 자체 java로 실행하여 결과 값 호출
			   -> public static void main(String[] args) throws Exception {
			   
			   System.out.println(num.getNodeName()+"==="+num.getNodeValue());
			   System.out.println(company.getNodeName()+"==="+company.getNodeValue());
			   System.out.println(title.getNodeName()+"==="+title.getNodeValue());
			   System.out.println(wage.getNodeName()+"==="+wage.getNodeValue());
			   System.out.println(career.getNodeName()+"==="+career.getNodeValue());
			   System.out.println(area.getNodeName()+"==="+area.getNodeValue());
			   System.out.println(area1.getNodeName()+"==="+area1.getNodeValue());
			   System.out.println(area2.getNodeName()+"==="+area2.getNodeValue());
			   System.out.println(insertdate.getNodeName()+"==="+insertdate.getNodeValue());
			   System.out.println(LH.getNodeName()+"==="+LH.getNodeValue());
			   */
		 
			   ApiDTO dto = new ApiDTO();
			   
			   if(num == null) {
				   dto.setNum("-");
			   }else { dto.setNum((String)num.getNodeValue()); }
			   
			   if(company == null) {
				   dto.setCompany("-");
			   }else { dto.setCompany((String)company.getNodeValue()); }
			   
			   if(title == null) {
				   dto.setTitle("-");
			   }else { dto.setTitle((String)title.getNodeValue()); }
			   
			   if(wage == null) {
				   dto.setWage("-");
			   }else { dto.setWage((String)wage.getNodeValue()); }
			   
			   if(career == null) {
				   dto.setCareer("-");
			   }else { dto.setCareer((String)career.getNodeValue()); }
			   
			   if(area == null) {
				   dto.setArea("-");
			   }else { dto.setArea((String)area.getNodeValue()); }
			   
			   if(area1 == null) {
				   dto.setArea1("-");
			   }else { dto.setArea1((String)area1.getNodeValue()); }
			   
			   if(area2 == null) {
				   dto.setArea2("-");
			   }else { dto.setArea2((String)area2.getNodeValue()); }
			   
			   if(insertdate == null) {
				   dto.setInsertdate("-");
			   }else { dto.setInsertdate((String)insertdate.getNodeValue()); }
			   
			   if(LH == null) {
				   dto.setLH("-");
			   }else { dto.setLH((String)LH.getNodeValue()); }
			   
			   infolist.add(dto);
			   
			   }
		  }catch(Exception ex) {
			  ex.printStackTrace();
		  }
		  return infolist;
	}
}
