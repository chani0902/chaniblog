package com.test.myproject.reply01.model;

import java.util.List;

public interface Reply01DAO {
	
//	´ñ±Û Á¶È¸(selectAll)
	public List<Reply01VO> selectAll(int board01_num) throws Exception;
	
//	´ñ±Û Á¶È¸(selectOne)
	public Reply01VO selectOne(int reply01_num) throws Exception;
	
//	´ñ±Û ÀÛ¼º
	public int insert(Reply01VO vo);
	
//	´ñ±Û ¼öÁ¤
	public int update(Reply01VO vo);
	
//	´ñ±Û »èÁ¦
	public int delete(Reply01VO vo);

}
