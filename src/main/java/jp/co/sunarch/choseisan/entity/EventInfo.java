package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class EventInfo {
	@Id
	protected Long eventId;
	protected String eventName;
	protected String memo;

	public EventInfo(){
		super();
	}
    public EventInfo (
    		String eventName,
    		String memo) {

    	super();
    	this.eventName = eventName;
    	this.memo = memo;
	}

	public EventInfo (
			Long eventId,
			String eventName,
			String memo) {

    	super();
    	this.eventId = eventId;
		this.eventName = eventName;
		this.memo = memo;
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

	@Override
	public String toString() {
		return "EventInfo [eventId=" + eventId + ", eventName=" + eventName
				+ ", memo=" + memo + "]";
	}
}