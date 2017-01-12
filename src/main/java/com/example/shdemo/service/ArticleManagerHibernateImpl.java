package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Article;
import com.example.shdemo.domain.UniqueAbility;

@Component
@Transactional
public class ArticleManagerHibernateImpl implements ArticleManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public int addArticle(Article article) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updateArticle(Article article) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteArticle(Article article) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article findArticleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getAllArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getRareArticles(boolean rare) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getNotRareArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUniqueAbility(UniqueAbility uniqueAbility) {
		uniqueAbility.setId(null);
		sessionFactory.getCurrentSession().persist(uniqueAbility);
	}

	@Override
	public boolean updateUniqueAbility(UniqueAbility uniqueAbility) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteUniqueAbility(UniqueAbility uniqueAbility) {
		uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class, uniqueAbility.getId());
		for(Article article : uniqueAbility.getArticles()){
			article.setUA(false);
		sessionFactory.getCurrentSession().update(article);
		}
		sessionFactory.getCurrentSession().delete(uniqueAbility);
	}

	@Override
	public UniqueAbility findUniqueAbilityByLevel(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UniqueAbility findUniqueAbilityById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UniqueAbility> getAllUniqueAbility() {
		return sessionFactory.getCurrentSession().getNamedQuery("uniqueAbility.all").list();
	}

//	public void setSessionFactory(SessionFactory sessionFactory) {
//		this.sessionFactory = sessionFactory;
//	}
//	
//	@Override
//	public void addClient(Person person) {
//		person.setId(null);
//		sessionFactory.getCurrentSession().persist(person);
//	}
//	
//	@Override
//	public void deleteClient(Person person) {
//		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//		
//		// lazy loading here
//		for (Car car : person.getCars()) {
//			car.setSold(false);
//			sessionFactory.getCurrentSession().update(car);
//		}
//		sessionFactory.getCurrentSession().delete(person);
//	}
//
//	@Override
//	public List<Car> getOwnedCars(Person person) {
//		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//		// lazy loading here - try this code without (shallow) copying
//		List<Car> cars = new ArrayList<Car>(person.getCars());
//		return cars;
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Person> getAllClients() {
//		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
//				.list();
//	}
//
//	@Override
//	public Person findClientByPin(String pin) {
//		return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
//	}
//
//
//	@Override
//	public Long addNewCar(Car car) {
//		car.setId(null);
//		return (Long) sessionFactory.getCurrentSession().save(car);
//	}
//
//	@Override
//	public void sellCar(Long personId, Long carId) {
//		Person person = (Person) sessionFactory.getCurrentSession().get(
//				Person.class, personId);
//		Car car = (Car) sessionFactory.getCurrentSession()
//				.get(Car.class, carId);
//		car.setSold(true);
//		person.getCars().add(car);
//	}
//
//	@Override
//	@SuppressWarnings("unchecked")
//	public List<Car> getAvailableCars() {
//		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
//				.list();
//	}
//	@Override
//	public void disposeCar(Person person, Car car) {
//
//		person = (Person) sessionFactory.getCurrentSession().get(Person.class,
//				person.getId());
//		car = (Car) sessionFactory.getCurrentSession().get(Car.class,
//				car.getId());
//
//		Car toRemove = null;
//		// lazy loading here (person.getCars)
//		for (Car aCar : person.getCars())
//			if (aCar.getId().compareTo(car.getId()) == 0) {
//				toRemove = aCar;
//				break;
//			}
//
//		if (toRemove != null)
//			person.getCars().remove(toRemove);
//
//		car.setSold(false);
//	}
//
//	@Override
//	public Car findCarById(Long id) {
//		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
//	}

}
