package jp.co.okozukai.ibatis.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jp.co.okozukai.ibatis.dto.Hanyou;

import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * 汎用項目マスタのDAO。
 * @author d-iimura
 *
 */
public final class HanyouDao {

	/** SqlMapClient */
	private SqlMapClient sqlMap;
	/** logger */
	private static final Logger log = Logger.getLogger(HanyouDao.class);

	/**
	 * コンストラクタ。
	 * @param sqlMap sqlMap
	 */
	public HanyouDao(SqlMapClient sqlMap) {
		this.sqlMap = sqlMap;
	}

	/**
	 * 汎用マスタから汎用区分に該当するレコードを取得します。
	 * @param hanyouKbn 汎用区分
	 * @return List
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<Hanyou> findHanyou(String hanyouKbn) throws SQLException {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("hanyouKbn", hanyouKbn);

		log.debug("hanyouKbn=" + hanyouKbn);

		return sqlMap.queryForList("findHanyou", paramMap);
	}

}
