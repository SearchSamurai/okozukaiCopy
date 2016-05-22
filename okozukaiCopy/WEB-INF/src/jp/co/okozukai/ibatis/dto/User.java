package jp.co.okozukai.ibatis.dto;


/**
 * ユーザ管理テーブルDTO。
 * @author d-iimura
 *
 */
public class User {

	/** ユーザID */
	private String userId;

	/** パスワード */
	private String userPassword;

	/** 登録日 */
	private String instDate;

	/** 登録者ID */
	private String instId;

	/** 更新日 */
	private String updateDate;

	/** 更新者ID */
	private String updateId;

	/**
	 * ユーザIDを取得します。
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定します。
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * パスワードを取得します。
	 * @return userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	/**
	 * パスワードを設定します。
	 * @param userPassword パスワード
	 */
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * 登録日を取得します。
	 * @return instDate
	 */
	public String getInstDate() {
		return instDate;
	}

	/**
	 * 登録日を設定します。
	 * @param instDate 登録日
	 */
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	/**
	 * 登録者IDを取得します。
	 * @return instId
	 */
	public String getInstId() {
		return instId;
	}

	/**
	 * 登録日を設定します。
	 * @param instId 登録者ID
	 */
	public void setInstId(String instId) {
		this.instId = instId;
	}

	/**
	 * 更新日を取得します。
	 * @return updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * 更新日を設定します。
	 * @param updateDate 更新日
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者IDを取得します。
	 * @return updateId
	 */
	public String getUpdateId() {
		return updateId;
	}

	/**
	 * 更新者IDを設定します。
	 * @param updateId 更新者ID
	 */
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

}