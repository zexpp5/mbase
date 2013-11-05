<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html>
	<head>
    	<title>易学网</title>
		<link rel="stylesheet" type="text/css" href="/static/css/site.css" />
		<link rel="stylesheet" type="text/css" href="/static/css/error.css" />
	</head>
	<body>		

		<div class="main">
			<div class="errorType">
				<img src="/static/img/404.png" />
			</div>
			<div class="errorInfo">
				<div>
					服务器内部错误。
				</div>
				<div class="errorDetail">
					<div>
						错误信息:
						<%=exception.getMessage()%>
					</div>
					<div id="detail_system_error_msg">
						<pre>
							<%
								exception.printStackTrace(new java.io.PrintWriter(out));
							%>
						</pre>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>
