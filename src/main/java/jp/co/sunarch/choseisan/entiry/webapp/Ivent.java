package jp.co.sunarch.choseisan.entiry.webapp;

import javax.persistence.Id;

//@Entity
// TODO テーブル名を決める(イベント情報)
//@Table(name = "")
public class Ivent {
	@Id
	protected String date;
	protected String iventName;
	protected String memo;
	protected String insertDate;
	protected String updateDate;
	protected String deleteFlag;

	public Ivent(){
		super();
	}

    public Ivent (String date,
			   String iventName,
			   String memo,
			   String insertDate,
			   String updateDate,
			   String deleteFlag){

    	super();
		this.date = date;
		this.iventName = iventName;
		this.memo = memo;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
		this.deleteFlag = deleteFlag;
    }

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date =date;
	}

	public String getIventName() {
		return iventName;
	}

	public void setIventName(String iventName) {
		this.iventName = iventName;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "Os [date=" + date + ", iventName=" + iventName
				+ ", memo=" + memo +  ", insertDate="
				+ insertDate + ", updateDate=" + updateDate + ", deleteFlag="
				+ deleteFlag + "]";
	}
}