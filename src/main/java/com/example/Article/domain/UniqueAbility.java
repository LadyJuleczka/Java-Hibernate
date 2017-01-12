package com.example.Article.domain;

import java.util.ArrayList;
import java.util.List;
import com.example.Article.domain.Article;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery(name = "uniqueAbility.all", query = "Select a from UniqueAbility a"),
	@NamedQuery(name = "uniqueAbility.byname", query = "Select a from UniqueAbility a where a.name = :name"),
})

public class UniqueAbility {

	private Long id;
	private boolean magic;
	private double power;
	private String name;
	private int level;
	private List<Article> articles = new ArrayList<Article>();
	
	public UniqueAbility(){
		
	}
	
	public UniqueAbility(boolean magic, double power, String name, int level){
		super();
		this.magic = magic;
		this.power = power;
		this.name = name;
		this.level = level;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public boolean getMagic() {
		return this.magic;
	}
	
	public void setMagic(boolean magic) {
		this.magic = magic;
	}
	
	public double getPower() {
		return this.power;
	}
	
	public void setPower(double power) {
		this.power = power;
	}
	
	@Column(unique = true, nullable = false)
	public String getName() {
		return this.name;
	}
	
	public void setName(String desc) {
		this.name = desc;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public void setArticle(List<Article> articles) {
		this.articles = articles;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Article> getArticles() {
		return articles;
	}
}
