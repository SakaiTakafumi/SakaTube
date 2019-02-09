package jp.co.sunarch.sakatube.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jp.co.sunarch.sakatube.form.VideoInfo;
import jp.co.sunarch.sakatube.service.VideoSearchService;
import jp.co.sunarch.sakatube.service.VideoUploadService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping(value="sakaTube", headers={"Accept=application/*", "Content-Type=multipart/*"})
public class VideoApiController {

	/**
	 * アップロードボタン押下時
	 *
	 * @param videoInfo
	 *
	 * @return resultMap
	 * @throws IOException
	 */
	@RequestMapping(value="api/upload", method = RequestMethod.POST)
	public Map<String, String> videoUpload(@ModelAttribute VideoInfo videoInfo) throws IOException {

		VideoUploadService videoUploadService = new VideoUploadService();
		Map<String, String> resultMap = videoUploadService.insertVideo(videoInfo);

		return resultMap;
	}


	/**
	 * 検索ボタン押下時
	 *
	 * @param keyWord
	 *
	 * @return resultList
	 */
	@RequestMapping(value="api/search")
	public List<VideoInfo> videoSearch(@RequestBody String keyWord) {

		VideoSearchService videoSearchService = new VideoSearchService();

		// 検索結果を取得する。
		List<VideoInfo> resultList = videoSearchService.searchVideoInfoByKeyword(keyWord);

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
	public void videoSearch(@PathVariable("id") Long id, @PathVariable("extension") String extension, HttpServletResponse res) throws IOException {

		VideoSearchService videoSearchService = new VideoSearchService();
		InputStream inputStream = videoSearchService.searchVideoById(id);
		OutputStream outputStream = res.getOutputStream();

//		res.addHeader("Content-Type", "video/mp4");
		copy(inputStream, outputStream);
	}

	private void copy(InputStream in, OutputStream out) throws IOException {
		byte[] buff = new byte[8192];
		int len = -1;
		while ((len = in.read(buff, 0, buff.length)) != -1) {
			out.write(buff, 0, len);
		}
	}
}