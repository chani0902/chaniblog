package com.test.myproject.reply01.model;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Reply01VO {
//	CREATE TABLE REPLY01 (
//			BOARD01_NUM NUMBER NOT NULL, 
//			REPLY01_NUM NUMBER NOT NULL, 
//			REPLY01_CONTENT VARCHAR2(2000) NOT NULL,
//			REPLY01_WRITER VARCHAR2(50) NOT NULL,
//			REPLY01_REGDATE TIMESTAMP DEFAULT NOW() NOT NULL, 
//			PRIMARY KEY(BOARD01_NUM, REPLY01_NUM)
//			);
	private int board01_num;
	private int reply01_num;
	private String reply01_content;
	private String reply01_writer;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date reply01_regdate;
	private String writercheck;
	public int getBoard01_num() {
		return board01_num;
	}
	public void setBoard01_num(int board01_num) {
		this.board01_num = board01_num;
	}
	public int getReply01_num() {
		return reply01_num;
	}
	public void setReply01_num(int reply01_num) {
		this.reply01_num = reply01_num;
	}
	public String getReply01_content() {
		return reply01_content;
	}
	public void setReply01_content(String reply01_content) {
		this.reply01_content = reply01_content;
	}
	public String getReply01_writer() {
		return reply01_writer;
	}
	public void setReply01_writer(String reply01_writer) {
		this.reply01_writer = reply01_writer;
	}
	public Date getReply01_regdate() {
		return reply01_regdate;
	}
	public void setReply01_regdate(Date reply01_regdate) {
		this.reply01_regdate = reply01_regdate;
	}
	public String getWritercheck() {
		return writercheck;
	}
	public void setWritercheck(String writercheck) {
		this.writercheck = writercheck;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + board01_num;
		result = prime * result + ((reply01_content == null) ? 0 : reply01_content.hashCode());
		result = prime * result + reply01_num;
		result = prime * result + ((reply01_regdate == null) ? 0 : reply01_regdate.hashCode());
		result = prime * result + ((reply01_writer == null) ? 0 : reply01_writer.hashCode());
		result = prime * result + ((writercheck == null) ? 0 : writercheck.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reply01VO other = (Reply01VO) obj;
		if (board01_num != other.board01_num)
			return false;
		if (reply01_content == null) {
			if (other.reply01_content != null)
				return false;
		} else if (!reply01_content.equals(other.reply01_content))
			return false;
		if (reply01_num != other.reply01_num)
			return false;
		if (reply01_regdate == null) {
			if (other.reply01_regdate != null)
				return false;
		} else if (!reply01_regdate.equals(other.reply01_regdate))
			return false;
		if (reply01_writer == null) {
			if (other.reply01_writer != null)
				return false;
		} else if (!reply01_writer.equals(other.reply01_writer))
			return false;
		if (writercheck == null) {
			if (other.writercheck != null)
				return false;
		} else if (!writercheck.equals(other.writercheck))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reply01VO [board01_num=" + board01_num + ", reply01_num=" + reply01_num + ", reply01_content="
				+ reply01_content + ", reply01_writer=" + reply01_writer + ", reply01_regdate=" + reply01_regdate
				+ ", writercheck=" + writercheck + "]";
	}
	
	
}
