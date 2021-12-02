package com.test.myproject.member.model;

import java.util.List;

public interface MemberDAO {
	
	public int insert(MemberVO vo);

	public int update(MemberVO vo);
	
	public int delete(MemberVO vo);
	
	public MemberVO selectOne(MemberVO vo);
	
	public List<MemberVO> selectAll();
	
	public List<MemberVO> searchList(String searchKey, String searchWord);
	
	public MemberVO login(MemberVO vo) throws Exception;
	
	public MemberVO idcheck(String member_id);

	public MemberVO mailcheck(String member_email);
	
	public MemberVO findid(String member_email);
	
	public MemberVO findpw(MemberVO vo);
	
	public int newpw(MemberVO vo);
	
	public int GetKey(String member_id, String key);
	
	public int alter_userKey(String member_id, String key);

}
