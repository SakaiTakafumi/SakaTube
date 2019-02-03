package jp.co.sunarch.sakatube.form;

import org.springframework.web.multipart.MultipartFile;

public class VideoInfo {

	private Long id;

	private String title;

	private String note;

	private String extension;

	private MultipartFile video;

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
	public MultipartFile getVideo() {
		return video;
	}
	public void setVideo(MultipartFile video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "VideoInfo [id=" + id + ", title=" + title + ", note=" + note
				+ ", extension=" + extension + ", video=" + video + "]";
	}
}