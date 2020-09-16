package org.sondage.controller;
import java.util.List;
import org.sondage.beans.Question;
import org.sondage.beans.Reponse;
import org.sondage.beans.User;

public interface Controller {
	
	public boolean maestroWriteResponse(List<Reponse> liste);
	public boolean  maestroWriteUser(User U);
	public int  maestroWriteIdResponse(String questionnaire);
	public  List<Question> maestroReadQuestionsEnfants();
	public  List<Question> maestroReadQuestionsTouristes();
	public  List<Question> maestroReadLieuxQuestions();
	public  List<Question> maestroReadSequenceActivityQuestions(String ontologie);
	public  List<Question> maestroReadModeDeTransportQuestions();
	public  List<Question> maestroReadActivityQuestions(String ontologie);
	public List<Reponse> maestroGetAnswersById(int userid);
	public List<Reponse> maestroGetAnswersWithMedian(String questionnaire);
	
}
