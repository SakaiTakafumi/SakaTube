package jp.co.sunarch.sakatube.controller;

import jp.co.sunarch.sakatube.Dto.VideoInfoDto;
import jp.co.sunarch.sakatube.form.VideoInfoForm;
import jp.co.sunarch.sakatube.service.VideoSearchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VideoController {

	// 動画一覧画面表示
	@RequestMapping("/videos")
	public String videoList(Model model) {
		model.addAttribute("videoInfo", new VideoInfoForm());
		return "videoList";
	}

	// 動画再生画面表示
	@RequestMapping("/video/{id}")
	public String videoPlay(Model model, @PathVariable("id") Long id) {

		VideoSearchService videoSearchService = new VideoSearchService();
		VideoInfoDto videoInfoDto = videoSearchService.searchVideoInfoById(id);

		model.addAttribute("path", "/api/video/" + videoInfoDto.getId() + "/"
				+ videoInfoDto.getExtension());
		model.addAttribute("title", videoInfoDto.getTitle());
		model.addAttribute("note", videoInfoDto.getNote());

		return "video";
	}
}
