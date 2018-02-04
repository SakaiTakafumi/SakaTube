package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventSchedule;

public class EventScheduleDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int EVENT_NAME = 2;
	private static final int MEMO = 3;

	private static final String INSERT_INTO_EVENT_SCHEDULE = "INSERT INTO EVENT_SCHEDULE (EVENT_ID, EVENT_SCHEDULE) VALUES (?,?)";

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
}