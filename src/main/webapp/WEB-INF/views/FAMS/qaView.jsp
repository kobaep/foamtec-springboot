<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#qaFirst">First Shot Review</a></li>
        <li><a data-toggle="tab" href="#qaLast">Final Review</a></li>
        <li><a data-toggle="tab" href="#documentReject">Document Reject</a></li>
    </ul>
    <div class="tab-content">
        <div id="qaFirst" class="tab-pane fade in active">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">First Shot Inspection</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="height: 540px;">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>FA No.</th>
                                    <th>Request Date</th>
                                    <th>Update Date</th>
                                    <th>Need Date</th>
                                    <th>Customer</th>
                                    <th>Part No.</th>
                                    <th>Status</th>
                                    <th>Update By</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="faRequest" items="${faRequestEngineerSendFirst}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${faRequest.faNumber}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                        <td>${faRequest.customer}</td>
                                        <td>${faRequest.partNo}</td>
                                        <td>${faRequest.status}</td>
                                        <c:set var="appuser" value="${faRequest.updateBy}"/>
                                        <td>${appuser.name}</td>
                                        <td>
                                            <a class="btn btn-primary btn-sm" href="${home}fams/qaPrivate/${faRequest.id}?reviewFirst" role="button">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="qaLast" class="tab-pane fade">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">Final Inspection</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="height: 540px;">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>FA No.</th>
                                    <th>Request Date</th>
                                    <th>Update Date</th>
                                    <th>Need Date</th>
                                    <th>Customer</th>
                                    <th>Part No.</th>
                                    <th>Status</th>
                                    <th>Update By</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="faRequest" items="${faRequestEngineerSendFinal}" varStatus="loop">
                                    <tr>
                                        <td>${loop.index + 1}</td>
                                        <td>${faRequest.faNumber}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                        <td>${faRequest.customer}</td>
                                        <td>${faRequest.partNo}</td>
                                        <td>${faRequest.status}</td>
                                        <c:set var="appuser" value="${faRequest.updateBy}"/>
                                        <td>${appuser.name}</td>
                                        <td>
                                            <a class="btn btn-primary btn-sm" href="${home}fams/qaPrivate/${faRequest.id}?reviewFinal" role="button">
                                                <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="documentReject" class="tab-pane fade">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">Final Inspection</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="height: 540px;">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th>No.</th>
                                <th>FA No.</th>
                                <th>Request Date</th>
                                <th>Update Date</th>
                                <th>Need Date</th>
                                <th>Customer</th>
                                <th>Part No.</th>
                                <th>Status</th>
                                <th>Update By</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="faRequest" items="${faRequestQaEngRejectDocument}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${faRequest.faNumber}</td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                    <td>${faRequest.customer}</td>
                                    <td>${faRequest.partNo}</td>
                                    <td>${faRequest.status}</td>
                                    <c:set var="appuser" value="${faRequest.updateBy}"/>
                                    <td>${appuser.name}</td>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="${home}fams/qaPrivate/${faRequest.id}?reviewFinal" role="button">
                                            <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>