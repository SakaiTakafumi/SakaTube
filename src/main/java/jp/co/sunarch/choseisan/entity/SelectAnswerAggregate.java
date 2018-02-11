package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class SelectAnswerAggregate {
	@Id
	protected Long eventId;
	protected String eventSchedule;
	protected String circleCnt;
	protected String triangleCnt;
	protected String crossCnt;

	public SelectAnswerAggregate(){
		super();
	}
    public SelectAnswerAggregate (
    		Long eventId,
    		String eventSchedule,
    		String circleCnt,
    		String triangleCnt,
    		String crossCnt) {

    	super();
    	this.eventId = eventId;
    	this.eventSchedule = eventSchedule;
    	this.circleCnt = circleCnt;
    	this.triangleCnt = triangleCnt;
    	this.circleCnt = crossCnt;
	}

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventSchedule() {
		return eventSchedule;
	}
	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
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
		return "SelectEvent [eventId=" + eventId + ", eventSchedule="
				+ eventSchedule + ", circleCnt=" + circleCnt + ", triangleCnt="
				+ triangleCnt + ", crossCnt=" + crossCnt + "]";
	}
}