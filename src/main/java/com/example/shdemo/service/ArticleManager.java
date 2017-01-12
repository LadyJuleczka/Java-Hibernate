package com.example.shdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

public interface ArticleManager {
	
	int addArticle(Article article);
	boolean updateArticle(Article article);
	void deleteArticle(Article article);
	Article findArticleById(int id);
	List<Article> getAllArticle();
	List<Article> getRareArticles(boolean rare);
	List<Article> getNotRareArticle();
	
	
	void addUniqueAbility(UniqueAbility uniqueAbility);
	boolean updateUniqueAbility(UniqueAbility uniqueAbility);
	void deleteUniqueAbility(UniqueAbility uniqueAbility);
	UniqueAbility findUniqueAbilityByLevel(int level);
	UniqueAbility findUniqueAbilityById(int id);
	List<UniqueAbility> getAllUniqueAbility();
}
