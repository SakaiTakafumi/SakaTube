package jp.co.sunarch.choseisan.logic;

import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DAO.AnswerInfoDAO;
import jp.co.sunarch.choseisan.DAO.EventAnswererInfoDAO;
import jp.co.sunarch.choseisan.entity.AnswerInfo;
import jp.co.sunarch.choseisan.entity.EventAnswererInfo;
import jp.co.sunarch.choseisan.form.AnswerForm;

public class EventAnswerLogic {
	public void eventAnswerLogic(Long eventId, AnswerForm answerForm) {

		EventAnswererInfoDAO eventAnswererInfoDao = new EventAnswererInfoDAO();
		AnswerInfoDAO answerInfoDao = new AnswerInfoDAO();
		List<AnswerInfo> answerList = new ArrayList<>();

		EventAnswererInfo eventAnswererInfo = new EventAnswererInfo(eventId, answerForm.getAnswererName(), answerForm.getAnswerComment());
		// 入力された回答内容をもとに、まずはイベント回答者情報テーブルに登録
		Long answererId = eventAnswererInfoDao.insertEventAnswererInfo(eventAnswererInfo);

		List<String> eventScheduleList = answerForm.getEventScheduleList();
		String checkList[] = answerForm.getCheckList().split(",");

		// 候補日程の日数分、回答情報テーブルに登録するためのオブジェクト生成
		for (int i=0; i<=eventScheduleList.size()-1; i++) {
			answerList.add(new AnswerInfo(answererId, eventScheduleList.get(i), checkList[i]));
		}

		// 回答情報テーブルに登録
		answerInfoDao.insertAnswerInfo(answerList);
	}
}