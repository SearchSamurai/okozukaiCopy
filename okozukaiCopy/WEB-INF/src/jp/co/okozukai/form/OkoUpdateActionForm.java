package jp.co.okozukai.form;

import java.util.ArrayList;
import java.util.List;

import jp.co.okozukai.ibatis.dto.Okozukai;

import org.apache.struts.validator.ValidatorForm;

/**
 * おこずかい帳更新ActionForm。
 * @author d-iimura
 *
 */
public final class OkoUpdateActionForm extends ValidatorForm {

	private static final long serialVersionUID = 1L;

	/** 検索結果リスト */
	private List<Okozukai> okoSearchList;

	/** おこずかいリスト */
	private List<Okozukai> okozukaiList = new ArrayList<Okozukai>();

	/**
	 * 検索結果リストを取得します。
	 * @return okoSearchList
	 */
	public List<Okozukai> getOkoSearchList() {
		return okoSearchList;
	}

	/**
	 * 検索結果リストを設定します。
	 * @param okoSearchCnt 検索結果リスト
	 */
	public void setOkoSearchList(List<Okozukai> okoSearchList) {
		this.okoSearchList = okoSearchList;
	}

	/**
	 * おこずかいリストを設定します。
	 * @param list おこずかいリスト
	 */
	public void setOkozukaiList(List<Okozukai> list){
		this.okozukaiList = list;
	}

	/**
	 * おこずかいリストを取得します。
	 * @return List<Okozukai>
	 */
	public List<Okozukai> getOkozukaiList() {
		return this.okozukaiList;
	}

	/**
	 * おこずかいリストの特定インデックスのDTOを取得します。
	 * @param cnt インデックス
	 * @return Okozukai
	 */
	public Okozukai getOkozukai(int cnt) {
		while (this.okozukaiList.size() <= cnt) {
			this.okozukaiList.add(new Okozukai());
		}
		return this.okozukaiList.get(cnt);
	}

}