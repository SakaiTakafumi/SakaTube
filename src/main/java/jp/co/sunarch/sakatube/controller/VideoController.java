package jp.co.sunarch.sakatube.controller;

import jp.co.sunarch.sakatube.dto.VideoInfoDto;
import jp.co.sunarch.sakatube.form.VideoInfoForm;
import jp.co.sunarch.sakatube.service.VideoSearchService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class VideoController {

	private final VideoSearchService videoSearchService;

	public VideoController (VideoSearchService videoSearchService) {
		this.videoSearchService = videoSearchService;
	}

	@RequestMapping("/")
	public String index(Model model) {
		return "redirect:videos";
	}

	// 動画一覧画面表示
	@RequestMapping("/videos")
	public String videoList(Model model) {
		model.addAttribute("videoInfo", new VideoInfoForm());
		return "videoList";
	}

	// 動画再生画面表示
	@RequestMapping("/video/{id}")
	public String videoPlay(Model model, @PathVariable("id") Long id) {

		VideoInfoDto videoInfoDto = videoSearchService.searchVideoInfoById(id);

		// 存在しない動画の時にエラーページに遷移させる。
		if (videoInfoDto == null) {
			throw new HttpStatus404Exception();
		}

		model.addAttribute("path", "/api/video/" + videoInfoDto.getId() + "/"+ videoInfoDto.getExtension());
		model.addAttribute("title", videoInfoDto.getTitle());
		model.addAttribute("note", videoInfoDto.getNote());

		return "video";
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	private class HttpStatus404Exception extends RuntimeException {
	}
}
