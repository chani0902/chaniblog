package com.test.myproject.board01.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class Board01VO implements Serializable {
	
	private int board01_num;
	private String board01_title;
	private String board01_content;
	private String board01_writer;
	private int board01_viewcnt;
	private String writercheck;
	private int reply_cnt;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date board01_regdate;
	public int getBoard01_num() {
		return board01_num;
	}
	public void setBoard01_num(int board01_num) {
		this.board01_num = board01_num;
	}
	public String getBoard01_title() {
		return board01_title;
	}
	public void setBoard01_title(String board01_title) {
		this.board01_title = board01_title;
	}
	public String getBoard01_content() {
		return board01_content;
	}
	public void setBoard01_content(String board01_content) {
		this.board01_content = board01_content;
	}
	public String getBoard01_writer() {
		return board01_writer;
	}
	public void setBoard01_writer(String board01_writer) {
		this.board01_writer = board01_writer;
	}
	public int getBoard01_viewcnt() {
		return board01_viewcnt;
	}
	public void setBoard01_viewcnt(int board01_viewcnt) {
		this.board01_viewcnt = board01_viewcnt;
	}
	public String getWritercheck() {
		return writercheck;
	}
	public void setWritercheck(String writercheck) {
		this.writercheck = writercheck;
	}
	public int getReply_cnt() {
		return reply_cnt;
	}
	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	public Date getBoard01_regdate() {
		return board01_regdate;
	}
	public void setBoard01_regdate(Date board01_regdate) {
		this.board01_regdate = board01_regdate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board01_content == null) ? 0 : board01_content.hashCode());
		result = prime * result + board01_num;
		result = prime * result + ((board01_regdate == null) ? 0 : board01_regdate.hashCode());
		result = prime * result + ((board01_title == null) ? 0 : board01_title.hashCode());
		result = prime * result + board01_viewcnt;
		result = prime * result + ((board01_writer == null) ? 0 : board01_writer.hashCode());
		result = prime * result + reply_cnt;
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
		Board01VO other = (Board01VO) obj;
		if (board01_content == null) {
			if (other.board01_content != null)
				return false;
		} else if (!board01_content.equals(other.board01_content))
			return false;
		if (board01_num != other.board01_num)
			return false;
		if (board01_regdate == null) {
			if (other.board01_regdate != null)
				return false;
		} else if (!board01_regdate.equals(other.board01_regdate))
			return false;
		if (board01_title == null) {
			if (other.board01_title != null)
				return false;
		} else if (!board01_title.equals(other.board01_title))
			return false;
		if (board01_viewcnt != other.board01_viewcnt)
			return false;
		if (board01_writer == null) {
			if (other.board01_writer != null)
				return false;
		} else if (!board01_writer.equals(other.board01_writer))
			return false;
		if (reply_cnt != other.reply_cnt)
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
		return "Board01VO [board01_num=" + board01_num + ", board01_title=" + board01_title + ", board01_content="
				+ board01_content + ", board01_writer=" + board01_writer + ", board01_viewcnt=" + board01_viewcnt
				+ ", writercheck=" + writercheck + ", reply_cnt=" + reply_cnt + ", board01_regdate=" + board01_regdate
				+ "]";
	}
	
}
