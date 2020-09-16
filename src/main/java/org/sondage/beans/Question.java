package org.sondage.beans;

public class Question {
	private int id;
	private String item1;
	private String item2;
	private int id_categorie;
	private String question;
	
	public int getId_categorie() {
		return id_categorie;
	}
	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getItem1() {
		return item1;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}
	
}
