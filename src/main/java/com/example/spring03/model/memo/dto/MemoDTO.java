package com.example.spring03.model.memo.dto;

import java.sql.Date;

public class MemoDTO {
	
	private int idx;
	private String write;
	private String memo;
	private Date post_date;
	
	public MemoDTO() {
		
	}
	
	public MemoDTO(String write, String memo) {
		this.write=write;
		this.memo=memo;
	}
	
	
	@Override
	public String toString() {
		return "MemoDTO [idx=" + idx + ", write=" + write + ", memo=" + memo + ", post_date=" + post_date + "]";
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWrite() {
		return write;
	}
	public void setWrite(String write) {
		this.write = write;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getPost_date() {
		return post_date;
	}
	public void setPost_date(Date post_date) {
		this.post_date = post_date;
	}
	
	

}
