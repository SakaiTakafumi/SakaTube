package jp.co.sunarch.sakatube.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.sakatube.DAO.VideoInfoDAO;
import jp.co.sunarch.sakatube.entity.SelectVideoInfo;
import jp.co.sunarch.sakatube.form.VideoInfo;

public class VideoSearchService {

	static final VideoInfoDAO videoInfoDao = new VideoInfoDAO();

	/**
	 * キーワードで動画の検索処理を行います。
	 *
	 * @param keyWord
	 */
	public List<VideoInfo> searchVideoInfoByKeyword(String keyword) {
		// レスポンス返却用
		List<VideoInfo> resultList = new ArrayList<>();

		List<SelectVideoInfo> videoInfoList = videoInfoDao.findVideoInfoByKeyWord(keyword);

		for (SelectVideoInfo selectVideoInfo : videoInfoList) {
			VideoInfo videoInfo = new VideoInfo();
			videoInfo.setId(selectVideoInfo.getVideoId());
			videoInfo.setTitle(selectVideoInfo.getVideoTitle());
			videoInfo.setNote(selectVideoInfo.getVideoNote());
			resultList.add(videoInfo);
		}
		return resultList;
	}

	/**
	 * IDで動画の検索処理を行います。
	 *
	 * @param keyWord
	 */
	public VideoInfo searchVideoInfoById(Long id) {
		// レスポンス返却用
		List<VideoInfo> resultList = new ArrayList<>();

		SelectVideoInfo selectVideoInfo = videoInfoDao.findVideoInfoById(id);

		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setId(selectVideoInfo.getVideoId());
		videoInfo.setTitle(selectVideoInfo.getVideoTitle());
		videoInfo.setNote(selectVideoInfo.getVideoNote());
		return videoInfo;
	}

	/**
	 * 動画を取得します。
	 *
	 * @param keyWord
	 */
	public ByteArrayInputStream searchVideoById(Long id) {
		byte[] video = videoInfoDao.findVideoById(id);
		ByteArrayInputStream bis = new ByteArrayInputStream(video);

		return bis;
	}
}
