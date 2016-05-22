package jp.co.okozukai.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.okozukai.common.StringUtils;
import jp.co.okozukai.common.constants.JspConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.form.LoginActionForm;
import jp.co.okozukai.form.OkoSearchActionForm;
import jp.co.okozukai.form.OkoUpdateActionForm;
import jp.co.okozukai.ibatis.dao.OkozukaiDao;
import jp.co.okozukai.ibatis.dto.Okozukai;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * おこずかい帳検索Action。
 * @author d-iimura
 *
 */
public final class OkoSearchAction extends BaseAction {

	/** logger */
	private static final Logger log = Logger.getLogger(OkoSearchAction.class);
	/** アクションフォーム */
    private OkoSearchActionForm okoSearchActionForm = new OkoSearchActionForm();
    /** Dao */
    private OkozukaiDao okozukaiDao = new OkozukaiDao(sqlMap);

    /**
     * おこずかい帳検索Actionメイン処理。
     */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		// formを取得する
		okoSearchActionForm = (OkoSearchActionForm)form;

		try {
			// トランザクション開始
			startTransaction();

			OkoUpdateActionForm okoUpdateActionForm = (OkoUpdateActionForm)req.getAttribute(SystemConst.REQUEST_KEY.OKO_UPDATE_ACTION_FORM);
			if (okoUpdateActionForm != null) {
				// 前画面の検索条件を引き継ぐ
				Map<String, String> beforeSearchMap = new HashMap<String, String>();
				beforeSearchMap = (HashMap<String, String>)req.getSession().getAttribute(SystemConst.SESSION_KEY.BEFORE_SEARCH_MAP);
				okoSearchActionForm.setYear(beforeSearchMap.get("year"));
				okoSearchActionForm.setMonth(beforeSearchMap.get("month"));
				okoSearchActionForm.setDay(beforeSearchMap.get("day"));
				okoSearchActionForm.setHinmokuCode(beforeSearchMap.get("hinmokuCode"));
				okoSearchActionForm.setInMoneyFrom(beforeSearchMap.get("inMoneyFrom"));
				okoSearchActionForm.setInMoneyTo(beforeSearchMap.get("inMoneyTo"));
				okoSearchActionForm.setOutMoneyFrom(beforeSearchMap.get("outMoneyFrom"));
				okoSearchActionForm.setOutMoneyTo(beforeSearchMap.get("outMoneyTo"));
				// 更新後の再検索用セッションを削除する
				req.getSession().removeAttribute("beforeSearchMap");
			}
			// セッションから現在ログインしているユーザIDを取得する
			String userId = ((LoginActionForm)req.getSession().getAttribute(SystemConst.SESSION_KEY.USER_INFO)).getUserId();
			// おこずかいテーブルからレコードを取得する
			List<Okozukai> tmpOkozukaiList = okozukaiDao.findOkozukai(userId, okoSearchActionForm);
			// 総レコード件数
			int searchCnt = 0;
			// 収入金額
			int inMoneySum = 0;
			// 支出金額
			int outMoneySum = 0;
			// レコードが取得できた場合
			List<Okozukai> okozukaiList = null;
			if (!StringUtils.isNullOrZero(tmpOkozukaiList)) {
				// 収入金額と支出金額の合計を算出する。1ページの表示件数分ではなくて、
				// 取得できたレコード全件の合計を出す
				for (Okozukai okozukai : tmpOkozukaiList) {
					inMoneySum = inMoneySum + Integer.parseInt(okozukai.getInMoney());
					outMoneySum = outMoneySum + Integer.parseInt(okozukai.getOutMoney());
				}
				// 取得したレコードから件数を取得する
				searchCnt = tmpOkozukaiList.size();
				// ページャーで指定された番号のデータにセットしなおす
				okozukaiList = getPagesList(tmpOkozukaiList);
			}
			// ページ番号リンクに付与する検索条件をフォームにセットする
			Map<String, String> searchMap = new HashMap<String, String>();
			searchMap.put("year", okoSearchActionForm.getYear());
			searchMap.put("month", okoSearchActionForm.getMonth());
			searchMap.put("day", okoSearchActionForm.getDay());
			searchMap.put("hinmokuCode", okoSearchActionForm.getHinmokuCode());
			searchMap.put("inMoneyFrom", okoSearchActionForm.getInMoneyFrom());
			searchMap.put("inMoneyTo", okoSearchActionForm.getInMoneyTo());
			searchMap.put("outMoneyFrom", okoSearchActionForm.getOutMoneyFrom());
			searchMap.put("outMoneyTo", okoSearchActionForm.getOutMoneyTo());
			okoSearchActionForm.setSearchMap(searchMap);
			// 更新後の再検索用にセッションにも保持する
			req.getSession().setAttribute(SystemConst.SESSION_KEY.BEFORE_SEARCH_MAP, searchMap);

			okoUpdateActionForm = new OkoUpdateActionForm();
			// バリデーションエラーメッセージが設定されている場合
			if (getErrors(req).size() != 0) {
				// バリデーションエラー時に入力した状態の検索結果リストを設定する
				okoUpdateActionForm.setOkoSearchList(((OkoUpdateActionForm)req.getAttribute(SystemConst.REQUEST_KEY.OKO_UPDATE_ACTION_FORM)).getOkozukaiList());
			} else {
				// 更新時に結果リストを取得できるように次画面のフォームに保持する
				okoUpdateActionForm.setOkoSearchList(okozukaiList);
			}

			req.setAttribute(SystemConst.REQUEST_KEY.OKO_UPDATE_ACTION_FORM, okoUpdateActionForm);
			// 更新前の検索結果リストをセッションにセット
			req.getSession().setAttribute(SystemConst.SESSION_KEY.BEFORE_OKOZUKAI_LIST, okozukaiList);
			// 収入金額、支出金額の合計をリクエストにセット
			req.setAttribute(SystemConst.REQUEST_KEY.IN_MONEY_SUM, inMoneySum);
			req.setAttribute(SystemConst.REQUEST_KEY.OUT_MONEY_SUM, outMoneySum);
			// レコード件数をリクエストにセット
			req.setAttribute(SystemConst.REQUEST_KEY.SEARCH_CNT, Integer.toString(searchCnt));
		} catch (SQLException se) {
			log.error(getMessage(SystemConst.LOG_MSG_KEY.ERROR_DB_ACCESS));
			log.error(se);
			return (mapping.findForward(JspConst.JSP_RESULT.ERROR));
		} catch (Exception e) {
			log.error(getMessage(SystemConst.LOG_MSG_KEY.ERROR_SYSTEM));
			log.error(e);
			return (mapping.findForward(JspConst.JSP_RESULT.ERROR));
		} finally {
			try {
				// トランザクション終了
				endTransaction();
				// セッション情報を出力
				outSessionInfo(req);
			} catch (SQLException se) {
				log.error(getMessage(SystemConst.LOG_MSG_KEY.ERROR_SYSTEM));
				log.error(se);
				return (mapping.findForward(JspConst.JSP_RESULT.ERROR));
			}
		}
		return (mapping.findForward(JspConst.JSP_RESULT.SUCCESS));
	}

	/**
	 * ページャーを作成します。
	 * @param okozukaiList 検索結果リスト
	 * @return List
	 */
	private List<Okozukai> getPagesList(List<Okozukai> okozukaiList) {
		// 1ページの最大表示件数
		int countlimit = SystemConst.OKO_SEARCH_PAGE_MAX_CNT;
		// 表示するページ数を計算。
		int pgs = okozukaiList.size() / countlimit;
		if (okozukaiList.size() % countlimit != 0) {
			pgs = pgs + 1;
		}
		// 1から最終ページまでの数字を格納するための配列
		int[] allpages = new int[pgs];
		for (int i = 0; i < pgs; i++) {
			allpages[i] = i + 1;
		}
		okoSearchActionForm.setAllpages(allpages);
		// 指定ページに該当するデータをリストに入れる
		ArrayList<Okozukai> arr = new ArrayList<Okozukai>();
		// 指定ページ番号を取得
		int pages = okoSearchActionForm.getPages();
		// 表示させるデータの最初の番号を計算
		int times = countlimit * pages;
		// リストに入っているbeanの分だけループ
		for (int l = 0; l < okozukaiList.size(); l++) {
			// 指定ページのデータ番号になったらリストに追加
			if (l >= times) {
				Okozukai bean = okozukaiList.get(l);
				arr.add(bean);
			}
			// データ件数が1ページの最大表示件数になったら取得終了
			if (arr.size() == countlimit) {
				break;
			}
		}
		return arr;
	}

}
