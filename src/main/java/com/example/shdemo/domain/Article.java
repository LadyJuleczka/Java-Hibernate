package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
@NamedQuery(name = "article.all", query = "Select a from Article a"),
@NamedQuery(name = "article.notHaveUA", query = "Select a from Article a where a.UA = false"),
})

public class Article {
	private Long id;
	private String name;
	private double dmg;
	private int uaId;
	private boolean UA = false;
	
	public Article(){
		
	}
	
	public Article(String name, double dmg){
		this.name = name;
		this.dmg = dmg;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public double getDmg() {
		return this.dmg;
	}
	
	public void setDmg(double dmg) {
		this.dmg = dmg;
	}
	
	
	public double getUaId() {
		return this.uaId;
	}
	
	public void setUaId(int ua) {
		this.uaId = ua;
	}

	public boolean isUA() {
		return UA;
	}

	public void setUA(boolean uA) {
		UA = uA;
	}
}
