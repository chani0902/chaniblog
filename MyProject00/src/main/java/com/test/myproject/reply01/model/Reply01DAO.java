package com.test.myproject.reply01.model;

import java.util.List;

public interface Reply01DAO {
	
//	��� ��ȸ(selectAll)
	public List<Reply01VO> selectAll(int board01_num) throws Exception;
	
//	��� ��ȸ(selectOne)
	public Reply01VO selectOne(int reply01_num) throws Exception;
	
//	��� �ۼ�
	public int insert(Reply01VO vo);
	
//	��� ����
	public int update(Reply01VO vo);
	
//	��� ����
	public int delete(Reply01VO vo);

}
