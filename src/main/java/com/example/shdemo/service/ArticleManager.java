package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

public interface ArticleManager {
	
	void addClient(UniqueAbility uniqueAbility);
	List<UniqueAbility> getAllClients();
	void deleteClient(UniqueAbility uniqueAbility);
	UniqueAbility findClientByPin(String pin);
	
	Long addNewCar(Article article);
	List<Article> getAvailableCars();
	void disposeCar(UniqueAbility uniqueAbility, Article article);
	Article findCarById(Long id);

	List<Article> getOwnedCars(UniqueAbility uniqueAbility);
	void sellCar(Long personId, Long carId);

}
