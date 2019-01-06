package jp.co.sunarch.sakatube.service;

import jp.co.sunarch.sakatube.DAO.VideoInfoDAO;

import org.springframework.web.multipart.MultipartFile;

public class VideoListService {

	/**
	 * 動画の登録処理を行います。
	 *
	 * @param title   動画タイトル
	 * @param note    動画説明
	 * @param video   動画
	 */
	public void insertVideo(String title, String note, MultipartFile video) {
		VideoInfoDAO videoInfoDao = new VideoInfoDAO();
		videoInfoDao.insertVideoInfo(title, note, video);
	}
}
