<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Foamtec</title>
    <spring:url value="/resources/core/css/hello.css" var="coreCss" />
    <spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/core/css/jquery-ui.min.css" var="jqueryUiCss" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${jqueryUiCss}" rel="stylesheet" />
    <link href="${coreCss}" rel="stylesheet" />
    <spring:url value="/resources/core/js/hello.js" var="coreJs" />
    <spring:url value="/resources/core/js/jquery.min.js" var="jqueryJs" />
    <spring:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
    <spring:url value="/resources/core/js/jquery-ui.min.js" var="jqueryUiJs" />
    <script src="${coreJs}"></script>
    <script src="${jqueryJs}"></script>
    <script src="${jqueryUiJs}"></script>
    <script src="${bootstrapJs}"></script>
    <script>
        var session = {
            context: '/' + (window.location.pathname.split('/')[1])
        };
    </script>
</head>
<body onunload="">
    <tiles:insertAttribute name="header" />
    <div class="jumbotron">
        <tiles:insertAttribute name="body"/>
    </div>
    <div class="container">
        <tiles:insertAttribute name="footer"/>
    </div>
</body>
</html>