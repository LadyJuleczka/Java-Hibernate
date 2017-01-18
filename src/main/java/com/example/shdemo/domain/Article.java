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
		@NamedQuery(name = "article.notHaveUA", query = "Select c from Article c where c.haveUA = false")
})
public class Article {

	private Long id;
	private String name;
	private int dmg;
	private Boolean haveUA = false;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDmg() {
		return dmg;
	}

	public void setDmg(int dmg) {
		this.dmg = dmg;
	}

	public Boolean getHaveUA() {
		return haveUA;
	}

	public void setHaveUA(Boolean haveUA) {
		this.haveUA = haveUA;
	}
}
