package jp.co.sunarch.sakatube;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class GenerateDB {

	private final JdbcTemplate jdbcTemplate;

	public GenerateDB(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

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
		jdbcTemplate.update(CREATE_VIDEO_ID_SEQ);
		jdbcTemplate.update(VIDEO_INFO_DDL);
	}
}