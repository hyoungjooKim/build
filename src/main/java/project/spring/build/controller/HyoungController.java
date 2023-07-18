package project.spring.build.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.spring.build.component.PlanDTO;
import project.spring.build.service.ApiExplorer;
import project.spring.build.service.HyoungService;

@Controller
@RequestMapping("/*")
public class HyoungController {
	
	@Autowired
	private HyoungService service;
	
	@Autowired
	private ApiExplorer api;
		
	//채용정보 사이트 (실제 사용할 파일)
	@RequestMapping("career")
	public String apiinkorea(Model model) throws Exception{
		model.addAttribute("key",service.kakao());
		
		List datalist = null;
		try {
			datalist = api.apimethod();
			model.addAttribute("api", datalist);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/careerapi/api";
	}
	
	@RequestMapping("conmap")
	public String maptocon(Model model) {
		model.addAttribute("key",service.kakao());		
		return "/map/conmap";
	}
	
	@RequestMapping("demap")
	public String maptode(Model model) {
		model.addAttribute("key",service.kakao());		
		return "/map/demap";
	}
	
	@RequestMapping("main")
	public String main(Model model,HttpSession session,HttpServletRequest request) {
		model.addAttribute("key",service.kakao());
		String dbuser = (String) session.getAttribute("Email");
		System.out.println("접속자 IP : "+request.getRemoteAddr());
		System.out.println("카카오 접속자 이메일 : "+dbuser);
		
		return "/main/MainPage";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:main";
	}
	@RequestMapping("session")
	public String createsession(HttpSession session,String email) {
		session.setAttribute("Email", email);
		return "/main/session";
	}
	
	@RequestMapping("developer")
	public String develop() {
		
		return "/main/developer"; 
	}
	
	@RequestMapping("addconplan")
	public String conplan(Model model,PlanDTO dto) {
		service.insertconplan(dto);
		return "redirect:conplanlist"; 
	}
	
	@RequestMapping("adddeplan")
	public String deplan(Model model,PlanDTO dto) {
		service.insertdeplan(dto);
		
		return "redirect:deplanlist"; 
	}
	
	@RequestMapping("myplan")
	public String plan(Model model,String pageNum,String plantype,HttpSession session) {
		model.addAttribute("key",service.kakao());
		model.addAttribute("plantype", plantype);
		String dbuser = (String) session.getAttribute("Email");
		// System.out.println(dbuser);
		List list = null;
		int count = 0;
		int pageSize = 10; // 페이지에서 보여주는 데이터 갯수 제한
	    if (pageNum == null) {
	        pageNum = "1";
	    }
	    
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    int number=0;
	    
	    if(plantype.equals("construction")) {
	    list = service.selectconplan(startRow,endRow,dbuser);
	    count = service.countconplan(dbuser);
	    // System.out.println("con");
	    }else if(plantype.equals("design")) {
	    	list = service.selectdeplan(startRow,endRow,dbuser);
		    count = service.countdeplan(dbuser); 
		    // System.out.println("de");
			 }	
	    
		
	    if (count > 0) {
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);  //2.5+1
	        int startPage = (int)(currentPage/10)*10+1; //2
			int pageBlock = 10;
	        int endPage = startPage + (pageBlock-1); // 2+ 9 = 11
	        if (endPage > pageCount) {endPage = pageCount; }
	        		
	        model.addAttribute("pageBlock",pageBlock);
	        model.addAttribute("startPage",startPage);
	        model.addAttribute("endPage",endPage);
	        model.addAttribute("pageCount",pageCount); 
	 }
	    
		model.addAttribute("list", list);
		
		
		return "/plan/planlist"; 
	}
	
	@RequestMapping("planupdate")
	public String planupdate(Model model,int dbnum,HttpSession session,String plantype) {
		model.addAttribute("key",service.kakao());
		if(plantype.equals("construction")) {
		PlanDTO dto = service.contentconplan(dbnum);
		model.addAttribute("dto", dto);
		}else if(plantype.equals("design")) {
		PlanDTO dto = service.contentdeplan(dbnum);
		model.addAttribute("dto", dto);
		}
		model.addAttribute("plantype", plantype);
		return "/plan/planupdate"; 
	}
	
	@RequestMapping("conupdatepro")
	public String conupdate(Model model,int dbnum,HttpSession session,PlanDTO dto) {
		model.addAttribute("key",service.kakao());
		service.updateconplan(dto);
		return "redirect:conplanlist"; 
	}
	
