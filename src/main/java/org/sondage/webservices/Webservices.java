package org.sondage.webservices;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.sondage.beans.Question;
import org.sondage.beans.Reponse;
import org.sondage.beans.User;
import org.sondage.controller.impl.ControllerImpl;
import com.google.gson.Gson;

@Path("/user")
public class Webservices {

	public static String lequestionnaire="";

	@GET
	@Path("/msg")
	public Response getMsg() {

		String output = "Jersey say : hi";

		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/reponse")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response  addAnswer(String rep){
		List<Reponse> liste=new ArrayList<Reponse>();
		ControllerImpl daoInstance=new ControllerImpl();
		JSONArray jsonArray=null;
		ArrayList<String> stringArray=null;
		try {
			jsonArray= new JSONArray(rep);
			stringArray= new ArrayList<String>();
			for (int i = 0; i < jsonArray.length(); i++) {
				stringArray.add(jsonArray.getString(i));
			}
			Gson g = new Gson();
			for(int i=0;i<stringArray.size();i++){
				Reponse repon = g.fromJson(stringArray.get(i), Reponse.class);
				liste.add(repon);
			}
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		boolean h=daoInstance.maestroWriteResponse(liste);
		String outputConnection ="";
		if(h){
			outputConnection ="TRUE";
		}
		else{
			outputConnection="FALSE";
		}
		return Response.status(200).entity(outputConnection).build();
	}

	@GET
	@Path("/questions/touristes")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListTouristes(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		liste=daoInstance.maestroReadQuestionsTouristes();
		Collections.shuffle(liste);
		Gson g = new Gson();
		String h=g.toJson(liste);
		return h;
	}

	@GET
	@Path("/questions/enfants")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListEnfants(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		liste=daoInstance.maestroReadQuestionsEnfants();
		Collections.shuffle(liste);
		Gson g = new Gson();
		String h=g.toJson(liste);
		return h;
	}

	@GET
	@Path("/questionstransport")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListTransport(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		liste=daoInstance.maestroReadModeDeTransportQuestions();
		Collections.shuffle(liste);
		Gson g = new Gson();
		String h=g.toJson(liste);
		return h;
	}

	@GET
	@Path("/questionsLieux")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListLieux(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		liste=daoInstance.maestroReadLieuxQuestions();
		Collections.shuffle(liste);
		Gson g = new Gson();
		String h=g.toJson(liste);
		return h;
	}

	@GET
	@Path("/questionsactivites")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListActivites(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		if(lequestionnaire.toString().equals("Enfants")){
			liste=daoInstance.maestroReadActivityQuestions(lequestionnaire.toLowerCase());
		}
		if(lequestionnaire.toString().equals("Touristes")){
			liste=daoInstance.maestroReadActivityQuestions(lequestionnaire.toLowerCase());
		}
		Collections.shuffle(liste);
		Gson g = new Gson();
		String h=g.toJson(liste);
		return h;
	}

	@GET
	@Path("/questionsequence")
	@Produces(MediaType.APPLICATION_JSON)
	public String  getListSequences(){
		ControllerImpl daoInstance = new ControllerImpl();
		List<Question> liste=new ArrayList<Question>();
		if(lequestionnaire.toString().equals("Enfants")){
			liste=daoInstance.maestroReadSequenceActivityQuestions(lequestionnaire.toLowerCase());
		}
		if(lequestionnaire.toString().equals("Touristes")){
			liste=daoInstance.maestroReadSequenceActivityQuestions(lequestionnaire.toLowerCase());
		}
		Collections.shuffle(liste);
		Gson g = new Gson();
		g.toJson(liste);
		return g.toJson(liste);
	}

	@POST
	@Path("/getanswers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserAnswers(String userid){
		ControllerImpl daoInstance=new ControllerImpl();
		int theUserId=Integer.parseUnsignedInt(userid);
		List<Reponse> map = daoInstance.maestroGetAnswersById(theUserId);
		Gson g = new Gson();
		return g.toJson(map);
	}

	
	@GET
	@Path("/getMedianOfAnswers")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMedianOfAnswers(){
		ControllerImpl daoInstance=new ControllerImpl();
		List<Reponse> liste1 = daoInstance.maestroGetAnswersWithMedian(lequestionnaire);
		Gson g = new Gson();
		return g.toJson(liste1);
	}

	
	@POST
	@Path("/registeruser")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addUser(String user){
		ControllerImpl daoInstance=new ControllerImpl();
		Gson g = new Gson();
		User p = g.fromJson(user, User.class);
		p.setQuestionnaire(lequestionnaire);
		boolean h=daoInstance.maestroWriteUser(p);
		String outputConnection ="";
		if(h==true){
			outputConnection ="TRUE";
		}
		else{
			outputConnection="FALSE";
		}
		lequestionnaire = p.getQuestionnaire();
		return outputConnection;
	}

	@GET
	@Path("/register/{questionnaire}")
	public Response addQuestionnaire(@PathParam("questionnaire") String questionnaire){
		String outputConnection ="";
		ControllerImpl daoInstance=new ControllerImpl();
		lequestionnaire = questionnaire;
		int a=daoInstance.maestroWriteIdResponse(questionnaire);
		if(lequestionnaire.equals("")){
			outputConnection="FALSE";
		}
		else{
			outputConnection="TRUE" + a;
		}
		return Response.status(200).entity(outputConnection).build();
	}

}
