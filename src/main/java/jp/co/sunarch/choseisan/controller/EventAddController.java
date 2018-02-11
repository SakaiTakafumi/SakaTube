package jp.co.sunarch.choseisan.controller;

import javax.validation.Valid;

import jp.co.sunarch.choseisan.form.EventAddForm;
import jp.co.sunarch.choseisan.logic.EventAddLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class EventAddController {

	// イベント情報登録
	@RequestMapping("/eventAdd")
	public String eventAdd(Model model) {
		model.addAttribute("eventAddForm", new EventAddForm());
		return "eventAdd";
	}

	// イベント情報登録完了
	@RequestMapping(value="/eventAddResult", method=RequestMethod.POST)
	public String eventAddResult(Model model,
			@Valid @ModelAttribute("eventAddForm") EventAddForm eventAddForm,
			BindingResult errors) {

		if (errors.hasErrors()) {
	        return "eventAdd";
	    }
		EventAddLogic eventAddLogic = new EventAddLogic();
		Long eventId = eventAddLogic.eventAddLogic(eventAddForm.getEventName(), eventAddForm.getEventSchedule(), eventAddForm.getMemo());

		String eventUrl = "http://localhost:8080/eventAnswer/" + eventId.toString();

		model.addAttribute("eventId", eventId);
		model.addAttribute("eventUrl", eventUrl);
		return "eventAddResult";
	}
}
