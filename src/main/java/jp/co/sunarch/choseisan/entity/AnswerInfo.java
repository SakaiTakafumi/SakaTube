package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "ANSWER_INFO")
public class AnswerInfo {
	@Id
	protected String eventId;
	protected String participantName;
	protected String answer;
	protected String answerComment;
	protected String deleteFlag;

	public AnswerInfo(){
		super();
	}

    public AnswerInfo (
    	String eventId,
    	String participantName,
    	String answer,
    	String answerComment,
    	String deleteFlag) {

    	super();
    	this.eventId = eventId;
		this.participantName = participantName;
		this.answer = answer;
		this.answerComment = answerComment;
		this.deleteFlag = deleteFlag;
    }

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAnswerComment() {
		return answerComment;
	}

	public void setAnswerComment(String answerComment) {
		this.answerComment = answerComment;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "AnswerInfo [eventId=" + eventId + ", participantName="
				+ participantName + ", answer=" + answer + ", answerComment="
				+ answerComment + ", deleteFlag=" + deleteFlag + "]";
	}
}