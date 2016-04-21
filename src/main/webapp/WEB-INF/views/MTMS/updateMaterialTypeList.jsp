<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">Material Type</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <c:forEach var="materialType" items="${materialTypes}">
                            <a href="${home}materialType/${materialType.id}?update" class="list-group-item list-group-item-success">
                                    ${materialType.typeName}
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>