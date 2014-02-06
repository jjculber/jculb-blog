package com.jculb.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jculb.model.Article;
import com.jculb.service.ArticleService;

@Controller
public class BlogController {

	@Autowired
	private ArticleService articleService;

	@RequestMapping(value = { "/article/{id:\\d+}",
			"/article/{id:\\d+}/{title}" })
	public String home(@PathVariable("id") long id, Map<String, Object> map)
			throws Exception {

		Article a = articleService.getArticle(id, true);

		if (a == null || !a.isPublished()) {
			throw new Exception("Article not found");
		}

		map.put("article", a);
		map.put("title", a.getTitle());

		return "article";
	}

	@RequestMapping("/")
	public String home(Map<String, Object> map) {
		List<Article> articles = articleService.getAllArticles(true);

		map.put("articles", articles);
		map.put("title", "Jculb Blog");

		return "home";
	}

}
