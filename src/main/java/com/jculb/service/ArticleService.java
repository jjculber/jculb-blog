package com.jculb.service;

import java.util.List;

import com.jculb.model.Article;

public interface ArticleService {

	public Article getArticle(long id, boolean requirePublished);
	
	public List<Article> getAllArticles(boolean requirePublished);
	
}
