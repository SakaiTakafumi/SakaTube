package jp.co.sunarch.choseisan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class GenerateDB extends DriverAccessor{

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
			// イベント情報テーブル
			String eventInfoDdl = "CREATE TABLE IF NOT EXISTS EVENT_INFO ("
					+ "EVENT_ID NUMBER(10) IDENTITY,"
					+ "EVENT_NAME VARCHAR(255) NOT NULL,"
					+ "MEMO VARCHAR(255))";

			PreparedStatement eventInfoDdlStmt = con.prepareStatement(eventInfoDdl);

			eventInfoDdlStmt.executeUpdate();

			eventInfoDdlStmt.close();

			// イベント候補日程テーブル
			String eventScheduleDdl = "CREATE TABLE IF NOT EXISTS EVENT_SCHEDULE ("
					+ "EVENT_ID NUMBER(10),"
					+ "EVENT_SCHEDULE VARCHAR(255),"
					+ "PRIMARY KEY(EVENT_ID,EVENT_SCHEDULE))";

			PreparedStatement eventScheduleDdlStmt = con.prepareStatement(eventScheduleDdl);

			eventScheduleDdlStmt.executeUpdate();

			eventScheduleDdlStmt.close();

			// 回答情報テーブル
			String answerInfoDdl = "CREATE TABLE IF NOT EXISTS ANSWER_INFO ("
					+ "EVENT_ID NUMBRT(10) NOT NULL,"
					+ "PARTICIPANT_NAME VARCHAR(255) NOT NULL,"
					+ "ANSWER VARCHAR(255),"
					+ "ANSWER_COMMENT VARCHAR(255),"
					+ "DELETE_FLAG CHAR(1) NOT NULL)";

			PreparedStatement answerInfoStmt = con.prepareStatement(answerInfoDdl);

			answerInfoStmt.executeUpdate();

			answerInfoStmt.close();

			con = null;
		} catch(SQLException e) {

		} finally {

		}
	}
}