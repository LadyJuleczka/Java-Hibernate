package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

	private final String NAME_1 = "Bolek";
	private final String PIN_1 = "1234";

	private final String NAME_2 = "Lolek";
	private final String PIN_2 = "4321";

	private final String MODEL_1 = "126p";
	private final String MAKE_1 = "Fiat";

	private final String MODEL_2 = "Mondeo";
	private final String MAKE_2 = "Ford";

	@Test
	public void addClientCheck() {

		List<UniqueAbility> retrievedClients = articleManager.getAllClients();

		// If there is a client with PIN_1 delete it
		for (UniqueAbility client : retrievedClients) {
			if (client.getPin().equals(PIN_1)) {
				articleManager.deleteClient(client);
			}
		}

		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setFirstName(NAME_1);
		uniqueAbility.setPin(PIN_1);
		// ... other properties here

		// Pin is Unique
		articleManager.addClient(uniqueAbility);

		UniqueAbility retrievedClient = articleManager.findClientByPin(PIN_1);

		assertEquals(NAME_1, retrievedClient.getFirstName());
		assertEquals(PIN_1, retrievedClient.getPin());
		// ... check other properties here
	}

	@Test
	public void addCarCheck() {

		Article article = new Article();
		article.setMake(MAKE_1);
		article.setModel(MODEL_1);
		// ... other properties here

		Long carId = articleManager.addNewCar(article);

		Article retrievedCar = articleManager.findCarById(carId);
		assertEquals(MAKE_1, retrievedCar.getMake());
		assertEquals(MODEL_1, retrievedCar.getModel());
		// ... check other properties here

	}

	@Test
	public void sellCarCheck() {

		UniqueAbility uniqueAbility = new UniqueAbility();
		uniqueAbility.setFirstName(NAME_2);
		uniqueAbility.setPin(PIN_2);

		articleManager.addClient(uniqueAbility);

		UniqueAbility retrievedPerson = articleManager.findClientByPin(PIN_2);

		Article article = new Article();
		article.setMake(MAKE_2);
		article.setModel(MODEL_2);

		Long carId = articleManager.addNewCar(article);

		articleManager.sellCar(retrievedPerson.getId(), carId);

		List<Article> ownedCars = articleManager.getOwnedCars(retrievedPerson);

		assertEquals(1, ownedCars.size());
		assertEquals(MAKE_2, ownedCars.get(0).getMake());
		assertEquals(MODEL_2, ownedCars.get(0).getModel());
	}

	// @Test -
	public void disposeCarCheck() {
		// Do it yourself
	}

}
