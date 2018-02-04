package jp.co.sunarch.choseisan.controller;

import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DAO.EventInfoDAO;
import jp.co.sunarch.choseisan.entity.SelectEvent;
import jp.co.sunarch.choseisan.form.AnswerForm;
import jp.co.sunarch.choseisan.logic.EventAnswerLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventAnswerController {
	EventInfoDAO eventDao = new EventInfoDAO();

	// 出欠回答
	@RequestMapping("/eventAnswer/{eventId}")
	public String answer(Model model,
			@PathVariable("eventId") Long eventId) {

		List<SelectEvent> selectEventList = this.eventDao.selectEvent(eventId);
		AnswerForm answerForm = new AnswerForm();

		List<String> eventScheduleList = new ArrayList<>();

		for (SelectEvent event : selectEventList) {
			eventScheduleList.add(event.getEventSchedule());
		}

		answerForm.setEventId(eventId);
		answerForm.setEventScheduleList(eventScheduleList);

		model.addAttribute("eventName", selectEventList.get(0).getEventName());
		model.addAttribute("memo", selectEventList.get(0).getMemo());
		model.addAttribute("answerForm", answerForm);

		return "eventAnswer";
	}

	// 出欠回答
	@RequestMapping("/eventAnswer")
	public String eventAnswer(Model model,
			@ModelAttribute("answerForm") AnswerForm answerForm,
			@RequestParam("eventId") Long eventId) {

		EventAnswerLogic eventAnswerLogic = new EventAnswerLogic();
		eventAnswerLogic.eventAnswerLogic(eventId, answerForm);
		String a = null;

		return "eventView";
	}
}