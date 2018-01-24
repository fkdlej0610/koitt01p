package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.MyService;
import service.ReviewService;

@Controller
public class MyController {
	@Autowired
	private MyService service;
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping("index.do")
	public ModelAndView getFestvalInfo() throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("festvalInfo", service.getFestivalInfo());
		mav.setViewName("index");
		
		return mav;
	}
	@RequestMapping("main.do")
	public ModelAndView getTopList(@RequestParam String contentTypeID) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("topList",reviewService.topSelect(contentTypeID));
		mav.setViewName("main");
		return mav;
	}
}
