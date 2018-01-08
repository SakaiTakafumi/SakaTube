package jp.co.sunarch.choseisan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventAnswerController {
	// 出欠回答
	@RequestMapping(value="/answer", method=RequestMethod.POST)
	public String answer(Model model,
//			@RequestParam("answerFormList") List<AnswerForm> answerFormList) {
		@RequestParam("participantName") String participantName,
		@RequestParam("answerComment") String answerComment) {
		String aaa = null;
		return "eventView";
	}
}