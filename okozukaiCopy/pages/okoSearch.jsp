<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.okozukai.form.OkoSearchActionForm" %>
<link rel="StyleSheet" href="/okozukai/css/okoSearch.css" type="text/css">
<script type="text/javascript" src="/okozukai/js/common.js" charset="UTF-8"></script>

<%
	String loginFlg = (String) request.getSession().getAttribute(SystemConst.SESSION_KEY.LOGIN_FLG);
%>

<html:html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Webおこずかい帳</title>
	</head>
	<body>
		<!-- メインコンテンツ部分 -->
		<div id="wrapper">
			<!-- メインヘッダー部分 -->
			<div>
				<jsp:include page="<%=JspConst.JSP_COMMON.HEADER%>" />
			</div>
			<!-- メインヘッダー部分ここまで -->
			<!-- トップコンテンツ部分 -->
			<div id="container">
				<!-- コンテンツ内部 -->
				<div id="contents">
					<h2>おこずかい帳 検索</h2>
					<% if (loginFlg != null && loginFlg.equals(SystemConst.SESSION_VALUE.LOGIN_FLG_ON)) { %>
						<p>※検索条件を入力後、画面下部の検索ボタンクリックでおこずかい帳に登録した内容を検索できます。<br>
						&nbsp;&nbsp;検索後、結果一覧の任意のテキストの値を変更して「更新する」ボタンをクリックで更新されます。<br>
						&nbsp;&nbsp;また、結果一覧の行データを全て削除して「更新する」ボタンをクリックすると、その行を削除できます。
						</p>
						<html:form action="/OkoSearchAction" method="POST">
							<table class="input_oko" width="100%">
								<tr align="center">
									<td colspan="5" style="padding:5px; background-color:#00cc66;">検索条件</td>
								</tr>
								<tr align="center">
									<td width="25%">日付</td>
									<td width="25%">収支品目</td>
									<td width="25%">収入金額</td>
									<td width="25%">支出金額</td>
								</tr>
								<tr align="center">
									<td nowrap="nowrap">
										<html:text property="year" style="width:40px;" maxlength="4" />年
										<html:text property="month" style="width:25px;" maxlength="2" />月
										<html:text property="day" style="width:25px;" maxlength="2" />日
									</td>
									<td nowrap="nowrap">
										<logic:notEmpty name="OkoSearchActionForm" property="hinmokuCode">
											<html:select property="hinmokuCode">
												<html:options collection="<%=SystemConst.APPLICATION_KEY.HINMOKU_LIST%>" labelProperty="dispName" property="value"/>
											</html:select>
										</logic:notEmpty>
										<logic:empty name="OkoSearchActionForm" property="hinmokuCode">
											<html:select property="hinmokuCode" value="">
												<html:options collection="<%=SystemConst.APPLICATION_KEY.HINMOKU_LIST%>" labelProperty="dispName" property="value"/>
											</html:select>
										</logic:empty>
									</td>
									<td nowrap="nowrap">
										<span style="margin-right:25px;"><html:text property="inMoneyFrom" style="width:80px;" />円</span><br>
										<span style="margin-right:35px;">～</span><br>
										<html:text property="inMoneyTo" style="width:80px;" />円以内
									</td>
									<td nowrap="nowrap">
										<span style="margin-right:25px;"><html:text property="outMoneyFrom" style="width:80px;" />円</span><br>
										<span style="margin-right:35px;">～</span><br>
										<html:text property="outMoneyTo" style="width:80px;" />円以内
									</td>
								</tr>
								<tr align="center">
									<td nowrap="nowrap" colspan="4">
										<html:submit property="submit" value=" 検索する " />
									</td>
								</tr>
							</table>
							<br>
							<br>
							<!-- 検索結果件数 -->
							<logic:notEmpty name="searchCnt" scope="request" >
								<logic:equal name="searchCnt" value="0" scope="request">
									検索条件に該当するデータはありませんでした。
								</logic:equal>
								<logic:notEqual name="searchCnt" value="0" scope="request">
									<b><bean:write name="searchCnt" scope="request"  /></b>件の該当するデータがあります。
									<!-- ページャー -->
									<% int pageNo = ((OkoSearchActionForm) request.getAttribute(SystemConst.REQUEST_KEY.OKO_SEARCH_ACTION_FORM)).getPages(); %>
									<logic:iterate id="itm" name="OkoSearchActionForm" property="allpages" indexId="i">
										<% if (pageNo == i) { %>
											<bean:write name="itm" />
										<% } else { %>
											<html:link action="/OkoSearchAction.do" name="OkoSearchActionForm" property="searchMap" indexed="true" indexId="pages">
												<bean:write name="itm" />
											</html:link>
										<% } %>
									</logic:iterate>
								</logic:notEqual>
							</logic:notEmpty>
						</html:form>
						<!-- メッセージエリア -->
						<br>
							<div class="message_area">
								<!-- バリデーションエラー -->
								<html:errors />
								<!-- actionでセットしたメッセージ -->
								<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
									<bean:write name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request" />
									(削除:<bean:write name="<%=SystemConst.REQUEST_KEY.DEL_CNT%>" scope="request" />件、更新:<bean:write name="<%=SystemConst.REQUEST_KEY.UPD_CNT%>" scope="request" />件)
								</logic:notEmpty>
							</div>
						<br>
						<!-- 検索結果リスト -->
						<html:form action="/OkoUpdateAction" method="POST">
							<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.OKO_UPDATE_ACTION_FORM%>" property="okoSearchList">
								<table class="input_oko" width="100%">
									<tr align="center">
										<td width="4%">No</td>
										<td width="23%">日付</td>
										<td width="17%">収支品目</td>
										<td width="17%">収入金額</td>
										<td width="17%">支出金額</td>
										<td width="32%">メモ</td>
									</tr>
									<logic:iterate id="okozukai" name="<%=SystemConst.REQUEST_KEY.OKO_UPDATE_ACTION_FORM%>" property="okoSearchList" indexId="idx" >
										<tr>
											<td nowrap="nowrap" align="right">
												<% int pageNo = ((OkoSearchActionForm) request.getAttribute(SystemConst.REQUEST_KEY.OKO_SEARCH_ACTION_FORM)).getPages(); %>
												<%= (idx + 1) + (pageNo * SystemConst.OKO_SEARCH_PAGE_MAX_CNT) %>
												<html:hidden name="okozukai" property="shoriNo" indexed="true" />
											</td>
											<td nowrap="nowrap">
												<html:text name="okozukai" property="year" style="width:40px;" maxlength="4" indexed="true" />年
												<html:text name="okozukai" property="month" style="width:25px;" maxlength="2" indexed="true" />月
												<html:text name="okozukai" property="day" style="width:25px;" maxlength="2" indexed="true" />日
											</td>
											<td nowrap="nowrap">
												<html:select name="okozukai" property="hinmokuCode" indexed="true">
													<html:options collection="<%=SystemConst.APPLICATION_KEY.HINMOKU_LIST%>" labelProperty="dispName" property="value"/>
												</html:select>
											</td>
											<td nowrap="nowrap">
												<html:text name="okozukai" property="inMoney" style="width:80px;" indexed="true" />円
											</td>
											<td nowrap="nowrap">
												<html:text name="okozukai" property="outMoney" style="width:80px;" indexed="true" />円
											</td>
											<td nowrap="nowrap">
												<html:text name="okozukai" property="memo" style="width:120px;" indexed="true" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<br>
								<table class="input_oko" width="100%">
									<tr>
										<td>収入金額合計</td>
										<td align="right">\<bean:write name="<%=SystemConst.REQUEST_KEY.IN_MONEY_SUM%>" scope="request" /></td>
										<td>支出金額合計</td>
										<td align="right">\<bean:write name="<%=SystemConst.REQUEST_KEY.OUT_MONEY_SUM%>" scope="request" /></td>
									</tr>
								</table>
								<!-- ボタンエリア -->
								<p>
								<div class="button_area">
									<span>
										<html:submit property="submit" value=" 更新する "  />
									</span>
								</div>
								</p>
							</logic:notEmpty>
						</html:form>
						<br>
					<% } else { %>
						<p>ログインしていません。</p>
						<p>ログイン後におこずかい帳に登録した内容を検索できます。</p>
					<% } %>
				</div>
				<!-- コンテンツ内部ここまで -->
				<!-- サイドエリア部分 -->
				<div>
					<jsp:include page="<%=JspConst.JSP_COMMON.MENU%>" />
				</div>
				<!-- サイドエリア部分 終わり -->
			</div>
			<!-- トップコンテンツ部分 終わり -->
			<!-- フッター部分 -->
			<div>
				<jsp:include page="<%=JspConst.JSP_COMMON.FOOTER%>" />
			</div>
			<!-- フッター部分 終わり -->
		</div>
		<!-- メインコンテンツ部分 終わり -->
	</body>
</html:html>
