package com.test.myproject.board00.model;

import java.util.List;

public interface Board00DAO {
	
	public int insert(Board00VO vo);

	public int update(Board00VO vo);
	
	public int delete(Board00VO vo);
	
	public Board00VO selectOne(Board00VO vo);
	
	public List<Board00VO> selectAll() throws Exception;
	
	public List<Board00VO> searchList(String searchKey, String searchWord);

//	목록 + 페이징
	public List<Board00VO> listpage(Criteria cri) throws Exception;
	
//	게시물 총 갯수
	public int listCount() throws Exception;
	
//	목록 + 페이징 + 검색
	public List<Board00VO> listSearch(SearchCriteria scri) throws Exception;
	
//	검색 결과 갯수
	public int countSearch(SearchCriteria scri) throws Exception;
	
//	조회수 증가
	public int viewcount(int board00_num);
	
//  회원 본인 작성 글 목록
	public List<Board00VO> my_notice(String writercheck);
	

}
