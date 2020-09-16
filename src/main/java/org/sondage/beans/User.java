package org.sondage.beans;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String email;
	private String profession; 
	private String sexe;
	private String agegroup;
	private String questionnaire;
	private String expert;
	
	public User(String expert,String email,String profession,String gender, String agegroup) {
		this.expert=expert;
		this.email=email;
		this.profession=profession;
		this.agegroup=agegroup;
		this.sexe=gender;
	}
	public User(String questionnaire){
		this.questionnaire=questionnaire;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getAgegroup() {
		return agegroup;
	}
	public void setAgegroup(String agegroup) {
		this.agegroup = agegroup;
	}
	public String getQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(String questionnaire) {
		this.questionnaire = questionnaire;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
}
