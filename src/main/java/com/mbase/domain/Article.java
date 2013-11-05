package com.mbase.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @Title: Article.java
 * @author Lance lance7in_gmail_com
 * @date Oct 29, 2013 4:10:47 PM
 * @version V0.1
 */
@Entity
@Table(name = "t_article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(length = 500)
	private String title;
	@Column(length = 10000)
	private String content;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
