package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventAnswererInfo;

public class EventAnswererInfoDAO extends DriverAccessor{

	private static final int INSERT_EVENT_ID = 1;
	private static final int INSERT_ANSWERER_NAME = 2;
	private static final int INSERT_ANSWER_COMMENT = 3;

	private static final int QUERY_EVENT_ID = 1;

	private static final int RESULT_ANSWERER_NAME = 3;
	private static final int RESULT_ANSWER_COMMENT = 4;

	private static final String INSERT_INTO_EVENT_ANSWERER_INFO = "INSERT INTO EVENT_ANSWERER_INFO (EVENT_ID, ANSWERER_ID, ANSWERER_NAME, ANSWER_COMMENT) VALUES (?,(SELECT NEXTVAL('ANSWERER_ID_SEQ')),?,?)";
	private static final String SELECT_ANSWERER_ID_SEQ = "SELECT EVENT_ID_SEQ.CURRVAL FROM DUAL";
	public static final String SELECT_EVENT_ANSWERER_INFO = "SELECT * FROM EVENT_ANSWERER_INFO WHERE EVENT_ID = ? ORDER BY ANSWERER_ID";

	/**
	 * 回答者情報を登録し、回答者IDを払い出します。
	 *
	 * @param eventAnswererInfo
	 * @return answererId
	 */
	public Long insertEventAnswererInfo(EventAnswererInfo eventAnswererInfo) {
		Long answererId = null;

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement eventAnswererInfoStmt = con.prepareStatement(INSERT_INTO_EVENT_ANSWERER_INFO);

			eventAnswererInfoStmt.setLong(INSERT_EVENT_ID, eventAnswererInfo.getEventId());
			eventAnswererInfoStmt.setString(INSERT_ANSWERER_NAME, eventAnswererInfo.getAnswererName());
			eventAnswererInfoStmt.setString(INSERT_ANSWER_COMMENT, eventAnswererInfo.getAnswerComment());

			eventAnswererInfoStmt.executeUpdate();

			PreparedStatement answererIdSeqStmt = con.prepareStatement(SELECT_ANSWERER_ID_SEQ);
			ResultSet result = answererIdSeqStmt.getGeneratedKeys();

			result.last();
			answererId = result.getLong(1);

			eventAnswererInfoStmt.close();
			answererIdSeqStmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answererId;
	}

	/**
	 * イベントIDから回答者を取得します。
	 *
	 * @param eventId    イベントID
	 */
	public List<String> selectAnswererNameByEventId(Long eventId) {
		List<String> answererNameList = new ArrayList<>();
		answererNameList.add(null);

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement eventAnswererInfoStmt = con.prepareStatement(SELECT_EVENT_ANSWERER_INFO);

			eventAnswererInfoStmt.setLong(QUERY_EVENT_ID, eventId);

			ResultSet result = eventAnswererInfoStmt.executeQuery();

			while(result.next()){
				answererNameList.add(result.getString(RESULT_ANSWERER_NAME));
			}

			eventAnswererInfoStmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answererNameList;
	}

	/**
	 * イベントIDから回答者を取得します。
	 *
	 * @param eventId    イベントID
	 */
	public List<String> selectAnswerCommentByEventId(Long eventId) {
		List<String> answererCommentList = new ArrayList<>();

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement eventAnswererInfoStmt = con.prepareStatement(SELECT_EVENT_ANSWERER_INFO);

			eventAnswererInfoStmt.setLong(QUERY_EVENT_ID, eventId);

			ResultSet result = eventAnswererInfoStmt.executeQuery();

			while(result.next()){
				answererCommentList.add(result.getString(RESULT_ANSWER_COMMENT));
			}

			eventAnswererInfoStmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answererCommentList;
	}

}