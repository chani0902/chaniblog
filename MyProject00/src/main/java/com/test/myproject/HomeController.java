package com.test.myproject;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.myproject.board01.model.Board01VO;
import com.test.myproject.board01.model.Criteria;
import com.test.myproject.board01.model.PageMaker;
import com.test.myproject.board01.model.SearchCriteria;
import com.test.myproject.board01.service.Board01Service;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	Board01Service b01s;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = {"/", "/index.do"}, method = RequestMethod.GET)
	public String index(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("Welcome index! Welcome chani!");
		
		List<Board01VO> list = b01s.poppost(scri);
		
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(b01s.countSearch(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "index";
	}
	
}
