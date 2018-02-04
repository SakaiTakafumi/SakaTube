package jp.co.sunarch.choseisan.logic;

import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DAO.EventInfoDAO;
import jp.co.sunarch.choseisan.DAO.EventScheduleDAO;
import jp.co.sunarch.choseisan.entity.EventInfo;
import jp.co.sunarch.choseisan.entity.EventSchedule;

public class EventAddLogic {
	public Long eventAddLogic(String eventName, String eventSchedules, String memo) {

		EventInfoDAO eventDao = new EventInfoDAO();
		EventScheduleDAO eventScheduleDao = new EventScheduleDAO();
		EventInfo eventInfo = new EventInfo(eventName, memo);
		// 入力されたイベント情報をイベント情報テーブルへ登録
		Long eventId = eventDao.insertEventInfo(eventInfo);

		// 候補日程のオブジェクトリスト(日数分)
		List<EventSchedule> eventScheduleList = new ArrayList<>();

		// 画面で入力された候補日程を改行でスプリットして日程ごとにオブジェクトを生成する。
		String eventSchedule[] = eventSchedules.split("\r\n");
		for (String eventDate : eventSchedule) {
			eventScheduleList.add(new EventSchedule(eventId, eventDate));
		}

		// イベント候補日程テーブルへ登録
		eventScheduleDao.insertEventSchedule(eventScheduleList);

		return eventId;
	}
}