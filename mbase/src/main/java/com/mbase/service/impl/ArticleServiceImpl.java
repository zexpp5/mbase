package com.mbase.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mbase.domain.Article;
import com.mbase.repository.ArticleRepository;
import com.mbase.service.ArticleService;

/**
 * 
 * @Title: ArticleServiceImpl.java
 * @author Lance lance7in_gmail_com
 * @date Oct 29, 2013 4:15:55 PM
 * @version V0.1
 */
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository repository;

	@Override
	public void createArticle(Article article) {
		repository.save(article);
	}

}
