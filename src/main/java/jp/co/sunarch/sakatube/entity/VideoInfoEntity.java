package jp.co.sunarch.sakatube.entity;

import java.io.InputStream;

public class VideoInfoEntity {

	protected Long videoId;
	protected String videoTitle;
	protected String videoNote;
	protected String extension;
	protected InputStream video;

	public VideoInfoEntity() {
		super();
	}

	public VideoInfoEntity(Long videoId, String videoTitle, String videoNote,
			String extension, InputStream video) {

		super();
		this.videoId = videoId;
		this.videoTitle = videoTitle;
		this.videoNote = videoNote;
		this.extension = extension;
		this.video = video;
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

	public InputStream getVideo() {
		return video;
	}

	public void setVideo(InputStream video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "VideoInfoEntity [videoId=" + videoId + ", videoTitle="
				+ videoTitle + ", videoNote=" + videoNote + ", extension="
				+ extension + ", video=" + video + "]";
	}
}