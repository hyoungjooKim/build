package project.spring.build.service;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import project.spring.build.component.PlanDTO;
import project.spring.build.mapper.HyoungMapper;

@Service
public class HyoungServiceImpl implements HyoungService {

	@Autowired
	private HyoungMapper mapper;
	
	public void insertdeplan(PlanDTO dto) {
		mapper.insertdeplan(dto);
	}
	
	public List<PlanDTO> selectdeplan(int startRow,int endRow,String dbuser){
		return mapper.selectdeplan(startRow,endRow,dbuser);
	}
	
	public PlanDTO contentdeplan(int num) {
		return mapper.contentdeplan(num);
	}
	
	public void deletedeplan(int num) {
		mapper.deletedeplan(num);
	}
	
	public void updatedeplan(PlanDTO dto) {
		mapper.updatedeplan(dto);
	}
	
	public int countdeplan(String dbuser) {
		return mapper.countdeplan(dbuser);
	}
	
	public void insertconplan(PlanDTO dto) {
		mapper.insertconplan(dto);
	} 
	
	public List<PlanDTO> selectconplan(int startRow,int endRow,String dbuser){
		return mapper.selectconplan(startRow, endRow,dbuser);
	}
	
	public PlanDTO contentconplan(int num) {
		return mapper.contentconplan(num);
	}
	
	public void deleteconplan(int num) {
		mapper.deleteconplan(num);
	}
	
	public void updateconplan(PlanDTO dto) {
		mapper.updateconplan(dto);
	}
	
	public int countconplan(String dbuser) {
		return mapper.countconplan(dbuser);
	}	

	public String kakao() {
		// kakao js 키값
		String key ="키 넣는 곳";
		return key;
	}
}
