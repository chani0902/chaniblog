package com.test.myproject.reply01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.reply01.model.Reply01DAO;
import com.test.myproject.reply01.model.Reply01VO;

@Service
public class Reply01Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Reply01Service.class);
	
	@Autowired
	Reply01DAO dao;
	
	public Reply01Service() {
		logger.info("Reply01Service()");
	}
	
	public List<Reply01VO> selectAll(int board01_num) throws Exception {
		return dao.selectAll(board01_num);
	}
	
	public Reply01VO selectOne(int reply01_num) throws Exception {
		return dao.selectOne(reply01_num);
	}
	
	public int insert(Reply01VO vo) {
		return dao.insert(vo);
	}
	
	public int update(Reply01VO vo) {
		return dao.update(vo);
	}
	
	public int delete(Reply01VO vo) {
		return dao.delete(vo);
	}
	
}
