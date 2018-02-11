package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class SelectAnswer {
	@Id
	protected String eventSchedule;
	protected String answererName;
	protected String answer;

	public SelectAnswer(){
		super();
	}
    public SelectAnswer (
    		String eventSchedule,
    		String answererName,
    		String answer) {

    	super();
    	this.eventSchedule = eventSchedule;
    	this.answererName = answererName;
    	this.answer = answer;
	}

	public String getEventSchedule() {
		return eventSchedule;
	}
	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
	}
	public String getAnswererName() {
		return answererName;
	}
	public void setAnswererName(String answererName) {
		this.answererName = answererName;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "SelectAnswerView [eventSchedule=" + eventSchedule
				+ ", answererName=" + answererName + ", answer=" + answer + "]";
	}
}