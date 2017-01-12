package com.example.shdemo.domain;
import java.util.ArrayList;
import java.util.List;
import com.example.shdemo.domain.Article;
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
@NamedQuery(name = "uniqueAbility.withmagic", query = "Select a from uniqueAbility a where a.magic = true"),
})

public class UniqueAbility {

	private int id;
	private boolean magic;
	private double power;
	private String desc;
	private int level;
	private List<Article> articles = new ArrayList<Article>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
	
	@Column(unique = true, nullable = false)
	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
//	
	public void setArticle(List<Article> articles) {
		this.articles = articles;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Article> getArticle() {
		return articles;
	}
//	
}
