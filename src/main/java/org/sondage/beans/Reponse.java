package org.sondage.beans;

import java.io.Serializable;

public class Reponse implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private int idreponse;
	private int iduser;
	private int idquestion;
	private Float degresimilarite;
	
	public Reponse(int idu,Float degresim){
		this.iduser=idu;
		this.degresimilarite=degresim;
	}
	public Reponse(Float degresim, int idquestion){
		this.idquestion=idquestion;
		this.degresimilarite=degresim;
	}
	
	public Reponse(int idu, int idq, Float degresim,int idr) {
		this.iduser=idu;
		this.idquestion=idq;
		this.degresimilarite=degresim;
		this.idreponse=idr;
	}
	
	public Reponse() {
	}
	
	public Float getDegresimilarite() {
		return degresimilarite;
	}
	public void setDegresimilarite(Float degresimilarite) {
		this.degresimilarite = degresimilarite;
	}
	public int getIduser() {
		return iduser;
	}
	public void setIduser(int iduser) {
		this.iduser = iduser;
	}
	public int getIdquestion() {
		return idquestion;
	}
	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}
	public int getIdreponse() {
		return idreponse;
	}
	public void setIdreponse(int idreponse) {
		this.idreponse = idreponse;
	}
}
