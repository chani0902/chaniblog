package com.test.myproject.board00.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;


public class Board00VO implements Serializable {
	
	private int board00_num;
	private String board00_title;
	private String board00_content;
	private String board00_writer;
	private int board00_viewcnt;
	private String writercheck;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date board00_regdate;
	public int getBoard00_num() {
		return board00_num;
	}
	public void setBoard00_num(int board00_num) {
		this.board00_num = board00_num;
	}
	public String getBoard00_title() {
		return board00_title;
	}
	public void setBoard00_title(String board00_title) {
		this.board00_title = board00_title;
	}
	public String getBoard00_content() {
		return board00_content;
	}
	public void setBoard00_content(String board00_content) {
		this.board00_content = board00_content;
	}
	public String getBoard00_writer() {
		return board00_writer;
	}
	public void setBoard00_writer(String board00_writer) {
		this.board00_writer = board00_writer;
	}
	public int getBoard00_viewcnt() {
		return board00_viewcnt;
	}
	public void setBoard00_viewcnt(int board00_viewcnt) {
		this.board00_viewcnt = board00_viewcnt;
	}
	public String getWritercheck() {
		return writercheck;
	}
	public void setWritercheck(String writercheck) {
		this.writercheck = writercheck;
	}
	public Date getBoard00_regdate() {
		return board00_regdate;
	}
	public void setBoard00_regdate(Date board00_regdate) {
		this.board00_regdate = board00_regdate;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((board00_content == null) ? 0 : board00_content.hashCode());
		result = prime * result + board00_num;
		result = prime * result + ((board00_regdate == null) ? 0 : board00_regdate.hashCode());
		result = prime * result + ((board00_title == null) ? 0 : board00_title.hashCode());
		result = prime * result + board00_viewcnt;
		result = prime * result + ((board00_writer == null) ? 0 : board00_writer.hashCode());
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
		Board00VO other = (Board00VO) obj;
		if (board00_content == null) {
			if (other.board00_content != null)
				return false;
		} else if (!board00_content.equals(other.board00_content))
			return false;
		if (board00_num != other.board00_num)
			return false;
		if (board00_regdate == null) {
			if (other.board00_regdate != null)
				return false;
		} else if (!board00_regdate.equals(other.board00_regdate))
			return false;
		if (board00_title == null) {
			if (other.board00_title != null)
				return false;
		} else if (!board00_title.equals(other.board00_title))
			return false;
		if (board00_viewcnt != other.board00_viewcnt)
			return false;
		if (board00_writer == null) {
			if (other.board00_writer != null)
				return false;
		} else if (!board00_writer.equals(other.board00_writer))
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
		return "Board00VO [board00_num=" + board00_num + ", board00_title=" + board00_title + ", board00_content="
				+ board00_content + ", board00_writer=" + board00_writer + ", board00_viewcnt=" + board00_viewcnt
				+ ", writercheck=" + writercheck + ", board00_regdate=" + board00_regdate
				+ "]";
	}
	
}
