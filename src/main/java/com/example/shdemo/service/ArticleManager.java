package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

public interface ArticleManager {
	
	int addArticle(Article article);
	boolean updateArticle(Article article);
	void deleteArticle(Article article);
	Article findArticleById(Long id);
	List<Article> getAllArticle();
//	List<Article> getRareArticles(boolean rare);
//	List<Article> getNotRareArticle();
	List<Article> getHaveUAArticles(String name);
	List<Article> getNotaveUAArticles();
	void giveArticleUA(Long uniqueAbilityId, Long ArticleId);
	
	
	void addUniqueAbility(UniqueAbility uniqueAbility);
	boolean updateUniqueAbility(UniqueAbility uniqueAbility);
	void deleteUniqueAbility(UniqueAbility uniqueAbility);
	UniqueAbility findUniqueAbilityByName(String name);
	UniqueAbility findUniqueAbilityById(Long id);
	List<UniqueAbility> getAllUniqueAbility();
}
