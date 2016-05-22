package jp.co.okozukai.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.okozukai.common.StringUtils;
import jp.co.okozukai.common.constants.JspConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.form.LoginActionForm;
import jp.co.okozukai.form.OkoUpdateActionForm;
import jp.co.okozukai.ibatis.dao.OkozukaiDao;
import jp.co.okozukai.ibatis.dto.Okozukai;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

/**
 * おこずかい帳更新Action。
 * @author d-iimura
 *
 */
public final class OkoUpdateAction extends BaseAction {

    /** クラス名 */
    private static final String className = "OkoUpdateAction";
	/** logger */
	private static final Logger log = Logger.getLogger(OkoUpdateAction.class);
	/** 更新用アクションフォーム */
    private OkoUpdateActionForm okoUpdateActionForm = new OkoUpdateActionForm();
    /** Dao */
    private OkozukaiDao okozukaiDao = new OkozukaiDao(sqlMap);
    /** バリデーション用ActionMessage */
	private ActionMessages errors;

	// 以下の定義はvalidateメソッドで使用する
	/** 年月(年)の最大桁数 */
	private static final int MAX_LENGTH_YEAR = 4;
	/** 年月(月)の最大桁数 */
	private static final int MAX_LENGTH_MONTH = 2;
	/** 年月(日)の最大桁数 */
	private static final int MAX_LENGTH_DAY = 2;
	/** 収入金額の最大桁数 */
	private static final int MAX_LENGTH_IN_MONEY = 9;
	/** 支出金額の最大桁数 */
	private static final int MAX_LENGTH_OUT_MONEY = 9;
	/** メモの最大桁数 */
	private static final int MAX_LENGTH_MEMO = 20;
	/** 半角数字 */
	private static final String HANSUU = "[0-9]+$";
	/** 品目コード */
	private static final String HINMOKUCODE = "(01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|16|17|18|19|20)+$";
	/** 範囲(月):From */
	private static final int MONTH_FROM = 1;
	/** 範囲(月):To */
	private static final int MONTH_TO = 12;
	/** 範囲(日):From */
	private static final int DAY_FROM = 1;
	/** 範囲(日):To */
	private static final int DAY_TO = 31;

    /**
     * おこずかい帳更新Actionメイン処理。
     */
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		// formを取得する
		okoUpdateActionForm = (OkoUpdateActionForm)form;

		errors = new ActionMessages();
		// バリデーションチェックを実施する
		if (!validate(okoUpdateActionForm)) {
			saveErrors(req, errors);

			return (mapping.findForward(JspConst.JSP_RESULT.INPUT));
		}

		// 削除件数
		int delCnt = 0;
		// 更新件数
		int updCnt = 0;

