package jp.co.sunarch.choseisan.logic;

import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DAO.EventDAO;
import jp.co.sunarch.choseisan.entity.EventInfo;
import jp.co.sunarch.choseisan.entity.EventSchedule;

public class EventAddLogic {
	public Long eventAddLogic(String eventName, String eventSchedules, String memo) {

		EventDAO eventDao = new EventDAO();
		EventInfo eventInfo = new EventInfo(eventName, memo);
		// 入力されたイベント情報を保存
		Long eventId = eventDao.insertEventInfo(eventInfo);

		// 候補日程のオブジェクトリスト(日数分)
		List<EventSchedule> eventScheduleList = new ArrayList<>();

		// 画面で入力された候補日程を改行でスプリットして日程ごとにオブジェクトを生成する。
		String eventSchedule[] = eventSchedules.split("\r\n");
		for (String eventDate : eventSchedule) {
			eventScheduleList.add(new EventSchedule(eventId, eventDate));
		}

		// DB登録
		eventDao.insertEventSchedule(eventScheduleList);

		return eventId;
	}
}