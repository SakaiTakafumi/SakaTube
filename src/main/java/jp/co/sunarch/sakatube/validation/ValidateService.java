package jp.co.sunarch.sakatube.validation;

import java.util.Map;

import jp.co.sunarch.sakatube.form.VideoInfo;

import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

public class ValidateService {

	public static String[] typeList = {"MP4","MOV","AVI"};

	/**
	 * バリデーションチェックを行います。
	 *
	 * @param videoInfo
	 */
	public void executeValidation(VideoInfo videoInfo, Map<String, String> resultMap) {

		// 動画タイトルの必須チェック
		checkTitleRequired(videoInfo.getTitle(), resultMap);
		// 選択されたファイルの存在チェック
		checkFileRequired(videoInfo.getVideo(), resultMap);
		// 選択されたファイルのサイズチェック
		checkFileSize(videoInfo.getVideo(), resultMap);
		// 選択されたファイルのタイプチェック
		checkFileType(videoInfo.getExtension(), resultMap);

	}

	/**
	 * 動画タイトルの必須チェックを行います。
	 *
	 * @param title
	 */
	public void checkTitleRequired(String title, Map<String, String> resultMap) {
		if (StringUtils.isEmpty(title)) {
			resultMap.put("titleRequiredError", "1");
		}
	}

	/**
	 * 選択されたファイルの必須チェックを行います。
	 *
	 * @param file
	 */
	public void checkFileRequired(MultipartFile file, Map<String, String> resultMap) {
		if (file.isEmpty()) {
			resultMap.put("fileRequiredError", "1");
		}
	}

	/**
	 * 選択されたファイルのサイズチェックを行います。
	 *
	 * @param file
	 */
	public void checkFileSize(MultipartFile file, Map<String, String> resultMap) {
		if (file.getSize() > 500000000) {
			resultMap.put("fileSizeError", "1");
		}
	}

	/**
	 * 選択されたファイルのタイプチェックを行います。
	 *
	 * @param file
	 */
	public void checkFileType(String extension, Map<String, String> resultMap) {
		for (String okType : typeList) {
			if (StringUtils.equals(extension, okType)) {
				return;
			}
		}
		resultMap.put("fileTypeError", "1");
	}
}