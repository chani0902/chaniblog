package com.test.myproject.member.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class MemberVO implements Serializable {
	
		private int member_num;
		private String member_id;
		private String member_pw;
		private String member_nickname;
		private String member_email;
		private String member_img; // 실습 당시 saveName 역할
		private MultipartFile multipartFile;
//		이메일 인증 관련
		private String authKey;
		private int authStatus;
		private int member_point;
		@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
		private Date member_regdate;
		
		
		public int getMember_num() {
			return member_num;
		}
		public void setMember_num(int member_num) {
			this.member_num = member_num;
		}
		public String getMember_id() {
			return member_id;
		}
		public void setMember_id(String member_id) {
			this.member_id = member_id;
		}
		public String getMember_pw() {
			return member_pw;
		}
		public void setMember_pw(String member_pw) {
			this.member_pw = member_pw;
		}
		public String getMember_nickname() {
			return member_nickname;
		}
		public void setMember_nickname(String member_nickname) {
			this.member_nickname = member_nickname;
		}
		public String getMember_email() {
			return member_email;
		}
		public void setMember_email(String member_email) {
			this.member_email = member_email;
		}
		public String getMember_img() {
			return member_img;
		}
		public void setMember_img(String member_img) {
			this.member_img = member_img;
		}
		public MultipartFile getMultipartFile() {
			return multipartFile;
		}
		public void setMultipartFile(MultipartFile multipartFile) {
			this.multipartFile = multipartFile;
		}
		public String getAuthKey() {
			return authKey;
		}
		public void setAuthKey(String authKey) {
			this.authKey = authKey;
		}
		public int getAuthStatus() {
			return authStatus;
		}
		public void setAuthStatus(int authStatus) {
			this.authStatus = authStatus;
		}
		public int getMember_point() {
			return member_point;
		}
		public void setMember_point(int member_point) {
			this.member_point = member_point;
		}
		public Date getMember_regdate() {
			return member_regdate;
		}
		public void setMember_regdate(Date member_regdate) {
			this.member_regdate = member_regdate;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((authKey == null) ? 0 : authKey.hashCode());
			result = prime * result + authStatus;
			result = prime * result + ((member_email == null) ? 0 : member_email.hashCode());
			result = prime * result + ((member_id == null) ? 0 : member_id.hashCode());
			result = prime * result + ((member_img == null) ? 0 : member_img.hashCode());
			result = prime * result + ((member_nickname == null) ? 0 : member_nickname.hashCode());
			result = prime * result + member_num;
			result = prime * result + member_point;
			result = prime * result + ((member_pw == null) ? 0 : member_pw.hashCode());
			result = prime * result + ((member_regdate == null) ? 0 : member_regdate.hashCode());
			result = prime * result + ((multipartFile == null) ? 0 : multipartFile.hashCode());
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
			MemberVO other = (MemberVO) obj;
			if (authKey == null) {
				if (other.authKey != null)
					return false;
			} else if (!authKey.equals(other.authKey))
				return false;
			if (authStatus != other.authStatus)
				return false;
			if (member_email == null) {
				if (other.member_email != null)
					return false;
			} else if (!member_email.equals(other.member_email))
				return false;
			if (member_id == null) {
				if (other.member_id != null)
					return false;
			} else if (!member_id.equals(other.member_id))
				return false;
			if (member_img == null) {
				if (other.member_img != null)
					return false;
			} else if (!member_img.equals(other.member_img))
				return false;
			if (member_nickname == null) {
				if (other.member_nickname != null)
					return false;
			} else if (!member_nickname.equals(other.member_nickname))
				return false;
			if (member_num != other.member_num)
				return false;
			if (member_point != other.member_point)
				return false;
			if (member_pw == null) {
				if (other.member_pw != null)
					return false;
			} else if (!member_pw.equals(other.member_pw))
				return false;
			if (member_regdate == null) {
				if (other.member_regdate != null)
					return false;
			} else if (!member_regdate.equals(other.member_regdate))
				return false;
			if (multipartFile == null) {
				if (other.multipartFile != null)
					return false;
			} else if (!multipartFile.equals(other.multipartFile))
				return false;
			return true;
		}
		@Override
		public String toString() {
			return "MemberVO [member_num=" + member_num + ", member_id=" + member_id + ", member_pw=" + member_pw
					+ ", member_nickname=" + member_nickname + ", member_email=" + member_email + ", member_img="
					+ member_img + ", multipartFile=" + multipartFile + ", authKey=" + authKey + ", authStatus="
					+ authStatus + ", member_point=" + member_point + ", member_regdate=" + member_regdate + "]";
		}
			
} // end class
