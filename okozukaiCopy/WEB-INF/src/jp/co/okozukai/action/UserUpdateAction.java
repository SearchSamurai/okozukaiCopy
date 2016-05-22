package jp.co.okozukai.action;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.okozukai.common.StringUtils;
import jp.co.okozukai.common.constants.JspConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.form.LoginActionForm;
import jp.co.okozukai.form.UserUpdateActionForm;
import jp.co.okozukai.ibatis.dao.UserDao;
import jp.co.okozukai.ibatis.dto.User;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * ユーザ情報変更Action。
 * @author d-iimura
 *
 */
public final class UserUpdateAction extends BaseAction {

    /** クラス名 */
    private static final String className = "UserUpdateAction";
	/** logger */
	private static final Logger log = Logger.getLogger(UserUpdateAction.class);
	/** アクションフォーム */
    private UserUpdateActionForm userUpdateActionForm = new UserUpdateActionForm();
    /** Dao */
    private UserDao userDao = new UserDao(sqlMap);

    /**
     * ユーザ情報変更Actionメイン処理。
     */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest req, HttpServletResponse res) {
		// formを取得する
		userUpdateActionForm = (UserUpdateActionForm)form;

		try {
			// トランザクション開始
			startTransaction();
			// 現在ログインしているユーザIDをセッションから取得する
			String updUserId = ((LoginActionForm)req.getSession().getAttribute(SystemConst.SESSION_KEY.USER_INFO)).getUserId();
			// ユーザIDが取得できない場合はエラー画面へ遷移する
			if (StringUtils.isNullOrEmpty(updUserId)) {
				return (mapping.findForward(JspConst.JSP_RESULT.ERROR));
			}
			// 変更しようとしているユーザIDが存在するかチェックする
			List<User> userList = userDao.findUser(userUpdateActionForm.getUserId());
			// 1件取得できた場合
			if (!StringUtils.isNullOrZero(userList)) {
				// エラーメッセージをリクエストにセットし、自画面遷移
				req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.ERROR_USERUPDATE_FAILD));
				return (mapping.findForward(JspConst.JSP_RESULT.INPUT));
			}
			// 現在ログインしているユーザIDをキーにユーザ管理テーブルを更新する
			userDao.updateUser(userUpdateActionForm.getUserId(), userUpdateActionForm.getUserPassword(), updUserId, className);
			// 現在ログインしているユーザ情報はログインアクションフォームをセッションにセットしているため、更新後の情報で
			// 詰めなおす
			LoginActionForm loginActionForm = new LoginActionForm();
			loginActionForm.setUserId(userUpdateActionForm.getUserId());
			loginActionForm.setUserPassword(userUpdateActionForm.getUserPassword());
			req.getSession().setAttribute(SystemConst.SESSION_KEY.USER_INFO, loginActionForm);
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
		// ユーザ情報更新完了メッセージをリクエストにセットする
		req.setAttribute(SystemConst.REQUEST_KEY.MESSAGE_INFO, getMessage(JspConst.DISP_MSG_KEY.INFO_USERUPDATE_COMPLETE));

		return (mapping.findForward(JspConst.JSP_RESULT.SUCCESS));
	}

}
