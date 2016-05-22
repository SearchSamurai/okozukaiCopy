<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<link rel="StyleSheet" href="/okozukai/css/userUpdateComplete.css" type="text/css">
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
					<h2>ユーザ情報更新完了</h2>
					<!-- メッセージエリア -->
					<p><div class="message_area">
						<!-- バリデーションエラー -->
						<html:errors />
						<!-- actionでセットしたメッセージ -->
						<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
							<bean:write name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request" />
						</logic:notEmpty>
					</div></p>
					<table class="input_userinfo">
						<tr>
							<td class="input_userinfo_head" align="left">ユーザID</td>
							<td><bean:write name="UserUpdateActionForm" property="userId" scope="request" /></td>
						</tr>
						<tr>
							<td class="input_userinfo_head" align="left">パスワード</td>
							<td><bean:write name="UserUpdateActionForm" property="userPassword" scope="request" /></td>
						</tr>
					</table>
					<!-- ボタンエリア -->
					<p><div class="button_area">
						<span>
							<input type="button" value=" トップページへ " onclick="move('<%=JspConst.JSP_FOWARD.TOP%>')" />
						</span>
					</div></p>
					<br>
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
