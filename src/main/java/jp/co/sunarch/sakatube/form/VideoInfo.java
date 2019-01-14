package jp.co.sunarch.sakatube.form;

import org.springframework.web.multipart.MultipartFile;

public class VideoInfo {

	private String title;

	private String note;

	private MultipartFile video;

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
	public MultipartFile getVideo() {
		return video;
	}
	public void setVideo(MultipartFile video) {
		this.video = video;
	}
}
