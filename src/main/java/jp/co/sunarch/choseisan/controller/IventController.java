package jp.co.sunarch.choseisan.controller;

import java.util.Date;

import jp.co.sunarch.choseisan.entiry.webapp.Ivent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IventController {
	public static final String NOT_DELETED = "0";
	public static final String DELETED = "1";

//	@Autowired
//	IventRepository repository;

	// イベント情報参照
	@RequestMapping("/iventView")
	public String iventList(Model model) {

		// TODO イベント情報を取得
		// いったんテストデータを直接突っ込んで、画面表示させてみる。
		Ivent ivent = new Ivent("2017/12/10", "自宅学習課題中間報告", "忘れずに！", new Date().toString(), new Date().toString(), NOT_DELETED);

		// 取得した利用者情報をmodelに設定
		model.addAttribute("ivent",ivent);

		return "iventView";
	}

	// イベント情報登録
	@RequestMapping("/iventAdd")
	public String iventAdd(Model model) {
		return "iventAdd";
	}

	// イベント情報登録完了
	@RequestMapping(value="/iventAddResult", method=RequestMethod.POST)
	public String iventAddResult(Model model,
			@RequestParam("date") String date,
			@RequestParam("iventName") String iventName,
			@RequestParam("memo") String memo) {

		// 日付、イベント名、備考は画面で入力された値を、
		// 登録日時、更新日時には現在時刻を、
		// 削除フラグには0をそれぞれ設定
		Ivent ivent = new Ivent(date, iventName, memo, new Date().toString(), new Date().toString(), NOT_DELETED);

		//TODO 入力されたイベント情報を保存
//		repository.saveAndFlush(ivent);

		model.addAttribute("ivent",ivent);
		return "iventAddResult";
	}
}