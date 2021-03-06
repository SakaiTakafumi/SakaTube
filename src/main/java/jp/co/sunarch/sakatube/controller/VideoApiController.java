package jp.co.sunarch.sakatube.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.sunarch.sakatube.dto.VideoInfoDto;
import jp.co.sunarch.sakatube.form.VideoInfoForm;
import jp.co.sunarch.sakatube.service.VideoSearchService;
import jp.co.sunarch.sakatube.service.VideoUploadService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoApiController {

	private final VideoUploadService videoUploadService;
	private final VideoSearchService videoSearchService;

	public VideoApiController (VideoUploadService videoUploadService, VideoSearchService videoSearchService) {
		this.videoUploadService = videoUploadService;
		this.videoSearchService = videoSearchService;
	}

	/**
	 * アップロードボタン押下時
	 *
	 * @param videoInfo
	 *
	 * @return resultMap
	 * @throws IOException
	 */
	@RequestMapping(value = "api/upload", method = RequestMethod.POST)
	public Map<String, Boolean> videoUpload(@ModelAttribute VideoInfoForm videoInfoForm) throws IOException {

		Map<String, Boolean> resultMap = videoUploadService.insertVideo(videoInfoForm);

		return resultMap;
	}

	/**
	 * 検索ボタン押下時
	 *
	 * @param keyword
	 *
	 * @return resultList
	 */
	@RequestMapping(value = "api/search", method=RequestMethod.GET)
	public List<VideoInfoDto> videoSearch(@RequestParam String keyword) {

		// 検索結果を取得する。
		List<VideoInfoDto> resultList = videoSearchService.searchVideoInfoByKeyword(keyword);

		return resultList;
	}

	/**
	 * 動画一覧画面から動画選択時
	 *
	 * @param id
	 * @param extension
	 * @param res
	 *
	 * @throws IOException
	 */
	@RequestMapping(value = "api/video/{id}/{extension}")
	public void videoSearch(@PathVariable("id") Long id,
			@PathVariable("extension") String extension, HttpServletResponse res) throws IOException {

		try (InputStream inputStream = videoSearchService.searchVideoById(id);
				OutputStream outputStream = res.getOutputStream();) {
			// res.addHeader("Content-Type", "video/mp4");
			copy(inputStream, outputStream);
		} catch (IOException e) {
			throw e;
		}
	}

	private void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[8192];
		int len = -1;
		while ((len = in.read(buff, 0, buff.length)) != -1) {
			out.write(buff, 0, len);
		}
	}
}