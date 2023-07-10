package project.spring.build.service;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import project.spring.build.component.DesignDTO;

public interface DesignService {
	public String weatherInfo(HttpSession session,String x, String y) throws JsonProcessingException;
	public String chatGPTCall(HttpSession session, String num) throws Exception;
	public String getNum(String a, String b);
	public String chatGPTFeature(String feature) throws Exception;
	public DesignDTO getXY(String a, String b);
}
