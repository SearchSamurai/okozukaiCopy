package org.apache.jsp.pages;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import jp.co.okozukai.common.constants.SystemConst;
import jp.co.okozukai.common.constants.JspConst;

public final class top_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/struts-html.tld");
    _jspx_dependants.add("/WEB-INF/struts-bean.tld");
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fhtml_005fhtml;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fhtml_005fhtml = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fhtml_005fhtml.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<link rel=\"StyleSheet\" href=\"/okozukai/css/top.css\" type=\"text/css\">\r\n");
      out.write("\r\n");
      //  html:html
      org.apache.struts.taglib.html.HtmlTag _jspx_th_html_005fhtml_005f0 = (org.apache.struts.taglib.html.HtmlTag) _005fjspx_005ftagPool_005fhtml_005fhtml.get(org.apache.struts.taglib.html.HtmlTag.class);
      _jspx_th_html_005fhtml_005f0.setPageContext(_jspx_page_context);
      _jspx_th_html_005fhtml_005f0.setParent(null);
      int _jspx_eval_html_005fhtml_005f0 = _jspx_th_html_005fhtml_005f0.doStartTag();
      if (_jspx_eval_html_005fhtml_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t<head>\r\n");
          out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
          out.write("\t\t<title>Webおこずかい帳</title>\r\n");
          out.write("\t</head>\r\n");
          out.write("\t<body>\r\n");
          out.write("\t\t<!-- メインコンテンツ部分 -->\r\n");
          out.write("\t\t<div id=\"wrapper\">\r\n");
          out.write("\t\t\t<!-- メインヘッダー部分 -->\r\n");
          out.write("\t\t\t<div>\r\n");
          out.write("\t\t\t\t");
          org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, JspConst.JSP_COMMON.HEADER, out, false);
          out.write("\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t\t<!-- メインヘッダー部分ここまで -->\r\n");
          out.write("\t\t\t<!-- トップコンテンツ部分 -->\r\n");
          out.write("\t\t\t<div id=\"container\">\r\n");
          out.write("\t\t\t\t<!-- コンテンツ内部 -->\r\n");
          out.write("\t\t\t\t<div id=\"contents\">\r\n");
          out.write("\t\t\t\t\t<h2>Webおこずかい</h2>\r\n");
          out.write("\t\t\t\t\t<p>何かあれば書く</p>\r\n");
          out.write("\t\t\t\t\t<p>説明とか</p>\r\n");
          out.write("\t\t\t\t\t<h2>最新情報＆更新情報</h2>\r\n");
          out.write("\t\t\t\t\t<p>2013.01.01&nbsp;&nbsp;本格的にサイト作成開始<img src=\"/okozukai/img/under_line.gif\" alt=\"\" border=\"0\" /></p>\r\n");
          out.write("\t\t\t\t\t<p>2013.01.02&nbsp;&nbsp;壁に当たりながら作成中<img src=\"/okozukai/img/under_line.gif\" alt=\"\" border=\"0\" /></p>\r\n");
          out.write("\t\t\t\t\t<p>2013.01.05&nbsp;&nbsp;一通りの基本機能の実装完了<img src=\"/okozukai/img/under_line.gif\" alt=\"\" border=\"0\" /></p>\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t<!-- コンテンツ内部ここまで -->\r\n");
          out.write("\t\t\t\t<!-- サイドエリア部分 -->\r\n");
          out.write("\t\t\t\t<div>\r\n");
          out.write("\t\t\t\t\t");
          org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, JspConst.JSP_COMMON.MENU, out, false);
          out.write("\r\n");
          out.write("\t\t\t\t</div>\r\n");
          out.write("\t\t\t\t<!-- サイドエリア部分 終わり -->\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t\t<!-- トップコンテンツ部分 終わり -->\r\n");
          out.write("\t\t\t<!-- フッター部分 -->\r\n");
          out.write("\t\t\t<div>\r\n");
          out.write("\t\t\t\t");
          org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, JspConst.JSP_COMMON.FOOTER, out, false);
          out.write("\r\n");
          out.write("\t\t\t</div>\r\n");
          out.write("\t\t\t<!-- フッター部分 終わり -->\r\n");
          out.write("\t\t</div>\r\n");
          out.write("\t\t<!-- メインコンテンツ部分 終わり -->\r\n");
          out.write("\t</body>\r\n");
          int evalDoAfterBody = _jspx_th_html_005fhtml_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_html_005fhtml_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fhtml_005fhtml.reuse(_jspx_th_html_005fhtml_005f0);
      out.write('\r');
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
