package controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.APIService;

@Controller
public class APIController {
	@Autowired
	private APIService service;
	
	@RequestMapping("search.do")
	public ModelAndView getSearch(@RequestParam(defaultValue = "") String search, 
			@RequestParam(defaultValue="") String contentTypeId, 
			@RequestParam(defaultValue="") String areaCode) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("searchInfo", service.searchAPIInfo(search, contentTypeId, areaCode));
		mav.setViewName("search");
		return mav;
	}
}
