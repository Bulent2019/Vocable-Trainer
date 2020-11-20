package com.example.Project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vocable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String word;
	private String alienWord;
	
	@Column(nullable = true, length = 64)
	private String picture;
	
	@ManyToOne
	
	@JoinColumn(name = "categoryId")
	private Category category;
	
	public Vocable() {
		super();
	}

	public Vocable(String word, String alienWord, String picture) {
		super();
		this.word = word;
		this.alienWord = alienWord;
		this.picture = picture;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getAlienWord() {
		return alienWord;
	}

	public void setAlienWord(String alienWord) {
		this.alienWord = alienWord;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null) {
		return "Vocable [id=" + id + ", word=" + word + ", alienword=" + alienWord + ", picture=" + picture
				+ "category =" + this.getCategory() + "]";
		} else {
			return "Vocable [id=" + id + ", word=" + word + ", alienord=" + alienWord + ", picture=" + picture
					+ "]";
		}
	}	
}
