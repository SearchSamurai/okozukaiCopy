package jp.co.okozukai.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jp.co.okozukai.form.OkoRegistActionForm;
import jp.co.okozukai.form.OkoSearchActionForm;
import jp.co.okozukai.ibatis.dto.Okozukai;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * おこずかいテーブルのDAO。
 * @author d-iimura
 *
 */
public final class OkozukaiDao {

	/** SqlMapClient */
	private SqlMapClient sqlMap;
	/** logger */
	private static final Logger log = Logger.getLogger(OkozukaiDao.class);

	/**
	 * コンストラクタ。
	 * @param sqlMap sqlMap
	 */
	public OkozukaiDao(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * おこずかいテーブルにレコードを登録します。
	 * @param shoriNo 処理No
	 * @param userId ユーザID
	 * @param form おこずかい帳登録アクションのフォーム
	 * @param className 処理クラス名
	 * @throws SQLException
	 */
	public void addOkozukai(String shoriNo, String userId, OkoRegistActionForm okoRegistActionForm, String className) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shoriNo", shoriNo);
		paramMap.put("userId", userId);
		paramMap.put("form", okoRegistActionForm);
		paramMap.put("className", className);

		log.debug("shoriNo=" + shoriNo);
		log.debug("userId=" + userId);
		log.debug("form=" + okoRegistActionForm);
		log.debug("className=" + className);

		sqlMap.insert("addOkozukai", paramMap);
	}

	/**
	 * おこずかいテーブルからユーザ毎の最大処理Noを取得します。
	 * @param userId ユーザID
	 * @return String 最大処理No
	 * @throws SQLException
	 */
	public String findMaxShoriNo(String userId) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);

		log.debug("userId=" + userId);

		return (String) sqlMap.queryForObject("findMaxShoriNo", paramMap);
	}

	/**
	 * おこずかいテーブルからユーザ毎の登録レコードを取得します。
	 * @param userId ユーザID
	 * @param form おこずかい帳検索アクションのフォーム
	 * @return List
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Okozukai> findOkozukai(String userId, OkoSearchActionForm okoSearchActionForm) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("form", okoSearchActionForm);

		log.debug("userId=" + userId);
		log.debug("form=" + okoSearchActionForm);

		return sqlMap.queryForList("findOkozukai", paramMap);
	}

	/**
	 * おこずかいテーブルのレコードを更新します。
	 * @param userId ユーザID
	 * @param okozukai おこずかい帳テーブルDTO
	 * @param className 処理クラス名
	 * @throws SQLException
	 */
	public void updateOkozukai(String userId, Okozukai okozukai, String className) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("dto", okozukai);
		paramMap.put("className", className);

		log.debug("userId=" + userId);
		log.debug("dto=" + okozukai);
		log.debug("className=" + className);

		sqlMap.update("updateOkozukai", paramMap);
	}

	/**
	 * おこずかいテーブルのレコードを削除します。
	 * @param userId ユーザID
	 * @param okozukai おこずかい帳テーブルDTO
	 * @param className 処理クラス名
	 * @throws SQLException
	 */
	public void deleteOkozukai(String userId, Okozukai okozukai, String className) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("dto", okozukai);
		paramMap.put("className", className);

		log.debug("userId=" + userId);
		log.debug("dto=" + okozukai);
		log.debug("className=" + className);

		sqlMap.update("deleteOkozukai", paramMap);
	}

}
