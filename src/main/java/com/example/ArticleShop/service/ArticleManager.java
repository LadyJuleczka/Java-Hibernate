package com.example.ArticleShop.service;

import java.util.List;

import com.example.ArticleShop.domain.Article;
import com.example.ArticleShop.domain.UniqueAbility;

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
	
	
	
	
	
	
	
	
//	void addClient(UniqueAbility uniqueAbility);
//	List<UniqueAbility> getAllClients();
//	void deleteClient(UniqueAbility uniqueAbility);
//	UniqueAbility findClientByPin(String pin);
//	
//	Long addNewCar(Article article);
//	List<Article> getAvailableCars();
//	void disposeCar(UniqueAbility uniqueAbility, Article article);
//	Article findCarById(Long id);
//
//	List<Article> getOwnedCars(UniqueAbility uniqueAbility);
//	void sellCar(Long personId, Long carId);

}
