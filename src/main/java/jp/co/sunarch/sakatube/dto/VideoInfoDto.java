package jp.co.sunarch.sakatube.dto;

public class VideoInfoDto {

	private Long id;

	private String title;

	private String note;

	private String extension;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "VideoInfo [id=" + id + ", title=" + title + ", note=" + note
				+ ", extension=" + extension + "]";
	}
}