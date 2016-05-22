<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<link rel="StyleSheet" href="/okozukai/css/userRegist.css" type="text/css">
<script type="text/javascript" src="/okozukai/js/common.js" charset="UTF-8"></script>

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
					<html:form action="/UserRegistAction" method="POST">
						<h2>新規ユーザ登録</h2>
						<p>※登録するユーザ情報を入力して下さい。</p>
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
						<table class="input_userinfo">
							<tr>
								<td align="right">ユーザID：</td>
								<td><html:text property="userId" size="25" maxlength="20" /></td>
							</tr>
							<tr>
								<td align="right"><right>パスワード：</right></td>
								<td><html:text property="userPassword" size="25" maxlength="20" /></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<br>
									<input type="button" value=" 戻る " onclick="move('<%=JspConst.JSP_FOWARD.TOP%>')" />
									<html:submit property="submit" value=" 登録する " />
								</td>
							</tr>
						</table>
						<br>
					</html:form>
				</div>
				<!-- コンテンツ内部ここまで -->
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
