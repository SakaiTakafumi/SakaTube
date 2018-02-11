package jp.co.sunarch.choseisan.form;

import javax.validation.constraints.NotNull;

public class EventAddForm {

	@NotNull
	private String eventName;
	@NotNull
	private String eventSchedule;
	private String memo;

	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventSchedule() {
		return eventSchedule;
	}
	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
