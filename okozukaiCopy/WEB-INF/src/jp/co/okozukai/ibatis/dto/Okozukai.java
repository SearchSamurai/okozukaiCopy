package jp.co.okozukai.ibatis.dto;


/**
 * おこずかいテーブルDTO。
 * @author d-iimura
 *
 */
public class Okozukai {

	/** 処理No */
	private String shoriNo;

	/** ユーザID */
	private String userId;

	/** 日付(年) */
	private String year;

	/** 日付(月) */
	private String month;

	/** 日付(日) */
	private String day;

	/** 品目コード */
	private String hinmokuCode;

	/** 収入金額 */
	private String inMoney;

	/** 支出金額 */
	private String outMoney;

	/** メモ */
	private String memo;

	/** 登録日 */
	private String instDate;

	/** 登録者ID */
	private String instId;

	/** 更新日 */
	private String updateDate;

	/** 更新者ID */
	private String updateId;

	/**
	 * 処理Noを取得します。
	 * @return shoriNo
	 */
	public String getShoriNo() {
		return shoriNo;
	}

	/**
	 * 処理Noを設定します。
	 * @param year 処理No
	 */
	public void setShoriNo(String shoriNo) {
		this.shoriNo = shoriNo;
	}

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
	 * 日付(年)を取得します。
	 * @return year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * 日付(年)を設定します。
	 * @param year 日付(年)
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * 日付(月)を取得します。
	 * @return month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * 日付(月)を設定します。
	 * @param month 日付(月)
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * 日付(日)を取得します。
	 * @return day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * 日付(日)を設定します。
	 * @param day 日付(日)
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * 品目コードを取得します。
	 * @return hinmokuCode
	 */
	public String getHinmokuCode() {
		return hinmokuCode;
	}

	/**
	 * 品目コードを設定します。
	 * @param hinmokuCode 品目コード
	 */
	public void setHinmokuCode(String hinmokuCode) {
		this.hinmokuCode = hinmokuCode;
	}

	/**
	 * 収入金額を取得します。
	 * @return inMoney
	 */
	public String getInMoney() {
		return inMoney;
	}

	/**
	 * 収入金額を設定します。
	 * @param inMoney 収入金額
	 */
	public void setInMoney(String inMoney) {
		this.inMoney = inMoney;
	}

	/**
	 * 支出金額を取得します。
	 * @return outMoney
	 */
	public String getOutMoney() {
		return outMoney;
	}

	/**
	 * 支出金額を設定します。
	 * @param outMoney 支出金額
	 */
	public void setOutMoney(String outMoney) {
		this.outMoney = outMoney;
	}

	/**
	 * メモを取得します。
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * メモを設定します。
	 * @param memo メモ
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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