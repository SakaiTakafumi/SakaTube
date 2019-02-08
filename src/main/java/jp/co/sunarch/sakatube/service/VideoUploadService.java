package jp.co.sunarch.sakatube.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.co.sunarch.sakatube.DAO.VideoInfoDAO;
import jp.co.sunarch.sakatube.entity.VideoInfoEntity;
import jp.co.sunarch.sakatube.form.VideoInfo;
import jp.co.sunarch.sakatube.validation.ValidateService;

public class VideoUploadService {

	/**
	 * 動画の登録処理を行います。
	 *
	 * @param videoInfo
	 * @throws IOException
	 */
	public Map<String, String> insertVideo(VideoInfo videoInfo) throws IOException {
		// レスポンス返却用
		Map<String, String> resultMap = new HashMap<>();

		String extension = videoInfo.getVideo().getOriginalFilename().substring(videoInfo.getVideo().getOriginalFilename().lastIndexOf(".") + 1).toUpperCase();
		videoInfo.setExtension(extension);

		// バリデーションチェック
		ValidateService validateService = new ValidateService();
		validateService.executeValidation(videoInfo, resultMap);

		// この時点でMapが空ではない場合は、何らかのバリデーションエラーがあるので、動画の登録処理は行わない。
		if (!resultMap.isEmpty()) {
			resultMap.put("uploadSuccess", "0");
			return resultMap;
		}

		// 動画の登録処理
		VideoInfoEntity videoInfoEntity = new VideoInfoEntity(videoInfo.getId(),
				videoInfo.getTitle(), videoInfo.getNote(), videoInfo.getExtension(), videoInfo.getVideo().getInputStream());

		VideoInfoDAO videoInfoDao = new VideoInfoDAO();

		// 登録実行。実行の成否によってレスポンスを分ける。
		if (videoInfoDao.insertVideoInfo(videoInfoEntity)) {
			resultMap.put("uploadSuccess", "1");
		} else {
			resultMap.put("uploadSuccess", "0");
		}

		return resultMap;
	}
}
