package com.example.Article.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.Article.domain.Article;
import com.example.Article.domain.UniqueAbility;
import com.example.Article.service.*;


@Component
@Transactional
public class ArticleManagerHibernateImpl implements ArticleManager {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@Override
	public void addUniqueAbility(UniqueAbility uniqueAbility) {
		uniqueAbility.setId(null);
		sessionFactory.getCurrentSession().persist(uniqueAbility);
	}

	@Override
	public Long addArticle(Article article) {
		article.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(article);
	}

	@Override
	public boolean updateArticle(Article article) {
		try{
			sessionFactory.getCurrentSession().update(article);
			}catch(Exception ex){
			return false;
			}
			return true;
	}

	@Override
	public void deleteArticle(Article article) {
		Article planeToDelete = (Article) sessionFactory.getCurrentSession().get(
				Article.class, article.getId());
		List<UniqueAbility> airlines = getAllUniqueAbility();
		if (planeToDelete.isUA()) {
			for (UniqueAbility ua : airlines) {
				for (Article a : ua.getArticles()) {
					if (a.getId() == planeToDelete.getId()) {
						ua.getArticles().remove(a);
						sessionFactory.getCurrentSession().update(ua);
						break;
					}
				}
			}
		}
		sessionFactory.getCurrentSession().delete(planeToDelete);

	}

	@Override
	public Article findArticleById(Long id) {
		return (Article) sessionFactory.getCurrentSession().get(Article.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getAllArticle() {
		return sessionFactory.getCurrentSession().getNamedQuery("article.all").list();
	}


	@Override
	public boolean updateUniqueAbility(UniqueAbility uniqueAbility) {
		try{
			sessionFactory.getCurrentSession().update(uniqueAbility);
			}catch(Exception ex){
			return false;
			}
			return true;
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
	public UniqueAbility findUniqueAbilityByName(String name) {
		return (UniqueAbility) sessionFactory.getCurrentSession().getNamedQuery("uniqueAbility.byname").setString("name", name).uniqueResult();
	}

	@Override
	public UniqueAbility findUniqueAbilityById(Long id) {
		return (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UniqueAbility> getAllUniqueAbility() {
		return sessionFactory.getCurrentSession().getNamedQuery("uniqueAbility.all").list();
	}

	@Override
	public List<Article> getHaveUAArticles(String name) {
		UniqueAbility uniqueAbility = findUniqueAbilityByName(name);
		return uniqueAbility.getArticles();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> getNotHaveUAArticles() {
		return sessionFactory.getCurrentSession().getNamedQuery("article.notHaveUA").list();
	}

	@Override
	public void giveArticleUA(Long uniqueAbilityId, Long articleId) {
		UniqueAbility uniqueAbility = (UniqueAbility) sessionFactory.getCurrentSession().get(UniqueAbility.class, uniqueAbilityId);
		Article article = (Article) sessionFactory.getCurrentSession().get(Article.class, articleId);
		article.setUA(true);
		uniqueAbility.getArticles().add(article);
		updateUniqueAbility(uniqueAbility);
		updateArticle(article);
		
	}
}