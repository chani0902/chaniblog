package com.test.myproject.board01.model;

import java.util.List;

public interface Board01DAO {
	
	public int insert(Board01VO vo);

	public int update(Board01VO vo);
	
	public int delete(Board01VO vo);
	
	public Board01VO selectOne(Board01VO vo);
	
	public List<Board01VO> selectAll() throws Exception;
	
	public List<Board01VO> searchList(String searchKey, String searchWord);

//	목록 + 페이징
	public List<Board01VO> listpage(Criteria cri) throws Exception;
	
//	인기 게시물
	public List<Board01VO> poppost(SearchCriteria scri) throws Exception;
	
//	게시물 총 갯수
	public int listCount() throws Exception;
	
//	목록 + 페이징 + 검색
	public List<Board01VO> listSearch(SearchCriteria scri) throws Exception;
	
//	검색 결과 갯수
	public int countSearch(SearchCriteria scri) throws Exception;
	
//	조회수 증가
	public int viewcount(int board01_num);
	
//	댓글 갯수 체크
	public int reply_cnt(int board01_num);
	
//	댓글 다는 게시글이 본인글인지 체크
	public Board01VO rp_mine(int board01_num);
	
}
