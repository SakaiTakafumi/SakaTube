package jp.co.sunarch.choseisan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO テーブル名を決める(イベント情報)
@Entity
@Table(name = "EVENT_SCHEDULE")
public class EventSchedule {
	@Id
	protected Long eventId;
	protected String eventSchedule;

	public EventSchedule(){
		super();
	}

    public EventSchedule (
    	Long eventId,
    	String eventSchedule) {

    	super();
    	this.eventId = eventId;
		this.eventSchedule = eventSchedule;
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

	@Override
	public String toString() {
		return "EventSchedule [eventId=" + eventId + ", eventSchedule="
				+ eventSchedule + "]";
	}
}