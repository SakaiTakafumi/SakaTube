package jp.co.sunarch.sakatube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenerateDB extends DriverAccessor {

	/**
	 * 動画情報テーブルのDDL
	 */
	private static final String VIDEO_INFO_DDL = "CREATE TABLE IF NOT EXISTS VIDEO_INFO ("
			+ "VIDEO_ID BIGINT(10) NOT NULL DEFAULT VIDEO_ID_SEQ.NEXTVAL,"
			+ "VIDEO_TITLE VARCHAR(255) NOT NULL,"
			+ "VIDEO_NOTE VARCHAR(1000),"
			+ "EXTENSION VARCHAR(10) NOT NULL,"
			+ "VIDEO BLOB NOT NULL,"
			+ "CREATE_DATE DATE NOT NULL DEFAULT CURRENT_DATE,"
			+ "PRIMARY KEY(VIDEO_ID));";

	/**
	 * 動画IDのシーケンス作成
	 */
	private static final String CREATE_VIDEO_ID_SEQ = "CREATE SEQUENCE IF NOT EXISTS VIDEO_ID_SEQ";

	public void createDB() {
		createTable();
	}

	/**
	 * DB環境の構築を行います。
	 *
	 */
	private void createTable() {

		Connection con = null;
		con = createConnection();

		try {

			// 動画IDのシーケンス
			PreparedStatement eventIdSeqStmt = con
					.prepareStatement(CREATE_VIDEO_ID_SEQ);
			eventIdSeqStmt.executeUpdate();
			eventIdSeqStmt.close();

			// 動画情報テーブル
			PreparedStatement eventInfoDdlStmt = con
					.prepareStatement(VIDEO_INFO_DDL);
			eventInfoDdlStmt.executeUpdate();
			eventInfoDdlStmt.close();

		} catch (SQLException e) {

		} catch (Exception e) {

		} finally {
			con = null;
		}
	}
}