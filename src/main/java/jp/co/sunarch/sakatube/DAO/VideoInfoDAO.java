package jp.co.sunarch.sakatube.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jp.co.sunarch.sakatube.DriverAccessor;

import org.springframework.web.multipart.MultipartFile;

public class VideoInfoDAO extends DriverAccessor{

	private static final int VIDEO_TITLE = 1;
	private static final int VIDEO_NOTE = 2;
	private static final int VIDEO = 3;

	private static final String INSERT_INTO_VIDEO_INFO = "INSERT INTO VIDEO_INFO (VIDEO_ID, VIDEO_TITLE, VIDEO_NOTE, VIDEO) VALUES ((SELECT NEXTVAL('VIDEO_ID_SEQ')),?,?,?)";
	private static final String SELECT_VIDEO_ID_SEQ = "SELECT VIDEO_ID_SEQ.CURRVAL FROM DUAL";
	private static final String SELECT_VIDEO_INFO = "SELECT "
			+ "VI.VIDEO_ID,"
			+ "VI.VIDEO_TITLE,"
			+ "VI.VIDEO_NOTE,"
			+ "VI.VIDEO,"
			+ "VI.CREATE_DATE "
			+ "FROM "
			+ "VIDEO_INFO VI;";

	/**
	 * 画面から入力された情報をDBに反映します。
	 *
	 * @param title   動画タイトル
	 * @param note    動画説明
	 * @param video   動画
	 */
	public void insertVideoInfo(String title, String note, MultipartFile video){

		Connection con = null;
		con = createConnection();
		try{
			PreparedStatement videoInfoStmt = con.prepareStatement(INSERT_INTO_VIDEO_INFO);

			videoInfoStmt.setString(VIDEO_TITLE, title);
			videoInfoStmt.setString(VIDEO_NOTE, note);
			videoInfoStmt.setBlob(VIDEO, video.getInputStream());

			// 登録実行
			videoInfoStmt.executeUpdate();

			videoInfoStmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			con = null;
		}
	}
}