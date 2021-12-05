package com.test.myproject.board01.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.myproject.board01.model.Board01DAO;
import com.test.myproject.board01.model.Board01VO;
import com.test.myproject.board01.model.Criteria;
import com.test.myproject.board01.model.SearchCriteria;
import com.test.myproject.reply01.model.Reply01VO;

@Service
public class Board01Service {
	
	private static final Logger logger = LoggerFactory.getLogger(Board01Service.class);
	
	@Autowired
	Board01DAO dao;
 	
	public Board01Service() {
		logger.info("Board01Service()");
	}
	
	public List<Board01VO> selectAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	public List<Board01VO> listpage(Criteria cri) throws Exception {
		return dao.listpage(cri);
	}
	
	public List<Board01VO> poppost(SearchCriteria scri) throws Exception {
		return dao.poppost(scri);
	}
	
	public List<Board01VO> listSearch(SearchCriteria scri) throws Exception {
		return dao.listSearch(scri);
	}
	
	public int listCount() throws Exception {
		return dao.listCount();
	}
	
	public int countSearch(SearchCriteria scri) throws Exception {
		return dao.countSearch(scri);
	}

	public Board01VO selectOne(Board01VO vo) {
		// TODO Auto-generated method stub
		return dao.selectOne(vo);
	}

	public List<Board01VO> searchList(String searchKey, String searchWord) {
		// TODO Auto-generated method stub
		return dao.searchList(searchKey, searchWord);
	}

	public int insert(Board01VO vo) {
		// TODO Auto-generated method stub
		return dao.insert(vo);
	}

	public int update(Board01VO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}

	public int delete(Board01VO vo) {
		// TODO Auto-generated method stub
		return dao.delete(vo);
	}
	
	public int viewcount(int board01_num) {
		// TODO Auto-generated method stub
		return dao.viewcount(board01_num);
	}
	
	public int reply_cnt(int board01_num) {
		// TODO Auto-generated method stub
		return dao.reply_cnt(board01_num);
	}
	
	public Board01VO rp_mine(int board01_num) {
		
		return dao.rp_mine(board01_num);
	}
	
	public List<Board01VO> my_post(String writercheck) {
		// TODO Auto-generated method stub
		return dao.my_post(writercheck);
	}
	
} // end class
