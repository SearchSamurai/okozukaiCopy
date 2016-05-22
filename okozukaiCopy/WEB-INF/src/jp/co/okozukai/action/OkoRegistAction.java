package jp.co.okozukai.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.okozukai.common.StringUtils;
import jp.co.okozukai.common.constants.JspConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.form.LoginActionForm;
import jp.co.okozukai.form.OkoRegistActionForm;
import jp.co.okozukai.ibatis.dao.OkozukaiDao;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * おこずかい帳登録Action。
 * @author d-iimura
 *
 */
public final class OkoRegistAction extends BaseAction {

    /** クラス名 */
    private static final String className = "OkoRegistAction";
	/** logger */
	private static final Logger log = Logger.getLogger(OkoRegistAction.class);
	/** アクションフォーム */
    private OkoRegistActionForm okoRegistActionForm = new OkoRegistActionForm();
    /** Dao */
    private OkozukaiDao okozukaiDao = new OkozukaiDao(sqlMap);

    /**
     * おこずかい帳登録Actionメイン処理。
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		// formを取得する
		okoRegistActionForm = (OkoRegistActionForm)form;

		try {
			// トランザクション開始
			startTransaction();
			// セッションから現在ログインしているユーザIDを取得する
			String userId = ((LoginActionForm) req.getSession().getAttribute(SystemConst.SESSION_KEY.USER_INFO)).getUserId();
			// 処理Noの最大値を取得して+1する
			String tmpShoriNo = okozukaiDao.findMaxShoriNo(userId);
			// 処理Noを初期化
			int shoriNo = 0;
			// 処理Noが取得できない場合
			if (StringUtils.isNullOrEmpty(tmpShoriNo)) {
				shoriNo = 1;
			// 処理Noができた場合
			} else {
				shoriNo = Integer.parseInt(tmpShoriNo) + 1;
			}
			// おこずかいテーブルに登録する
			okozukaiDao.addOkozukai(Integer.toString(shoriNo), userId, okoRegistActionForm, className);
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
		// おこずかい帳登録完了メッセージをリクエストにセットする
		req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.INFO_OKOREGIST_COMPLETE));

		return (mapping.findForward(JspConst.JSP_RESULT.SUCCESS));
	}

}
