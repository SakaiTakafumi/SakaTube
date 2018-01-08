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
					+ "EVENT_ID VARCHAR(255) PRIMARY KEY NOT NULL,"
					+ "EVENT_NAME VARCHAR(255) NOT NULL,"
					+ "EVENT_DATE VARCHAR(255) NOT NULL,"
					+ "MEMO VARCHAR(255),"
					+ "DELETE_FLAG CHAR(1) NOT NULL)";

			PreparedStatement eventInfoDdlStmt = con.prepareStatement(eventInfoDdl);

			eventInfoDdlStmt.executeUpdate();

			eventInfoDdlStmt.close();

			// 回答情報テーブル
			String answerInfoDdl = "CREATE TABLE IF NOT EXISTS ANSWER_INFO ("
					+ "EVENT_ID VARCHAR(255) NOT NULL,"
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