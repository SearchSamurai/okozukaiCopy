package jp.co.okozukai.form;

import java.util.Map;

/**
 * おこずかい帳検索ActionForm。
 * @author d-iimura
 *
 */
public final class OkoSearchActionForm extends PagerActionForm {

	private static final long serialVersionUID = 1L;

	/** 日付(年) */
	private String year;

	/** 日付(月) */
	private String month;

	/** 日付(日) */
	private String day;

	/** 品目コード */
	private String hinmokuCode;

	/** 収入金額From */
	private String inMoneyFrom;

	/** 収入金額To */
	private String inMoneyTo;

	/** 支出金額From */
	private String outMoneyFrom;

	/** 支出金額To */
	private String outMoneyTo;

	/** メモ */
	private String memo;

	/** 検索条件格納用Map */
	private Map<String, String> searchMap;

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
	 * 収入金額Fromを取得します。
	 * @return inMoneyFrom
	 */
	public String getInMoneyFrom() {
		return inMoneyFrom;
	}

	/**
	 * 収入金額Fromを設定します。
	 * @param inMoneyFrom 収入金額From
	 */
	public void setInMoneyFrom(String inMoneyFrom) {
		this.inMoneyFrom = inMoneyFrom;
	}

	/**
	 * 収入金額Toを取得します。
	 * @return inMoneyTo
	 */
	public String getInMoneyTo() {
		return inMoneyTo;
	}

	/**
	 * 収入金額Toを設定します。
	 * @param inMoneyTo 収入金額To
	 */
	public void setInMoneyTo(String inMoneyTo) {
		this.inMoneyTo = inMoneyTo;
	}

	/**
	 * 支出金額Fromを取得します。
	 * @return outMoneyFrom
	 */
	public String getOutMoneyFrom() {
		return outMoneyFrom;
	}

	/**
	 * 支出金額Fromを設定します。
	 * @param outMoneyFrom 支出金額From
	 */
	public void setOutMoneyFrom(String outMoneyFrom) {
		this.outMoneyFrom = outMoneyFrom;
	}

	/**
	 * 支出金額Toを取得します。
	 * @return outMoneyTo
	 */
	public String getOutMoneyTo() {
		return outMoneyTo;
	}

	/**
	 * 支出金額Toを設定します。
	 * @param outMoneyTo 支出金額To
	 */
	public void setOutMoneyTo(String outMoneyTo) {
		this.outMoneyTo = outMoneyTo;
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
	 * 検索条件格納用Mapを取得します。
	 * @return searchMap
	 */
	public Map<String, String> getSearchMap() {
		return searchMap;
	}

	/**
	 * 検索条件格納用Mapを設定します。
	 * @param searchMap 検索条件格納用Map
	 */
	public void setSearchMap(Map<String, String> searchMap) {
		this.searchMap = searchMap;
	}

}