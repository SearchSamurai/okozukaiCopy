package jp.co.okozukai.ibatis.dao;

import jp.co.okozukai.ibatis.SqlMapConfig;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * DAOの基底クラス。
 * @author d-iimura
 *
 */
public class BaseDao {

	/** SqlMapClient */
	public static final SqlMapClient sqlMap = SqlMapConfig.getSqlMapInstance();

}
