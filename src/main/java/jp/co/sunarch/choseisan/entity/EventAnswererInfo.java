package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class EventAnswererInfo {
	@Id
	protected Long eventId;
	protected Long answererId;
	protected String answererName;
	protected String answerComment;

	public EventAnswererInfo(){
		super();
	}
    public EventAnswererInfo (
    		Long eventId,
    		String answererName,
    		String answerComment) {

    	super();
    	this.eventId = eventId;
    	this.answererName = answererName;
    	this.answerComment = answerComment;
	}

    public EventAnswererInfo (
    		Long eventId,
    		Long answererId,
    		String answererName,
    		String answerComment) {

    	super();
    	this.eventId = eventId;
    	this.answererId = answererId;
		this.answererName = answererName;
		this.answerComment = answerComment;
    }

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Long getAnswererId() {
		return answererId;
	}
	public void setAnswererId(Long answererId) {
		this.answererId = answererId;
	}
	public String getAnswererName() {
		return answererName;
	}
	public void setAnswererName(String answererName) {
		this.answererName = answererName;
	}
	public String getAnswerComment() {
		return answerComment;
	}
	public void setAnswerComment(String answerComment) {
		this.answerComment = answerComment;
	}

	@Override
	public String toString() {
		return "EventAnswererInfo [eventId=" + eventId + ", answererId="
				+ answererId + ", answererName=" + answererName
				+ ", answerComment=" + answerComment + "]";
	}
}