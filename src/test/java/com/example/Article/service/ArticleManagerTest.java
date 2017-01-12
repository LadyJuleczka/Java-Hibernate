package com.example.Article.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.example.Article.domain.Article;
import com.example.Article.domain.UniqueAbility;
import com.example.Article.service.ArticleManager;
import com.example.Article.service.ArticleManagerHibernateImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ArticleManagerTest {

	@Autowired
	ArticleManager articleManager;

	private final String NAMEA_1 = "Obrazenia krytyczne zwiekszone o 20%";
	private final int LEVEL_1 = 10;
	private final double POWER_1 = 420;
	private final boolean Magic_1 = false;

	private final String NAME_1 = "Ostrze nieskonosci";
	private final double DMG_1 = 80;

	private final String NEXTNAMEA = "Predkosc poruszania zwiekszona o 20";
	private final int NEXTLEVEL = 15;
	private final double NEXTPOWER = 450;
	private final boolean NEXTMAGIC = true;
	private final String NEXTNAME_1 = "Widmowe Ostrze";
	private final double NEXTDMG_1 = 120;

	@Before
	public void ResetDatabases() {
		for (UniqueAbility uniqueAbility : articleManager.getAllUniqueAbility()) {
			articleManager.deleteUniqueAbility(uniqueAbility);
		}
		for (Article article : articleManager.getAllArticle()) {
			articleManager.deleteArticle(article);
			;
		}
		UniqueAbility umiejetnosc = new UniqueAbility(Magic_1, POWER_1, NAMEA_1, LEVEL_1);
		Article przedmiot = new Article(NAME_1, DMG_1);
		articleManager.addArticle(przedmiot);
		articleManager.addUniqueAbility(umiejetnosc);
		articleManager.giveArticleUA(umiejetnosc.getId(), przedmiot.getId());
	}

	@Test
	public void CheckAddArticle() {
		Article article = new Article(NEXTNAME_1, NEXTDMG_1);
		articleManager.addArticle(article);
		Article addedArticle = articleManager.findArticleById(article.getId());
		assertEquals(NEXTNAME_1,addedArticle.getName());
		assertEquals(NEXTDMG_1,addedArticle.getDmg());
	}
	
	@Test
	public void CheckAddUniqueAbility(){
		UniqueAbility uniqueAbility = new UniqueAbility(NEXTMAGIC,NEXTPOWER,NEXTNAME_1, NEXTLEVEL);
		
		articleManager.addUniqueAbility(uniqueAbility);
		UniqueAbility addedUniqueAbility = articleManager.findUniqueAbilityByName(uniqueAbility.getName());
		assertEquals(NEXTNAME_1,addedUniqueAbility.getName());
		assertEquals(NEXTMAGIC,addedUniqueAbility.getMagic());
		assertEquals(NEXTPOWER,addedUniqueAbility.getPower());
		assertEquals(NEXTLEVEL,addedUniqueAbility.getLevel());
	}
	
	@Test
	public void FindArticleById(){
		Article articleF = articleManager.getAllArticle().get(0);
		
		Article articleR = articleManager.findArticleById(articleF.getId());
		
		assertEquals(articleR.getName(),articleF.getName());
		assertEquals(articleR.getDmg(),articleF.getDmg());
	}
	
	@Test
	public void findUniqueAbilityById(){
		UniqueAbility uniqueAbilityF = articleManager.getAllUniqueAbility().get(0);
		
		UniqueAbility uniqueAbility = articleManager.findUniqueAbilityById(uniqueAbilityF.getId());
		
		assertEquals(uniqueAbility.getName(),uniqueAbilityF.getName());
		assertEquals(uniqueAbility.getLevel(),uniqueAbilityF.getLevel());
		assertEquals(uniqueAbility.getMagic(),uniqueAbilityF.getMagic());
		assertEquals(uniqueAbility.getPower(),uniqueAbilityF.getPower());
	}
	
	@Test
	public void CheckUpdateUniqueAbility(){
		UniqueAbility uniqueAbility = articleManager.getAllUniqueAbility().get(0);
		assertEquals(NAMEA_1,uniqueAbility.getName());
		assertEquals(POWER_1,uniqueAbility.getPower());
		uniqueAbility.setPower(NEXTPOWER);
		uniqueAbility.setName(NEXTNAMEA);
		assertEquals(true,articleManager.updateUniqueAbility(uniqueAbility));
//		UniqueAbility addedUniqueAbility = articleManager.getAllUniqueAbility().get(0);
		
		assertEquals(NEXTPOWER,uniqueAbility.getPower());
		assertEquals(NEXTNAMEA,uniqueAbility.getName());
	}
	
}