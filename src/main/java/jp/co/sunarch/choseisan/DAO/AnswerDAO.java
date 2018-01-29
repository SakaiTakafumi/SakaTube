package jp.co.sunarch.choseisan.DAO;

import jp.co.sunarch.choseisan.DriverAccessor;

public class AnswerDAO extends DriverAccessor{

	private static final int EVENT_ID = 1;
	private static final int PARTICIPANT_NAME = 2;
	private static final int ANSWER = 3;
	private static final int ANSWER_COMMENT = 4;

	public static final String NOT_DELETED = "0";
	public static final String DELETED = "1";

	public static final String SELECT_ANSWER_INFO = "SELECT * FROM ANSWER_INFO WHERE EVENT_ID = ? AND DELETE_FLAG = ?";

//	public List<AnswerInfo> selectAnswerInfo(String eventId) {
//
//		List<AnswerInfo> answerList = new ArrayList<>();
//		ResultSet result = null;
//		Connection con = null;
//		con = createConnection();
//		try{
//			PreparedStatement stmt = con.prepareStatement(SELECT_ANSWER_INFO);
//
//			stmt.setString(1, eventId);
//			stmt.setString(2, NOT_DELETED);
//
//			result = stmt.executeQuery();
//			while (result.next()) {
//				AnswerInfo answer = new AnswerInfo (
//						result.getString(EVENT_ID),
//						result.getString(PARTICIPANT_NAME),
//						result.getString(ANSWER),
//						result.getString(ANSWER_COMMENT),
//						NOT_DELETED);
//				answerList.add(answer);
//			}
//			stmt.close();
//			con = null;
//
//		} catch(SQLException e) {
//
//		} finally {
//
//		}
//		return answerList;
//	}
}