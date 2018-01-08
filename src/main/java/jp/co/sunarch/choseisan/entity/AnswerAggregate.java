package jp.co.sunarch.choseisan.entity;

import javax.persistence.Id;

public class AnswerAggregate {
	@Id
	protected String eventDate;
	protected String circleCnt;
	protected String triangleCnt;
	protected String crossCnt;

	public AnswerAggregate(){
		super();
	}

    public AnswerAggregate (
    	String eventDate,
    	String circleCnt,
    	String triangleCnt,
    	String crossCnt) {

    	super();
    	this.eventDate = eventDate;
		this.circleCnt = circleCnt;
		this.triangleCnt = triangleCnt;
		this.crossCnt = crossCnt;
    }

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getCircleCnt() {
		return circleCnt;
	}

	public void setCircleCnt(String circleCnt) {
		this.circleCnt = circleCnt;
	}

	public String getTriangleCnt() {
		return triangleCnt;
	}

	public void setTriangleCnt(String triangleCnt) {
		this.triangleCnt = triangleCnt;
	}

	public String getCrossCnt() {
		return crossCnt;
	}

	public void setCrossCnt(String crossCnt) {
		this.crossCnt = crossCnt;
	}

	@Override
	public String toString() {
		return "AnswerAggregate [eventDate=" + eventDate + ", circleCnt="
				+ circleCnt + ", triangleCnt=" + triangleCnt + ", crossCnt="
				+ crossCnt + "]";
	}
}