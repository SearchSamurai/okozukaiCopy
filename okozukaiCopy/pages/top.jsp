<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import="jp.co.okozukai.common.constants.SystemConst" %>
<%@ page import="jp.co.okozukai.common.constants.JspConst" %>
<link rel="StyleSheet" href="/okozukai/css/top.css" type="text/css">

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
					<h2>Webおこずかい</h2>
					<p>何かあれば書く</p>
					<p>説明とか</p>
					<h2>最新情報＆更新情報</h2>
					<p>2013.01.01&nbsp;&nbsp;本格的にサイト作成開始<img src="/okozukai/img/under_line.gif" alt="" border="0" /></p>
					<p>2013.01.02&nbsp;&nbsp;壁に当たりながら作成中<img src="/okozukai/img/under_line.gif" alt="" border="0" /></p>
					<p>2013.01.05&nbsp;&nbsp;一通りの基本機能の実装完了<img src="/okozukai/img/under_line.gif" alt="" border="0" /></p>
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
