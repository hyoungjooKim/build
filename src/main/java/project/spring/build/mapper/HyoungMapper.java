package project.spring.build.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import project.spring.build.component.PlanDTO;



public interface HyoungMapper {

	public void insertdeplan(PlanDTO dto); 
	
	public List<PlanDTO> selectdeplan(@Param("startRow") int startRow,@Param("endRow") int endRow,@Param("dbuser")String dbuser);
	
	public PlanDTO contentdeplan(int num);
	
	public void deletedeplan(int num);
	
	public void updatedeplan(PlanDTO dto);
	
	public int countdeplan(@Param("dbuser")String dbuser);
	
	public void insertconplan(PlanDTO dto); 
	
	public List<PlanDTO> selectconplan(@Param("startRow") int startRow,@Param("endRow") int endRow,@Param("dbuser")String dbuser);
	
	public PlanDTO contentconplan(int num);
	
	public void deleteconplan(int num);
	
	public void updateconplan(PlanDTO dto);
	
	public int countconplan(@Param("dbuser")String dbuser);
	
}
