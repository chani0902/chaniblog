package com.test.myproject.board00.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Board00DAOimpl implements Board00DAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Board00DAOimpl.class);
	
	@Autowired
	SqlSession sqlSession; // @Autowired 까먹지 말고 꼭 넣자 ㅠㅠ
	
	public Board00DAOimpl() {
		logger.info("Board00DAOimpl()");
	}
	
	@Override
	public int insert(Board00VO vo) {
		logger.info("b00_insert()" + vo);
		int flag = sqlSession.insert("B00_INSERT", vo);
		
		logger.info("b00_insert flag : " + flag);
		return flag;
	}

	@Override
	public int update(Board00VO vo) {
		logger.info("b00_update()" + vo);
		int flag = sqlSession.update("B00_UPDATE", vo);
		
		logger.info("b00_update flag : " + flag);
		return flag;
	}

	@Override
	public int delete(Board00VO vo) {
		logger.info("b00_delete()" + vo);
		int flag = sqlSession.delete("B00_DELETE", vo);
		
		logger.info("b00_ delete flag : " + flag);
		return flag;
	}

	@Override
	public Board00VO selectOne(Board00VO vo) {
		logger.info("b00_selectOne()" + vo);
		Board00VO vo2 = sqlSession.selectOne("B00_SELECT_ONE", vo);
		
		logger.info("b00_selectOne vo2 : " + vo2);
		return vo2;
	}

	@Override
	public List<Board00VO> selectAll() throws Exception {
		logger.info("b00_selectAll()");
		List<Board00VO> list = sqlSession.selectList("B00_SELECT_ALL");
		
		return list;
	}

	@Override
	public List<Board00VO> searchList(String searchKey, String searchWord) {
		logger.info("searchList()");
		logger.info("searchKey : " + searchKey);
		logger.info("searchWord : " + searchWord);
		List<Board00VO> list = null;
		
		if (searchKey.equals("board00_title")) {
			list = sqlSession.selectList("B00_SEARCH_LIST_TITLE","%"+searchWord+"%");
//			제목 검색
		} else if (searchKey.equals("board00_content")) {
			list = sqlSession.selectList("B00_SEARCH_LIST_CONTENT", "%"+searchWord+"%");
//			내용 검색
		} 
		
		return list;
	}

	@Override
	public List<Board00VO> listpage(Criteria cri) throws Exception {
		logger.info("listpage");
		return sqlSession.selectList("B00_LIST_PAGE", cri);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("B00_LIST_COUNT");
	}

	@Override
	public List<Board00VO> listSearch(SearchCriteria scri) throws Exception {
		logger.info("listsearch");
		return sqlSession.selectList("B00_LIST_SEARCH", scri);
	}

	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		logger.info("countSearch : " + scri);
		return sqlSession.selectOne("B00_COUNT_SEARCH", scri);
	}

	@Override
	public int viewcount(int board00_num) {
		logger.info("viewcnt at" + board00_num);
		return sqlSession.update("B00_VIEWCOUNT", board00_num);
	}

	@Override
	public List<Board00VO> my_notice(String writercheck) {
		logger.info("my_notice() : " + writercheck);
		List<Board00VO> list = sqlSession.selectList("MY_NOTICE", writercheck);
		
		return list;
	}

	

}
