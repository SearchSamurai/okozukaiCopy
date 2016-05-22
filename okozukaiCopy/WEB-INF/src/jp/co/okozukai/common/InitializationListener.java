package jp.co.okozukai.common;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import jp.co.okozukai.action.BaseAction;
import jp.co.okozukai.common.constants.HanyouConst;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.ibatis.dao.HanyouDao;
import jp.co.okozukai.ibatis.dto.Hanyou;

import org.apache.log4j.Logger;

/**
 * InitializationListenerクラス。
 * @author d-iimura
 *
 */
public class InitializationListener extends BaseAction implements ServletContextListener {

	/** logger */
	private static final Logger log = Logger.getLogger(InitializationListener.class);
    /** Dao */
    private HanyouDao hanyouDao = new HanyouDao(sqlMap);

    /**
     * Webアプリケーション終了時に実行します。
     * @param ev ServletContextEvent
     */
	public void contextDestroyed(ServletContextEvent ev) {
		ServletContext sc = ev.getServletContext();
		log.info(sc.getServletContextName() + "■■■■■■■■■■ WEBアプリケーション起動終了!! ■■■■■■■■■■");
		System.exit(1);
	}

    /**
     * Webアプリケーション開始時に実行します。
     * @param ev ServletContextEvent
     */
	@SuppressWarnings("rawtypes")
	public void contextInitialized(ServletContextEvent ev) {
		ServletContext sc = ev.getServletContext();
		log.info(sc.getServletContextName() + "■■■■■■■■■■ WEBアプリケーション起動開始!! ■■■■■■■■■■");

		try {
			// トランザクション開始
			startTransaction();

			log.info("---------------- applicationリソースファイル読み込み ----------------");
			ResourceBundle applicationBundle = ResourceBundle.getBundle("application");
			Enumeration applicationEnum = applicationBundle.getKeys();
			Map<String, String> applicationMap = new HashMap<String, String>();
			while(applicationEnum.hasMoreElements()) {
				String key = (String)applicationEnum.nextElement();
				String value = applicationBundle.getString(key);
				applicationMap.put(key, value);
				log.info("applicationリソース=[key:" + key + ", value:" + value + "]");
			}
			sc.setAttribute(SystemConst.APPLICATION_KEY.APPLICATION_MAP, applicationMap);

			log.info("---------------- 汎用項目マスタレコード取得 ----------------");
			// 汎用項目マスタから品目セレクトボックスのデータを取得する
			List<Hanyou> hinmokuList = hanyouDao.findHanyou(HanyouConst.HANYOU_KBN_0);
			// 品目セレクトボックスの中身を設定しつつログ出力
			for (Hanyou hinmoku : hinmokuList) {
				log.info(hinmoku.toString());
			}
			sc.setAttribute(SystemConst.APPLICATION_KEY.HINMOKU_LIST, hinmokuList);
		} catch (SQLException se) {
			log.error(se);
			contextDestroyed(ev);
		} catch (Exception e) {
			log.error(e);
			contextDestroyed(ev);
		} finally {
			try {
				// トランザクション終了
				endTransaction();
			} catch (Exception e) {
				log.error(e);
				contextDestroyed(ev);
			}
		}
	}

}
