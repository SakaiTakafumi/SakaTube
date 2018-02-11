package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventSchedule;

public class EventScheduleDAO extends DriverAccessor{

	private static final String INSERT_INTO_EVENT_SCHEDULE = "INSERT INTO EVENT_SCHEDULE (EVENT_ID, EVENT_SCHEDULE) VALUES (?,?)";
	private static final String SELECT_EVENT_SCHEDULE = "SELECT EVENT_SCHEDULE FROM EVENT_SCHEDULE WHERE EVENT_ID = ?";

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
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
	}

	/**
	 * 候補日程を取得します。
	 *
	 * @param eventId
	 */
	public List<String> selectEventSchedule(Long eventId) {
		List<String> eventScheduleList = new ArrayList<>();
		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement eventScheduleStmt = con.prepareStatement(SELECT_EVENT_SCHEDULE);

			eventScheduleStmt.setLong(1, eventId);
			ResultSet result = eventScheduleStmt.executeQuery();

			while (result.next()) {
				eventScheduleList.add(result.getString(1));
			}

			eventScheduleStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return eventScheduleList;
	}
}