package com.test.myproject.board01.model;

import java.util.List;

public interface Board01DAO {
	
	public int insert(Board01VO vo);

	public int update(Board01VO vo);
	
	public int delete(Board01VO vo);
	
	public Board01VO selectOne(Board01VO vo);
	
	public List<Board01VO> selectAll() throws Exception;
	
	public List<Board01VO> searchList(String searchKey, String searchWord);

//	��� + ����¡
	public List<Board01VO> listpage(Criteria cri) throws Exception;
	
//	�α� �Խù�
	public List<Board01VO> poppost(SearchCriteria scri) throws Exception;
	
//	�Խù� �� ����
	public int listCount() throws Exception;
	
//	��� + ����¡ + �˻�
	public List<Board01VO> listSearch(SearchCriteria scri) throws Exception;
	
//	�˻� ��� ����
	public int countSearch(SearchCriteria scri) throws Exception;
	
//	��ȸ�� ����
	public int viewcount(int board01_num);
	
//	��� ���� üũ
	public int reply_cnt(int board01_num);
	
//	��� �ٴ� �Խñ��� ���α����� üũ
	public Board01VO rp_mine(int board01_num);
	
}
