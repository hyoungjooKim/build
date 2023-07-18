package project.spring.build.component;

public class PlanDTO {
	private int dbnum; // 시퀀스
	private String dbuser; // 세션 사용자
	private String dblocation; // 지역
	private String dbdate; // 시공 날짜
	private String dbwork; // 시공 공정
	private String dbresult; // 시공 계획 실행 유무
	private String dbmaxtem; // 시공 최고 온도
	private String dbmintem; // 시공 최저 온도
	private String dbrain; // 시공 강수량
	private String dbgpt; // chat-gpt 출력문
	private String dbnote; // 메모
	private String dbaverweather; // 설계 지역 평균 기상 데이터
	
	
	
	public int getDbnum() {
		return dbnum;
	}
	public void setDbnum(int dbnum) {
		this.dbnum = dbnum;
	}
	public String getDbuser() {
		return dbuser;
	}
	public void setDbuser(String dbuser) {
		this.dbuser = dbuser;
	}
	public String getDblocation() {
		return dblocation;
	}
	public void setDblocation(String dblocation) {
		this.dblocation = dblocation;
	}
	public String getDbdate() {
		return dbdate;
	}
	public void setDbdate(String dbdate) {
		this.dbdate = dbdate;
	}
	public String getDbwork() {
		return dbwork;
	}
	public void setDbwork(String dbwork) {
		this.dbwork = dbwork;
	}
	public String getDbresult() {
		return dbresult;
	}
	public void setDbresult(String dbresult) {
		this.dbresult = dbresult;
	}
	public String getDbmaxtem() {
		return dbmaxtem;
	}
	public void setDbmaxtem(String dbmaxtem) {
		this.dbmaxtem = dbmaxtem;
	}
	public String getDbmintem() {
		return dbmintem;
	}
	public void setDbmintem(String dbmintem) {
		this.dbmintem = dbmintem;
	}
	public String getDbrain() {
		return dbrain;
	}
	public void setDbrain(String dbrain) {
		this.dbrain = dbrain;
	}
	public String getDbgpt() {
		return dbgpt;
	}
	public void setDbgpt(String dbgpt) {
		this.dbgpt = dbgpt;
	}
	public String getDbnote() {
		return dbnote;
	}
	public void setDbnote(String dbnote) {
		this.dbnote = dbnote;
	}
	public String getDbaverweather() {
		return dbaverweather;
	}
	public void setDbaverweather(String dbaverweather) {
		this.dbaverweather = dbaverweather;
	}
		
}
