package jp.co.okozukai.common.constants;

/**
 * JSP用の定数定義クラス。
 * @author d-iimura
 *
 */
public final class JspConst {

	/** JSPのRESULTマッピングの定義 */
	public static class JSP_RESULT {
		/** success */
		public static final String SUCCESS = "success";
		/** input */
		public static final String INPUT = "input";
		/** error */
		public static final String ERROR = "error";
	}

	/** JSPの共通定義 */
	public static class JSP_COMMON {
		/** ヘッダー */
		public static final String HEADER = "./parts/header.jsp";
		/** メニュー */
		public static final String MENU = "./parts/menu.jsp";
		/** フッター */
		public static final String FOOTER = "./parts/footer.jsp";
	}

	/** JSPのFOWARD先のパス定義 */
	public static class JSP_FOWARD {
		/** ログイン画面 */
		public static final String LOGIN = "/okozukai/pages/login.jsp";
		/** TOP画面 */
		public static final String TOP = "/okozukai/pages/top.jsp";
		/** 新規ユーザ登録画面 */
		public static final String USER_REGIST = "/okozukai/pages/userRegist.jsp";
		/** ユーザ情報変更画面 */
		public static final String USER_UPDATE = "/okozukai/pages/userUpdate.jsp";
		/** おこずかい帳登録画面 */
		public static final String OKO_REGIST = "/okozukai/pages/okoRegist.jsp";
		/** おこずかい帳検索画面 */
		public static final String OKO_SEARCH = "/okozukai/pages/okoSearch.jsp";
	}

	/** 画面表示メッセージキー定義 */
	public static class DISP_MSG_KEY {
		/** ログイン失敗(ユーザID or パスワード誤り) */
		public static final String ERROR_LOGIN_FAILD = "msg.error.login.faild";
		/** ログイン失敗(ユーザレコード未存在) */
		public static final String ERROR_LOGIN_NOREGIST = "msg.error.login.noRegist";
		/** 新規ユーザ登録失敗(既に同一ユーザIDのユーザが存在する) */
		public static final String ERROR_USERREGIST_FAILD = "msg.error.userRegist.faild";
		/** 新規ユーザ登録完了 */
		public static final String INFO_USERREGIST_COMPLETE = "msg.info.userRegist.complete";
		/** ユーザ情報変更失敗(既に同一ユーザIDのユーザが存在する) */
		public static final String ERROR_USERUPDATE_FAILD = "msg.error.userUpdate.faild";
		/** ユーザ情報変更完了 */
		public static final String INFO_USERUPDATE_COMPLETE = "msg.info.userUpdate.complete";
		/** おこずかい帳登録完了 */
		public static final String INFO_OKOREGIST_COMPLETE = "msg.info.okoRegist.complete";
		/** おこずかい帳更新完了 */
		public static final String INFO_OKOUPDATE_COMPLETE = "msg.info.okoUpdate.complete";
	}

}