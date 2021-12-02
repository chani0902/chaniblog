package com.test.myproject.board01.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Board01DAOimpl implements Board01DAO {
	
	private static final Logger logger = LoggerFactory.getLogger(Board01DAOimpl.class);
	
	@Autowired
	SqlSession sqlSession; // @Autowired 까먹지 말고 꼭 넣자 ㅠㅠ
	
	public Board01DAOimpl() {
		logger.info("Board01DAOimpl()");
	}
	
	@Override
	public int insert(Board01VO vo) {
		logger.info("b01_insert()" + vo);
		int flag = sqlSession.insert("B01_INSERT", vo);
		
		logger.info("b01_insert flag : " + flag);
		return flag;
	}

	@Override
	public int update(Board01VO vo) {
		logger.info("b01_update()" + vo);
		int flag = sqlSession.update("B01_UPDATE", vo);
		
		logger.info("b01_update flag : " + flag);
		return flag;
	}

	@Override
	public int delete(Board01VO vo) {
		logger.info("b01_delete()" + vo);
		int flag = sqlSession.delete("B01_DELETE", vo);
		
		logger.info("b01_ delete flag : " + flag);
		return flag;
	}

	@Override
	public Board01VO selectOne(Board01VO vo) {
		logger.info("b01_selectOne()" + vo);
		Board01VO vo2 = sqlSession.selectOne("B01_SELECT_ONE", vo);
		
		logger.info("b01_selectOne vo2 : " + vo2);
		return vo2;
	}

	@Override
	public List<Board01VO> selectAll() throws Exception {
		logger.info("b01_selectAll()");
		List<Board01VO> list = sqlSession.selectList("B01_SELECT_ALL");
		
		return list;
	}

	@Override
	public List<Board01VO> searchList(String searchKey, String searchWord) {
		logger.info("searchList()");
		logger.info("searchKey : " + searchKey);
		logger.info("searchWord : " + searchWord);
		List<Board01VO> list = null;
		
		if (searchKey.equals("board01_title")) {
			list = sqlSession.selectList("B01_SEARCH_LIST_TITLE","%"+searchWord+"%");
//			제목 검색
		} else if (searchKey.equals("board01_content")) {
			list = sqlSession.selectList("B01_SEARCH_LIST_CONTENT", "%"+searchWord+"%");
//			내용 검색
		} else if (searchKey.equals("board01_writer")) {
			list = sqlSession.selectList("B01_SEARCH_LIST_WRITER", "%"+searchWord+"%");
//			내용 검색
		}
		
		return list;
	}

	@Override
	public List<Board01VO> listpage(Criteria cri) throws Exception {
		logger.info("listpage");
		return sqlSession.selectList("B01_LIST_PAGE", cri);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("B01_LIST_COUNT");
	}

	@Override
	public List<Board01VO> listSearch(SearchCriteria scri) throws Exception {
		logger.info("listsearch");
		return sqlSession.selectList("B01_LIST_SEARCH", scri);
	}

	@Override
	public int countSearch(SearchCriteria scri) throws Exception {
		logger.info("countSearch : " + scri);
		return sqlSession.selectOne("B01_COUNT_SEARCH", scri);
	}

	@Override
	public int viewcount(int board01_num) {
		logger.info("viewcnt at" + board01_num);
		return sqlSession.update("B01_VIEWCOUNT", board01_num);
	}

	@Override
	public List<Board01VO> poppost(SearchCriteria scri) throws Exception {
		logger.info("pop_post");
		return sqlSession.selectList("B01_POP_POST", scri);
	}

	@Override
	public int reply_cnt(int board01_num) {
		logger.info("reply_cnt at :" + board01_num);
		return sqlSession.update("B01_REPLYCNT", board01_num);
	}

	@Override
	public Board01VO rp_mine(int board01_num) {
		logger.info("rp_mine : " + board01_num);
		return sqlSession.selectOne("RP_MINE", board01_num);
	}

}
