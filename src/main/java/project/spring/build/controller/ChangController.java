package project.spring.build.controller;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.spring.build.component.DesignDTO;
import project.spring.build.service.*;

@Controller
@RequestMapping("/*")
public class ChangController {
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	private DesignService service;

	@RequestMapping("conn")
	public String conn() {
		return "career";
	}
	

	@RequestMapping("constructInput")
	public String aconstructInput(Model model, HttpSession session, @RequestParam String location) throws Exception {
		model.addAttribute("location", location);
		Calendar calendar = Calendar.getInstance();
		String locationTrim = location.trim();
		String[] locationArray = locationTrim.split(" ");
		String a = locationArray[0];
		String b = locationArray[1];
		String x = null;
		String y = null;
		DesignDTO XY = service.getXY(a, b);
		x = XY.getLocationX();
		y = XY.getLocationY();
		String c = service.weatherInfo(session, x , y);
		model.addAttribute("day1", sdf.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		model.addAttribute("day2", sdf.format(calendar.getTime()));
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		model.addAttribute("day3", sdf.format(calendar.getTime()));
		//System.out.println(c);
		return "construct/constructInput";
	}
	
	@RequestMapping("constructOutput")
	public String aconstructOutput(Model model, HttpSession session, @RequestParam String construct,
			@RequestParam String num,String location) throws Exception {
		session.setAttribute("construct", construct);
		model.addAttribute("location", location);
		model.addAttribute("constr", construct);
		model.addAttribute("num", num);
		String gpt = null;
		try {
			gpt = service.chatGPTCall(session, num);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		String[] text = gpt.split("\\n");
		model.addAttribute("gpt" + num, text);
		
		Calendar calendar = Calendar.getInstance();
		
		if(num.equals("1")) {
		model.addAttribute("whatday", sdf.format(calendar.getTime()));		
		}else if(num.equals("2")) {
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		model.addAttribute("whatday", sdf.format(calendar.getTime()));
		}else if(num.equals("3")) {	
		calendar.add(Calendar.DAY_OF_MONTH, 2);
		model.addAttribute("whatday", sdf.format(calendar.getTime()));
		}
		
		//System.out.println(sdf.format(calendar.getTime()));
		
		return "construct/constructOutput";
	}
	// ResponseEntity.ok(response.getResponse().getHeader().getResultCode());
	@RequestMapping("design")
	public String aconstructOutput(Model model, @RequestParam String location) throws Exception {
		//System.out.println(location);
		
		String locationTrim = location.trim();
		String[] locationArray = locationTrim.split(" ");
		String a = locationArray[0];
		String b = locationArray[1];
		String feature = service.getNum(a, b);
		//System.out.println(feature);
		model.addAttribute("feature", feature);
		model.addAttribute("location", location);
		return "design/design";
	}
	
	@RequestMapping("designGo")
	public String adesignGo(Model model, @RequestParam String feature,String location) throws Exception {
		String design = service.chatGPTFeature(feature);
		
		
		String[] text = design.split("\\n");
		model.addAttribute("design", text);
		model.addAttribute("feature", feature);
		model.addAttribute("location", location);
		return "design/design";
	}
}
