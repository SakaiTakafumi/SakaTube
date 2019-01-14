package jp.co.sunarch.sakatube.service;

import java.util.HashMap;
import java.util.Map;

import jp.co.sunarch.sakatube.DAO.VideoInfoDAO;
import jp.co.sunarch.sakatube.form.VideoInfo;
import jp.co.sunarch.sakatube.validation.ValidateService;

public class VideoUploadService {

	/**
	 * 動画の登録処理を行います。
	 *
	 * @param videoInfo
	 */
	public Map<String, String> insertVideo(VideoInfo videoInfo) {
		// レスポンス返却用
		Map<String, String> resultMap = new HashMap<>();

		// バリデーションチェック
		ValidateService validateService = new ValidateService();
		validateService.executeValidation(videoInfo, resultMap);

		// この時点でMapが空ではない場合は、何らかのバリデーションエラーがあるので、動画の登録処理は行わない。
		if (!resultMap.isEmpty()) {
			resultMap.put("uploadSuccess", "0");
			return resultMap;
		}

		// 動画の登録処理
		VideoInfoDAO videoInfoDao = new VideoInfoDAO();
		videoInfoDao.insertVideoInfo(videoInfo, resultMap);

		return resultMap;
	}
}
