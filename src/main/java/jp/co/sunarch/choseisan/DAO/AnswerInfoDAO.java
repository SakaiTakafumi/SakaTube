package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.AnswerInfo;

public class AnswerInfoDAO extends DriverAccessor{

	private static final int ANSWERER_ID = 1;
	private static final int EVENT_SCHEDULE = 2;
	private static final int ANSWER = 3;


	public static final String INSERT_INTO_ANSWER_INFO = "INSERT INTO ANSWER_INFO (ANSWERER_ID, EVENT_SCHEDULE, ANSWER) VALUES (?,?,?)";

	public Long insertAnswerInfo(List<AnswerInfo> answerInfoList) {
		Long answererId = null;

		Connection con = null;
		con = createConnection();
		try{
			for (AnswerInfo answerInfo : answerInfoList) {
				PreparedStatement answerInfoStmt = con.prepareStatement(INSERT_INTO_ANSWER_INFO);

				answerInfoStmt.setLong(ANSWERER_ID, answerInfo.getAnswererId());
				answerInfoStmt.setString(EVENT_SCHEDULE, answerInfo.getEventSchedule());
				answerInfoStmt.setString(ANSWER, answerInfo.getAnswer());

				answerInfoStmt.executeUpdate();

				answerInfoStmt.close();
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answererId;
	}
}