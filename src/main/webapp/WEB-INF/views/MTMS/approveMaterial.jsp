<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<c:set var="materialType" value="${material.materialType}"/>
<div class="container">
    <div class="row">
        <form class="form-horizontal">
            <div class="form-group">
                <label class="col-sm-2 control-label">Material Type :</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${materialType.typeName}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Material Name :</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${material.materialName}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">UL Number :</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${material.ulNumber}</p>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-2 control-label">Manufacturing :</label>
                <div class="col-sm-10">
                    <p class="form-control-static">${material.manufacturing}</p>
                </div>
            </div>
            <c:if test="${not empty material.specUrl}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Spec :</label>
                    <div class="col-sm-10 form-inline">
                        <a href="${home}resources/filePDF${material.specUrl}" target="_blank" role="button"><p class="form-control-static">Spec PDF File</p></a>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty material.rohsUrl}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">RoHS :</label>
                    <div class="col-sm-10 form-inline">
                        <a href="${home}resources/filePDF${material.rohsUrl}" target="_blank" role="button"><p class="form-control-static">RoHS PDF File</p></a> &nbsp; &nbsp; &nbsp;
                        <p class="form-control-static">Test Date <fmt:formatDate pattern="dd/MM/yyyy"  value="${material.rohsDateTest}" /></p>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty material.msdsUrl}">
            <div class="form-group">
                <label class="col-sm-2 control-label">MSDS :</label>
                <div class="col-sm-10 form-inline">
                    <a href="${home}resources/filePDF${material.msdsUrl}" target="_blank" role="button"><p class="form-control-static">MSDS PDF File</p></a>
                </div>
                </c:if>
                <c:if test="${not empty material.halogenUrl}">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Halogen Free :</label>
                    <div class="col-sm-10 form-inline">
                        <a href="${home}resources/filePDF${material.halogenUrl}" target="_blank" role="button"><p class="form-control-static">Halogen Free PDF File</p></a> &nbsp; &nbsp; &nbsp;
                        <p class="form-control-static">Test Date <fmt:formatDate pattern="dd/MM/yyyy"  value="${material.halogenDateTest}" /></p>
                    </div>
                </div>
                </c:if>
                <input type="text" class="hidden" id="inputId" value="${material.id}">
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="button" id="btnReject" class="btn btn-danger btn-lg">Reject</button>
                        <button type="button" id="btnApprove" class="col-sm-offset-1 btn btn-success btn-lg">Approve</button>
                    </div>
                </div>
        </form>
    </div>
</div>
<div class="modal fade" id="alertRejectModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title">Reject Reason</h4>
            </div>
            <div class="modal-body">
                <textarea class="form-control" rows="3" id="inputReason"><jsp:text/></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnRejectReason">reject</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $("#btnApprove").click(function() {
            var data = {
                inputId : $("#inputId").val(),
                action : "APPROVE",
                reason : "qa approve"
            };
            $.ajax({
                url: "${home}mtms/materialPrivate/approve",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}mtms/material?waitApproveList";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });

        $("#btnReject").click(function() {
            $("#alertRejectModal").modal({show:true});
        });

        $("#btnRejectReason").click(function() {
            var data = {
                inputId : $("#inputId").val(),
                action : "REJECT",
                reason : $("#inputReason").val()
            };
            $.ajax({
                url: "${home}mtms/materialPrivate/reject",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}mtms/material?waitApproveList";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });
    });
</script>