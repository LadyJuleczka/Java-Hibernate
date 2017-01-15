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
public class ArticleMangerHibernateImpl implements ArticleManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void addClient(UniqueAbility uniqueAbility) {
		uniqueAbility.setId(null);
		sessionFactory.getCurrentSession().persist(uniqueAbility);
	}
	
	@Override
	public void deleteClient(UniqueAbility uniqueAbility) {
		uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class,
				uniqueAbility.getId());
		
		// lazy loading here
		for (Article article : uniqueAbility.getCars()) {
			article.setSold(false);
			sessionFactory.getCurrentSession().update(article);
		}
		sessionFactory.getCurrentSession().delete(uniqueAbility);
	}

	@Override
	public List<Article> getOwnedCars(UniqueAbility uniqueAbility) {
		uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class,
				uniqueAbility.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Article> articles = new ArrayList<Article>(uniqueAbility.getCars());
		return articles;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UniqueAbility> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all")
				.list();
	}

	@Override
	public UniqueAbility findClientByPin(String pin) {
		return (UniqueAbility) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin).uniqueResult();
	}


	@Override
	public Long addNewCar(Article article) {
		article.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(article);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		UniqueAbility uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(
				UniqueAbility.class, personId);
		Article article = (Article) sessionFactory.getCurrentSession()
				.get(Article.class, carId);
		article.setSold(true);
		uniqueAbility.getCars().add(article);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Article> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold")
				.list();
	}
	@Override
	public void disposeCar(UniqueAbility uniqueAbility, Article article) {

		uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class,
				uniqueAbility.getId());
		article = (Article) sessionFactory.getCurrentSession().get(Article.class,
				article.getId());

		Article toRemove = null;
		// lazy loading here (person.getCars)
		for (Article aCar : uniqueAbility.getCars())
			if (aCar.getId().compareTo(article.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			uniqueAbility.getCars().remove(toRemove);

		article.setSold(false);
	}

	@Override
	public Article findCarById(Long id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class, id);
	}

}
