package com.newlecture.javaweb.entity;

import java.util.Date;

public class NoticeView extends Notice{
	private String writerName;
	private int countCmt;
	
	public NoticeView() {
	}

	public NoticeView(String id, String title, String content, String writerId, Date regDate, int hit) {
		super(id, title, content, writerId, regDate, hit);
	}

	public String getWriterName() {
		return writerName;
	}

	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}

	public int getCountCmt() {
		return countCmt;
	}

	public void setCountCmt(int countCmt) {
		this.countCmt = countCmt;
	}
	
	
}
