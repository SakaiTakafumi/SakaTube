package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventInfo;

public class EventDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int EVENT_NAME = 2;
	private static final int EVENT_DATE = 3;
	private static final int MEMO = 4;
	public static final String NOT_DELETED = "0";
	public static final String DELETED = "1";

	public static final String INSERT_INTO_EVENT_INFO = "INSERT INTO EVENT_INFO VALUES (?,?,?,?,?)";
	public static final String SELECT_EVENT_INFO = "SELECT * FROM EVENT_INFO WHERE EVENT_ID = ? AND DELETE_FLAG = ?";

    /**
     * 画面から入力された情報をDBに反映します。
     *
     * @param eventId    イベントID
     * @param eventName  イベント名
     * @param eventDate  候補日程
     * @param memo       メモ
     */
	public void insertEvent(String eventId, String eventName, String eventDate, String memo){

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement stmt = con.prepareStatement(INSERT_INTO_EVENT_INFO);

			stmt.setString(1, eventId);
			stmt.setString(2, eventName);
			stmt.setString(3, eventDate);
			stmt.setString(4, memo);
			stmt.setString(5, NOT_DELETED);

			stmt.executeUpdate();

			stmt.close();
			con = null;

		} catch(SQLException e) {

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
			stmt.setString(2, NOT_DELETED);

			result = stmt.executeQuery();

			// 取得レコードは1件の想定なので、それ以外の場合にはnullを返す。
			result.last();
			int lastRowNum = result.getRow();
			if (lastRowNum != 1) {
				return null;
			}

			// 取得レコードでイベント情報のオブジェクト生成
			event = new EventInfo(
					result.getString(EVENT_ID),
					result.getString(EVENT_NAME),
					result.getString(EVENT_DATE),
					result.getString(MEMO),
					NOT_DELETED);

			stmt.close();
			con = null;
		} catch(SQLException e) {

		} finally {

		}
		return event;
	}
}