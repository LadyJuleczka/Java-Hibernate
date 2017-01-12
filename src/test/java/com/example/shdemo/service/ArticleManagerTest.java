package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
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
import com.example.shdemo.service.*;

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
	public void ResetDatabases(){
	for(UniqueAbility uniqueAbility : articleManager.getAllUniqueAbility()){
		articleManager.deleteUniqueAbility(uniqueAbility);
	}
	for(Article article : articleManager.getAllArticle()){
		articleManager.deleteArticle(article);;
	}
	UniqueAbility umiejetnosc = new UniqueAbility(Magic_1, POWER_1, NAMEA_1, LEVEL_1)
	Article przedmiot = new Article(NAME_1, DMG_1);
	articleManager.addArticle(przedmiot);
	articleManager.addUniqueAbility(umiejetnosc);
	articleManager.giveArticleUA(umiejetnosc.getId(), przedmiot.getId());
	}
	
	
//	
//	@Test
//	public void checkConnection(){
//		assertNotNull(articleManager.getConnection());
//	}
//	
//	@Test
//	public void checkAddingUniqueAbility(){
//		articleManager.clearUniqueAbility();
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		articleManager.clearUniqueAbility();
//		articleManager.clearArticle();
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 0
//		List<UniqueAbility> uas = articleManager.getAllUniqueAbility();
//		UniqueAbility uasRetrieved = uas.get(0);
//		
//		assertEquals(DESC_1, uasRetrieved.getDesc());
//		assertEquals(POWER_1, uasRetrieved.getPower(), 0.1);
//		assertEquals(Magic_1, uasRetrieved.getMagic());
//		assertEquals(LEVEL_1, uasRetrieved.getLevel());
//
//	}
//	
//	@Test
//	public void checkAddingArticle() {	
//		
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 1
//		
//		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
//		UniqueAbility abiRetrieved = unis.get(0);
//		
//		Article article = new Article();
//		article.setName(NAME_1);
//		article.setDmg(DMG_1);
//		article.setUaId(abiRetrieved.getId());
//		articleManager.addArticle(abiRetrieved, article);   //id = 0
//		
//		Article artRetrived = abiRetrieved.getArticle().get(0);
////		System.out.println("t");
////		System.out.println(artRetrived.getName());
////		System.out.println(artRetrived.getDmg());
//		
//		assertEquals(NAME_1, artRetrived.getName());
//		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
//	}
//	
//	@Test
//	public void ArticleGet() {
//		
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua));   //id = 2
//
//		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
//		UniqueAbility abiRetrieved = unis.get(0);
//		
//		Article article = new Article();
//		article.setName(NAME_1);
//		article.setDmg(DMG_1);
//		article.setUaId(abiRetrieved.getId());
//		articleManager.addArticle(abiRetrieved, article);     //id  = 1
//		
//		unis = articleManager.getAllUniqueAbility();
//		abiRetrieved = unis.get(0);
//		
//		List<Article> art = articleManager.getAllArticle(abiRetrieved.getId());
//		Article artRetrived = art.get(0);
//		assertEquals(1, art.size());
//		assertEquals(NAME_1, artRetrived.getName());
//		assertEquals(DMG_1, artRetrived.getDmg(), 0.1);
//	}
//	
//	@Test
//	public void GetAllUniqueAbility(){
//		
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 3
//
//		ua.setDesc(NEXTDESC);
//		ua.setPower(NEXTPOWER);
//		ua.setMagic(NEXTMAGIC);
//		ua.setLevel(NEXTLEVEL);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua)); //id = 4
//		
//		List<UniqueAbility> unis = articleManager.getAllUniqueAbility();
//		UniqueAbility abiRetrieved = unis.get(0);
//		assertEquals(DESC_1, abiRetrieved.getDesc());
//		abiRetrieved = unis.get(1);
//		assertEquals(NEXTDESC, abiRetrieved.getDesc());
//		
//	}
//	
//	
//	@Test
//	public void EditUniqueAbility() {
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 5
//		
//		List<UniqueAbility> uniabi = articleManager.getAllUniqueAbility();
//		UniqueAbility abiRetrieved = uniabi.get(0);
//		abiRetrieved.setDesc(NEXTDESC);
//		abiRetrieved.setPower(NEXTPOWER);
//		abiRetrieved.setLevel(NEXTLEVEL);
//		abiRetrieved.setMagic(NEXTMAGIC);
//		articleManager.editUniqueAbility(abiRetrieved.getId(), abiRetrieved);
//		
//		uniabi = articleManager.getAllUniqueAbility();
//		UniqueAbility edited = uniabi.get(0);
//		assertEquals(NEXTDESC, edited.getDesc());
//		assertEquals(NEXTPOWER, edited.getPower(), 0.01);
//		assertEquals(NEXTLEVEL, edited.getLevel());
//		assertEquals(NEXTMAGIC, edited.getMagic());
//	}
//
//	@Test
//	public void UniqueAbilityDelete(){
//		
//		UniqueAbility ua = new UniqueAbility();
//		ua.setDesc(DESC_1);
//		ua.setPower(POWER_1);
//		ua.setMagic(Magic_1);
//		ua.setLevel(LEVEL_1);
//		
//		assertEquals(1,articleManager.addUniqueAbility(ua)); // id = 6
//		
//
//		UniqueAbility abi = new UniqueAbility();
//		abi.setDesc(NEXTDESC);
//		abi.setPower(NEXTPOWER);
//		abi.setMagic(NEXTMAGIC);
//		abi.setLevel(NEXTLEVEL);
//		
//		articleManager.addUniqueAbility(abi); // id = 7
//		
//		List<UniqueAbility> abis = articleManager.getAllUniqueAbility();
//		UniqueAbility abiRetrieved = abis.get(0);
//		
//		assertEquals(2, abis.size());
//		
//		Article accessory = new Article();
//		accessory.setName(NAME_1);
//		accessory.setDmg(DMG_1);
//		accessory.setUaId(abiRetrieved.getId());
//		articleManager.addArticle(abiRetrieved, accessory);   //id = 2
//
//		abis = articleManager.getAllUniqueAbility();
//		abiRetrieved = abis.get(0);
//
//	    // deleting the user
//		articleManager.deleteUniqueAbility(abiRetrieved);
//		abis = articleManager.getAllUniqueAbility();
//	    
//	    // checking deletion
//	    assertEquals(1, abis.size());
//	    
//	    UniqueAbility uniabi2 = abis.get(0);
//		assertEquals(NEXTDESC, uniabi2.getDesc());
//		assertEquals(NEXTPOWER, uniabi2.getPower(), 0.1);
//		assertEquals(NEXTMAGIC, uniabi2.getMagic());
//		assertEquals(NEXTLEVEL, uniabi2.getLevel());
//	}
}
