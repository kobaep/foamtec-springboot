<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h3 class="panel-title" align="center">FA Request By ${name}</h3>
        </div>
        <div class="panel-body">
            <div class="table-responsive" style="height: 540px;">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>FA No.</th>
                            <th>Create Date</th>
                            <th>Update Date</th>
                            <th>Need Date</th>
                            <th>Customer</th>
                            <th>Part No.</th>
                            <th>Update By</th>
                            <th>Status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="faRequest" items="${faRequests}" varStatus="loop">
                            <c:choose>
                                <c:when test="${faRequest.status eq 'saleCancel' or faRequest.status eq 'engCancel'}">

                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${faRequest.faNumber}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                        <td>${faRequest.customer}</td>
                                        <td>${faRequest.partNo}</td>
                                        <c:set var="appuser" value="${faRequest.updateBy}"/>
                                        <td>${appuser.name}</td>
                                        <td>${faRequest.status}</td>
                                        <td>
                                            <a class="btn btn-primary btn-sm" href="${home}fams/requestPrivate/${faRequest.id}?update" role="button">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>