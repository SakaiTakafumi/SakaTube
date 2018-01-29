package jp.co.sunarch.choseisan.controller;

import jp.co.sunarch.choseisan.form.EventAddForm;
import jp.co.sunarch.choseisan.logic.EventAddLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class EventAddController {

	// イベント情報登録
	@RequestMapping("/eventAdd")
	public String eventAdd(Model model) {
		model.addAttribute("modelValue", "こんにちは!");
		model.addAttribute("eventAddForm", new EventAddForm());
		return "eventAdd";
	}

	// イベント情報登録完了
	@RequestMapping(value="/eventAddResult", method=RequestMethod.POST)
	public String eventAddResult(Model model,
			@RequestParam("eventName") String eventName,
			@RequestParam("eventSchedule") String eventSchedule,
			@RequestParam("memo") String memo) {

		EventAddLogic eventAddLogic = new EventAddLogic();
		Long eventId = eventAddLogic.eventAddLogic(eventName, eventSchedule, memo);

		String eventUrl = "http://localhost:8080/eventView/" + eventId.toString();


		model.addAttribute("eventId", eventId);
		model.addAttribute("eventUrl", eventUrl);
		return "eventAddResult";
	}
}
