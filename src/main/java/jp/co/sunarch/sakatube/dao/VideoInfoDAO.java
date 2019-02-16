package jp.co.sunarch.sakatube.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.co.sunarch.sakatube.entity.SelectVideoInfo;
import jp.co.sunarch.sakatube.entity.VideoInfoEntity;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class VideoInfoDAO {

	private final JdbcTemplate jdbcTemplate;

	public VideoInfoDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String VIDEO_INFO_VIDEO_ID = "VIDEO_ID";
	private static final String VIDEO_INFO_VIDEO_TITLE = "VIDEO_TITLE";
	private static final String VIDEO_INFO_VIDEO_NOTE = "VIDEO_NOTE";
	private static final String VIDEO_INFO_EXTENSION = "EXTENSION";
	private static final String VIDEO_INFO_VIDEO = "VIDEO";

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

		// 登録実行
		int i = jdbcTemplate.update(INSERT_INTO_VIDEO_INFO,
				videoInfo.getVideoTitle(), videoInfo.getVideoNote(), videoInfo.getExtension(), videoInfo.getVideo());

		if (i == 0) {
			return false;
		}
		return true;
	}

	/**
	 * キーワードで動画情報を検索します。
	 *
	 * @param keyWord
	 */
	public List<SelectVideoInfo> findVideoInfoByKeyWord(String keyWord) {

		keyWord = "%" + keyWord + "%";

		// 検索実行
		List<Map<String, Object>> searchResultList = jdbcTemplate.queryForList(SELECT_VIDEO_INFO_BY_KEY_WORD, keyWord, keyWord);

		List<SelectVideoInfo> videoInfoList = new ArrayList<>();

		for (Map<String, Object> searchResult : searchResultList) {
			SelectVideoInfo videoInfo = new SelectVideoInfo();
			videoInfo.setVideoId(Long.parseLong(String.valueOf(searchResult.get(VIDEO_INFO_VIDEO_ID))));
			videoInfo.setVideoTitle(searchResult.get(VIDEO_INFO_VIDEO_TITLE).toString());
			if (searchResult.get(VIDEO_INFO_VIDEO_NOTE) != null) {
				videoInfo.setVideoNote(searchResult.get(VIDEO_INFO_VIDEO_NOTE).toString());
			}

			videoInfoList.add(videoInfo);
		}
		return videoInfoList;
	}

	/**
	 * IDで動画情報を検索します。
	 *
	 * @param keyWord
	 */
	public SelectVideoInfo findVideoInfoById(Long id) {

		// 検索実行
		List<Map<String, Object>> searchResultList = jdbcTemplate.queryForList(SELECT_VIDEO_INFO_BY_ID, id);

		// 0件 または 複数件取得された場合は、nullを返す。
		if (searchResultList.size() != 1) {
			return null;
		}

		SelectVideoInfo selectVideoInfo = new SelectVideoInfo();
		selectVideoInfo.setVideoId(Long.parseLong(String.valueOf(searchResultList.get(0).get(VIDEO_INFO_VIDEO_ID))));
		selectVideoInfo.setVideoTitle(searchResultList.get(0).get(VIDEO_INFO_VIDEO_TITLE).toString());
		if (searchResultList.get(0).get(VIDEO_INFO_VIDEO_NOTE) != null) {
			selectVideoInfo.setVideoNote(searchResultList.get(0).get(VIDEO_INFO_VIDEO_NOTE).toString());
		}
		selectVideoInfo.setExtension(searchResultList.get(0).get(VIDEO_INFO_EXTENSION).toString());

		return selectVideoInfo;
	}

	/**
	 * IDから動画を取得します。
	 *
	 * @param eventId
	 * @param scheduleList
	 * @throws IOException
	 */
	public byte[] findVideoById(Long id) throws IOException {

		// 検索実行
		List<Map<String, Object>> searchResultList = jdbcTemplate.queryForList(SELECT_VIDEO_BY_ID, id);

		// 0件 または 複数件取得された場合は、nullを返す。
		if (searchResultList.size() != 1) {
			return null;
		}

		return (byte[]) searchResultList.get(0).get(VIDEO_INFO_VIDEO);
	}
}