package jp.co.sunarch.sakatube.form;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class VideoInfo {

	@NotNull
	private String title;
	@NotNull
	private String note;
	@NotNull
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
