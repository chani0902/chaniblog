package com.test.myproject.member.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.member.model.MemberDAO;
import com.test.myproject.member.model.MemberVO;

@Service
public class MemberService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	MemberDAO dao;
	
	public MemberService() {
		logger.info("MemberService()");
	}
	
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}

	public MemberVO selectOne(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.selectOne(vo);
	}

	public List<MemberVO> searchList(String searchKey, String searchWord) {
		// TODO Auto-generated method stub
		return dao.searchList(searchKey, searchWord);
	}

	public int insert(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	public int update(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	public int delete(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.delete(vo);
	}
	
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	public MemberVO idcheck(String member_id) {
		return dao.idcheck(member_id);
	}
	
	public MemberVO mailcheck(String member_email) {
		return dao.mailcheck(member_email);
	}
	
	public MemberVO findid(String member_email) {
		return dao.findid(member_email);
	}
	
	public MemberVO findpw(MemberVO vo) {
		return dao.findpw(vo);
	}
	
	public int newpw(MemberVO vo) {
		return dao.newpw(vo);
	}
	
	public int alter_userKey(String member_id, String key) {
		return dao.alter_userKey(member_id, key);
	}
	
	public int POINT_POST(String member_id, String point) {
		return dao.POINT_POST(member_id, point);
	}
	
	public List<MemberVO> hotuser() {
		return dao.hotuser();
	}

	
}
