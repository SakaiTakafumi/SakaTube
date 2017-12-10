package jp.co.sunarch.choseisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TopController {
	@RequestMapping("/top")
	public String top(Model model) {
		return "top";
	}
}
