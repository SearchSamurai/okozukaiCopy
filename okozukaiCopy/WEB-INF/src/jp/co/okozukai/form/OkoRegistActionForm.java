package jp.co.okozukai.form;
import org.apache.struts.validator.ValidatorForm;

/**
 * おこずかい帳登録ActionForm。
 * @author d-iimura
 *
 */
public final class OkoRegistActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

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

}