package jp.co.sunarch.choseisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventViewController {

	// 出欠回答
	@RequestMapping("/eventView")
	public String eventView(Model model) {
		return "eventView";
	}
}