package jp.co.sunarch.sakatube.controller;

import jp.co.sunarch.sakatube.form.VideoInfo;
import jp.co.sunarch.sakatube.service.VideoSearchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoClientController {

	private static String path = "http://localhost:8080/sakaTube/";

	// 動画再生画面表示
	@RequestMapping("/video/{id}")
	public String videoPlay(Model model, @PathVariable("id") Long id) {

		VideoSearchService videoSearchService = new VideoSearchService();
		VideoInfo videoInfo = videoSearchService.searchVideoInfoById(id);

		model.addAttribute("path", path + videoInfo.getId() + "/" + videoInfo.getExtension());
		model.addAttribute("title", videoInfo.getTitle());
		model.addAttribute("note", videoInfo.getNote());

		return "video";
	}
}
