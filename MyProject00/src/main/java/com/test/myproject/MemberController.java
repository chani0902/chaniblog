package com.test.myproject;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.test.myproject.member.model.MemberVO;
import com.test.myproject.member.service.MailSendService;
import com.test.myproject.member.service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MailSendService mailsender;
	
	@Autowired
	MemberService ms;
	
//	패스워드 암호화
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	ServletContext sContext;
	
	@Autowired
	HttpServletRequest req;
	
	@Autowired
	HttpSession session;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/mv_selectAll.do" , method = RequestMethod.GET)
	public String mv_selectAll(Model model) {
		logger.info("Welcome mv_selectAll!");
		
		List<MemberVO> list = ms.selectAll();
		
		model.addAttribute("list", list);
		
		return "member/selectAll";
	}
	
	@RequestMapping(value = "/mv_searchList.do" , method = RequestMethod.GET)
	public String mv_searchList(Model model, String searchKey, String searchWord) {
		logger.info("Welcome mv_searchList!");
		
		logger.info("searchKey : {}" , searchKey);
		logger.info("searchWord : {}" , searchWord);
		
		
		List<MemberVO> list = ms.searchList(searchKey, searchWord);
		System.out.println("searchList : " + list);
		
		
		model.addAttribute("list", list);
		
		return "member/selectAll";
	}
	
	@RequestMapping(value = "/mv_selectOne.do" , method = RequestMethod.GET)
	public String mv_selectOne(Model model, MemberVO vo) {
		logger.info("Welcome mv_selectOne!");
		
		MemberVO vo2 = ms.selectOne(vo);
		
		model.addAttribute("vo2", vo2);
		
		logger.info("vo2 : {}", vo2);
		
		return "member/selectOne";
	}
	
	@RequestMapping(value = "/mv_insert.do" , method = RequestMethod.GET)
	public String mv_insert() {
		logger.info("Welcome mv_insert!");
		
				
		return "member/insert";
	}
	
//	회원 가입 post
	@RequestMapping(value = "/mv_insertOK.do" , method = RequestMethod.POST)
	public String mv_insertOK(@ModelAttribute MemberVO vo, RedirectAttributes rttr, HttpServletRequest request) throws IllegalStateException, IOException {
		logger.info("Welcome mv_insertOK!");
		logger.info("vo : {}", vo);
		
		int result = 0;
		
		if (!vo.getMultipartFile().getOriginalFilename().equals("")) {
			vo.setMember_img(vo.getMultipartFile().getOriginalFilename());
			logger.info("file length : " + vo.getMultipartFile().getOriginalFilename().length());
			String realPath = sContext.getRealPath("resources/uploadimg");
			logger.info("realPath : {}", realPath);
			
			File f = new File(realPath+"\\"+vo.getMember_img()); 
			vo.getMultipartFile().transferTo(f); 
	
			
		//// create thumbnail image/////////
	         BufferedImage original_buffer_img = ImageIO.read(f); 
	         BufferedImage thumb_buffer_img = new BufferedImage(150, 150, BufferedImage.TYPE_3BYTE_BGR);
	         Graphics2D graphic = thumb_buffer_img.createGraphics();
	         graphic.drawImage(original_buffer_img, 0, 0, 150, 150, null); 

	         File thumb_file = new File(realPath + "/thumb_" + vo.getMember_img()); 
	         ImageIO.write(thumb_buffer_img, "jpg", thumb_file);
		
		} // end if
		
//		패스워드 암호화 코드
		String inputPass = vo.getMember_pw();
		String pass = passEncoder.encode(inputPass);
		vo.setMember_pw(pass);
		
		result = ms.insert(vo);
		logger.info("result : " + result);
		
//		메일 전송 코드
		mailsender.mailSendWithUserKey(vo.getMember_email(), vo.getMember_id(), request);
	
		if(result == 1) {
			rttr.addFlashAttribute("msg", "회원 가입 성공");
			return "redirect:index.do";
		} else {
			return "redirect:mv_insert.do";
		}
				
	}
	
//	메일 인증 마지막 단계(인증 상태 변경)
	@RequestMapping(value = "/key_alter", method = RequestMethod.GET)
	public String key_alterConfirm(@RequestParam("member_id") String member_id, @RequestParam("user_key") String key, RedirectAttributes rttr) {

		ms.alter_userKey(member_id, key); // 해당 id와 key가 일치하면 인증 상태(authstatus)를 1로 바꿔줌

		return "member/welcome";
	}
	

