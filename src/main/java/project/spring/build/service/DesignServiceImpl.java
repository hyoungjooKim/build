package project.spring.build.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import project.spring.build.component.DesignDTO;
import project.spring.build.component.weatherDTO.ResponseData;
import project.spring.build.component.weatherDTO.ResponseItem;
import project.spring.build.mapper.DesignMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import java.io.IOException;

@Service
public class DesignServiceImpl implements DesignService{
	
	@Autowired
	private DesignMapper mapper;
	SimpleDateFormat sdfForWeather = new SimpleDateFormat("yyyyMMdd");
	
	private class WeatherAPI{
		public String call(String sdf,String x , String y) throws IOException {
		        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"); /*URL*/
		        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=키 넣는 곳"); /*Service Key : */
		        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*한 페이지 결과 수*/
		        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
		        urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(sdf, "UTF-8")); /*‘21년 6월 28일 발표*/
		        urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode("0200", "UTF-8")); /*06시 발표(정시단위) */
		        urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(x, "UTF-8")); /*예보지점의 X 좌표값*/
		        urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(y, "UTF-8")); /*예보지점의 Y 좌표값*/
		        
		        return urlBuilder.toString();
		        
		}
	}
	
	private class ChatGPT {
	    public String chatGPT(String text) throws Exception {
	    			//System.out.println(text);
	    	        String url = "https://api.openai.com/v1/chat/completions";
	    	        HttpClient httpClient = HttpClients.createDefault();
	    	        HttpPost httpPost = new HttpPost(url);
	    	        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer '키 넣는 곳'");
	    	        StringEntity requestEntity = new StringEntity(
	    	                "{ \"temperature\": 0.6, \"messages\": [{\"role\": \"user\", \"content\": \""+text+"\"}], \"max_tokens\": 2000 ,\"model\": \""+"gpt-3.5-turbo"+"\" }",
	    	                ContentType.APPLICATION_JSON
	    	            );
	    	            httpPost.setEntity(requestEntity);
	    	        
	    	        HttpResponse response = httpClient.execute(httpPost);
	    	        HttpEntity responseEntity = response.getEntity();
	    	        String responseBody = EntityUtils.toString(responseEntity);
	    	        return responseBody;
	    }
	}
	    
	@Override
	public String weatherInfo(HttpSession session, String x , String y) throws JsonProcessingException{
		Calendar calendar = Calendar.getInstance();
		String sdf1 = sdfForWeather.format(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String sdf2 = sdfForWeather.format(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		String sdf3 = sdfForWeather.format(calendar.getTime());
		WeatherAPI weather = new WeatherAPI();
		ObjectMapper objectMapper = new ObjectMapper();
        String url = null;
        ResponseData response = null;
        try{
        	url = weather.call(sdf1,x ,y);
		 	HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            String response1 = EntityUtils.toString(httpResponse.getEntity());
            response = objectMapper.readValue(response1, ResponseData.class);
        }catch(Exception e){
        }
        List<ResponseItem> pop = response.getResponse().getBody().getItems().getItem().stream()
        		.filter(responseItem -> responseItem.getFcstTime().equals("1200") && responseItem.getCategory().equals("POP"))
        		.filter(responseItem -> responseItem.getFcstDate().equals(sdf1) || responseItem.getFcstDate().equals(String.valueOf(sdf2)) || responseItem.getFcstDate().equals(String.valueOf(sdf3)))
        		.collect(Collectors.toList());
        
        List<ResponseItem> tmn = response.getResponse().getBody().getItems().getItem().stream()
        		.filter(responseItem -> responseItem.getFcstTime().equals("0600") && responseItem.getCategory().equals("TMN"))
        		.filter(responseItem -> responseItem.getFcstDate().equals(sdf1) || responseItem.getFcstDate().equals(String.valueOf(sdf2)) || responseItem.getFcstDate().equals(String.valueOf(sdf3)))
        		.collect(Collectors.toList());
        
        List<ResponseItem> tmx = response.getResponse().getBody().getItems().getItem().stream()
        		.filter(responseItem -> responseItem.getFcstTime().equals("1500") && responseItem.getCategory().equals("TMX"))
        		.filter(responseItem -> responseItem.getFcstDate().equals(sdf1) || responseItem.getFcstDate().equals(String.valueOf(sdf2)) || responseItem.getFcstDate().equals(String.valueOf(sdf3)))
        		.collect(Collectors.toList());
        
        session.setAttribute("POP1", pop.get(0).getFcstValue());
        session.setAttribute("POP2", pop.get(1).getFcstValue());
        session.setAttribute("POP3", pop.get(2).getFcstValue());
        session.setAttribute("TMN1", tmn.get(0).getFcstValue());
        session.setAttribute("TMN2", tmn.get(1).getFcstValue());
        session.setAttribute("TMN3", tmn.get(2).getFcstValue());
        session.setAttribute("TMX1", tmx.get(0).getFcstValue());
        session.setAttribute("TMX2", tmx.get(1).getFcstValue());
        session.setAttribute("TMX3", tmx.get(2).getFcstValue());    
        return null;
	}
	
	@Override
	public String chatGPTCall(HttpSession session, String num) throws Exception {
		ChatGPT chat = new ChatGPT();
		String gpt = null;
	    try {
	        StringBuilder promptBuilder = new StringBuilder();
	        promptBuilder.append("(1000토큰 이내) 아래에 있는 오늘의 날씨와 공정을 조건으로 건축물 건설에 대한 안전 지침을 제공해줘. 해당공정이 날씨에 적합한지도 제공해줘")
	                .append(" 강수 확률: ").append(session.getAttribute("POP"+num))
	                .append(", 최저 기온: ").append(session.getAttribute("TMN"+num))
	                .append(", 최고 기온: ").append(session.getAttribute("TMX"+num))
	                .append(", 공정: " + session.getAttribute("construct"));

	        String prompt = promptBuilder.toString();

	        String response = chat.chatGPT(prompt);
	        //System.out.println(response);
	        JSONObject jsonResponse = new JSONObject(response);
	        JSONArray choices = jsonResponse.getJSONArray("choices");
	        if (choices.length() > 0) {
	            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
	            gpt = message.getString("content");
	        }
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    
	    //System.out.println(gpt);
	    return gpt;
	}
	@Override
	public String chatGPTFeature(String feature) throws Exception {
		ChatGPT chat = new ChatGPT();
		String gpt = null;
	    try {
	        String response = chat.chatGPT("다음 내용을 참고로 건축설계에 대한 지침좀 해줘 : " + feature);
	        //System.out.println(response);
	        JSONObject jsonResponse = new JSONObject(response);
	        JSONArray choices = jsonResponse.getJSONArray("choices");
	        if (choices.length() > 0) {
	            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
	            gpt = message.getString("content");
	        }
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    //System.out.println(gpt);
	    return gpt;
	}
	
	@Override
	public String getNum(String a, String b) {
		//System.out.println(a.trim());
		//System.out.println(b.trim());
		String designNum = mapper.getNum(a.trim(),b.trim());
		String feature = mapper.feature(designNum);
		return feature;
	}
	
	@Override
	public DesignDTO getXY(String a, String b) {
		DesignDTO XY = mapper.getXY(a,b); 
		return XY;
	}

}

