package jp.co.sunarch.choseisan.entity;

import javax.persistence.Id;

public class AnswerForm {
	@Id
	protected String eventId;
	protected String eventDate;
	protected String participantName;
	protected String answer;

	public AnswerForm(){
		super();
	}

    public AnswerForm (
    	String eventId,
    	String eventDate,
    	String participantName,
    	String answer) {

    	super();
    	this.eventId = eventId;
    	this.eventDate = eventDate;
		this.participantName = participantName;
		this.answer = answer;
    }

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
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

	@Override
	public String toString() {
		return "AnswerForm [eventId=" + eventId + ", eventDate=" + eventDate
				+ ", participantName=" + participantName + ", answer=" + answer
				+ "]";
	}
}