package com.jculb.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jculb.model.Article;

@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private DataSource dataSource;

	public void setDataSource(DataSource source) {
		dataSource = source;
	}

	@Override
	public Article getArticle(long id, boolean requirePublished) {

		String sql = "SELECT * FROM article WHERE id=?";
		
		if (requirePublished) {
			sql += " AND published!=0";
		}
		
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);

			ps.setLong(1, id);
			ResultSet result = ps.executeQuery();

			if (result.next()) {
				Article a = new Article();
				a.setTitle(result.getString("title"));
				a.setBody(result.getString("body"));
				a.setIntro(result.getString("intro"));
				a.setPublished(result.getBoolean("published"));
				a.setDate(new Date(result.getDate("date").getTime()));
				return a;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public List<Article> getAllArticles(boolean requirePublished) {
		
		List<Article> articles = new ArrayList<Article>();

		String sql = "SELECT id, title, intro, published, date FROM article";
		if (requirePublished) {
			sql += " WHERE published!=0";
		}
		sql += " ORDER BY date DESC";
		
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = dataSource.getConnection();
			ps = conn.prepareCall(sql);

			ResultSet result = ps.executeQuery();

			while (result.next()) {
				Article a = new Article();
				a.setId(result.getLong("id"));
				a.setTitle(result.getString("title"));
				a.setIntro(result.getString("intro"));
				a.setPublished(result.getBoolean("published"));
				a.setDate(new Date(result.getDate("date").getTime()));
				articles.add(a);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return articles;
	}
}
