package jp.co.sunarch.sakatube.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jp.co.sunarch.sakatube.dao.VideoInfoDAO;
import jp.co.sunarch.sakatube.dto.VideoInfoDto;
import jp.co.sunarch.sakatube.entity.SelectVideoInfo;

import org.springframework.stereotype.Component;

@Component
public class VideoSearchService {

	private final VideoInfoDAO videoInfoDao;

	public VideoSearchService(VideoInfoDAO videoInfoDao) {
		this.videoInfoDao = videoInfoDao;
	}

	/**
	 * キーワードで動画の検索処理を行います。
	 *
	 * @param keyword
	 */
	public List<VideoInfoDto> searchVideoInfoByKeyword(String keyword) {
		// レスポンス返却用
		List<VideoInfoDto> resultList = new ArrayList<>();

		List<SelectVideoInfo> videoInfoList = videoInfoDao.findVideoInfoByKeyWord(keyword);

		for (SelectVideoInfo selectVideoInfo : videoInfoList) {
			VideoInfoDto videoInfo = new VideoInfoDto();
			videoInfo.setId(selectVideoInfo.getVideoId());
			videoInfo.setTitle(selectVideoInfo.getVideoTitle());
			videoInfo.setNote(selectVideoInfo.getVideoNote());
			resultList.add(videoInfo);
		}
		return resultList;
	}

	/**
	 * IDで動画の検索処理を行います。
	 *
	 * @param keyWord
	 */
	public VideoInfoDto searchVideoInfoById(Long id) {

		// IDから動画情報を取得する。
		SelectVideoInfo selectVideoInfo = videoInfoDao.findVideoInfoById(id);

		// 該当する動画情報が存在しない場合はnullを返す。
		if (selectVideoInfo == null) {
			return null;
		}

		VideoInfoDto videoInfo = new VideoInfoDto();
		videoInfo.setId(selectVideoInfo.getVideoId());
		videoInfo.setTitle(selectVideoInfo.getVideoTitle());
		videoInfo.setNote(selectVideoInfo.getVideoNote());
		videoInfo.setExtension(selectVideoInfo.getExtension());

		return videoInfo;
	}

	/**
	 * 動画を取得します。
	 *
	 * @param keyWord
	 * @throws IOException
	 */
	public InputStream searchVideoById(Long id) throws IOException {
		byte[] video = videoInfoDao.findVideoById(id);
		ByteArrayInputStream bis = new ByteArrayInputStream(video);

		return bis;
	}
}
