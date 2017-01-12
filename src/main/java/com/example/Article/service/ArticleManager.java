package com.example.Article.service;

import java.util.List;

import com.example.Article.domain.Article;
import com.example.Article.domain.UniqueAbility;

public interface ArticleManager {
	
	Long addArticle(Article article);
	boolean updateArticle(Article article);
	void deleteArticle(Article article);
	Article findArticleById(Long id);
	List<Article> getAllArticle();
	List<Article> getHaveUAArticles(String name);
	List<Article> getNotHaveUAArticles();
	void giveArticleUA(Long uniqueAbilityId, Long ArticleId);
	
	
	void addUniqueAbility(UniqueAbility uniqueAbility);
	boolean updateUniqueAbility(UniqueAbility uniqueAbility);
	void deleteUniqueAbility(UniqueAbility uniqueAbility);
	UniqueAbility findUniqueAbilityByName(String name);
	UniqueAbility findUniqueAbilityById(Long id);
	List<UniqueAbility> getAllUniqueAbility();
}
