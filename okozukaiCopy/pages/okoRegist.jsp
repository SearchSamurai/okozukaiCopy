<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<link rel="StyleSheet" href="/okozukai/css/okoRegist.css" type="text/css">
<script type="text/javascript" src="/okozukai/js/common.js" charset="UTF-8"></script>

<%
	String loginFlg = (String) request.getSession().getAttribute(SystemConst.SESSION_KEY.LOGIN_FLG);
	List hinmokuList = new ArrayList();
	hinmokuList = (List) application.getAttribute(SystemConst.APPLICATION_KEY.HINMOKU_LIST);
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
					<html:form action="/OkoRegistAction" method="POST">
						<h2>おこずかい帳 登録</h2>
						<% if (loginFlg != null && loginFlg.equals(SystemConst.SESSION_VALUE.LOGIN_FLG_ON)) { %>
							<p>※項目を入力後、画面下部の登録ボタンクリックでおこずかい帳に登録されます。</p>
							<!-- メッセージエリア -->
							<p>
							<div class="message_area">
								<!-- バリデーションエラー -->
								<html:errors />
								<!-- actionでセットしたメッセージ -->
								<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
									<bean:write name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request" />
								</logic:notEmpty>
							</div>
							</p>
							<table class="input_oko" width="100%">
								<tr align="center">
									<td width="23%">日付</td>
									<td width="17%">収支品目</td>
									<td width="17%">収入金額</td>
									<td width="17%">支出金額</td>
									<td width="26%">メモ</td>
								</tr>
								<tr>
									<td nowrap="nowrap">
										<html:text property="year" style="width:40px;" maxlength="4" />年
										<html:text property="month" style="width:25px;" maxlength="2" />月
										<html:text property="day" style="width:25px;" maxlength="2" />日
									</td>
									<td nowrap="nowrap">
										<html:select property="hinmokuCode">
											<html:options collection="<%=SystemConst.APPLICATION_KEY.HINMOKU_LIST%>" labelProperty="dispName" property="value"/>
										</html:select>
									</td>
									<td nowrap="nowrap">
										<html:text property="inMoney" style="width:80px;" maxlength="9" />円
									</td>
									<td nowrap="nowrap">
										<html:text property="outMoney" style="width:80px;" maxlength="9" />円
									</td>
									<td nowrap="nowrap">
										<html:text property="memo" style="width:135px;" maxlength="20" />
									</td>
								</tr>
							</table>
							<!-- ボタンエリア -->
							<p><div class="button_area">
								<span>
									<logic:empty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
										<html:submit property="submit" value=" 登録する "  />
										<input type="button" value=" クリアする " onclick="clearFormAll()" />
									</logic:empty>
									<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
										<input type="button" value=" 次を入力 " onclick="move('<%=JspConst.JSP_FOWARD.OKO_REGIST%>')" />
									</logic:notEmpty>
								</span>
							</div></p>
							<br>
						<% } else { %>
							<p>ログインしていません。</p>
							<p>ログイン後におこずかい帳に登録することができます。</p>
						<% } %>
					</html:form>
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
