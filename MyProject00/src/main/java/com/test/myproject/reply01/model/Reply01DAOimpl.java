package com.test.myproject.reply01.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Reply01DAOimpl implements Reply01DAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Reply01DAOimpl.class);
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<Reply01VO> selectAll(int board01_num) throws Exception {
		logger.info("rp_selectAll()");
		List<Reply01VO> list = sqlSession.selectList("R01_SELECT_ALL", board01_num);
		
		logger.info("rp_selectAll list size() : " + list.size());
		return list;
	}
	
	
	@Override
	public Reply01VO selectOne(int reply01_num) throws Exception {
		logger.info("r01_selectOne()" + reply01_num);
		Reply01VO vo2 = sqlSession.selectOne("R01_SELECT_ONE", reply01_num);
		
		logger.info("b01_selectOne vo2 : " + vo2);
		return vo2;
	}

	@Override
	public int insert(Reply01VO vo) {
		logger.info("r01_insert()" + vo);
		int flag = sqlSession.insert("R01_INSERT", vo);
		
		logger.info("r01_insert flag : " + flag);
		return flag;
	}

	@Override
	public int update(Reply01VO vo) {
		logger.info("r01_update()" + vo);
		int flag = sqlSession.insert("R01_UPDATE", vo);
		
		logger.info("r01_update flag : " + flag);
		return flag;
	}

	@Override
	public int delete(Reply01VO vo) {
		logger.info("r01_delete()" + vo);
		int flag = sqlSession.insert("R01_DELETE", vo);
		
		logger.info("r01_delete flag : " + flag);
		return flag;
	}
	
	@Override
	public List<Reply01VO> my_comment(String writercheck) {
		logger.info("my_comment() : " + writercheck);
		List<Reply01VO> list = sqlSession.selectList("MY_COMMENT", writercheck);
		
		return list;
	}

}
