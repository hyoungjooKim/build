package project.spring.build.service;

import java.util.HashMap;

public interface HyoungService {

	public String getAccessToken(String authorize_code);
	
	public HashMap<String, Object> getUserInfo(String access_Token);
	
}
