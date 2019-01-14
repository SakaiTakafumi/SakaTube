package jp.co.sunarch.sakatube.controller;

import java.util.Map;

import jp.co.sunarch.sakatube.form.VideoInfo;
import jp.co.sunarch.sakatube.service.VideoUploadService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value="sakaTube")
public class VideoListController {

	@RequestMapping(value="/videoUpload", method = RequestMethod.POST)
	public Map<String, String> videoUpload(@ModelAttribute VideoInfo videoInfo) {

		VideoUploadService videoUploadService = new VideoUploadService();
		Map<String, String> resultMap = videoUploadService.insertVideo(videoInfo);

		return resultMap;
	}
}