//	비밀번호 찾기 메일을 통해 접속하는 페이지
	@RequestMapping(value = "/mv_newpw.do" , method = RequestMethod.GET)
	public String mv_newpw(MemberVO vo, Model model) {
		logger.info("Welcome mv_newpw!");
		MemberVO vo2 = ms.selectOne(vo);
		model.addAttribute("vo2", vo2);
		logger.info("update vo2 : {}", vo2);
		
		return "member/newpw";
	}
	
	@RequestMapping(value ="/mv_newpwOK.do", method = RequestMethod.POST)
	public String mv_newpwOK(MemberVO vo, RedirectAttributes rttr) throws IllegalStateException, IOException {
		logger.info("Welcome mv_updateOK!");
		
		int result2 = 0;

//		새 비밀번호도 암호화 처리
		String inputPass = vo.getMember_pw();
		String pass = passEncoder.encode(inputPass);
		vo.setMember_pw(pass);
		
		result2 = ms.newpw(vo);
		logger.info("result2 : " + result2);
		
		if(result2 == 1) {
			rttr.addFlashAttribute("msg", "변경 성공");
			return "redirect:mv_login.do";
		} else {
			logger.info("result2 : {}", result2);
			return "redirect:mv_newpw.do";
		}
		
	}
	
	@RequestMapping(value = "/mv_update.do" , method = RequestMethod.GET)
	public String mv_update(MemberVO vo, Model model) {
		logger.info("Welcome mv_update!");
		MemberVO vo2 = ms.selectOne(vo);
		model.addAttribute("vo2", vo2);
		logger.info("update vo2 : {}", vo2);
		
		return "member/update";
	}
	
	@RequestMapping(value ="/mv_updateOK.do", method = RequestMethod.POST)
	public String mv_updateOK(MemberVO vo, RedirectAttributes rttr) throws IllegalStateException, IOException {
		logger.info("Welcome mv_updateOK!");
		
		logger.info("vo : " + vo);
		logger.info("filename : " + vo.getMultipartFile().getOriginalFilename());
		
		int result2 = 0;
		
		if (!vo.getMultipartFile().getOriginalFilename().equals("")) {
			vo.setMember_img(vo.getMultipartFile().getOriginalFilename());
			logger.info("file length : " + vo.getMultipartFile().getOriginalFilename().length());
			
			String realPath = sContext.getRealPath("resources/uploadimg");
			logger.info("realPath : {}", realPath);
			
			File f = new File(realPath+"\\"+vo.getMember_img()); 
			vo.getMultipartFile().transferTo(f); 
			
			
	         BufferedImage original_buffer_img = ImageIO.read(f); 
	         BufferedImage thumb_buffer_img = new BufferedImage(50, 50, BufferedImage.TYPE_3BYTE_BGR);
	         Graphics2D graphic = thumb_buffer_img.createGraphics();
	         graphic.drawImage(original_buffer_img, 0, 0, 50, 50, null); 

	         File thumb_file = new File(realPath + "/thumb_" + vo.getMember_img()); 
	         ImageIO.write(thumb_buffer_img, "jpg", thumb_file);
		
		} // end if~else
		
	
		String inputPass = vo.getMember_pw();
		String pass = passEncoder.encode(inputPass);
		vo.setMember_pw(pass);
		
		result2 = ms.update(vo);
		logger.info("result2 : " + result2);
		
		if(result2 == 1) {
			rttr.addFlashAttribute("msg", true);
			session.setAttribute("member_nickname", vo.getMember_nickname());
			return "redirect:mv_selectOne.do?member_id=" + vo.getMember_id();
		} else {
			logger.info("result2 : {}", result2);
			rttr.addFlashAttribute("msg", false);
			return "redirect:mv_selectOne.do?member_id=" + vo.getMember_id();
		}
		
	}
	
	@RequestMapping(value = "/mv_delete.do" , method = RequestMethod.GET)
	public String mv_delete() {
		logger.info("Welcome mv_delete!");
		logger.info((String)session.getAttribute("member_id"));
		return "member/delete";
	}
	
	@RequestMapping(value ="/mv_deleteOK.do", method = RequestMethod.GET)
	public String mv_deleteOK(MemberVO vo, RedirectAttributes rttr) {
		logger.info("Welcome mv_deleteOK!");
		logger.info("vo : " + vo);
		logger.info(vo.getMember_id());
		
		if(!(session.getAttribute("member_id").equals(vo.getMember_id()))) {
			ms.delete(vo);
			rttr.addFlashAttribute("msg", "회원 강퇴 성공");
			return "redirect:mv_selectAll.do";
		}
		
		int result3 = ms.delete(vo);
			
		if(result3 == 1) {
			session.invalidate();
			rttr.addFlashAttribute("msg", "회원 탈퇴 성공");
			return "redirect:index.do";
		} else {
			return "redirect:mv_selectOne.do?member_id=" + vo.getMember_id();
		}
		
	}
	
	// 로그인
	@RequestMapping(value = "/mv_login.do" , method = RequestMethod.GET)
	public String mv_login(RedirectAttributes rttr) {
		logger.info("Welcome mv_login!");
		rttr.addFlashAttribute("msg", true);
		return "member/login";
	}
	
	// 로그인 OK
	@RequestMapping(value = "/mv_loginOK.do", method = RequestMethod.POST)
	public String mv_loginOK(MemberVO vo, RedirectAttributes rttr, Model model) throws Exception {
	 logger.info("mv_loginOK");
	 
	 session = req.getSession();
	 
	 MemberVO login = ms.login(vo);
	 
//	 아예 존재하지 않는 아이디로 접근을 시도했을 때 1차적으로 걸러줌
	 if (login == null) {
		 session.setAttribute("member", null); 
		 rttr.addFlashAttribute("msg", "로그인 실패");
		 return "redirect:mv_login.do"; 
	 }
	 
	 boolean passMatch = passEncoder.matches(vo.getMember_pw(), login.getMember_pw());
	 
	 logger.info("mail auth : " + login.getAuthStatus());
	 
//	 입력한 패스워드가 암호화된 패스워드와 동일한지 체크하는 부분
	 if(login != null && passMatch) { 
		 
//		 메일 인증이 완료되지 않은 사용자 거르는 부분 (authstatus가 1이 아닌 사용자)
		 if(login.getAuthStatus() == 1) {
			 session.setAttribute("member_id", vo.getMember_id());
			 session.setAttribute("member_nickname", login.getMember_nickname());
			 session.setMaxInactiveInterval(300); // 300초동안만 세션 유지
			 session.setAttribute("member", login); // 세션에 가져오기 (모든 정보가 다 담겨있음)
			 rttr.addFlashAttribute("msg", "로그인 성공");
		 } else {
			 session.setAttribute("member", null); 
			 rttr.addFlashAttribute("msg", "메일 인증 요청");
			 return "redirect:mv_login.do"; 
		 }
		 	  
	 } else {
		 session.setAttribute("member", null); 
		 rttr.addFlashAttribute("msg", "로그인 실패");
		 return "redirect:mv_login.do"; 
	 }
	 	   
	 return "redirect:index.do";
	}
	
	// 로그아웃
	@RequestMapping(value = "/mv_logout.do" , method = RequestMethod.GET)
	public String mv_logout(RedirectAttributes rttr) {
		logger.info("Goodbye mv_logout!");
		
		session.invalidate();
		rttr.addFlashAttribute("msg", "로그아웃 성공");
		
		return "redirect:index.do";
	}
	
	
