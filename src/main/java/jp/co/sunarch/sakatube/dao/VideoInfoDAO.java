package jp.co.sunarch.sakatube.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.sakatube.DriverAccessor;
import jp.co.sunarch.sakatube.entity.SelectVideoInfo;
import jp.co.sunarch.sakatube.entity.VideoInfoEntity;

public class VideoInfoDAO extends DriverAccessor {

	private static final int VIDEO_TITLE = 1;
	private static final int VIDEO_NOTE = 2;
	private static final int EXTENSION = 3;
	private static final int VIDEO = 4;

	private static final String INSERT_INTO_VIDEO_INFO = "INSERT INTO VIDEO_INFO (VIDEO_ID, VIDEO_TITLE, VIDEO_NOTE, EXTENSION, VIDEO) VALUES ((SELECT NEXTVAL('VIDEO_ID_SEQ')),?,?,?,?)";
	private static final String SELECT_VIDEO_INFO_BY_KEY_WORD = "SELECT VIDEO_ID, VIDEO_TITLE, VIDEO_NOTE "
			+ "FROM "
			+ "VIDEO_INFO "
			+ "WHERE "
			+ "VIDEO_TITLE LIKE ? "
			+ "UNION SELECT VIDEO_ID, VIDEO_TITLE, VIDEO_NOTE "
			+ "FROM "
			+ "VIDEO_INFO " + "WHERE " + "VIDEO_NOTE LIKE ?";
	private static final String SELECT_VIDEO_INFO_BY_ID = "SELECT VIDEO_ID, VIDEO_TITLE, VIDEO_NOTE, EXTENSION FROM VIDEO_INFO WHERE VIDEO_ID = ?";
	private static final String SELECT_VIDEO_BY_ID = "SELECT VIDEO FROM VIDEO_INFO WHERE VIDEO_ID = ?";

	/**
	 * 画面から入力された情報をDBに反映します。
	 *
	 * @param videoInfo
	 *
	 * @return true : 登録成功、false : 登録失敗
	 */
	public boolean insertVideoInfo(VideoInfoEntity videoInfo) {

		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement videoInfoStmt = con
					.prepareStatement(INSERT_INTO_VIDEO_INFO);

			videoInfoStmt.setString(VIDEO_TITLE, videoInfo.getVideoTitle());
			videoInfoStmt.setString(VIDEO_NOTE, videoInfo.getVideoNote());
			videoInfoStmt.setString(EXTENSION, videoInfo.getExtension());
			videoInfoStmt.setBlob(VIDEO, videoInfo.getVideo());

			// 登録実行
			videoInfoStmt.executeUpdate();

			videoInfoStmt.close();

		} catch (SQLException e) {
			return false;
		} finally {
			con = null;
		}
		return true;
	}

	/**
	 * キーワードで動画情報を検索します。
	 *
	 * @param keyWord
	 */
	public List<SelectVideoInfo> findVideoInfoByKeyWord(String keyWord) {
		List<SelectVideoInfo> videoInfoList = new ArrayList<>();
		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement videoStmt = con
					.prepareStatement(SELECT_VIDEO_INFO_BY_KEY_WORD);

			videoStmt.setString(1, "%" + keyWord + "%");
			videoStmt.setString(2, "%" + keyWord + "%");

			ResultSet result = videoStmt.executeQuery();

			while (result.next()) {
				SelectVideoInfo videoInfo = new SelectVideoInfo();
				videoInfo.setVideoId(result.getLong(1));
				videoInfo.setVideoTitle(result.getString(2));
				videoInfo.setVideoNote(result.getString(3));
				videoInfoList.add(videoInfo);
			}
			videoStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return videoInfoList;
	}

	/**
	 * IDで動画情報を検索します。
	 *
	 * @param keyWord
	 */
	public SelectVideoInfo findVideoInfoById(Long id) {
		SelectVideoInfo videoInfo = new SelectVideoInfo();
		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement videoStmt = con
					.prepareStatement(SELECT_VIDEO_INFO_BY_ID);

			videoStmt.setLong(1, id);

			ResultSet result = videoStmt.executeQuery();

			while (result.next()) {
				videoInfo.setVideoId(result.getLong(1));
				videoInfo.setVideoTitle(result.getString(2));
				videoInfo.setVideoNote(result.getString(3));
				videoInfo.setExtension(result.getString(4));
				return videoInfo;
			}
			videoStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return videoInfo;
	}

	/**
	 * IDから動画を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 */
	public byte[] findVideoById(Long id) {
		byte[] video = null;
		Connection con = null;
		con = createConnection();
		try {
			PreparedStatement videoStmt = con
					.prepareStatement(SELECT_VIDEO_BY_ID);

			videoStmt.setLong(1, id);

			ResultSet result = videoStmt.executeQuery();

			while (result.next()) {
				video = result.getBytes(1);
			}
			videoStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con = null;
		}
		return video;
	}
}