		try {
			// トランザクション開始
			startTransaction();
			// セッションから現在ログインしているユーザIDを取得する
			String userId = ((LoginActionForm)req.getSession().getAttribute(SystemConst.SESSION_KEY.USER_INFO)).getUserId();
			// セッションから更新前の検索結果リストを取得する
			List<Okozukai> beforeOkozukaiList = (List<Okozukai>)req.getSession().getAttribute(SystemConst.SESSION_KEY.BEFORE_OKOZUKAI_LIST);
			// フォームにセットされている結果一覧のリスト分おこずかいテーブルを更新する
			for (Okozukai okozukai : okoUpdateActionForm.getOkozukaiList()) {
				// 変更ありなしフラグ
				boolean updateFlg = false;
				// 更新前に検索した内容と変更があるかチェックする
				for (Okozukai beforeOkozukai : beforeOkozukaiList) {
					// 処理Noが一致するレコードの場合、入力値に変更があるかチェックする
					if (okozukai.getShoriNo().equals(beforeOkozukai.getShoriNo())) {
						// 入力前後の差分をチェックする
						if (checkDiff(okozukai, beforeOkozukai)) {
							updateFlg = true;
						}
						break;
					}
				}
				// 変更ありなしフラグが変更ありの場合はDB操作を行う
				if (updateFlg) {
					// 入力値が全てブランクの場合、そのレコードを削除する
					if (checkAllBlank(okozukai)) {
						okozukaiDao.deleteOkozukai(userId, okozukai, className);
						delCnt++;
					// 入力値が1つでもある場合は更新する
					} else {
						okozukaiDao.updateOkozukai(userId, okozukai, className);
						updCnt++;
					}
				}
			}
			// コミット
			commit();
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
		// おこずかい帳更新完了メッセージをリクエストにセットする
		req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.INFO_OKOUPDATE_COMPLETE));
		// 削除、更新件数をリクエストにセットする
		req.setAttribute(SystemConst.REQUEST_KEY.DEL_CNT, delCnt);
		req.setAttribute(SystemConst.REQUEST_KEY.UPD_CNT, updCnt);

		return (mapping.findForward(JspConst.JSP_RESULT.SUCCESS));
	}

	/**
	 * おこずかいDTOの入力前後の差分をチェックします。
	 * @param okozukai 入力後のおこずかいDTO
	 * @param beforeOkozukai 入力前のおこずかいDTO
	 * @return true, false
	 */
	public boolean checkDiff(Okozukai okozukai, Okozukai beforeOkozukai) {
		if (!okozukai.getYear().equals(beforeOkozukai.getYear()) || !okozukai.getMonth().equals(beforeOkozukai.getMonth())
				|| !okozukai.getDay().equals(beforeOkozukai.getDay()) || !okozukai.getHinmokuCode().equals(beforeOkozukai.getHinmokuCode())
				|| !okozukai.getInMoney().equals(beforeOkozukai.getInMoney()) || !okozukai.getOutMoney().equals(beforeOkozukai.getOutMoney())
				|| !okozukai.getMemo().equals(beforeOkozukai.getMemo())) {
			return true;
		}
		return false;
	}

	/**
	 * おこずかいDTOの入力値が全て空かチェックします。
	 * @param okozukai 入力後のおこずかいDTO
	 * @return true, false
	 */
	public boolean checkAllBlank(Okozukai okozukai) {
		if (StringUtils.isNullOrEmpty(okozukai.getYear()) && StringUtils.isNullOrEmpty(okozukai.getMonth())
				&& StringUtils.isNullOrEmpty(okozukai.getDay()) && StringUtils.isNullOrEmpty(okozukai.getHinmokuCode())
				&& StringUtils.isNullOrEmpty(okozukai.getInMoney()) && StringUtils.isNullOrEmpty(okozukai.getOutMoney())
				&& StringUtils.isNullOrEmpty(okozukai.getMemo())) {
			return true;
		}
		return false;
	}

	/**
	 * バリデーションチェックを実施します。
	 * @param okoUpdateActionForm おこずかい帳更新Form
	 * @return true, false
	 */
	public boolean validate(OkoUpdateActionForm okoUpdateActionForm) {
		// 結果一覧リストからDTOを取得する
		for (Okozukai okozukai : okoUpdateActionForm.getOkozukaiList()) {
			// 入力値が全て空の場合は削除なので、バリデーションチェックは実施しない
			if (!checkAllBlank(okozukai)) {
				// 年月(年)のチェックを行う
				checkYear(okozukai.getYear());
				// 年月(月)のチェックを行う
				checkMonth(okozukai.getMonth());
				// 年月(日)のチェックを行う
				checkDay(okozukai.getDay());
				// 収支項目のチェックを行う
				checkHinmokuCode(okozukai.getHinmokuCode());
				// 収入金額のチェックを行う
				checkInMoney(okozukai.getInMoney());
				// 支出金額のチェックを行う
				checkOutMoney(okozukai.getOutMoney());
				// メモのチェックを行う
				checkMemo(okozukai.getMemo());

				// 1レコードのチェックで既にエラーがあったらチェック終了
				// エラーメッセージは溜め込まない方式
				if (errors.size() != 0) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 日付(年)のバリデーションチェックを実施します。
	 * @param year 日付(年)
	 */
	public void checkYear(String year) {
		// 必須チェック
		if (year == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "日付(年)"));
		}
		// 桁数チェック
		if (year.length() > MAX_LENGTH_YEAR) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "日付(年)", MAX_LENGTH_YEAR));
		}
		// 形式チェック
		if (!year.matches(HANSUU)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_HANEISUU, "日付(年)"));
		}
	}

	/**
	 * 日付(月)のバリデーションチェックを実施します。
	 * @param month 日付(月)
	 */
	public void checkMonth(String month) {
		// 必須チェック
		if (month == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "日付(月)"));
		}
		// 桁数チェック
		if (month.length() > MAX_LENGTH_MONTH) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "日付(月)", MAX_LENGTH_MONTH));
		}
		// 形式チェック
		if (!month.matches(HANSUU)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_HANEISUU, "日付(月)"));
		}
		// 範囲チェック
		if ((Integer.parseInt(month) < MONTH_FROM) || (Integer.parseInt(month) > MONTH_TO)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_INTRANGE, "日付(月)", "", MONTH_FROM, MONTH_TO));
		}
	}

	/**
	 * 日付(日)のバリデーションチェックを実施します。
	 * @param day 日付(日)
	 */
	public void checkDay(String day) {
		// 必須チェック
		if (day == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "日付(日)"));
		}
		// 桁数チェック
		if (day.length() > MAX_LENGTH_DAY) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "日付(日)", MAX_LENGTH_DAY));
		}
		// 形式チェック
		if (!day.matches(HANSUU)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_HANEISUU, "日付(日)"));
		}
		// 範囲チェック
		if ((Integer.parseInt(day) < DAY_FROM) || (Integer.parseInt(day) > DAY_TO)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_INTRANGE, "日付(日)", "", DAY_FROM, DAY_TO));
		}
	}

	/**
	 * 収支品目のバリデーションチェックを実施します。
	 * @param hinmokuCode 収支品目
	 */
	private void checkHinmokuCode(String hinmokuCode) {
		// 必須チェック
		if (hinmokuCode == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "収支品目"));
		}
		// 形式チェック
		if (!hinmokuCode.matches(HINMOKUCODE)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_FAILD, "収支品目"));
		}
	}

	/**
	 * 収入金額のバリデーションチェックを実施します。
	 * @param inMoney 収入金額
	 */
	private void checkInMoney(String inMoney) {
		// 必須チェック
		if (inMoney == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "収入金額"));
		}
		// 桁数チェック
		if (inMoney.length() > MAX_LENGTH_IN_MONEY) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "収入金額", MAX_LENGTH_IN_MONEY));
		}
		// 形式チェック
		if (!inMoney.matches(HANSUU)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_HANEISUU, "収入金額"));
		}
	}

	/**
	 * 支出金額のバリデーションチェックを実施します。
	 * @param outMoney 支出金額
	 */
	private void checkOutMoney(String outMoney) {
		// 必須チェック
		if (outMoney == null) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_REQUIRED, "支出金額"));
		}
		// 桁数チェック
		if (outMoney.length() > MAX_LENGTH_OUT_MONEY) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "支出金額", MAX_LENGTH_OUT_MONEY));
		}
		// 形式チェック
		if (!outMoney.matches(HANSUU)) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_HANEISUU, "支出金額"));
		}
	}

	/**
	 * メモのバリデーションチェックを実施します。
	 * @param memo メモ
	 */
	private void checkMemo(String memo) {
		// 桁数チェック
		if (memo.length() > MAX_LENGTH_MEMO) {
			// エラーメッセージを設定する
			errors.add(ActionMessages.GLOBAL_MESSAGE,
                    new ActionMessage(SystemConst.VALIDATION_KEY.ERRORS_MAXLENGTH, "メモ", MAX_LENGTH_MEMO));
		}
	}

}
