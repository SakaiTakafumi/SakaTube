package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_SCHEDULE")
public class AnswerInfo {
	@Id
	protected Long answererId;
	protected String eventSchedule;
	protected String answer;

	public AnswerInfo(){
		super();
	}

    public AnswerInfo (
    	Long answererId,
    	String eventSchedule,
    	String answer) {

    	super();
    	this.answererId = answererId;
		this.eventSchedule = eventSchedule;
		this.answer = answer;
    }

	public Long getAnswererId() {
		return answererId;
	}

	public void setAnswererId(Long answererId) {
		this.answererId = answererId;
	}

	public String getEventSchedule() {
		return eventSchedule;
	}

	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Answer [answererId=" + answererId + ", eventSchedule="
				+ eventSchedule + ", answer=" + answer + "]";
	}
}