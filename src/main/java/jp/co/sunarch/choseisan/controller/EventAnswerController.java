package jp.co.sunarch.choseisan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.sunarch.choseisan.DAO.EventAnswererInfoDAO;
import jp.co.sunarch.choseisan.DAO.EventInfoDAO;
import jp.co.sunarch.choseisan.entity.SelectAnswerAggregate;
import jp.co.sunarch.choseisan.entity.SelectEvent;
import jp.co.sunarch.choseisan.form.AnswerForm;
import jp.co.sunarch.choseisan.logic.EventAnswerLogic;
import jp.co.sunarch.choseisan.logic.EventViewLogic;

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

		// イベント回答処理
		EventAnswerLogic eventAnswerLogic = new EventAnswerLogic();
		eventAnswerLogic.eventAnswerLogic(eventId, answerForm);

		EventViewLogic eventViewLogic = new EventViewLogic();
		List<String> eventScheduleList = answerForm.getEventScheduleList();

		// イベント情報を取得
		EventInfoDAO eventInfoDao = new EventInfoDAO();
		List<SelectEvent> event = eventInfoDao.selectEvent(eventId);

		// 回答者リストを取得
		EventAnswererInfoDAO eventAnswererInfoDao = new EventAnswererInfoDAO();
		List<String> answererNameList = eventAnswererInfoDao.selectAnswererNameByEventId(eventId);

		// 回答状況の集計結果取得
		List<SelectAnswerAggregate> answerAggregateList = eventViewLogic.getAnswerAggregate(eventId, eventScheduleList);

		// 回答内容を取得
		Map<String, List<String>> answerMap = eventViewLogic.getAnswer(eventId, eventScheduleList);

		model.addAttribute("eventName", event.get(0).getEventName());
		model.addAttribute("memo", event.get(0).getMemo());
		model.addAttribute("answerAggregateList", answerAggregateList);
		model.addAttribute("answererNameList", answererNameList);
		model.addAttribute("answerMap", answerMap);

		return "eventView";
	}
}