package jp.co.sunarch.sakatube.controller;

import jp.co.sunarch.sakatube.form.VideoInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VideoListClientController {

	// 動画一覧画面
	@RequestMapping("/videoList")
	public String videoList(Model model) {
		model.addAttribute("videoInfo", new VideoInfo());
		return "videoList";
	}
}
