package com.test.myproject.member.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOimpl implements MemberDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOimpl.class);
	
	@Autowired
	SqlSession sqlSession; // ibatis 泥섎━ �빐以섏빞�븿..! pom.xml�뿉 �벑濡�

	@Autowired(required = false)
	private MemberDAO dao;
	
	@Autowired
	SqlSessionTemplate userSqlSession;
	
	public MemberDAOimpl() {
		logger.info("MemberDAOimpl()"); // �옒 �쑉�뒗吏� �솗�씤
	}
	
	@Override
	public int insert(MemberVO vo) {
		logger.info("mv_insert()" + vo);
		int flag = sqlSession.insert("MV_INSERT", vo);
		
		logger.info("mv_insert flag : " + flag);
		return flag;
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("mv_update()" + vo);
		int flag = sqlSession.update("MV_UPDATE", vo);
		
		logger.info("mv_update flag : " + flag);
		return flag;
	}

	@Override
	public int delete(MemberVO vo) {
		logger.info("mv_delete()" + vo);
		int flag = sqlSession.delete("MV_DELETE", vo);
		
		logger.info("mv_ delete flag : " + flag);
		return flag;
	}

	@Override
	public MemberVO selectOne(MemberVO vo) {
		logger.info("mv_selectOne()" + vo);
		MemberVO vo2 = sqlSession.selectOne("MV_SELECT_ONE", vo);
		
		logger.info("mv_selectOne vo2 : " + vo2);
		return vo2;
	}

	@Override
	public List<MemberVO> selectAll() {
		logger.info("mv_selectAll()");
		List<MemberVO> list = sqlSession.selectList("MV_SELECT_ALL");
		
		logger.info("mv_selectAll list size() : " + list.size());
		return list;
	}

	@Override
	public List<MemberVO> searchList(String searchKey, String searchWord) {
		logger.info("searchList()");
		logger.info("searchKey : " + searchKey);
		logger.info("searchWord : " + searchWord);
		List<MemberVO> list = null;
		
		if (searchKey.equals("member_nickname")) {
			list = sqlSession.selectList("MV_SEARCH_LIST_NICKNAME","%"+searchWord+"%");
//			�땳�꽕�엫 寃��깋�� �늻援щ굹 �븷 �닔 �엳寃�..!
		} else if (searchKey.equals("member_id")) {
			list = sqlSession.selectList("MV_SEARCH_LIST_ID", "%"+searchWord+"%");
//			id 寃��깋�� 愿�由ъ옄 沅뚰븳�쑝濡�..??
		}

		return list;
	}

	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		logger.info("login() : " + vo);
//		MemberVO vo2 = sqlSession.selectOne("MV_LOGIN", vo);
		MemberVO vo2 = sqlSession.selectOne("loginBcrypt", vo); // 패스워드 암호화 적용 로그인
		
		logger.info("loginBcrypt : " + vo2);
		return vo2;
	}

	@Override
	public MemberVO idcheck(String member_id) {
		logger.info("idcheck() : " + member_id);
		MemberVO vo2 = sqlSession.selectOne("MV_ID_CHECK", member_id);
		
		logger.info("mv_idcheck vo2 : " + vo2);
		return vo2;
	}

	@Override
	public MemberVO mailcheck(String member_email) {
		logger.info("mailcheck() : " + member_email);
		MemberVO vo2 = sqlSession.selectOne("MV_MAIL_CHECK", member_email);
		
		logger.info("mv_mailcheck vo2 : " + vo2);
		return vo2;
	}
	
	@Override
	public MemberVO findid(String member_email) {
		logger.info("mailcheck() : " + member_email);
		MemberVO vo2 = sqlSession.selectOne("MV_FIND_ID", member_email);
		
		logger.info("mv_findid vo2 : " + vo2);
		return vo2;
	}

	@Override
	public int GetKey(String member_id, String key) {
		
		int result = 0;
		dao = userSqlSession.getMapper(MemberDAO.class);
		
		result = dao.GetKey(member_id, key);
		logger.info("GetKey result : " + result);
		
		return result;
	}

	@Override
	public int alter_userKey(String member_id, String key) {
		int result = 0;
		
		dao = userSqlSession.getMapper(MemberDAO.class);
		
		result = dao.alter_userKey(member_id, key);		
		
		return result;
	}

	@Override
	public MemberVO findpw(MemberVO vo) {
		logger.info("findpw() : " + vo);
		
		MemberVO vo2 = sqlSession.selectOne("MV_FIND_PW", vo); // 아이디와 이메일 둘다 일치하면 정보 불러와줌
		
		logger.info("findpw : " + vo2);
		return vo2;
	}

	@Override
	public int newpw(MemberVO vo) {
		logger.info("mv_newpw()" + vo);
		int flag = sqlSession.update("MV_NEWPW", vo);
		
		logger.info("mv_newpw flag : " + flag);
		return flag;
	}

	@Override
	public int POINT_POST(String member_id, String point) {
		logger.info("POINT_POST()");
		int result = 0;
		dao = userSqlSession.getMapper(MemberDAO.class);
		result = dao.POINT_POST(member_id, point);
		
		return result;
	}

	@Override
	public List<MemberVO> hotuser() {
		logger.info("mv_selectAll()");
		List<MemberVO> list2 = sqlSession.selectList("MV_HOT_USER");
		
		logger.info("mv_selectAll list size() : " + list2.size());
		return list2;
	}


	

	

}
