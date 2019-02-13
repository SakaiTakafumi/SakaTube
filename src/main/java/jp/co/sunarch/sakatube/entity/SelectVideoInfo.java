package jp.co.sunarch.sakatube.entity;

import org.springframework.stereotype.Component;

@Component
public class SelectVideoInfo {

	protected Long videoId;
	protected String videoTitle;
	protected String videoNote;
	protected String extension;

	public SelectVideoInfo() {
		super();
	}

	public SelectVideoInfo(Long videoId, String videoTitle, String videoNote,
			String extension) {

		super();
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.videoNote = videoNote;
		this.extension = extension;
	}

	public Long getVideoId() {
		return videoId;
	}

	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}

	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	public String getVideoNote() {
		return videoNote;
	}

	public void setVideoNote(String videoNote) {
		this.videoNote = videoNote;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Override
	public String toString() {
		return "SelectVideoInfo [videoId=" + videoId + ", videoTitle="
				+ videoTitle + ", videoNote=" + videoNote + ", extension="
				+ extension + "]";
	}
}