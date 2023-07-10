package project.spring.build.controller;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		return "home";
	}
	
	
	

	@RequestMapping("constructInput")
	public String constructInput(Model model, HttpSession session, @RequestParam String location) throws Exception {
		System.out.println(location);
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
		System.out.println(c);
		return "construct/constructInput";
	}
	
	@RequestMapping("constructOutput")
	public String constructOutput(Model model, HttpSession session, @RequestParam String construct,
			@RequestParam String num) throws Exception {
		session.setAttribute("construct", construct);
		String gpt = null;
		try {
			gpt = service.chatGPTCall(session, num);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String[] text = gpt.split("\\n");

		model.addAttribute("gpt" + num, text);
		return "construct/constructOutput";
	}
	// ResponseEntity.ok(response.getResponse().getHeader().getResultCode());
	@RequestMapping("design")
	public String constructOutput(Model model, @RequestParam String location) throws Exception {
		System.out.println(location);
		
		String locationTrim = location.trim();
		String[] locationArray = locationTrim.split(" ");
		String a = locationArray[0];
		String b = locationArray[1];
		String feature = service.getNum(a, b);
		System.out.println(feature);
		model.addAttribute("feature", feature);
		return "design/design";
	}
	
	@RequestMapping("designGo")
	public String designGo(Model model, @RequestParam String feature) throws Exception {
		String design = service.chatGPTFeature(feature);
		String[] text = design.split("\\n");
		model.addAttribute("design", text);
		return "design/design";
	}
	
	
	
}
