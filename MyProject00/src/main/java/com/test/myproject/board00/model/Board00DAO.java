package com.test.myproject.board00.model;

import java.util.List;

public interface Board00DAO {
	
	public int insert(Board00VO vo);

	public int update(Board00VO vo);
	
	public int delete(Board00VO vo);
	
	public Board00VO selectOne(Board00VO vo);
	
	public List<Board00VO> selectAll() throws Exception;
	
	public List<Board00VO> searchList(String searchKey, String searchWord);

//	��� + ����¡
	public List<Board00VO> listpage(Criteria cri) throws Exception;
	
//	�Խù� �� ����
	public int listCount() throws Exception;
	
//	��� + ����¡ + �˻�
	public List<Board00VO> listSearch(SearchCriteria scri) throws Exception;
	
//	�˻� ��� ����
	public int countSearch(SearchCriteria scri) throws Exception;
	
//	��ȸ�� ����
	public int viewcount(int board00_num);
	
//  ȸ�� ���� �ۼ� �� ���
	public List<Board00VO> my_notice(String writercheck);
	

}
