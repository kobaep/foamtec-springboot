<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#saleRequest">Waiting Review</a></li>
            <li><a data-toggle="tab" href="#engineerApprove">Waiting First Sample</a></li>
            <li><a data-toggle="tab" href="#qaApproveFirst">Waiting Final Sample</a></li>
        </ul>
        <div class="tab-content">
            <div id="saleRequest" class="tab-pane fade in active">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Waiting Review</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="height: 300px;">
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
                                        <th>Sale Out</th>
                                        <th>Request By</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="faRequest" items="${faRequestSaleCreates}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuser" value="${faRequest.updateBy}"/>
                                            <td>${appuser.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/engineerPrivate/${faRequest.id}?review" role="button">
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
            <div id="engineerApprove" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Waiting First Sample</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="height: 300px;">
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
                                    <c:forEach var="faRequest" items="${faRequestEngineerApproves}" varStatus="loop">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/engineerPrivate/${faRequest.id}?sendItemFirst" role="button">
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
            <div id="qaApproveFirst" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Waiting Final Sample</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="height: 300px;">
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
                                    <c:forEach var="faRequest" items="${faRequestQaApproveFirst}" varStatus="loop">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/engineerPrivate/${faRequest.id}?sendItemFinal" role="button">
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
    <div class="row">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#qaRejectFirst">QA Reject First Shot</a></li>
            <li><a data-toggle="tab" href="#qaRejectFinal">QA Reject Final</a></li>
        </ul>
        <div class="tab-content">
            <div id="qaRejectFirst" class="tab-pane fade in active">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">QA Reject First Shot</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="height: 300px;">
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>FA No.</th>
                                        <th>Request Date</th>
                                        <th>Need Date</th>
                                        <th>Customer</th>
                                        <th>Part No.</th>
                                        <th>Sale Out</th>
                                        <th>Request By</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="faRequest" items="${faRequestQaRejectFirst}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/engineerPrivate/${faRequest.id}?sendItemFirst" role="button">
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
            <div id="qaRejectFinal" class="tab-pane fade">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">QA Reject Final</h3>
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="height: 300px;">
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
                                    <c:forEach var="faRequest" items="${faRequestQaRejectFinal}" varStatus="loop">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/engineerPrivate/${faRequest.id}?sendItemFinal" role="button">
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
</div>