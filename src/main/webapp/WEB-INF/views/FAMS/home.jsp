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
            <li class="active"><a data-toggle="tab" href="#saleRequest">Sale Request FA</a></li>
            <li><a data-toggle="tab" href="#engineerApprove">Engineer Approve</a></li>
            <li><a data-toggle="tab" href="#qaFirst">QA First Inspection</a></li>
            <li><a data-toggle="tab" href="#qaApproveFirst">QA Approve First Shot</a></li>
            <li><a data-toggle="tab" href="#qaFinalInspection">QA Final Inspection</a></li>
            <li><a data-toggle="tab" href="#qaApproveFinal">QA Approve Final</a></li>
            <li><a data-toggle="tab" href="#saleCoSendItem">Sale Co. Send Item Customer</a></li>
        </ul>
        <div class="tab-content">
            <div id="saleRequest" class="tab-pane fade in active">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">FA Request</h3>
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
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/show/${faRequest.id}?detail" role="button">
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
                        <h3 class="panel-title" align="center">Engineer Approve</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestEngineerApproves}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showEngApprove/${faRequest.id}?detail" role="button">
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
            <div id="qaFirst" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">QA First Shot Inspection</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestEngineerSendFirst}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showQaFirst/${faRequest.id}?detail" role="button">
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
                        <h3 class="panel-title" align="center">QA Approve First Shot</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestQaApproveFirst}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showQaFirst/${faRequest.id}?detail" role="button">
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
            <div id="qaFinalInspection" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">QA Final Inspection</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestEngineerSendFinal}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showQaFirst/${faRequest.id}?detail" role="button">
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
            <div id="qaApproveFinal" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">QA Approve Final</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestQaApproveFinal}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showEngApprove/${faRequest.id}?detail" role="button">
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
            <div id="saleCoSendItem" class="tab-pane fade">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Sale Co. Send Item Customer</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestSaleCoSendItem}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td>
                                            <td>${faRequest.faNumber}</td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.createDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${faRequest.updateDate}" /></td>
                                            <td><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></td>
                                            <td>${faRequest.customer}</td>
                                            <td>${faRequest.partNo}</td>
                                            <td>${faRequest.saleOut}</td>
                                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                                            <td>${appuserCreate.name}</td>
                                            <td>
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showSaleCo/${faRequest.id}?detail" role="button">
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
            <li class="active"><a data-toggle="tab" href="#engReject">Engineer Reject</a></li>
            <li><a data-toggle="tab" href="#qaRejectFirst">QA Reject First Shot</a></li>
            <li><a data-toggle="tab" href="#qaRejectFinal">QA Reject Final</a></li>
        </ul>
        <div class="tab-content">
            <div id="engReject" class="tab-pane fade in active">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h3 class="panel-title" align="center">Engineer Reject</h3>
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
                                    <c:forEach var="faRequest" items="${faRequestEngineerRejects}" varStatus="loop">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/show/${faRequest.id}?detail" role="button">
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
            <div id="qaRejectFirst" class="tab-pane fade">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/showQaFirst/${faRequest.id}?detail" role="button">
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
                                        <th>Need Date</th>
                                        <th>Customer</th>
                                        <th>Part No.</th>
                                        <th>Sale Out</th>
                                        <th>Request By</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="faRequest" items="${faRequestQaRejectFinal}" varStatus="loop">
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
                                                <a class="btn btn-primary btn-sm" href="${home}fams/show/${faRequest.id}?detail" role="button">
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