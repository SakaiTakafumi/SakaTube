package jp.co.sunarch.sakatube.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import jp.co.sunarch.sakatube.dao.VideoInfoDAO;
import jp.co.sunarch.sakatube.entity.VideoInfoEntity;
import jp.co.sunarch.sakatube.form.VideoInfoForm;
import jp.co.sunarch.sakatube.validation.ValidateService;

public class VideoUploadService {

	/**
	 * 動画の登録処理を行います。
	 *
	 * @param videoInfoForm
	 * @throws IOException
	 */
	public Map<String, Boolean> insertVideo(VideoInfoForm videoInfoForm) throws IOException {
		// レスポンス返却用
		Map<String, Boolean> resultMap = new HashMap<>();

		String extension = videoInfoForm.getVideo().getOriginalFilename().substring(videoInfoForm.getVideo().getOriginalFilename().lastIndexOf(".") + 1).toUpperCase();
		videoInfoForm.setExtension(extension);

		// バリデーションチェック
		ValidateService validateService = new ValidateService();
		validateService.executeValidation(videoInfoForm, resultMap);

		// この時点でMapが空ではない場合は、何らかのバリデーションエラーがあるので、動画の登録処理は行わない。
		if (!resultMap.isEmpty()) {
			resultMap.put("uploadSuccess", false);
			return resultMap;
		}

		// 動画の登録処理
		try (InputStream inputStream = videoInfoForm.getVideo()
				.getInputStream()) {
			VideoInfoEntity videoInfoEntity = new VideoInfoEntity(null,
					videoInfoForm.getTitle(), videoInfoForm.getNote(),
					videoInfoForm.getExtension(), inputStream);

			VideoInfoDAO videoInfoDao = new VideoInfoDAO();

			// 登録実行。実行の成否によってレスポンスを分ける。
			if (videoInfoDao.insertVideoInfo(videoInfoEntity)) {
				resultMap.put("uploadSuccess", true);
			} else {
				resultMap.put("uploadSuccess", false);
			}
		} catch (IOException e) {
			throw e;
		}
		return resultMap;
	}
}