	@RequestMapping("deupdatepro")
	public String deupdate(Model model,int dbnum,HttpSession session,PlanDTO dto) {
		model.addAttribute("key",service.kakao());
		service.updatedeplan(dto);
		return "redirect:deplanlist"; 
	}
	
	
	@RequestMapping("plancontent")
	public String plancontent(Model model,int dbnum,String plantype) {
		model.addAttribute("key",service.kakao());
		PlanDTO dto = null;
		if(plantype.equals("construction")) {
		dto = service.contentconplan(dbnum);
		model.addAttribute("dto", dto);
		}else if(plantype.equals("design")) {
		dto = service.contentdeplan(dbnum);
		model.addAttribute("dto", dto);
		}
		
		model.addAttribute("plantype", plantype);
		return "/plan/plancontent"; 
	}
	
	@RequestMapping("plandelete")
	public String plandelete(Model model,String plantype,int dbnum) {
		String movepath = null;
		if(plantype.equals("construction")) {
		movepath = "redirect:conplanlist";
		service.deleteconplan(dbnum);
		}else if(plantype.equals("design")) {
		movepath = "redirect:deplanlist";
		service.deletedeplan(dbnum);
		}
		return movepath; 
	}
	
	
	
	
	
	// contruction
	@RequestMapping("conplanlist")
	public String conplanlist(Model model,String pageNum,HttpSession session) {
		model.addAttribute("key",service.kakao());
		String plantype = "construction";
		model.addAttribute("plantype", plantype);
		String dbuser = (String) session.getAttribute("Email");
		// System.out.println(dbuser);
		List list = null;
		int count = 0;
		int pageSize = 10; // 페이지에서 보여주는 데이터 갯수 제한
	    if (pageNum == null) {
	        pageNum = "1";
	    }
	    
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    int number=0;
	    
	    list = service.selectconplan(startRow,endRow,dbuser);
	    count = service.countconplan(dbuser);
	    // System.out.println("con");
	    
	    if (count > 0) {
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);  //2.5+1
	        int startPage = (int)(currentPage/10)*10+1; //2
			int pageBlock = 10;
	        int endPage = startPage + (pageBlock-1); // 2+ 9 = 11
	        if (endPage > pageCount) {endPage = pageCount; }
	        		
	        model.addAttribute("pageBlock",pageBlock);
	        model.addAttribute("startPage",startPage);
	        model.addAttribute("endPage",endPage);
	        model.addAttribute("pageCount",pageCount); 
	 }
	    
		model.addAttribute("list", list);
		
		
		return "/plan/planlist"; 
	}
	
	
	// design
	@RequestMapping("deplanlist")
	public String deplanlist(Model model,String pageNum,HttpSession session) {
		model.addAttribute("key",service.kakao());
		String plantype = "design";
		model.addAttribute("plantype", plantype);
		
		String dbuser = (String) session.getAttribute("Email");
		//System.out.println(dbuser);
		List list = null;
		int count = 0;
		int pageSize = 10; // 페이지에서 보여주는 데이터 갯수 제한
	    if (pageNum == null) {
	        pageNum = "1";
	    }
	    
	    int currentPage = Integer.parseInt(pageNum);
	    int startRow = (currentPage - 1) * pageSize + 1;
	    int endRow = currentPage * pageSize;
	    int number=0;
	    
	    
	    	list = service.selectdeplan(startRow,endRow,dbuser);
		    count = service.countdeplan(dbuser); 
		    // System.out.println("de");
			 	
	    
		
	    if (count > 0) {
	        int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);  //2.5+1
	        int startPage = (int)(currentPage/10)*10+1; //2
			int pageBlock = 10;
	        int endPage = startPage + (pageBlock-1); // 2+ 9 = 11
	        if (endPage > pageCount) {endPage = pageCount; }
	        		
	        model.addAttribute("pageBlock",pageBlock);
	        model.addAttribute("startPage",startPage);
	        model.addAttribute("endPage",endPage);
	        model.addAttribute("pageCount",pageCount); 
	 }
	    
		model.addAttribute("list", list);
		
		
		return "/plan/planlist"; 
	}
	
	
	@RequestMapping("loading")
	public String loading() {
			
		return "/main/loading";
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
