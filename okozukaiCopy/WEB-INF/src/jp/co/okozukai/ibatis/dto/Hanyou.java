package jp.co.okozukai.ibatis.dto;


/**
 * 汎用マスタDTO。
 * @author d-iimura
 *
 */
public class Hanyou {

	/** 並び順 */
	private String sortNo;

	/** 項目表示名 */
	private String dispName;

	/** 設定値 */
	private String value;

	/** 汎用区分 */
	private String hanyouKbn;

	/** 登録日 */
	private String instDate;

	/** 登録者ID */
	private String instId;

	/** 更新日 */
	private String updateDate;

	/** 更新者ID */
	private String updateId;

	/**
	 * 並び順を取得します。
	 * @return sortNo
	 */
	public String getSortNo() {
		return sortNo;
	}

	/**
	 * 並び順を設定します。
	 * @param sortNo 並び順
	 */
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}

	/**
	 * 項目表示名を取得します。
	 * @return dispName
	 */
	public String getDispName() {
		return dispName;
	}

	/**
	 * 項目表示名を設定します。
	 * @param dispName 項目表示名
	 */
	public void setDispName(String dispName) {
		this.dispName = dispName;
	}

	/**
	 * 設定値を取得します。
	 * @return value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 設定値を設定します。
	 * @param value 設定値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 汎用区分を取得します。
	 * @return hanyouKbn
	 */
	public String getHanyouKbn() {
		return hanyouKbn;
	}

	/**
	 * 汎用区分を設定します。
	 * @param hanyouKbn 汎用区分
	 */
	public void setHanyouKbn(String hanyouKbn) {
		this.hanyouKbn = hanyouKbn;
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