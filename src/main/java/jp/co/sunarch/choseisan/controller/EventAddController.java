package jp.co.sunarch.choseisan.controller;

import jp.co.sunarch.choseisan.UniqueId;
import jp.co.sunarch.choseisan.DAO.EventDAO;
import jp.co.sunarch.choseisan.entity.EventInfo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventAddController {
	public static final String NOT_DELETED = "0";
	public static final String DELETED = "1";

	// イベント情報登録
	@RequestMapping("/eventAdd")
	public String eventAdd(Model model) {
		return "eventAdd";
	}

	// イベント情報登録完了
	@RequestMapping(value="/eventAddResult", method=RequestMethod.POST)
	public String eventAddResult(Model model,
			@RequestParam("eventName") String eventName,
			@RequestParam("eventDate") String eventDate,
			@RequestParam("memo") String memo) {

		EventDAO eventDao = new EventDAO();

		UniqueId uniqueId = new UniqueId();

		// イベントIDの払い出し
		String eventId = uniqueId.createId();

		// 日付、イベント名、備考は画面で入力された値を、
		// 削除フラグには0をそれぞれ設定
		EventInfo event = new EventInfo(eventId, eventName, eventDate, memo, NOT_DELETED);

		// 入力されたイベント情報を保存
		eventDao.insertEvent(eventId, eventName, eventDate, memo);

		model.addAttribute("event",event);
		return "eventAddResult";
	}
}
