package org.sondage.controller.impl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.sondage.beans.DatabaseAccess;
import org.sondage.beans.Question;
import org.sondage.beans.Reponse;
import org.sondage.beans.User;
import org.sondage.controller.Controller;
import org.sondage.dao.impl.DaoImpl;


public class ControllerImpl implements Controller{

	static DaoImpl daoImplObjet=new DaoImpl();

	public boolean maestroWriteResponse(List<Reponse> liste) {
		boolean worked = false;
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"),DatabaseAccess.properties.getProperty("DB_PASSWORD"),Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")),DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			daoImplObjet.writeResponse(liste, C);
			worked = true;
		} catch (SQLException e) {
			closeConnection(C);
		}
		return worked;
	}

	public boolean maestroWriteUser (User U) {
		boolean worked =false;
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"),DatabaseAccess.properties.getProperty("DB_PASSWORD"),Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")),DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			worked =daoImplObjet.writeUser(U, C);
		} catch (SQLException e) {
			closeConnection(C);
		}
		return worked;
	}
	public int maestroWriteIdResponse (String questionnaire) {
		int worked =0;
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"),DatabaseAccess.properties.getProperty("DB_PASSWORD"),Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")),DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			worked =daoImplObjet.writeIdResponse(C,questionnaire);
		} catch (SQLException e) {
			closeConnection(C);
		}
		return worked;
	}

	public List<Question> maestroReadQuestionsEnfants() {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"), DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readQuestionsEnfants(C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Question> maestroReadQuestionsTouristes() {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readQuestionsTouristes(C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	private void closeConnection(Connection C){
		try {
			C.close();
			System.out.println("your connection is closed");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Question> maestroReadLieuxQuestions() {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"), DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readLieuxQuestions(C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Question> maestroReadSequenceActivityQuestions(String ontologie) {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"), DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readSequenceActivityQuestions(ontologie,C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Question> maestroReadModeDeTransportQuestions() {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"), DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readModeDeTransportQuestions(C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Question> maestroReadActivityQuestions(String ontologie) {
		List<Question> liste = new ArrayList<Question>();
		DatabaseAccess.readProperties();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try {
			liste = daoImplObjet.readActivityQuestions(ontologie,C);
		} 
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Reponse> maestroGetAnswersById(int userid) {
		List<Reponse> liste = new ArrayList<Reponse>();
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		try{
			liste=daoImplObjet.readSimilarityDegreesById(userid,C);
		}
		catch (SQLException e) {
			closeConnection(C);
		}
		return liste;
	}

	public List<Reponse> maestroGetAnswersWithMedian(String questionnaire) {
		Connection C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
		List<Reponse> listeOfResponsesWithMedian = new ArrayList<Reponse>();
		List<Integer> listedesQuestions = new ArrayList<Integer>();
		try {
			C = daoImplObjet.connect(DatabaseAccess.properties.getProperty("DB_NAME"),DatabaseAccess.properties.getProperty("DB_USER"), DatabaseAccess.properties.getProperty("DB_PASSWORD"), Integer.parseUnsignedInt(DatabaseAccess.properties.getProperty("numPort")), DatabaseAccess.properties.getProperty("bdd_IP"));
			listedesQuestions = daoImplObjet.getTheQuestions(C,questionnaire);
			for(int j=0;j<listedesQuestions.size();j++){
				int idquestion = listedesQuestions.get(j);
				List<Float> listeOfHerAnswers = new ArrayList<Float>();
				listeOfHerAnswers = daoImplObjet.getQuestionAnswers(questionnaire,idquestion, C);
				Float medianeOfQuestion = daoImplObjet.calculateMedian(listeOfHerAnswers);
				Reponse reponse = new Reponse(medianeOfQuestion, idquestion);
				listeOfResponsesWithMedian.add(reponse);
			}
			closeConnection(C);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listeOfResponsesWithMedian;
	}

}
