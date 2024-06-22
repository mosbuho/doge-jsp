<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="mngr.enc.PwCrypt"%>
<%@ page import="java.util.List"%>
<%
PwCrypt.getInstance().cryptProcess();
out.println("암호화 성공");
%>