//	비동기 데이터 전송 기술인 Ajax를 활용하여 페이지를 유지한 채로 특정 데이터만을 받아온다
	@ResponseBody
	@RequestMapping(value = "/mv_idcheck.do", method = RequestMethod.POST)
	public int mv_idcheck() {
		logger.info("mv_idcheck.do");
		
		String member_id = req.getParameter("member_id");
		logger.info("checked member_id : " + member_id);
		MemberVO idcheck = ms.idcheck(member_id);
		
		int result = 0;
		
		if (idcheck != null) {
			result = 1;
			
		}
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/mv_mailcheck.do", method = RequestMethod.POST)
	public int mv_mailcheck() {
		logger.info("mv_mailcheck.do");
		
		String member_email = req.getParameter("member_email");
		logger.info("checked member_email : " + member_email);
		MemberVO mailcheck = ms.mailcheck(member_email);
		
		int result = 0;
		
		if (mailcheck != null) {
			result = 1;
			
		}
		
		return result;
	}
	
	@RequestMapping(value = "/mv_findid.do" , method = RequestMethod.GET)
	public String mv_findid(Model model) {
		logger.info("Welcome findid!");
		
		return "member/findid";
	}
	
//	ajax 이용, 등록된 이메일로 아이디를 찾아와서 보여줌
	@ResponseBody
	@RequestMapping(value = "/mv_findidOK.do", method = RequestMethod.POST)
	public String mv_findid() {
		logger.info("mv_findidOK.do");
		
		String member_email = req.getParameter("member_email");
		logger.info("findid : " + member_email);
		MemberVO findid = ms.findid(member_email);
		
		if (findid != null) {
			logger.info("find id : " + findid.getMember_id());
			return findid.getMember_id();
			
		} else {
			return "null";
		}
	}
	
//	비밀번호 찾기 GET (비밀번호 찾기 페이지로 이동)
	@RequestMapping(value = "/mv_findpw.do" , method = RequestMethod.GET)
	public String mv_findpw(Model model) {
		logger.info("Welcome findid!");
		
		return "member/findpw";
	}
	
//	비밀번호 찾기 POST (메일 발송)
	@RequestMapping(value = "/mv_findpwOK.do", method = RequestMethod.POST)
	public String mv_findpw(MemberVO vo, RedirectAttributes rttr, HttpServletRequest request) {
		logger.info("mv_findpwOK.do");
		
		MemberVO vo2 = ms.findpw(vo);
		logger.info("findpwOK : {}", vo2);
		
//		입력한 정보가 부정확할 시
		if (vo2 == null) {
			rttr.addFlashAttribute("msg", "재확인 요청");
			return "redirect:mv_findpw.do";
		}
//		메일 발송 코드
		mailsender.mailSendForFdpw(vo2.getMember_email(), vo2.getMember_id(), request);
		
		rttr.addFlashAttribute("msg", "메일 확인 요망");
		
		return "redirect:mv_login.do";
	}
	
}
