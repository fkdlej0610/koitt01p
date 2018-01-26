   package controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.LoginService;
import service.MyService;
import service.ReviewService;

@Controller
public class MyController {
	@Autowired
	private MyService service;
	
	@Autowired
	private LoginService lService;
	
	@Autowired
	private ReviewService rService;
	
	@RequestMapping("index.do")
	public ModelAndView getFestvalInfo() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("festvalInfo", service.getFestivalInfo());
		mav.setViewName("index");
		
		return mav;
	}
	@RequestMapping("main.do")
	public ModelAndView getTopList() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("topListFestival",rService.topSelectFestival());
		mav.addObject("topListCountry",rService.topSelectCountry());
		mav.addObject("topListRestaurant",rService.topSelectRestaurant());
		mav.setViewName("main");
		return mav;
	}
	
	//=================================회원가입,로그인단================================//
	@RequestMapping("createUserForm.do")
	public void createUserForm() {}
	
	@RequestMapping("login.do")
	public String login(HttpSession session,String userID,String password) {
		if(lService.login(userID, password)) {
			session.setAttribute("userID", userID);
			return "redirect:main.do";
		}
		else {
			return "login.do";
		}
		
	}
	@RequestMapping("createUser.do")
	public String createUser(@RequestParam HashMap<String, String> params) {
		if(params.get("password").equals(params.get("pw_CHECK"))) {
			lService.createUser(params);
		}
		return "redirect:main.do";
	}
	
}
