package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.EventAnswererInfo;

public class EventAnswererInfoDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int ANSWERER_NAME = 2;
	private static final int ANSWER_COMMENT = 3;

	private static final String INSERT_INTO_EVENT_ANSWERER_INFO = "INSERT INTO EVENT_ANSWERER_INFO (EVENT_ID, ANSWERER_ID, ANSWERER_NAME, ANSWER_COMMENT) VALUES (?,(SELECT NEXTVAL('ANSWERER_ID_SEQ')),?,?)";
	private static final String SELECT_ANSWERER_ID_SEQ = "SELECT EVENT_ID_SEQ.CURRVAL FROM DUAL";
	public static final String SELECT_ANSWER_INFO = "SELECT * FROM ANSWER_INFO WHERE EVENT_ID = ? AND DELETE_FLAG = ?";

	public Long insertEventAnswererInfo(EventAnswererInfo eventAnswererInfo) {
		Long answererId = null;

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement eventAnswererInfoStmt = con.prepareStatement(INSERT_INTO_EVENT_ANSWERER_INFO);

			eventAnswererInfoStmt.setLong(EVENT_ID, eventAnswererInfo.getEventId());
			eventAnswererInfoStmt.setString(ANSWERER_NAME, eventAnswererInfo.getAnswererName());
			eventAnswererInfoStmt.setString(ANSWER_COMMENT, eventAnswererInfo.getAnswerComment());

			eventAnswererInfoStmt.executeUpdate();

			PreparedStatement answererIdSeqStmt = con.prepareStatement(SELECT_ANSWERER_ID_SEQ);
			ResultSet result = answererIdSeqStmt.getGeneratedKeys();

			result.last();
			answererId = result.getLong(1);

			eventAnswererInfoStmt.close();
			answererIdSeqStmt.close();
			con = null;

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {

		}
		return answererId;
	}
}