package jp.co.okozukai.common.constants;

/**
 * システム用の定数定義クラス。
 * @author d-iimura
 *
 */
public final class SystemConst {

	/** おこずかい帳検索 1ページの最大表示件数 */
	public static final int OKO_SEARCH_PAGE_MAX_CNT = 10;

	/** ファイルパス定義 */
	public static class FILE_CONFIG {
		/** sqlMapConfigファイル名 */
		public static final String SQL_MAP_CONFIG_FILE = "sqlMapConfig.xml";
		/** application.propertiesファイル名 */
		public static final String APPLICATION_PROPERTIES_FILE = "../resources/application.properties";
	}

	/** アプリケーションキー定義 */
	public static class APPLICATION_KEY {
		/** 汎用項目マスタ(品目リスト) */
		public static final String HINMOKU_LIST = "hinmokuList";
		/** applicationリソースファイル */
		public static final String APPLICATION_MAP = "applicationMap";
	}

	/** セッションキー定義 */
	public static class SESSION_KEY {
		/** ユーザ情報 */
		public static final String USER_INFO = "userInfo";
		/** ログインフラグ */
		public static final String LOGIN_FLG = "loginFlg";
		/** 更新後の再検索条件用Map。更新後に更新前の検索条件で再検索をするためのキー */
		public static final String BEFORE_SEARCH_MAP= "beforeSearchMap";
		/** 更新前の検索結果リスト */
		public static final String BEFORE_OKOZUKAI_LIST= "beforeOkozukaiList";
	}

	/** セッションキーバリュー定義 */
	public static class SESSION_VALUE {
		/** ログインフラグ：ログイン済み */
		public static final String LOGIN_FLG_ON = "1";
	}

	/** リクエストキー定義 */
	public static class REQUEST_KEY {
		/** メッセージ */
		public static final String MESSAGE_INFO = "messageInfo";
		/** 削除件数 */
		public static final String DEL_CNT = "delCnt";
		/** 更新件数 */
		public static final String UPD_CNT = "updCnt";
		/** おこずかい帳更新フォーム */
		public static final String OKO_UPDATE_ACTION_FORM = "OkoUpdateActionForm";
		/** おこずかい帳検索フォーム */
		public static final String OKO_SEARCH_ACTION_FORM = "OkoSearchActionForm";
		/** レコード件数 */
		public static final String SEARCH_CNT= "searchCnt";
		/** 収入金額の合計 */
		public static final String IN_MONEY_SUM = "inMoneySum";
		/** 支出金額の合計 */
		public static final String OUT_MONEY_SUM = "outMoneySum";
	}

	/** ログメッセージキー定義 */
	public static class LOG_MSG_KEY {
		/** 共通メッセージ */
		public static final String ERROR_DB_ACCESS = "msg.error.db.access";
		public static final String ERROR_SYSTEM = "msg.error.system";
	}

	/** validationチェックキー定義 */
	public static class VALIDATION_KEY {
		/** 必須チェック */
		public static final String ERRORS_REQUIRED = "errors.required";
		/** 桁数チェック */
		public static final String ERRORS_MAXLENGTH = "errors.maxlength";
		/** 形式チェック */
		public static final String ERRORS_HANEISUU = "errors.haneisuu";
		/** 範囲チェック */
		public static final String ERRORS_INTRANGE = "errors.intrange";
		/** 設定値不正 */
		public static final String ERRORS_FAILD = "errors.faild";
	}

}