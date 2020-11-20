package com.example.Project.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	
	private Long categoryId;
	private String name;
	
	@OneToMany (cascade = CascadeType.ALL, mappedBy = "category")
	private List<Vocable> vocables;
	
	public Category() {
		super();
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Vocable> getVocable() {
		return vocables;
	}
	
	public void setVocables(List<Vocable> vocables) {
		this.vocables = vocables;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}

}
