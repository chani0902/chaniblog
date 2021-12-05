package com.test.myproject.board00.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.board00.model.Board00DAO;
import com.test.myproject.board00.model.Board00VO;
import com.test.myproject.board00.model.SearchCriteria;

@Service
public class Board00Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Board00Service.class);
	
	@Autowired
	Board00DAO dao;
 	
	public Board00Service() {
		logger.info("Board00Service()");
	}
	
	public List<Board00VO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	
	public List<Board00VO> listSearch(SearchCriteria scri) throws Exception {
		return dao.listSearch(scri);
	}
	
	public int listCount() throws Exception {
		return dao.listCount();
	}
	
	public int countSearch(SearchCriteria scri) throws Exception {
		return dao.countSearch(scri);
	}

	public Board00VO selectOne(Board00VO vo) {
		// TODO Auto-generated method stub
		return dao.selectOne(vo);
	}

	public List<Board00VO> searchList(String searchKey, String searchWord) {
		// TODO Auto-generated method stub
		return dao.searchList(searchKey, searchWord);
	}

	public int insert(Board00VO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	public int update(Board00VO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	public int delete(Board00VO vo) {
		// TODO Auto-generated method stub
		return dao.delete(vo);
	}
	
	public int viewcount(int board00_num) {
		// TODO Auto-generated method stub
		return dao.viewcount(board00_num);
	}
	
	public List<Board00VO> my_notice(String writercheck) {
		// TODO Auto-generated method stub
		return dao.my_notice(writercheck);
	}
	
} // end class
