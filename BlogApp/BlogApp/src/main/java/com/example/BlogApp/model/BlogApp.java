package com.example.BlogApp.model;
import jakarta.persistence.*;
import lombok.ToString;
@Entity
@Table(name="BlogApplication")
@ToString
public class BlogApp {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;
	private String Title;
	private String Genre;
	private String Content;
	public BlogApp(long id, String title, String genre, String content) {
		super();
		this.Id = id;
		this.Title = title;
		this.Genre = genre;
		this.Content = content;
	}
	public BlogApp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		this.Id = id;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		this.Genre = genre;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		this.Content = content;
	}
}
