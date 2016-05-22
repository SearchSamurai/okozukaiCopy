package jp.co.okozukai.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.okozukai.common.StringUtils;
import jp.co.okozukai.common.constants.JspConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.form.LoginActionForm;
import jp.co.okozukai.ibatis.dao.UserDao;
import jp.co.okozukai.ibatis.dto.User;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ログインAction。
 * @author d-iimura
 *
 */
public final class LoginAction extends BaseAction {

	/** logger */
	private static final Logger log = Logger.getLogger(LoginAction.class);
	/** アクションフォーム */
    private LoginActionForm loginActionForm = new LoginActionForm();
    /** Dao */
    private UserDao userDao = new UserDao(sqlMap);

    /**
     * ログインActionメイン処理。
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		// formを取得する
		loginActionForm = (LoginActionForm)form;


			String s = "ナガシマ";

		try {
			// トランザクション開始
			startTransaction();
			// ユーザ管理テーブルから入力されたユーザIDをキーにレコードを取得する
			List<User> userList = userDao.findUser(loginActionForm.getUserId());
			// 1件取得できた場合
			if (!StringUtils.isNullOrZero(userList)) {
				// DTOに詰める(テーブル構成上ユーザはuniqueになるはずなのでリストインデックスは固定)
				User user = userList.get(0);
				// 入力されたパスワードとDBから取得したパスワードが一致した場合
				if (user.getUserPassword().equals(loginActionForm.getUserPassword())) {
					// セッションにユーザ情報をセットする
					req.getSession().setAttribute(SystemConst.SESSION_KEY.USER_INFO, loginActionForm);
					// セッションにログインフラグをセットする
					req.getSession().setAttribute(SystemConst.SESSION_KEY.LOGIN_FLG, SystemConst.SESSION_VALUE.LOGIN_FLG_ON);
					// TOP画面へ
					return (mapping.findForward(JspConst.JSP_RESULT.SUCCESS));
				// 入力されたパスワードとDBから取得したパスワードが一致しない場合
				} else {
					// エラーメッセージをリクエストにセットし、自画面遷移
					req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.ERROR_LOGIN_FAILD));
					return (mapping.findForward(JspConst.JSP_RESULT.INPUT));
				}
			}
			// エラーメッセージをリクエストにセットし、自画面遷移
			req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.ERROR_LOGIN_NOREGIST));

			return (mapping.findForward(JspConst.JSP_RESULT.INPUT));
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
	}

}
