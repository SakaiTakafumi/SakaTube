package jp.co.sunarch.choseisan.logic;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jp.co.sunarch.choseisan.DAO.AnswerViewDAO;
import jp.co.sunarch.choseisan.DAO.EventAnswererInfoDAO;
import jp.co.sunarch.choseisan.entity.SelectAnswer;
import jp.co.sunarch.choseisan.entity.SelectAnswerAggregate;

import org.h2.util.StringUtils;


public class EventViewLogic {

	/**
	 * 回答状況の集計結果を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 */
	public List<SelectAnswerAggregate> getAnswerAggregate(Long eventId, List<String> scheduleList) {

		AnswerViewDAO answerViewDao = new AnswerViewDAO();

		List<SelectAnswerAggregate> answerAggregateList = answerViewDao.selectAnswerAggregate(eventId, scheduleList);
		return answerAggregateList;
	}

	/**
	 * 回答内容を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 */
	public Map<String, List<String>> getAnswer(Long eventId, List<String> scheduleList) {

		Map<String, List<String>> answerMap = new LinkedHashMap<>();

		// DBから回答内容を取得
		AnswerViewDAO answerViewDao = new AnswerViewDAO();
		List<SelectAnswer> answerList = answerViewDao.selectAnswer(eventId);

		for (String schedule : scheduleList) {
			List<String> answerValueList = new ArrayList<>();
			for (SelectAnswer answer : answerList) {
				if (StringUtils.equals(answer.getEventSchedule(), schedule)) {
					String value = null;

					if (StringUtils.equals(answer.getAnswer(), "1")) {
						value = "○";
					} else if (StringUtils.equals(answer.getAnswer(), "2")) {
						value = "△";
					} else if (StringUtils.equals(answer.getAnswer(), "3")) {
						value = "×";
					}
					answerValueList.add(value);
				}
			}
			answerMap.put(schedule, answerValueList);
		}

		// コメントも取得
		EventAnswererInfoDAO eventAnswererInfoDao = new EventAnswererInfoDAO();
		List<String> commentList = eventAnswererInfoDao.selectAnswerCommentByEventId(eventId);
		answerMap.put("コメント", commentList);

		return answerMap;
	}
}