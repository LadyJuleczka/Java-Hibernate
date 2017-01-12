package com.example.jdbcdemo.domain;

public class Article {
	private int id;
	private String name;
	private double dmg;
	private int uaId;
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
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
}
