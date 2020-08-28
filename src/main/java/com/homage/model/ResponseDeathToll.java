package com.homage.model;

import java.util.List;

public class ResponseDeathToll {
	
	private long total;
	private long corona;
	private List<Member> memberList;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getCorona() {
		return corona;
	}
	public void setCorona(long corona) {
		this.corona = corona;
	}
	public List<Member> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<Member> memberList) {
		this.memberList = memberList;
	}
	@Override
	public String toString() {
		return "ResponseDeathToll [total=" + total + ", corona=" + corona + "]";
	}
}
