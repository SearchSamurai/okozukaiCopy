package jp.co.okozukai.form;
import org.apache.struts.validator.ValidatorForm;

/**
 * ログインActionForm。
 * @author d-iimura
 *
 */
public final class LoginActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String userId;

	/** パスワード */
	private String userPassword;

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

}