package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class SelectEvent {
	@Id
	protected Long eventId;
	protected String eventName;
	protected String memo;
	protected String eventSchedule;

	public SelectEvent(){
		super();
	}
    public SelectEvent (
    		Long eventId,
    		String eventName,
    		String memo,
    		String eventSchedule) {

    	super();
    	this.eventId = eventId;
    	this.eventName = eventName;
    	this.memo = memo;
    	this.eventSchedule = eventSchedule;
	}

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEventSchedule() {
		return eventSchedule;
	}
	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
	}

	@Override
	public String toString() {
		return "SelectEvent [eventId=" + eventId + ", eventName=" + eventName
				+ ", memo=" + memo + ", eventSchedule=" + eventSchedule + "]";
	}
}