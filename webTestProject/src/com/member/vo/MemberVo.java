package com.member.vo;

public class MemberVo {
	private String m_email      ;
	private String m_pw         ;
	private String m_name       ;
	private String m_nickname   ;
	private String m_mobile     ;
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVo [m_email=").append(m_email).append(", m_pw=").append(m_pw).append(", m_name=")
				.append(m_name).append(", m_nickname=").append(m_nickname).append(", m_mobile=").append(m_mobile)
				.append("]");
		return builder.toString();
	}
	
	
	public MemberVo(String m_email, String m_pw, String m_name, String m_nickname, String m_mobile) {
		super();
		this.m_email = m_email;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_nickname = m_nickname;
		this.m_mobile = m_mobile;
	}

	
	public MemberVo() {
		super();
	}


	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_pw() {
		return m_pw;
	}
	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_nickname() {
		return m_nickname;
	}
	public void setM_nickname(String m_nickname) {
		this.m_nickname = m_nickname;
	}
	public String getM_mobile() {
		return m_mobile;
	}
	public void setM_mobile(String m_mobile) {
		this.m_mobile = m_mobile;
	}
	
}
