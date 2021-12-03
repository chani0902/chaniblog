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
	
//	�н����� ��ȣȭ
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
	
//	ȸ�� ���� post
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
		
//		�н����� ��ȣȭ �ڵ�
		String inputPass = vo.getMember_pw();
		String pass = passEncoder.encode(inputPass);
		vo.setMember_pw(pass);
		
		result = ms.insert(vo);
		logger.info("result : " + result);
		
//		���� ���� �ڵ�
		mailsender.mailSendWithUserKey(vo.getMember_email(), vo.getMember_id(), request);
	
		if(result == 1) {
			rttr.addFlashAttribute("msg", "ȸ�� ���� ����");
			return "redirect:index.do";
		} else {
			return "redirect:mv_insert.do";
		}
				
	}
	
//	���� ���� ������ �ܰ�(���� ���� ����)
	@RequestMapping(value = "/key_alter", method = RequestMethod.GET)
	public String key_alterConfirm(@RequestParam("member_id") String member_id, @RequestParam("user_key") String key, RedirectAttributes rttr) {

		ms.alter_userKey(member_id, key); // �ش� id�� key�� ��ġ�ϸ� ���� ����(authstatus)�� 1�� �ٲ���

		return "member/welcome";
	}
	

//	��й�ȣ ã�� ������ ���� �����ϴ� ������
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

//		�� ��й�ȣ�� ��ȣȭ ó��
		String inputPass = vo.getMember_pw();
		String pass = passEncoder.encode(inputPass);
		vo.setMember_pw(pass);
		
		result2 = ms.newpw(vo);
		logger.info("result2 : " + result2);
		
		if(result2 == 1) {
			rttr.addFlashAttribute("msg", "���� ����");
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
			rttr.addFlashAttribute("msg", "ȸ�� ���� ����");
			return "redirect:mv_selectAll.do";
		}
		
		int result3 = ms.delete(vo);
			
		if(result3 == 1) {
			session.invalidate();
			rttr.addFlashAttribute("msg", "ȸ�� Ż�� ����");
			return "redirect:index.do";
		} else {
			return "redirect:mv_selectOne.do?member_id=" + vo.getMember_id();
		}
		
	}
	
	// �α���
	@RequestMapping(value = "/mv_login.do" , method = RequestMethod.GET)
	public String mv_login(RedirectAttributes rttr) {
		logger.info("Welcome mv_login!");
		rttr.addFlashAttribute("msg", true);
		return "member/login";
	}
	
	// �α��� OK
	@RequestMapping(value = "/mv_loginOK.do", method = RequestMethod.POST)
	public String mv_loginOK(MemberVO vo, RedirectAttributes rttr, Model model) throws Exception {
	 logger.info("mv_loginOK");
	 
	 session = req.getSession();
	 
	 MemberVO login = ms.login(vo);
	 
//	 �ƿ� �������� �ʴ� ���̵�� ������ �õ����� �� 1�������� �ɷ���
	 if (login == null) {
		 session.setAttribute("member", null); 
		 rttr.addFlashAttribute("msg", "�α��� ����");
		 return "redirect:mv_login.do"; 
	 }
	 
	 boolean passMatch = passEncoder.matches(vo.getMember_pw(), login.getMember_pw());
	 
	 logger.info("mail auth : " + login.getAuthStatus());
	 
//	 �Է��� �н����尡 ��ȣȭ�� �н������ �������� üũ�ϴ� �κ�
	 if(login != null && passMatch) { 
		 
//		 ���� ������ �Ϸ���� ���� ����� �Ÿ��� �κ� (authstatus�� 1�� �ƴ� �����)
		 if(login.getAuthStatus() == 1) {
			 session.setAttribute("member_id", vo.getMember_id());
			 session.setAttribute("member_nickname", login.getMember_nickname());
			 session.setMaxInactiveInterval(300); // 300�ʵ��ȸ� ���� ����
			 session.setAttribute("member", login); // ���ǿ� �������� (��� ������ �� �������)
			 rttr.addFlashAttribute("msg", "�α��� ����");
		 } else {
			 session.setAttribute("member", null); 
			 rttr.addFlashAttribute("msg", "���� ���� ��û");
			 return "redirect:mv_login.do"; 
		 }
		 	  
	 } else {
		 session.setAttribute("member", null); 
		 rttr.addFlashAttribute("msg", "�α��� ����");
		 return "redirect:mv_login.do"; 
	 }
	 	   
	 return "redirect:index.do";
	}
	
	// �α׾ƿ�
	@RequestMapping(value = "/mv_logout.do" , method = RequestMethod.GET)
	public String mv_logout(RedirectAttributes rttr) {
		logger.info("Goodbye mv_logout!");
		
		session.invalidate();
		rttr.addFlashAttribute("msg", "�α׾ƿ� ����");
		
		return "redirect:index.do";
	}
	
	
//	�񵿱� ������ ���� ����� Ajax�� Ȱ���Ͽ� �������� ������ ä�� Ư�� �����͸��� �޾ƿ´�
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
	
//	ajax �̿�, ��ϵ� �̸��Ϸ� ���̵� ã�ƿͼ� ������
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
	
//	��й�ȣ ã�� GET (��й�ȣ ã�� �������� �̵�)
	@RequestMapping(value = "/mv_findpw.do" , method = RequestMethod.GET)
	public String mv_findpw(Model model) {
		logger.info("Welcome findid!");
		
		return "member/findpw";
	}
	
//	��й�ȣ ã�� POST (���� �߼�)
	@RequestMapping(value = "/mv_findpwOK.do", method = RequestMethod.POST)
	public String mv_findpw(MemberVO vo, RedirectAttributes rttr, HttpServletRequest request) {
		logger.info("mv_findpwOK.do");
		
		MemberVO vo2 = ms.findpw(vo);
		logger.info("findpwOK : {}", vo2);
		
//		�Է��� ������ ����Ȯ�� ��
		if (vo2 == null) {
			rttr.addFlashAttribute("msg", "��Ȯ�� ��û");
			return "redirect:mv_findpw.do";
		}
//		���� �߼� �ڵ�
		mailsender.mailSendForFdpw(vo2.getMember_email(), vo2.getMember_id(), request);
		
		rttr.addFlashAttribute("msg", "���� Ȯ�� ���");
		
		return "redirect:mv_login.do";
	}
	
}
