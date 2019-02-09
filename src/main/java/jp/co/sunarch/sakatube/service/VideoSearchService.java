package jp.co.sunarch.sakatube.service;

import java.io.InputStream;
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

		// IDから動画情報を取得する。
		SelectVideoInfo selectVideoInfo = videoInfoDao.findVideoInfoById(id);

		VideoInfo videoInfo = new VideoInfo();
		videoInfo.setId(selectVideoInfo.getVideoId());
		videoInfo.setTitle(selectVideoInfo.getVideoTitle());
		videoInfo.setNote(selectVideoInfo.getVideoNote());
		videoInfo.setExtension(selectVideoInfo.getExtension());
		return videoInfo;
	}

	/**
	 * 動画を取得します。
	 *
	 * @param keyWord
	 */
	public InputStream searchVideoById(Long id) {
		// IDから動画を取得する。
		InputStream video = videoInfoDao.findVideoById(id);

		return video;
	}
}
