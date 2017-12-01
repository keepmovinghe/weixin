<%@ page import="com.keep.pojo.SNSUserInfo" %><%--
  Created by IntelliJ IDEA.
  User: chenxh
  Date: 2017/6/20
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>OAuth2.0 网页授权</title>
    <meta name="viewport" content="width=device-width,user-scalable=0">
  </head>
  <body>
    <%
      // 获取由 OAuthServlet 传入的参数
      SNSUserInfo user = (SNSUserInfo) request.getAttribute("snsUserInfo");
      if(null != user){
    %>
    <table width="100%" cellspacing="0" cellpadding="0">
      <tr><td width="20%">属性</td><td width="20%">值</td></tr>
      <tr><td>OpenId</td><td><%user.getOpenId();%></td></tr>
      <tr><td>昵称</td><td><%user.getNickName();%></td></tr>
      <tr><td>性别</td><td><%user.getSex();%></td></tr>
      <tr><td>国家</td><td><%user.getCountry();%></td></tr>
      <tr><td>省份</td><td><%user.getProvince();%></td></tr>
      <tr><td>城市</td><td><%user.getCity();%></td></tr>
      <tr><td>头像</td><td><%user.getHeadImgUrl();%></td></tr>
      <tr><td>特权</td><td><%user.getPrivilegeList();%></td></tr>
    </table>
  </body>
  <%
    }else
    out.print("未获取到用户信息！");
  %>
</html>
