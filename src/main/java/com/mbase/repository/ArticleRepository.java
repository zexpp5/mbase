package com.mbase.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mbase.domain.Article;

/**
 * 
 * @Title: AtricleRepository.java
 * @author Lance lance7in_gmail_com
 * @date Oct 29, 2013 4:14:14 PM
 * @version V0.1
 */
public interface ArticleRepository extends
		PagingAndSortingRepository<Article, Long> {

}
