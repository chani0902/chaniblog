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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.myproject.board01.model.Board01VO;
import com.test.myproject.board01.model.Criteria;
import com.test.myproject.board01.model.PageMaker;
import com.test.myproject.board01.model.SearchCriteria;
import com.test.myproject.board01.service.Board01Service;
import com.test.myproject.member.service.MemberService;
import com.test.myproject.reply01.model.Reply01VO;
import com.test.myproject.reply01.service.Reply01Service;

@Controller
public class Board01Controller {
	
	
	private static final Logger logger = LoggerFactory.getLogger(Board01Controller.class);
	
	@Autowired
	Board01Service b01s;
	
	@Autowired
	Reply01Service r01s;
	
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
	@RequestMapping(value = "/b01_selectAll.do" , method = RequestMethod.GET)
	public String b01_selectAll(Model model) throws Exception {
		logger.info("Welcome b01_selectAll!");
		
		List<Board01VO> list = b01s.selectAll();
		
		model.addAttribute("list", list);
		
		return "board01/selectAll";
		
	}
	
//	selectAll + 페이징 기능..!
	@RequestMapping(value = "/b01_listpage.do" , method = RequestMethod.GET)
	public String b01_listpage(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("Welcome b01_listpage!");
		
		List<Board01VO> list = b01s.listpage(cri);
		
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(b01s.listCount());
		model.addAttribute("pageMaker", pageMaker);
		
		return "board01/listpage";
		
	}
	
	@RequestMapping(value = "/b01_listsearch.do" , method = RequestMethod.GET)
	public String b01_listsearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("Welcome b01_listsearch!");
		
		List<Board01VO> list = b01s.listSearch(scri);
		
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(b01s.countSearch(scri));
		model.addAttribute("pageMaker", pageMaker);
		
		return "board01/listsearch";
		
	}
	
	@RequestMapping(value = "/b01_searchList.do" , method = RequestMethod.GET)
	public String b01_searchList(Model model, String searchKey, String searchWord) {
		logger.info("Welcome b01_searchList!");
		
		logger.info("searchKey : {}" , searchKey);
		logger.info("searchWord : {}" , searchWord);
		
		
		List<Board01VO> list = b01s.searchList(searchKey, searchWord);
		System.out.println("searchList : " + list);
		
		
		model.addAttribute("list", list);
		
		return "board01/selectAll";
	}
	
