package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_INFO")
public class EventInfo {
	@Id
	protected String eventId;
	protected String eventName;
	protected String eventDate;
	protected String memo;
	protected String deleteFlag;

	public EventInfo(){
		super();
	}

    public EventInfo (
    	String eventId,
    	String eventName,
    	String eventDate,
    	String memo,
    	String deleteFlag) {

    	super();
    	this.eventId = eventId;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.memo = memo;
		this.deleteFlag = deleteFlag;
    }

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDate() {
		return eventDate;
	}

	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "EventInfo [eventId=" + eventId + ", eventName=" + eventName
				+ ", eventDate=" + eventDate + ", memo=" + memo
				+ ", deleteFlag=" + deleteFlag + "]";
	}
}