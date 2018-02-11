package jp.co.sunarch.choseisan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GenerateDB extends DriverAccessor{

	/**
	 * イベント情報テーブルのDDL
	 */
	private static final String EVENT_INFO_DDL = "CREATE TABLE IF NOT EXISTS EVENT_INFO ("
			+ "EVENT_ID NUMBER(10) NOT NULL DEFAULT EVENT_ID_SEQ.NEXTVAL,"
			+ "EVENT_NAME VARCHAR(255) NOT NULL,"
			+ "MEMO VARCHAR(255),"
			+ "PRIMARY KEY(EVENT_ID))";

	/**
	 * イベント候補日程情報テーブルのDDL
	 */
	private static final String EVENT_SCHEDULE_DDL = "CREATE TABLE IF NOT EXISTS EVENT_SCHEDULE ("
			+ "EVENT_ID NUMBER(10),"
			+ "EVENT_SCHEDULE VARCHAR(255),"
			+ "PRIMARY KEY(EVENT_ID,EVENT_SCHEDULE))";

	/**
	 * イベント回答者情報テーブルのDDL
	 */
	private static final String EVENT_ANSWERER_INFO = "CREATE TABLE IF NOT EXISTS EVENT_ANSWERER_INFO ("
			+ "EVENT_ID NUMBER(10) NOT NULL,"
			+ "ANSWERER_ID NUMBER(10) NOT NULL DEFAULT ANSWERER_ID_SEQ.NEXTVAL,"
			+ "ANSWERER_NAME VARCHAR(255),"
			+ "ANSWER_COMMENT VARCHAR(255),"
			+ "PRIMARY KEY(EVENT_ID, ANSWERER_ID))";

	/**
	 * 回答情報テーブルのDDL
	 */
	private static final String ANSWER_INFO = "CREATE TABLE IF NOT EXISTS ANSWER_INFO ("
			+ "ANSWERER_ID NUMBER(10) NOT NULL,"
			+ "EVENT_SCHEDULE VARCHAR(255),"
			+ "ANSWER CHAR(1),"
			+ "PRIMARY KEY(ANSWERER_ID, EVENT_SCHEDULE))";

	/**
	 * 回答情報のビュー作成
	 */
	private static final String ANSWER_VIEW = "CREATE OR REPLACE VIEW ANSWER_VIEW AS "
			+ "SELECT "
			+ "ES.EVENT_ID, "
			+ "ES.EVENT_SCHEDULE, "
			+ "EAI.ANSWERER_ID, "
			+ "EAI.ANSWERER_NAME, "
			+ "AI.ANSWER "
			+ "FROM "
			+ "EVENT_SCHEDULE ES "
			+ "INNER JOIN EVENT_ANSWERER_INFO EAI "
			+ "ON ES.EVENT_ID = EAI.EVENT_ID "
			+ "INNER JOIN ANSWER_INFO AI "
			+ "ON EAI.ANSWERER_ID = AI.ANSWERER_ID "
			+ "AND ES.EVENT_SCHEDULE = AI.EVENT_SCHEDULE "
			+ "ORDER BY ES.EVENT_SCHEDULE;";

	/**
	 * イベントIDのシーケンス作成
	 */
	private static final String CREATE_EVENT_ID_SEQ = "CREATE SEQUENCE IF NOT EXISTS EVENT_ID_SEQ";

	/**
	 * 回答者IDのシーケンス作成
	 */
	private static final String CREATE_ANSWERER_ID_SEQ = "CREATE SEQUENCE IF NOT EXISTS ANSWERER_ID_SEQ";

	public void createDB(){
    	createTable();
	}

	/**
	 * DB環境の構築を行います。
	 *
	 */
	private void createTable(){

		Connection con = null;
		con = createConnection();

		try {

			// イベントIDのシーケンス
			PreparedStatement eventIdSeqStmt = con.prepareStatement(CREATE_EVENT_ID_SEQ);
			eventIdSeqStmt.executeUpdate();
			eventIdSeqStmt.close();

			// 回答者IDのシーケンス
			PreparedStatement answererIdSeqStmt = con.prepareStatement(CREATE_ANSWERER_ID_SEQ);
			answererIdSeqStmt.executeUpdate();
			answererIdSeqStmt.close();

			// イベント情報テーブル
			PreparedStatement eventInfoDdlStmt = con.prepareStatement(EVENT_INFO_DDL);
			eventInfoDdlStmt.executeUpdate();
			eventInfoDdlStmt.close();

			// イベント候補日程テーブル
			PreparedStatement eventScheduleDdlStmt = con.prepareStatement(EVENT_SCHEDULE_DDL);
			eventScheduleDdlStmt.executeUpdate();
			eventScheduleDdlStmt.close();

			// イベント回答者情報テーブル
			PreparedStatement eventAnswererInfoStmt = con.prepareStatement(EVENT_ANSWERER_INFO);
			eventAnswererInfoStmt.executeUpdate();
			eventAnswererInfoStmt.close();

			// 回答情報テーブル
			PreparedStatement answerInfoStmt = con.prepareStatement(ANSWER_INFO);
			answerInfoStmt.executeUpdate();
			answerInfoStmt.close();

			// 回答情報のビュー
			PreparedStatement answerViewStmt = con.prepareStatement(ANSWER_VIEW);
			answerViewStmt.executeUpdate();
			answerViewStmt.close();

		} catch(SQLException e) {

		} catch(Exception e) {

		} finally {
			con = null;
		}
	}
}