package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventInfo;
import jp.co.sunarch.choseisan.entity.SelectEvent;

public class EventInfoDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int EVENT_NAME = 2;
	private static final int MEMO = 3;
	private static final int EVENT_SCHEDULE = 4;

	private static final String INSERT_INTO_EVENT_INFO = "INSERT INTO EVENT_INFO (EVENT_ID, EVENT_NAME, MEMO) VALUES ((SELECT NEXTVAL('EVENT_ID_SEQ')),?,?)";
	private static final String SELECT_EVENT_ID_SEQ = "SELECT EVENT_ID_SEQ.CURRVAL FROM DUAL";
	private static final String SELECT_EVENT_INFO = "SELECT "
			+ "EI.EVENT_ID,"
			+ "EI.EVENT_NAME,"
			+ "EI.MEMO,"
			+ "ES.EVENT_SCHEDULE "
			+ "FROM "
			+ "EVENT_INFO EI "
			+ "INNER JOIN EVENT_SCHEDULE ES "
			+ "ON EI.EVENT_ID = ES.EVENT_ID "
			+ "WHERE EI.EVENT_ID = ?";

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
			PreparedStatement eventInfoStmt = con.prepareStatement(INSERT_INTO_EVENT_INFO);

//			eventInfoStmt.setLong(1, 0);
			eventInfoStmt.setString(1, eventInfo.getEventName());
			eventInfoStmt.setString(2, eventInfo.getMemo());

			eventInfoStmt.executeUpdate();

			PreparedStatement eventIdSeqStmt = con.prepareStatement(SELECT_EVENT_ID_SEQ);
			ResultSet result = eventIdSeqStmt.getGeneratedKeys();

			result.last();
			eventId = result.getLong(1);

			eventInfoStmt.close();
			eventIdSeqStmt.close();
			con = null;

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return eventId;
	}

	/**
	 * 画面から入力されたイベントIDでイベント情報を取得します。
	 *
	 * @param eventId イベントID
	 */
	public List<SelectEvent> selectEvent(Long eventId){

		List<SelectEvent> eventList = new ArrayList<>();
		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement stmt = con.prepareStatement(SELECT_EVENT_INFO);

			stmt.setLong(1, eventId);

			ResultSet result = stmt.executeQuery();

			// 取得レコードがない場合にはnullを返す。
//			result.last();
//			int lastRowNum = result.getRow();
//			if (lastRowNum == 0) {
//				return null;
//			}

			while(result.next()){
				// 取得レコードでイベント情報のオブジェクト生成
				String eventName = result.getString(EVENT_NAME);
				String memo = result.getString(MEMO);
				String eventSchedule = result.getString(EVENT_SCHEDULE);
//				eventList.add(new SelectEvent(
//						result.getLong(EVENT_ID),
//						result.getString(EVENT_NAME),
//						result.getString(MEMO),
//						result.getString(EVENT_SCHEDULE)));
				SelectEvent event = new SelectEvent(eventId, eventName, memo, eventSchedule);

				eventList.add(event);
			  }

			stmt.close();
			con = null;
		} catch(SQLException e) {

		} finally {

		}
		return eventList;
	}
}