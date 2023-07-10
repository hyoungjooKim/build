package project.spring.build.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		model.addAttribute("key","5258cbeb3b50db04d8cf5e5e7d6eb80c");		
		return "/map/conmap";
	}
	
	@RequestMapping("demap")
	public String maptode(Model model) {
		model.addAttribute("key","5258cbeb3b50db04d8cf5e5e7d6eb80c");		
		return "/map/demap";
	}
	
	@RequestMapping("main")
	public String main(Model model) {
	model.addAttribute("key","5258cbeb3b50db04d8cf5e5e7d6eb80c");	
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
	
	
	
}
