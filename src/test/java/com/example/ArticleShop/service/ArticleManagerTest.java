package com.example.ArticleShop.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.ArticleShop.domain.Article;
import com.example.ArticleShop.domain.UniqueAbility;
import com.example.ArticleShop.service.ArticleManager;

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
	
	@Test
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
	
	@Test
	public void CheckGetAllUniqueAbility(){
		List<UniqueAbility> addedUA_1 = articleManager.getAllUniqueAbility();
		int size = addedUA_1.size();
		
		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setName(NEXTNAMEA);
		uniqueAbility.setLevel(NEXTLEVEL);
		articleManager.addUniqueAbility(uniqueAbility);

		List<UniqueAbility> addedUA = articleManager.getAllUniqueAbility();
		size++;
		assertEquals(size,addedUA.size());
		assertEquals(NAMEA_1,addedUA.get(0).getName());
		assertEquals(NEXTNAMEA,addedUA.get(1).getName());
	}
	
	@Test
	public void getAllPlanesCheck(){
		List<Article> addedArticles_1 = articleManager.getAllArticle();
		int size = addedArticles_1.size();
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		articleManager.addArticle(article);
		List<Article> addedArticles = articleManager.getAllArticle();
		size++;
		assertEquals(size,addedArticles.size());
		assertEquals(NAME_1,addedArticles.get(0).getName());
		assertEquals(NEXTNAME_1,addedArticles.get(1).getName());
	}
	
	@Test
	public void CheckGetArticleWithUA(){
		
		List<Article> addedArticles_1 = articleManager.getAllArticle();
		int size = addedArticles_1.size();
		
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		articleManager.addArticle(article);
		
		UniqueAbility uniqueAbility = articleManager.getAllUniqueAbility().get(0);
		articleManager.giveArticleUA(uniqueAbility.getId(), article.getId());
		
		
		List<Article> addedArticles = articleManager.getHaveUAArticles(uniqueAbility.getName());
		size++;
		assertEquals(size,addedArticles.size());
		assertEquals(true,addedArticles.get(0).getHaveUA());
		assertEquals(true,addedArticles.get(1).getHaveUA());
		assertEquals(article.getId(),addedArticles.get(1).getId());
	}
	
	@Test
	public void CheckGetArticleWithoutUA(){
		
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);

		articleManager.addArticle(article);
		List<Article> planesRetrieved = articleManager.getNotHaveUAArticles();
		assertEquals(1,planesRetrieved.size());
		assertEquals(article.getId(),planesRetrieved.get(0).getId());
		assertEquals(article.getHaveUA(),false);
	}
	
	@Test
	public void CheckaddUAtoArticle(){
		
		Article article = new Article();
		article.setName(NEXTNAME_1);
		article.setDmg(NEXTDMG_1);
		articleManager.addArticle(article);
		
		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setName(NEXTNAMEA);
		uniqueAbility.setLevel(NEXTLEVEL);
		articleManager.addUniqueAbility(uniqueAbility);
		
		articleManager.giveArticleUA(uniqueAbility.getId(), article.getId());
		List<Article> addedArticle = articleManager.getHaveUAArticles(uniqueAbility.getName());

		assertEquals(true,article.getHaveUA());
		assertEquals(article.getId(),addedArticle.get(0).getId());
		assertEquals(1,addedArticle.size());
	}
}
