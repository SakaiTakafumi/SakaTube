package jp.co.sunarch.choseisan.form;

import java.util.List;

public class AnswerForm {
	private Long eventId;
	private String answererName;
	private List<String> eventScheduleList;
	private String checkList;
	private String answerComment;

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getAnswererName() {
		return answererName;
	}
	public void setAnswererName(String answererName) {
		this.answererName = answererName;
	}
	public List<String> getEventScheduleList() {
		return eventScheduleList;
	}
	public void setEventScheduleList(List<String> eventScheduleList) {
		this.eventScheduleList = eventScheduleList;
	}
	public String getCheckList() {
		return checkList;
	}
	public void setCheckList(String checkList) {
		this.checkList = checkList;
	}
	public String getAnswerComment() {
		return answerComment;
	}
	public void setAnswerComment(String answerComment) {
		this.answerComment = answerComment;
	}

}
