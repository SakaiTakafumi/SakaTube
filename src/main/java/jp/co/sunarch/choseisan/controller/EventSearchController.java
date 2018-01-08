package jp.co.sunarch.choseisan.controller;

import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DAO.AnswerDAO;
import jp.co.sunarch.choseisan.DAO.EventDAO;
import jp.co.sunarch.choseisan.entity.AnswerAggregate;
import jp.co.sunarch.choseisan.entity.AnswerForm;
import jp.co.sunarch.choseisan.entity.AnswerInfo;
import jp.co.sunarch.choseisan.entity.EventInfo;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventSearchController {

	// イベント情報検索
	@RequestMapping("/eventSearch")
	public String eventSearch(Model model) {
		return "eventSearch";
	}

	// イベント情報参照
	@RequestMapping("/eventView")
	public String eventList(Model model,
			@RequestParam("eventId") String eventId) {

		EventDAO eventDao = new EventDAO();
		AnswerDAO answerDao = new AnswerDAO();
		EventInfo event = eventDao.selectEvent(eventId);
		List<AnswerInfo> answerInfoList = answerDao.selectAnswerInfo(eventId);

		// 該当レコードが存在しない または複数レコード取得された(あり得ないはずだけど)場合
		if (event == null) {
			return "eventNotExist";
		}

		// イベント情報テーブル/日づけを改行でsplitして、それぞれの日づけごとにオブジェクトを生成する。
		List<AnswerAggregate> answerAggregateList = new ArrayList<>();
		String eventDateList[] = event.getEventDate().split("\r\n");
		// 出欠回答用のオブジェクトも日づけごとに生成する。
		// TODO もっとスマートなやり方を考えたい。。。
		List<AnswerForm> answerFormList = new ArrayList<>();

		for (String eventDate : eventDateList) {
			AnswerAggregate answerAggregate = new AnswerAggregate(eventDate, null, null, null);
			AnswerForm answerForm = new AnswerForm(eventId, eventDate, null, null);
			answerAggregateList.add(answerAggregate);
			answerFormList.add(answerForm);
		}

		int currentCnt = 0;

		// 回答状況を"○","△","×"ごとに集計する。
		for (AnswerAggregate answerAggregate : answerAggregateList) {
			int circleCnt = 0;
			int triangleCnt = 0;
			int crossCnt = 0;

			for (AnswerInfo answerInfo : answerInfoList) {
				String splitAnswer[] = answerInfo.getAnswer().split(",");
				if (StringUtils.equals(splitAnswer[currentCnt], "○")) {
					circleCnt++;
				} else if (StringUtils.equals(splitAnswer[currentCnt], "△")) {
					triangleCnt++;
				} else {
					crossCnt++;
				}
			}
			answerAggregate.setCircleCnt(String.valueOf(circleCnt));
			answerAggregate.setTriangleCnt(String.valueOf(triangleCnt));
			answerAggregate.setCrossCnt(String.valueOf(crossCnt));

			currentCnt++;
		}

		// 取得した情報をmodelに設定
		model.addAttribute("event",event);
		model.addAttribute("answerAggregateList", answerAggregateList);
		model.addAttribute("answerFormList", answerFormList);

		return "eventView";
	}

	// イベント情報検索
	@RequestMapping("/eventNotExist")
	public String eventNotExist(Model model) {
		return "eventNotExist";
	}
}
