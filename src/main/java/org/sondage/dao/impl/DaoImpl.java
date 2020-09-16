package org.sondage.dao.impl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.sondage.beans.DatabaseAccess;
import org.sondage.beans.Question;
import org.sondage.beans.Reponse;
import org.sondage.beans.User;
import org.sondage.dao.Dao;

public class DaoImpl implements Dao{

	public Connection connect(String dataBaseName, String username,
			String password, int numeroPort, String bdd_IP) {
		System.out.println("-------- Mysql "+ "JDBC Connection Testing ------------");
		Connection connection = null;
		try{
			DatabaseAccess.readProperties();
			Properties properties = DatabaseAccess.properties;
			Class.forName(properties.getProperty("DB_DRIVER"));
		}
		catch(ClassNotFoundException e){
			System.out.println("Where is your Mysql JDBC Driver? "+ "Include in your library path!");
			e.printStackTrace();
			return connection;
		} 
		System.out.println("Mysql JDBC Driver Registered!");
		try{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://"+bdd_IP+":"+numeroPort+"/"+dataBaseName, username,
					password);
		}
		catch(SQLException e){
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		}
		else{
			System.out.println("Failed to make connection!");
		}
		return connection;
	}

	public void writeResponse(List<Reponse> liste, Connection C) throws SQLException {
		PreparedStatement statement=null;
		String insertTableSQL = "INSERT INTO tbl_reponses (idu,idquestion,degre_similarite)"
				+ " VALUES (?,?,?)";
		statement = C.prepareStatement(insertTableSQL);
		for(Reponse rep:liste){
			Float degresimilarite = rep.getDegresimilarite();
			int idUser = rep.getIduser();
			int idQuestion =rep.getIdquestion();
			statement.setInt(1,idUser);
			statement.setInt(2,idQuestion);
			double d=degresimilarite;
			statement.setDouble(3,d);
			System.out.println(insertTableSQL);
			statement.execute();
		}
		if (statement != null) {
			statement.close();
		}
		if (C != null) {
			System.out.println("Your connection is closed.");
			C.close();
		}
	}

	public List<Float> getQuestionAnswers(String questionnaire,int idquestion,Connection C) throws SQLException{
		List<Float> liste = new ArrayList<Float>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select degre_similarite from tbl_reponses,tbl_utilisateur where tbl_reponses.idu = tbl_utilisateur.id AND "
				+ "tbl_utilisateur.questionnaire = '"+questionnaire+"' AND tbl_reponses.idquestion = "+idquestion;
		st = C.prepareStatement(sql);
		rs = st.executeQuery();
		while(rs.next()) {
			Float degre_similarite=rs.getFloat("degre_similarite");
			liste.add(degre_similarite);
		}
		if (st != null) {
			st.close();
		}
		if (rs!= null) {
			rs.close();
		}
		return liste;
	}
	
	public List<Integer> getTheQuestions(Connection C, String questionnaire) throws SQLException {
		List<Integer> liste = new ArrayList<Integer>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select idquestion from tbl_ontologies_"+ questionnaire.toLowerCase();
		st = C.prepareStatement(sql);
		rs = st.executeQuery();
		while(rs.next()) {
			int idquestion=rs.getInt("idquestion");
			liste.add(idquestion);
		}
		if (st != null) {
			st.close();
		}
		if (rs!= null) {
			rs.close();
		}
		return liste;
	}
	
	public Float calculateMedian (List<Float> a){
		Collections.sort(a);
		int middle = a.size()/2;
		if (a.size() % 2 == 1) {
			return a.get(middle);
		} else {
			return (float) ((a.get(middle-1) + a.get(middle)) / 2.0);
		}
	}
	public boolean writeUser(User U, Connection C) throws SQLException {
		boolean a=true;
		PreparedStatement statement = null;
		String sexe = U.getSexe();
		String agegroup = U.getAgegroup();
		String expert=U.getExpert();
		String email=U.getEmail();
		String profession=U.getProfession();
		if(expert.equals("Un de mes sujets d'étude est la mobilité des enfants")){
			expert="Un de mes sujets d''études est la mobilité des enfants";
		}
		String insertTableSQL = "UPDATE tbl_utilisateur SET sexe='"+sexe+"', agegroupe='"+agegroup+"', expert='"+expert+"', email='"+email+"', profession='"+profession+
				"' WHERE id="+U.getId();
		statement = C.prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
		System.out.println(insertTableSQL);
		statement.executeUpdate();
		if (statement != null) {
			statement.close();
		}
		if (C != null) {
			System.out.println("Your connection is closed.");
			C.close();
		}
		return a ;
	}

	public int writeIdResponse(Connection C,String questionnaire) throws SQLException {
		int id=0;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String insertTableSQL = "INSERT INTO tbl_utilisateur (sexe,agegroupe,questionnaire,email,profession,expert)"
				+ "VALUES ('"+""+"','"+""+"','"+questionnaire+"','"+""+"','"+""+"','"+""+"')";
		statement = C.prepareStatement(insertTableSQL,Statement.RETURN_GENERATED_KEYS);
		System.out.println(insertTableSQL);
		statement.executeUpdate();
		rs = statement.getGeneratedKeys();
		if(rs.next()){
			id=(int) rs.getInt(1);
		}
		if (statement != null) {
			statement.close();
		}
		if (C != null) {
			System.out.println("Your connection is closed.");
			C.close();
		}
		return id;
	}

	public List<Question> readQuestionsEnfants(Connection connection) throws SQLException{
		ArrayList<Question> list = new ArrayList<>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_enfants";
		st = connection.prepareStatement(sql);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			list.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return list;
	}

	public List<Question> readQuestionsTouristes(Connection connection) throws SQLException {
		ArrayList<Question> list = new ArrayList<>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_touristes";
		st = connection.prepareStatement(sql);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			list.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}

		return list;
	}

	public List<Question> readActivityQuestions(String ontologies,Connection connection)
			throws SQLException {
		List<Question> liste=new ArrayList<Question>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_"+ontologies +" where id_category=?";
		st = connection.prepareStatement(sql);
		st.setInt(1,3);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			liste.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return liste;
	}

	public List<Question> readModeDeTransportQuestions(Connection connection)
			throws SQLException {
		List<Question> liste=new ArrayList<Question>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_enfants where id_category=?";
		st = connection.prepareStatement(sql);
		st.setInt(1,1);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			liste.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return liste;
	}

	public List<Question> readSequenceActivityQuestions(String ontologies,Connection connection)
			throws SQLException {
		List<Question> liste=new ArrayList<Question>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_"+ontologies +" where id_category=?";
		st = connection.prepareStatement(sql);
		st.setInt(1,4);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			liste.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return liste;
	}

	public List<Question> readLieuxQuestions(Connection connection)
			throws SQLException {
		List<Question> liste=new ArrayList<Question>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_ontologies_touristes where id_category=?";
		st = connection.prepareStatement(sql);
		st.setInt(1,2);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("idquestion");
			String item1= rs.getString("item1");
			String item2= rs.getString("item2");
			String question=rs.getString("question");
			Question p = new Question();
			p.setId(id);
			p.setItem1(item1);
			p.setItem2(item2);
			p.setQuestion(question);
			liste.add(p);
		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return liste;
	}

	public List<Reponse> readSimilarityDegreesById(int userid,Connection connection) throws SQLException {
		List<Reponse> listeIdquestionDegree=new ArrayList<Reponse>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select * from tbl_reponses where idu = ?";
		st = connection.prepareStatement(sql);
		st.setInt(1,userid);
		rs = st.executeQuery();
		while(rs.next()) {
			Float degre=rs.getFloat("degre_similarite");
			int idquestion=rs.getInt("idquestion");
			int idreponse=rs.getInt("idresponse");
			int iduser=rs.getInt("idu");
			Reponse reponse=new Reponse(iduser,idquestion,degre,idreponse);
			listeIdquestionDegree.add(reponse);

		}
		if (connection != null) {
			System.out.println("Your connection is closed.");
			connection.close();
		}
		if (rs!= null) {
			rs.close();
		}
		if (st != null) {
			st.close();
		}
		return listeIdquestionDegree;
	}

	public List<Integer> getUsersWhoAnswered(Connection connection,String questionnaire) throws SQLException{
		List<Integer> liste = new ArrayList<Integer>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select distinct id from tbl_utilisateur where questionnaire = ?";
		st = connection.prepareStatement(sql);
		st.setString(1,questionnaire);
		rs = st.executeQuery();
		while(rs.next()) {
			int id=rs.getInt("id");
			liste.add(id);
		}
		return liste;
	}

	public List<Float> getQuestionsWithAnswers(Connection C,String questionnaire) throws SQLException {
		List<Float> liste = new ArrayList<Float>();
		PreparedStatement st=null;
		ResultSet rs=null;
		String sql = "Select idquestion,degre_similarite from tbl_reponses,tbl_utilisateur where tbl_reponses.idu = tbl_utilisateur.id AND "
				+ "tbl_utilisateur.questionnaire = '"+questionnaire+"' Order by idquestion ";
		st = C.prepareStatement(sql);
		rs = st.executeQuery();
		while(rs.next()) {
			Float degre_similarite = rs.getFloat("degre_similarite");
			liste.add(degre_similarite);
		}
		if (st != null) {
			st.close();
		}
		if (C != null) {
			System.out.println("Your connection is closed.");
			C.close();
		}
		if (rs!= null) {
			rs.close();
		}
		return liste;
	}
}