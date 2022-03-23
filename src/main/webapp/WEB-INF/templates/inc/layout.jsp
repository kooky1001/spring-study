<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>

<head>
    <title><tiles:getAsString name="title"/></title>
    <meta charset="UTF-8">

    <link href="/css/layout.css" type="text/css" rel="stylesheet" />
    <link href="/css/index.css" type="text/css" rel="stylesheet" />
    <script>
    
    </script>
</head>

<body>
    <!-- header 부분 -->
	<tiles:insertAttribute name="header"/>

    <!-- --------------------------- <body> --------------------------------------- -->
	<tiles:insertAttribute name="body"/>

    <!-- ------------------- <footer> --------------------------------------- -->
	<tiles:insertAttribute name="footer"/>
    
</body>

</html>