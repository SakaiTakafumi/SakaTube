package jp.co.sunarch.sakatube.validation;

import java.util.Map;

import jp.co.sunarch.sakatube.form.VideoInfoForm;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Component
public class ValidateService {

	public static String[] typeList = { "MP4", "MOV", "AVI" };

	/**
	 * バリデーションチェックを行います。
	 *
	 * @param videoInfoForm
	 */
	public void executeValidation(VideoInfoForm videoInfoForm, Map<String, Boolean> resultMap) {

		// 動画タイトルの必須チェック
		checkTitleRequired(videoInfoForm.getTitle(), resultMap);
		// 選択されたファイルの存在チェック
		checkFileRequired(videoInfoForm.getVideo(), resultMap);
		// 選択されたファイルのサイズチェック
		checkFileSize(videoInfoForm.getVideo(), resultMap);
		// 選択されたファイルのタイプチェック
		checkFileType(videoInfoForm.getExtension(), resultMap);

	}

	/**
	 * 動画タイトルの必須チェックを行います。
	 *
	 * @param title
	 */
	public void checkTitleRequired(String title, Map<String, Boolean> resultMap) {
		if (StringUtils.isEmpty(title)) {
			resultMap.put("titleRequiredError", true);
		}
	}

	/**
	 * 選択されたファイルの必須チェックを行います。
	 *
	 * @param file
	 */
	public void checkFileRequired(MultipartFile file, Map<String, Boolean> resultMap) {
		if (file.isEmpty()) {
			resultMap.put("fileRequiredError", true);
		}
	}

	/**
	 * 選択されたファイルのサイズチェックを行います。
	 *
	 * @param file
	 */
	public void checkFileSize(MultipartFile file, Map<String, Boolean> resultMap) {
		if (file.getSize() > 500000000) {
			resultMap.put("fileSizeError", true);
		}
	}

	/**
	 * 選択されたファイルのタイプチェックを行います。
	 *
	 * @param file
	 */
	public void checkFileType(String extension, Map<String, Boolean> resultMap) {
		for (String okType : typeList) {
			if (StringUtils.equals(extension, okType)) {
				return;
			}
		}
		resultMap.put("fileTypeError", true);
	}
}