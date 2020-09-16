package org.sondage.dao;
import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.sondage.beans.Question;
import org.sondage.beans.Reponse;
import org.sondage.beans.User;

public interface Dao {

	public Connection connect(String dataBaseName, String username, String password, int numeroPort, String bdd_IP);
	public void  writeResponse(List<Reponse> liste, Connection C) throws SQLException;
	public boolean writeUser(User U,Connection C) throws SQLException;
	public int writeIdResponse(Connection C,String questionnaire) throws SQLException;
	public  List<Question> readQuestionsEnfants(Connection connection) throws SQLException;
	public  List<Question> readQuestionsTouristes(Connection connection) throws SQLException;
	public  List<Question> readActivityQuestions(String ontologie,Connection connection) throws SQLException;
	public  List<Question> readModeDeTransportQuestions(Connection connection) throws SQLException;
	public  List<Question> readSequenceActivityQuestions(String ontologie,Connection connection) throws SQLException;
	public  List<Question> readLieuxQuestions(Connection connection) throws SQLException;
	public List<Reponse> readSimilarityDegreesById(int userid,Connection connection) throws SQLException;
	public List<Float> getQuestionAnswers(String questionnaire,int idquestion,Connection C) throws SQLException;
	public List<Integer> getTheQuestions(Connection C, String questionnaire) throws SQLException;
	public List<Float> getQuestionsWithAnswers(Connection C,String questionnaire) throws SQLException;
	
}
