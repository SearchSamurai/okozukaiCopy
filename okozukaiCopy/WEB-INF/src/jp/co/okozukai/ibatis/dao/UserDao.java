package jp.co.okozukai.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jp.co.okozukai.ibatis.dto.User;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * ユーザ管理テーブルのDAO。
 * @author d-iimura
 *
 */
public final class UserDao {

	/** SqlMapClient */
	private SqlMapClient sqlMap;
	/** logger */
	private static final Logger log = Logger.getLogger(UserDao.class);

	/**
	 * コンストラクタ。
	 * @param sqlMap sqlMap
	 */
	public UserDao(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * ユーザ管理テーブルからユーザIDに紐ついたレコードを取得します。
	 * @param userId ユーザID
	 * @return List
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<User> findUser(String userId) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);

		log.debug("userId=" + userId);

		return sqlMap.queryForList("findUser", paramMap);
	}

	/**
	 * ユーザ管理テーブルにユーザを登録します。
	 * @param userId ユーザID
	 * @param userPassword パスワード
	 * @param className 処理クラス名
	 * @throws SQLException
	 */
	public void addUser(String userId, String userPassword, String className) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userPassword", userPassword);
		paramMap.put("className", className);

		log.debug("userId=" + userId);
		log.debug("userPassword=" + userPassword);
		log.debug("className=" + className);

		sqlMap.insert("addUser", paramMap);
	}

	/**
	 * ユーザ管理テーブルのユーザを更新します。
	 * @param userId ユーザID
	 * @param userPassword パスワード
	 * @param updUserId 更新対象ユーザID
	 * @param className 処理クラス名
	 * @throws SQLException
	 */
	public void updateUser(String userId, String userPassword, String updUserId, String className) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("userPassword", userPassword);
		paramMap.put("updUserId", updUserId);
		paramMap.put("className", className);

		log.debug("userId=" + userId);
		log.debug("userPassword=" + userPassword);
		log.debug("updUserId=" + updUserId);
		log.debug("className=" + className);

		sqlMap.update("updateUser", paramMap);
	}

}
