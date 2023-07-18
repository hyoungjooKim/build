package project.spring.build.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import project.spring.build.component.PlanDTO;

public interface HyoungService {

	public void insertdeplan(PlanDTO dto); 
	
	public List<PlanDTO> selectdeplan(int startRow,int endRow,String dbuser);
	
	public PlanDTO contentdeplan(int num);
	
	public void deletedeplan(int num);
	
	public void updatedeplan(PlanDTO dto);
	
	public int countdeplan(String dbuser);
	
	public void insertconplan(PlanDTO dto); 
	
	public List<PlanDTO> selectconplan(int startRow,int endRow,String dbuser);
	
	public PlanDTO contentconplan(int num);
	
	public void deleteconplan(int num);
	
	public void updateconplan(PlanDTO dto);
	
	public int countconplan(String dbuser);
	
	public String kakao();
}
