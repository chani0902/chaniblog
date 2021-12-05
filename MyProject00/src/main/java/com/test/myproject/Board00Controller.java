package com.test.myproject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.myproject.board00.model.Board00VO;
import com.test.myproject.board00.model.PageMaker;
import com.test.myproject.board00.model.SearchCriteria;
import com.test.myproject.board00.service.Board00Service;
import com.test.myproject.member.service.MemberService;

@Controller
public class Board00Controller {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Board00Controller.class);
	
	@Autowired
	Board00Service b00s;
	
	@Autowired
	MemberService m01s;
	
	@Autowired
	ServletContext sContext;
	
	@Autowired
	HttpSession session;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@RequestMapping(value = "/b00_selectAll.do" , method = RequestMethod.GET)
	public String b00_selectAll(Model model) throws Exception {
		logger.info("Welcome b00_selectAll!");
		
		List<Board00VO> list = b00s.selectAll();
		
		model.addAttribute("list", list);
		
		return "board00/selectAll";
		
	}
	
	@RequestMapping(value = "/b00_listsearch.do" , method = RequestMethod.GET)
	public String b00_listsearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("Welcome b00_listsearch!");
		
		List<Board00VO> list = b00s.listSearch(scri);
		
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(b00s.countSearch(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "board00/listsearch";
		
	}
	
	@RequestMapping(value = "/b00_searchList.do" , method = RequestMethod.GET)
	public String b00_searchList(Model model, String searchKey, String searchWord) {
		logger.info("Welcome b00_searchList!");
		
		logger.info("searchKey : {}" , searchKey);
		logger.info("searchWord : {}" , searchWord);
		
		
		List<Board00VO> list = b00s.searchList(searchKey, searchWord);
		System.out.println("searchList : " + list);
		
		
		model.addAttribute("list", list);
		
		return "board00/selectAll";
	}
	
//	글 조회 (/read 와 동일)
	@RequestMapping(value = "/b00_selectOne.do" , method = RequestMethod.GET)
	public String b00_selectOne(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("scri") SearchCriteria scri, Model model, Board00VO vo) throws Exception {
		logger.info("Welcome b00_selectOne!");
		
		Board00VO vo2 = b00s.selectOne(vo);	
		
//		쿠키를 생성하여 조회수 증가 (새로고침 조회수 증가 방지용)
		Cookie[] cookies = request.getCookies(); // 쿠키 배열을 불러온다
		int visitor = 0;
		
		for (Cookie cookie : cookies) {
			logger.info(cookie.getName());
			if(cookie.getName().equals((String)session.getAttribute("member_id")+"noti")) {
				visitor = 1;
				logger.info((String)session.getAttribute("member_id")+"noti" + " pass");
				
				// 게시글 번호별 쿠키 처리 부분
				if(cookie.getValue().contains(request.getParameter("board00_num"))) {
					logger.info((String)session.getAttribute("member_id")+"noti" + " if pass");
				} else {
					cookie.setValue(cookie.getValue() + "_" + request.getParameter("board00_num"));
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					b00s.viewcount(vo2.getBoard00_num()); // 조회수 증가 부분
				}
			}
		}

//		쿠키가 없을때 쿠키 생성 후 카운트 늘리기
		if(visitor == 0) {
			Cookie cookie1 = new Cookie((String)session.getAttribute("member_id")+"noti", request.getParameter("board00_num"));
			cookie1.setMaxAge(60*60*24);
			response.addCookie(cookie1);
			b00s.viewcount(vo2.getBoard00_num()); // 조회수 증가 부분
		}
		
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);
		
		logger.info("vo2 : {}", vo2);
		
		return "board00/selectOne";
	}
	
	@RequestMapping(value = "/MY_NOTICE.do" , method = RequestMethod.GET)
	public  JSONArray MY_POST(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("scri") SearchCriteria scri, Model model, Board00VO vo) throws Exception {
		logger.info("Welcome MY_NOTICE!");
		
		String writercheck = request.getParameter("writercheck");
				
		List<Board00VO> list2 = b00s.my_notice(writercheck);	
		System.out.println(list2);
		model.addAttribute(list2);
		
		JSONArray jarr = new JSONArray(list2);
		System.out.println(jarr);
		
		return jarr;
	}
	
	@RequestMapping(value = "/b00_insert.do" , method = RequestMethod.GET)
	public String b00_insert() {
		logger.info("Welcome b00_insert!");
				
		return "board00/insert";
	}
	
	@RequestMapping(value = "/b00_insertOK.do" , method = RequestMethod.POST)
	public String b00_insertOK(Board00VO vo, RedirectAttributes rttr) throws IllegalStateException, IOException {
		logger.info("Welcome b00_insertOK!");
		logger.info("vo : {}", vo);
		
//		세션에서 아이디를 받아와서 작성자를 확인함
		String writercheck = (String) session.getAttribute("member_id");
		
		int result = 0;
		
//		if (!vo.getMultipartFile().getOriginalFilename().equals("")) {
//			vo.setMember_img(vo.getMultipartFile().getOriginalFilename());
//			logger.info("file length : " + vo.getMultipartFile().getOriginalFilename().length());
//			String realPath = sContext.getRealPath("resources/uploadimg");
//			logger.info("realPath : {}", realPath);
//			
//			File f = new File(realPath+"\\"+vo.getMember_img()); 
//			vo.getMultipartFile().transferTo(f); 
	
			
		//// create thumbnail image/////////
//	         BufferedImage original_buffer_img = ImageIO.read(f); 
//	         BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
//	         Graphics2D graphic = thumb_buffer_img.createGraphics();
//	         graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null); 
//
//	         File thumb_file = new File(realPath + "/thumb_" + vo.getMember_img()); 
//	         ImageIO.write(thumb_buffer_img, "jpg", thumb_file);
		
//		} // end if
		
		result = b00s.insert(vo);
		logger.info("result : " + result);
		
//		포인트 증가 부분 (세션에서 아이디를 받아다가 집어넣어줌)
		if (vo.getBoard00_content().length() > 50) {
			String point = "15";
			m01s.POINT_POST(writercheck, point);
//			50자 이상 적으면 15포인트 증가
		} else {
			String point = "10";
			m01s.POINT_POST(writercheck, point);
		}
		
		if(result == 1) {
			rttr.addFlashAttribute("msg", "글 작성 성공");
			return "redirect:b00_listsearch.do";
		} else {
			return "redirect:b00_insert.do";
		}
				
	}
	
	@RequestMapping(value = "/b00_update.do" , method = RequestMethod.GET)
	public String b00_update(@ModelAttribute("scri") SearchCriteria scri, Model model, Board00VO vo) {
		logger.info("Welcome b00_update!");
		Board00VO vo2 = b00s.selectOne(vo);
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);
		return "board00/update";
	}
	
	@RequestMapping(value ="/b00_updateOK.do", method = RequestMethod.POST)
	public String b00_updateOK(@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, Board00VO vo, Model model) throws IllegalStateException, IOException {
		logger.info("Welcome b00_updateOK!");
		logger.info("vo : " + vo);
//		logger.info("filename : " + vo.getMultipartFile().getOriginalFilename());
		
		int result2 = 0;
		
//		if (!vo.getMultipartFile().getOriginalFilename().equals("")) {
//			vo.setMember_img(vo.getMultipartFile().getOriginalFilename());
//			logger.info("file length : " + vo.getMultipartFile().getOriginalFilename().length());
//			
//			String realPath = sContext.getRealPath("resources/uploadimg");
//			logger.info("realPath : {}", realPath);
//			
//			File f = new File(realPath+"\\"+vo.getMember_img()); 
//			vo.getMultipartFile().transferTo(f); 
//			
//			
//	         BufferedImage original_buffer_img = ImageIO.read(f); 
//	         BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
//	         Graphics2D graphic = thumb_buffer_img.createGraphics();
//	         graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null); 
//
//	         File thumb_file = new File(realPath + "/thumb_" + vo.getMember_img()); 
//	         ImageIO.write(thumb_buffer_img, "jpg", thumb_file);
//		
//		} // end if~else
		
		result2 = b00s.update(vo);
		model.addAttribute("scri", scri);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchKey", scri.getSearchKey());
		rttr.addAttribute("searchWord", scri.getSearchWord());
		
		logger.info("result2 : " + result2);
		
		if(result2 == 1) {
			rttr.addFlashAttribute("msg", "글 수정 성공");
			return "redirect:b00_listsearch.do";
		} else {
			logger.info("result2 : {}", result2);
			return "redirect:b00_selectOne.do?board00_num=" + vo.getBoard00_num();
		}
		
	}
	
	@RequestMapping(value = "/b00_delete.do" , method = RequestMethod.GET)
	public String b00_delete(@ModelAttribute("scri") SearchCriteria scri, Model model, Board00VO vo) {
		logger.info("Welcome b00_delete!");
		Board00VO vo2 = b00s.selectOne(vo);
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);	
		return "board00/delete";
	}
	
	@RequestMapping(value ="/b00_deleteOK.do", method = RequestMethod.POST)
	public String b00_deleteOK(@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, Board00VO vo, Model model) {
		logger.info("Welcome b00_deleteOK!");
		logger.info("vo : " + vo);
		Board00VO vo2 = b00s.selectOne(vo);
		
		int result3 = b00s.delete(vo);
		
//		글 작성 포인트 삭제 부분
		if (vo2.getBoard00_content().length() > 50) {
			String point = "-15";
			m01s.POINT_POST(vo2.getWritercheck(), point);
		} else {
			String point = "-10";
			m01s.POINT_POST(vo2.getWritercheck(), point);
		}
		
		model.addAttribute("scri", scri);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchKey", scri.getSearchKey());
		rttr.addAttribute("searchWord", scri.getSearchWord());
		
		if(result3 == 1) {
			rttr.addFlashAttribute("msg", "글 삭제 성공");
			return "redirect:b00_listsearch.do";
		} else {
			return "redirect:b00_selectOne.do?board00_num=" + vo.getBoard00_num();
		}
		
	}
	
}
