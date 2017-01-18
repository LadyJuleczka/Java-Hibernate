package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.xml.crypto.Data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ArticleManagerTest {

	@Autowired
	ArticleManager articleManager;

	
	private final String NAMEA_1 = "Obrazenia krytyczne zwiekszone o 20%";
	private final int LEVEL_1 = 10;
	private final int POWER_1 = 420;
	private final boolean Magic_1 = false;

	private final String NAME_1 = "Ostrze nieskonosci";
	private final int DMG_1 = 80;

	private final String NEXTNAMEA = "Predkosc poruszania zwiekszona o 20";
	private final int NEXTLEVEL = 15;
	private final int NEXTPOWER = 450;
	private final boolean NEXTMAGIC = true;
	
	private final String NEXTNAME_1 = "Widmowe Ostrze";
	private final int NEXTDMG_1 = 120;
	
	
	
	//private final String NAME_1 = "Bolek";
	private final String PIN_1 = "1234";

	private final String NAME_2 = "Lolek";
	private final String PIN_2 = "4321";

	private final String MODEL_1 = "126p";
	private final String MAKE_1 = "Fiat";

	private final String MODEL_2 = "Mondeo";
	private final String MAKE_2 = "Ford";

	
	@Before
	public void ResetDatabases() {
		for (UniqueAbility uniqueAbility : articleManager.getAllUniqueAbility()) {
			articleManager.deleteUniqueAbility(uniqueAbility);
		}
		for (Article article : articleManager.getAllArticle()) {
			articleManager.deleteArticle(article);
			;
		}
		UniqueAbility umiejetnosc = new UniqueAbility();
		umiejetnosc.setName(NAMEA_1);
		umiejetnosc.setLevel(LEVEL_1);
		Article przedmiot = new Article();
		przedmiot.setName(NAME_1);
		przedmiot.setDmg(DMG_1);
		articleManager.addArticle(przedmiot);
		articleManager.addUniqueAbility(umiejetnosc);
		articleManager.giveArticleUA(umiejetnosc.getId(), przedmiot.getId());
	}
	
	@Test
	public void CheckAddArticle() {
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		articleManager.addArticle(article);
		Article addedArticle = articleManager.findArticleById(article.getId());
		assertEquals(NEXTNAME_1, addedArticle.getName());
		assertEquals(NEXTDMG_1, addedArticle.getDmg());
	}
	
	@Test
	public void CheckAddUniqueAbility() {
		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setName(NEXTNAMEA);
		uniqueAbility.setLevel(NEXTLEVEL);

		articleManager.addUniqueAbility(uniqueAbility);
		UniqueAbility addedUniqueAbility = articleManager.findUniqueAbilityByName(uniqueAbility.getName());
		assertEquals(NEXTNAMEA, addedUniqueAbility.getName());
		assertEquals(NEXTLEVEL, addedUniqueAbility.getLevel());
	}
	
	
	@Test
	public void FindArticleById() {
		Article articleF = articleManager.getAllArticle().get(0);

		Article articleR = articleManager.findArticleById(articleF.getId());

		assertEquals(articleR.getName(), articleF.getName());
		assertEquals(articleR.getDmg(), articleF.getDmg());
	}
	
	@Test
	public void findUniqueAbilityById() {
		UniqueAbility uniqueAbilityF = articleManager.getAllUniqueAbility().get(0);

		UniqueAbility uniqueAbility = articleManager
				.findUniqueAbilityById(uniqueAbilityF.getId());

		assertEquals(uniqueAbility.getName(), uniqueAbilityF.getName());
		assertEquals(uniqueAbility.getLevel(), uniqueAbilityF.getLevel());
	}
	
	@Test
	public void CheckUpdateUniqueAbility() {
		UniqueAbility uniqueAbility = articleManager.getAllUniqueAbility().get(0);
		assertEquals(NAMEA_1, uniqueAbility.getName());
		assertEquals(LEVEL_1, uniqueAbility.getLevel());
		uniqueAbility.setLevel(NEXTLEVEL);
		uniqueAbility.setName(NEXTNAMEA);
		assertEquals(true, articleManager.updateUniqueAbility(uniqueAbility));
		UniqueAbility addedUniqueAbility = articleManager.getAllUniqueAbility().get(0);

		assertEquals(NEXTLEVEL, addedUniqueAbility.getLevel());
		assertEquals(NEXTNAMEA, addedUniqueAbility.getName());
	}

	
	@Test
	public void CheckUpdateArticle() {
		Article article = articleManager.getAllArticle().get(0);
		assertEquals(NAME_1, article.getName());
		assertEquals(DMG_1, article.getDmg());
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		assertEquals(true, articleManager.updateArticle(article));
		Article updatedarticle = articleManager.getAllArticle().get(0);

		assertEquals(NEXTDMG_1, updatedarticle.getDmg());
		assertEquals(NEXTNAME_1, updatedarticle.getName());
	}
	
	
	public void CheckDeleteArticle(){
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		articleManager.addArticle(article);
		
		assertEquals(NEXTNAME_1, articleManager.findArticleById(article.getId()).getName());
		int size = articleManager.getAllArticle().size();
		assertEquals(size,articleManager.getAllArticle().size());
		articleManager.deleteArticle(article);
		assertEquals(null,articleManager.findArticleById(article.getId()));
		size--;
		assertEquals(size,articleManager.getAllArticle().size());
	}
	
	@Test
	public void CheckDeleteUniqueAbility(){
		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setName(NEXTNAMEA);
		uniqueAbility.setLevel(NEXTLEVEL);
		articleManager.addUniqueAbility(uniqueAbility);


		UniqueAbility addedUniqueAbility = articleManager.findUniqueAbilityByName(uniqueAbility.getName());
		assertEquals(NEXTNAMEA, addedUniqueAbility.getName());
		
		int size = articleManager.getAllUniqueAbility().size();
		assertEquals(size,articleManager.getAllUniqueAbility().size());
		articleManager.deleteUniqueAbility(uniqueAbility);
		assertEquals(null,articleManager.findUniqueAbilityByName(uniqueAbility.getName()));
		size--;
		assertEquals(size,articleManager.getAllUniqueAbility().size());
	}
	
	
//	@Test
//	public void addClientCheck() {
//
//		List<UniqueAbility> retrievedClients = articleManager.getAllClients();
//
//		// If there is a client with PIN_1 delete it
//		for (UniqueAbility client : retrievedClients) {
//			if (client.getPin().equals(PIN_1)) {
//				articleManager.deleteClient(client);
//			}
//		}
//
//		UniqueAbility uniqueAbility = new UniqueAbility();
//		uniqueAbility.setFirstName(NAME_1);
//		uniqueAbility.setPin(PIN_1);
//		// ... other properties here
//
//		// Pin is Unique
//		articleManager.addClient(uniqueAbility);
//
//		UniqueAbility retrievedClient = articleManager.findClientByPin(PIN_1);
//
//		assertEquals(NAME_1, retrievedClient.getFirstName());
//		assertEquals(PIN_1, retrievedClient.getPin());
//		// ... check other properties here
//	}
//
//	@Test
//	public void addCarCheck() {
//
//		Article article = new Article();
//		article.setName(MAKE_1);
//		article.setDmg(234);
//		// ... other properties here
//
//		Long carId = articleManager.addNewCar(article);
//
//		Article retrievedCar = articleManager.findCarById(carId);
//		assertEquals(MAKE_1, retrievedCar.getName());
//		assertEquals(MODEL_1, retrievedCar.getDmg());
//		// ... check other properties here
//
//	}
//
//	@Test
//	public void sellCarCheck() {
//
//		UniqueAbility uniqueAbility = new UniqueAbility();
//		uniqueAbility.setFirstName(NAME_2);
//		uniqueAbility.setPin(PIN_2);
//
//		articleManager.addClient(uniqueAbility);
//
//		UniqueAbility retrievedPerson = articleManager.findClientByPin(PIN_2);
//
//		Article article = new Article();
//		article.setName(MAKE_2);
//		article.setDmg(23);
//
//		Long carId = articleManager.addNewCar(article);
//
//		articleManager.sellCar(retrievedPerson.getId(), carId);
//
//		List<Article> ownedCars = articleManager.getOwnedCars(retrievedPerson);
//
//		assertEquals(1, ownedCars.size());
//		assertEquals(MAKE_2, ownedCars.get(0).getName());
//		assertEquals(MODEL_2, ownedCars.get(0).getDmg());
//	}
//
//	// @Test -
//	public void disposeCarCheck() {
//		// Do it yourself
//	}

}
