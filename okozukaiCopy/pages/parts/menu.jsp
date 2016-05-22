<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<link rel="StyleSheet" href="/okozukai/css/parts/menu.css" type="text/css">

<%
	String loginFlg = (String) request.getSession().getAttribute(SystemConst.SESSION_KEY.LOGIN_FLG);
%>

<div id="side_area">
	<h2>メニュー</h2>
	<ul>
		<li><a href="<%=JspConst.JSP_FOWARD.TOP%>">トップページ</a></li>
		<li><a href="<%=JspConst.JSP_FOWARD.OKO_REGIST%>">おこずかい帳 登録</a></li>
		<li><a href="<%=JspConst.JSP_FOWARD.OKO_SEARCH%>">おこずかい帳 検索</a></li>
	</ul>

	<% if (loginFlg != null && loginFlg.equals(SystemConst.SESSION_VALUE.LOGIN_FLG_ON)) { %>
		<h2>ユーザ情報</h2>
		<table width="100%">
			<tr>
				<td class="user_info" align="center">
					ようこそ<bean:write name="<%=SystemConst.SESSION_KEY.USER_INFO%>" property="userId" scope="session" />さん
				</td>
			</tr>
		</table>
		<ul>
			<li><a href="<%=JspConst.JSP_FOWARD.USER_UPDATE%>">ユーザ情報変更</a></li>
		</ul>
		<center>
			<html:form action="/LogoutAction" method="POST">
				<html:submit property="submit" value=" ログアウト " />
			</html:form>
		</center>
		<br>
	<% } else { %>
		<h2>ログイン</h2>
		<table>
			<html:form action="/LoginAction" method="POST">
				<tr>
					<td align="left">ユーザID：</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"><html:text property="userId" size="25" maxlength="20" /></td>
				</tr>
				<tr>
					<td align="left">パスワード：</td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2"><html:password property="userPassword" size="25" maxlength="20" /></td>
				</tr>
				<!-- メッセージエリア -->
				<div id="message_area">
					<tr>
						<td colspan="2" class="message_area" align="center">
							<!-- バリデーションエラー -->
							<html:errors />
							<!-- actionでセットしたメッセージ -->
							<logic:notEmpty name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request">
								<bean:write name="<%=SystemConst.REQUEST_KEY.MESSAGE_INFO%>" scope="request" />
							</logic:notEmpty>
						</td>
					</tr>
				</div>
				<tr>
					<td colspan="2" align="center"><html:submit property="submit" value=" ログイン " /></td>
				</tr>
				<tr>
					<td colspan="2" align="center">※新規登録する方は<html:link href="<%=JspConst.JSP_FOWARD.USER_REGIST%>" >こちら</html:link></td>
				</tr>
			</html:form>
		</table>
	<% } %>
</div>
