package jp.co.sunarch.choseisan.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.choseisan.DriverAccessor;
import jp.co.sunarch.choseisan.entity.SelectAnswer;
import jp.co.sunarch.choseisan.entity.SelectAnswerAggregate;

public class AnswerViewDAO extends DriverAccessor{

	private static final int ANSWERER_ID = 1;
	private static final int EVENT_SCHEDULE = 2;
	private static final int ANSWER = 3;

	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_CIRCLE_CNT = 1;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_CIRCLE_CNT = 2;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_TRIANGLE_CNT = 3;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_TRIANGLE_CNT = 4;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_CROSS_CNT = 5;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_CROSS_CNT = 6;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID = 7;
	private static final int SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE = 8;

	private static final int ANSWER_VIEW_EVENT_SCHEDULE = 1;
	private static final int ANSWER_VIEW_CIRCLE_CNT = 2;
	private static final int ANSWER_VIEW_TRIANGLE_CNT = 3;
	private static final int ANSWER_VIEW_CROSS_CNT = 4;

	private static final int SELECT_ANSWER_VIEW_EVENT_SCHEDULE = 1;
	private static final int SELECT_ANSWER_VIEW_ANSWERER_NAME = 2;
	private static final int SELECT_ANSWER_VIEW_ANSWER = 3;

	public static final String SELECT_ANSWER_AGGREGATE =
			"SELECT "
			+ "ANSWER_VIEW.EVENT_SCHEDULE, "
			+ "(SELECT COUNT(*) FROM ANSWER_VIEW WHERE EVENT_ID = ? AND EVENT_SCHEDULE = ? AND ANSWER = '1') AS CIECLE_CNT, "
			+ "(SELECT COUNT(*) FROM ANSWER_VIEW WHERE EVENT_ID = ? AND EVENT_SCHEDULE = ? AND ANSWER = '2') AS TRIANGLE_CNT, "
			+ "(SELECT COUNT(*) FROM ANSWER_VIEW WHERE EVENT_ID = ? AND EVENT_SCHEDULE = ? AND ANSWER = '3') AS CROSS_CNT "
			+ "FROM "
			+ "ANSWER_VIEW "
			+ "WHERE "
			+ "EVENT_ID = ? "
			+ "AND EVENT_SCHEDULE = ? "
			+ "GROUP BY "
			+ "EVENT_SCHEDULE;";

	public static final String SELECT_ANSWER =
			"SELECT "
			+ "EVENT_SCHEDULE, "
			+ "ANSWERER_NAME, "
			+ "ANSWER  "
			+ "FROM "
			+ "ANSWER_VIEW "
			+ "WHERE "
			+ "EVENT_ID = ? "
			+ "ORDER BY "
			+ "EVENT_SCHEDULE, ANSWERER_ID;";

	/**
	 * 回答の集計結果を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 */
	public List<SelectAnswerAggregate> selectAnswerAggregate(Long eventId, List<String> scheduleList) {

		List<SelectAnswerAggregate> answerAggregateList = new ArrayList<>();
		Connection con = null;
		con = createConnection();
		try{
			for (String schedule : scheduleList) {
				PreparedStatement answerViewStmt = con.prepareStatement(SELECT_ANSWER_AGGREGATE);

				answerViewStmt.setLong(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_CIRCLE_CNT, eventId);
				answerViewStmt.setString(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_CIRCLE_CNT, schedule);
				answerViewStmt.setLong(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_TRIANGLE_CNT, eventId);
				answerViewStmt.setString(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_TRIANGLE_CNT, schedule);
				answerViewStmt.setLong(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID_FOR_CROSS_CNT, eventId);
				answerViewStmt.setString(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE_FOR_CROSS_CNT, schedule);
				answerViewStmt.setLong(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_ID, eventId);
				answerViewStmt.setString(SELECT_ANSWER_AGGREGATE_QUERY_EVENT_SCHEDULE, schedule);

				ResultSet result = answerViewStmt.executeQuery();

				while(result.next()){
					SelectAnswerAggregate selectAnswerAggregate = new SelectAnswerAggregate();
					selectAnswerAggregate.setEventSchedule(result.getString(ANSWER_VIEW_EVENT_SCHEDULE));
					selectAnswerAggregate.setCircleCnt(result.getString(ANSWER_VIEW_CIRCLE_CNT) + "人");
					selectAnswerAggregate.setTriangleCnt(result.getString(ANSWER_VIEW_TRIANGLE_CNT) + "人");
					selectAnswerAggregate.setCrossCnt(result.getString(ANSWER_VIEW_CROSS_CNT) + "人");
					answerAggregateList.add(selectAnswerAggregate);
				}
				answerViewStmt.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answerAggregateList;
	}

	/**
	 * 各回答者の回答内容を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 */
	public List<SelectAnswer> selectAnswer(Long eventId) {
		List<SelectAnswer> answerList = new ArrayList<>();
		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement answerViewStmt = con.prepareStatement(SELECT_ANSWER);
			answerViewStmt.setLong(1, eventId);

			ResultSet result = answerViewStmt.executeQuery();

			while(result.next()) {
				SelectAnswer selectAnswer = new SelectAnswer();
				selectAnswer.setEventSchedule(result.getString(SELECT_ANSWER_VIEW_EVENT_SCHEDULE));
				selectAnswer.setAnswererName(result.getString(SELECT_ANSWER_VIEW_ANSWERER_NAME));
				selectAnswer.setAnswer(result.getString(SELECT_ANSWER_VIEW_ANSWER));
				answerList.add(selectAnswer);
			}

			answerViewStmt.close();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return answerList;
	}
}