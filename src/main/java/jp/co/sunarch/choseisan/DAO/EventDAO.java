package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventInfo;
import jp.co.sunarch.choseisan.entity.EventSchedule;

public class EventDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int EVENT_NAME = 2;
	private static final int MEMO = 3;
	public static final String NOT_DELETED = "0";
	public static final String DELETED = "1";

//	public static final String INSERT_INTO_EVENT_INFO = "INSERT INTO EVENT_INFO (EVENT_ID, EVENT_NAME, MEMO) VALUES (?,?,?)";
	public static final String INSERT_INTO_EVENT_INFO = "INSERT INTO EVENT_INFO (EVENT_NAME, MEMO) VALUES (?,?)";
	public static final String INSERT_INTO_EVENT_SCHEDULE = "INSERT INTO EVENT_SCHEDULE (EVENT_ID, EVENT_SCHEDULE) VALUES (?,?)";
	public static final String SELECT_EVENT_INFO = "SELECT * FROM EVENT_INFO WHERE EVENT_ID = ?";

	/**
	 * 画面から入力された情報をDBに反映します。
	 *
	 * @param eventId    イベントID
	 * @param eventName  イベント名
	 * @param eventDate  候補日程
	 * @param memo       メモ
	 */
	public Long insertEventInfo(EventInfo eventInfo){

		Long eventId = null;

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement eventInfoStmt = con.prepareStatement(INSERT_INTO_EVENT_INFO, Statement.RETURN_GENERATED_KEYS);

//			eventInfoStmt.setLong(1, 0);
			eventInfoStmt.setString(1, eventInfo.getEventName());
			eventInfoStmt.setString(2, eventInfo.getMemo());

			eventInfoStmt.executeUpdate();

			ResultSet rs = eventInfoStmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
			    eventId = rs.getLong(1);
			}

			eventInfoStmt.close();
			con = null;

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return eventId;
	}

	/**
	 * 候補日程を登録します。
	 *
	 * @param eventScheduleList
	 */
	public void insertEventSchedule(List<EventSchedule> eventScheduleList) {
		Connection con = null;
		con = createConnection();
		try {
			for (EventSchedule eventSchedule : eventScheduleList) {
				PreparedStatement eventScheduleStmt = con.prepareStatement(INSERT_INTO_EVENT_SCHEDULE);

				eventScheduleStmt.setLong(1, eventSchedule.getEventId());
				eventScheduleStmt.setString(2, eventSchedule.getEventSchedule());

				eventScheduleStmt.executeUpdate();

				eventScheduleStmt.close();
//				con = null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 画面から入力されたイベントIDでイベント情報を取得します。
	 *
	 * @param eventId イベントID
	 */
	public EventInfo selectEvent(String eventId){

		EventInfo event = null;
		ResultSet result = null;
		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement stmt = con.prepareStatement(SELECT_EVENT_INFO);

			stmt.setString(1, eventId);

			result = stmt.executeQuery();

			// 取得レコードは1件の想定なので、それ以外の場合にはnullを返す。
			result.last();
			int lastRowNum = result.getRow();
			if (lastRowNum != 1) {
				return null;
			}

			// 取得レコードでイベント情報のオブジェクト生成
			event = new EventInfo(
					result.getLong(EVENT_ID),
					result.getString(EVENT_NAME),
					result.getString(MEMO));

			stmt.close();
			con = null;
		} catch(SQLException e) {

		} finally {

		}
		return event;
	}
}