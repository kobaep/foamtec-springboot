<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<spring:url value="/" var="home" />
<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">Reject Or Request By QA</div>
                <div class="panel-body">
                    <div style="height: 700px;" class="table-responsive">
                        <table class="table table-hover">
                            <thead>
                            <tr class="danger">
                                <th>#</th>
                                <th>Material Type</th>
                                <th>Material Name</th>
                                <th>Spec</th>
                                <th>MSDS</th>
                                <th>RoHS</th>
                                <th>Halogen Free</th>
                                <th>Guarantee L.</th>
                                <th>Red P.</th>
                                <th>Reason</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="materialReject" items="${materialsReject}" varStatus="loop">
                                <c:set var="materialRejectType" value="${materialReject.materialType}"/>
                                <c:set var="documentHistorys" value="${materialReject.documentHistorys}"/>
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td>${materialRejectType.typeName}</td>
                                    <td>${materialReject.materialName}</td>
                                    <c:choose>
                                        <c:when test="${not empty materialReject.specUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty materialReject.msdsUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty materialReject.rohsUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty materialReject.halogenUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty material.guaranteeLetterUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${not empty material.redPhosphorusUrl}">
                                            <td><span class="glyphicon glyphicon-ok" aria-hidden="true"></span></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></td>
                                        </c:otherwise>
                                    </c:choose>
                                        <c:forEach var="documentHistory" items="${documentHistorys}" varStatus="loopStatus">
                                            <c:if test="${loopStatus.last}">
                                                <td>
                                                    ${documentHistory.remark}
                                                </td>
                                            </c:if>
                                        </c:forEach>
                                    <td>
                                        <a class="btn btn-primary btn-sm" href="${home}mtms/materialPrivate/${materialReject.id}?update" role="button">
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