//	글 조회 (/read 와 동일)
	@RequestMapping(value = "/b01_selectOne.do" , method = RequestMethod.GET)
	public String b01_selectOne(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("scri") SearchCriteria scri, Model model, Board01VO vo) throws Exception {
		logger.info("Welcome b01_selectOne!");
		
		Board01VO vo2 = b01s.selectOne(vo);	
		
//		쿠키를 생성하여 조회수 증가 (새로고침 조회수 증가 방지용)
		Cookie[] cookies = request.getCookies(); // 쿠키 배열을 불러온다
		int visitor = 0;
		
		for (Cookie cookie : cookies) {
			logger.info(cookie.getName());
			if(cookie.getName().equals((String)session.getAttribute("member_id"))) {
				visitor = 1;
				logger.info((String)session.getAttribute("member_id") + " pass");
				
				// 게시글 번호별 쿠키 처리 부분
				if(cookie.getValue().contains(request.getParameter("board01_num"))) {
					logger.info((String)session.getAttribute("member_id") + " if pass");
				} else {
					cookie.setValue(cookie.getValue() + "_" + request.getParameter("board01_num"));
					cookie.setMaxAge(60*60*24);
					response.addCookie(cookie);
					b01s.viewcount(vo2.getBoard01_num()); // 조회수 증가 부분
				}
			}
		}

//		쿠키가 없을때 쿠키 생성 후 카운트 늘리기
		if(visitor == 0) {
			Cookie cookie1 = new Cookie((String)session.getAttribute("member_id"), request.getParameter("board01_num"));
			cookie1.setMaxAge(60*60*24);
			response.addCookie(cookie1);
			b01s.viewcount(vo2.getBoard01_num()); // 조회수 증가 부분
		}
		
		
//		댓글 개수 체크
		b01s.reply_cnt(vo2.getBoard01_num());
		
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);
		
		List<Reply01VO> repList = r01s.selectAll(vo.getBoard01_num());
		model.addAttribute("repList", repList);
		
		logger.info("vo2 : {}", vo2);
		
		return "board01/selectOne";
	}
	
	
	@RequestMapping(value = "/MY_POST.do" , method = RequestMethod.GET)
	public  JSONArray MY_POST(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("scri") SearchCriteria scri, Model model, Board01VO vo) throws Exception {
		logger.info("Welcome MY_POST!");
		
		String writercheck = request.getParameter("writercheck");
				
		List<Board01VO> list2 = b01s.my_post(writercheck);	
		System.out.println(list2);
		model.addAttribute(list2);
		
		JSONArray jarr = new JSONArray(list2);
		System.out.println(jarr);
		
		return jarr;
	}
	
	@RequestMapping(value = "/rp_insert.do" , method = RequestMethod.POST)
	public String rp_insert(Board01VO bvo, Reply01VO vo, SearchCriteria scri, RedirectAttributes rttr) {
		logger.info("vo : {}", vo);
		int result = 0;
		result = r01s.insert(vo);
		
//		자기 글에 다는 댓글인지 확인하는 부분 (자기의 글이면 포인트 증가는 안하게)
		Board01VO bvo2 = b01s.rp_mine(vo.getBoard01_num());
		logger.info("rp_mine : " +bvo2.getWritercheck());
		if (!bvo2.getWritercheck().equals((String)session.getAttribute("member_id"))) {
//			댓글 작성시 포인트 증가 부분
			String member_id = (String)session.getAttribute("member_id");
			String point = "2";
			m01s.POINT_POST(member_id, point);
		}
		
		rttr.addAttribute("board01_num", vo.getBoard01_num());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchKey", scri.getSearchKey());
		rttr.addAttribute("searchWord", scri.getSearchWord());
		
		return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
	}
	
	// 댓글 수정 GET
	@RequestMapping(value = "/r01_update.do", method = RequestMethod.GET)
	public String r01_update(@RequestParam("reply01_num") int reply01_num,
	      @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
	 logger.info("r01_update.do");
	 
	 Reply01VO vo = null;
	 
	 vo = r01s.selectOne(reply01_num);
	 
	 model.addAttribute("vo", vo);
	 model.addAttribute("scri", scri);
	 return "board01/rp_update";
	}
	
	// 댓글 수정 POST
	@RequestMapping(value = "/r01_updateOK.do", method = RequestMethod.POST)
	public String r01_updateOK(Reply01VO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
	 logger.info("r01_updateOK.do");
	 
	 int result = 0;
	 result = r01s.update(vo);
	 
	 rttr.addAttribute("board01_num", vo.getBoard01_num());
	 rttr.addAttribute("page", scri.getPage());
	 rttr.addAttribute("perPageNum", scri.getPerPageNum());
	 rttr.addAttribute("searchKey", scri.getSearchKey());
	 rttr.addAttribute("searchWord", scri.getSearchWord());
	 
	 if(result == 1) {
			rttr.addFlashAttribute("msg", "댓글 수정 성공");
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		} else {
			rttr.addFlashAttribute("msg", "댓글 수정 실패");
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		}
	}
	
	// 댓글 삭제 GET
		@RequestMapping(value = "/r01_delete.do", method = RequestMethod.GET)
		public String r01_delete(@RequestParam("reply01_num") int reply01_num,
		      @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		 logger.info("r01_delete.do");
		 
		 Reply01VO vo = null;
		 
		 vo = r01s.selectOne(reply01_num);
		 
		 model.addAttribute("vo", vo);
		 model.addAttribute("scri", scri);
		 return "board01/rp_delete";
		}

	// 댓글 삭제 POST
	@RequestMapping(value = "/r01_deleteOK.do", method = RequestMethod.POST)
	public String r01_deleteOK(Reply01VO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
	 logger.info("/r01_deleteOK.do");
	 Reply01VO vo2 = r01s.selectOne(vo.getReply01_num());
	 logger.info("rp_delete vo2 : " + vo2);
	 
	 int result = 0;
	 result = r01s.delete(vo);
	 
//	 자기글에 댓글 삭제시 포인트 감소 없게
	 Board01VO bvo2 = b01s.rp_mine(vo2.getBoard01_num());
	 logger.info("rp_mine : " +bvo2.getWritercheck());
	 
		if (!bvo2.getWritercheck().equals(vo2.getWritercheck())) {
			String point = "-2";
			m01s.POINT_POST(vo2.getWritercheck(), point);
		}
 
	 
	 rttr.addAttribute("board01_num", vo.getBoard01_num());
	 rttr.addAttribute("page", scri.getPage());
	 rttr.addAttribute("perPageNum", scri.getPerPageNum());
	 rttr.addAttribute("searchKey", scri.getSearchKey());
	 rttr.addAttribute("searchWord", scri.getSearchWord());
	 logger.info("rp_delete_result : {}", result);
	 
	 if(result == 1) {
			rttr.addFlashAttribute("msg", "댓글 삭제 성공");
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		} else {
			rttr.addFlashAttribute("msg", "댓글 삭제 실패");
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		}

	}
	
	@RequestMapping(value = "/b01_insert.do" , method = RequestMethod.GET)
	public String b01_insert() {
		logger.info("Welcome b01_insert!");
				
		return "board01/insert";
	}
	
	@RequestMapping(value = "/b01_insertOK.do" , method = RequestMethod.POST)
	public String b01_insertOK(Board01VO vo, RedirectAttributes rttr) throws IllegalStateException, IOException {
		logger.info("Welcome b01_insertOK!");
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
		
		result = b01s.insert(vo);
		logger.info("result : " + result);
		
//		포인트 증가 부분 (세션에서 아이디를 받아다가 집어넣어줌)
		if (vo.getBoard01_content().length() > 50) {
			String point = "15";
			m01s.POINT_POST(writercheck, point);
//			50자 이상 적으면 15포인트 증가
		} else {
			String point = "10";
			m01s.POINT_POST(writercheck, point);
		}
		
		if(result == 1) {
			rttr.addFlashAttribute("msg", "글 작성 성공");
			return "redirect:b01_listsearch.do";
		} else {
			return "redirect:b01_insert.do";
		}
				
	}
	
	@RequestMapping(value = "/b01_update.do" , method = RequestMethod.GET)
	public String b01_update(@ModelAttribute("scri") SearchCriteria scri, Model model, Board01VO vo) {
		logger.info("Welcome b01_update!");
		Board01VO vo2 = b01s.selectOne(vo);
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);
		return "board01/update";
	}
	
	@RequestMapping(value ="/b01_updateOK.do", method = RequestMethod.POST)
	public String b01_updateOK(@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, Board01VO vo, Model model) throws IllegalStateException, IOException {
		logger.info("Welcome b01_updateOK!");
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
		
		result2 = b01s.update(vo);
		model.addAttribute("scri", scri);
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchKey", scri.getSearchKey());
		rttr.addAttribute("searchWord", scri.getSearchWord());
		
		logger.info("result2 : " + result2);
		
		if(result2 == 1) {
			rttr.addFlashAttribute("msg", "글 수정 성공");
			return "redirect:b01_listsearch.do";
		} else {
			logger.info("result2 : {}", result2);
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		}
		
	}
	
	@RequestMapping(value = "/b01_delete.do" , method = RequestMethod.GET)
	public String b01_delete(@ModelAttribute("scri") SearchCriteria scri, Model model, Board01VO vo) {
		logger.info("Welcome b01_delete!");
		Board01VO vo2 = b01s.selectOne(vo);
		model.addAttribute("vo2", vo2);
		model.addAttribute("scri", scri);	
		return "board01/delete";
	}
	
	@RequestMapping(value ="/b01_deleteOK.do", method = RequestMethod.POST)
	public String b01_deleteOK(@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr, Board01VO vo, Model model) {
		logger.info("Welcome b01_deleteOK!");
		logger.info("vo : " + vo);
		Board01VO vo2 = b01s.selectOne(vo);
		
		int result3 = b01s.delete(vo);
		
//		글 작성 포인트 삭제 부분
		if (vo2.getBoard01_content().length() > 50) {
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
			return "redirect:b01_listsearch.do";
		} else {
			return "redirect:b01_selectOne.do?board01_num=" + vo.getBoard01_num();
		}
		
	}
	
}
