package jp.co.sunarch.choseisan.controller;

import java.util.List;
import java.util.Map;

import jp.co.sunarch.choseisan.DAO.EventAnswererInfoDAO;
import jp.co.sunarch.choseisan.DAO.EventInfoDAO;
import jp.co.sunarch.choseisan.DAO.EventScheduleDAO;
import jp.co.sunarch.choseisan.entity.SelectAnswerAggregate;
import jp.co.sunarch.choseisan.entity.SelectEvent;
import jp.co.sunarch.choseisan.logic.EventViewLogic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventViewController {

	// 出欠回答
	@RequestMapping("/eventView")
	public String eventView(Model model,
			@RequestParam("eventId") Long eventId) {

		EventScheduleDAO eventScheduleDao = new EventScheduleDAO();
		EventViewLogic eventViewLogic = new EventViewLogic();

		// 候補日程を取得
		List<String> eventScheduleList = eventScheduleDao.selectEventSchedule(eventId);

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