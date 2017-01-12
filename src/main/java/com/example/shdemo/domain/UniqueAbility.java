package com.example.jdbcdemo.domain;
import java.util.ArrayList;

import com.example.jdbcdemo.domain.Article;

public class UniqueAbility {

	private int id;
	private boolean magic;
	private double power;
	private String desc;
	private int level;
	private ArrayList<Article> articles = new ArrayList<Article>();
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
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
	
	public String getDesc() {
		return this.desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
//	
	public void addArticle(Article input) {
		this.articles.add(input);
	}
	
	public ArrayList<Article> getArticle() {
		return this.articles;
	}
//	
}
