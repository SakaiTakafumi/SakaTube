package jp.co.sunarch.sakatube.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jp.co.sunarch.sakatube.service.VideoListService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value="sakaTube")
public class VideoListController {

	@RequestMapping(value="/videoUpload", method = RequestMethod.POST)
	public Map<String, String> videoUpload(
			@RequestParam("title") String title,
			@RequestParam("note") String note,
			@RequestParam("video") MultipartFile video) {

//		String title = request.getParameter("title");
//		String note = request.getParameter("note");
//		String video = request.getParameter("video");
		VideoListService videoListService = new VideoListService();
		videoListService.insertVideo(title, note, video);

		try {
			byte[] a = video.getBytes();
			System.out.println(video.getSize());
			System.out.println(video.getSize());
			System.out.println("");
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("note", note);
//		map.put("video", video);
		System.out.println("title : " + title);
		System.out.println("note : " + note);
//		System.out.println("video : " + video);

		return map;
	}
}
