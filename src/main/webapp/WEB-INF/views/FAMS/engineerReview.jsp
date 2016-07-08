<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
    <div class="col-sm-12">
        <form class="form-horizontal">
            <fieldset>
                <legend>FA Detail</legend>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label">FA Number :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.faNumber}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Customer :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.customer}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Product Group :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.productGroup}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Part No. :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.partNo}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Part Name :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.partName}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Revision :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.revision}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Request By :</label>
                        <div class="col-sm-8">
                            <c:set var="appuserCreate" value="${faRequest.createBy}"/>
                            <label class="form-control-static">${appuserCreate.name}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Sale Out :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.saleOut}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">QWS No. :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.qwsNo}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">APQP No. :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.apqpNo}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Need Date :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static"><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.needDate}" /></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">FA Approve :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.faApproveQty} pcs</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">FA For Sell :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.faForSellQty} pcs</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Sample Test :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.samplTestQty} pcs</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Sample PCC :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.samplePccQty} pcs</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Type Request :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.typeRequest}</label>
                        </div>
                    </div>
                    <c:if test="${not empty faRequest.reSubmitDetail}">
                        <div class="form-group">
                            <label for="inputRemark" class="col-sm-4 control-label">Re Submit Detail :</label>
                            <div class="col-sm-8">
                                <textarea rows="2" class="form-control"  disabled>${faRequest.reSubmitDetail}</textarea>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch1">Material 1 :</label>
                        <div class="col-sm-4">
                            <label class="form-control-static">${faRequest.material1}</label>
                        </div>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputBatch1" placeholder="Batch Number." autocomplete="off" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch2">Material 2 :</label>
                        <c:if test="${not empty faRequest.material2}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material2}</label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="inputBatch2" placeholder="Batch Number." autocomplete="off" required>
                            </div>
                        </c:if>
                        <c:if test="${empty faRequest.material2}">
                            <div class="col-sm-4">
                                <label class="form-control-static"> n/a </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control hidden" id="inputBatch2" value="noNo">
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch3">Material 3 :</label>
                        <c:if test="${not empty faRequest.material3}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material3}</label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="inputBatch3" placeholder="Batch Number." autocomplete="off" required>
                            </div>
                        </c:if>
                        <c:if test="${empty faRequest.material3}">
                            <div class="col-sm-4">
                                <label class="form-control-static"> n/a </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control hidden" id="inputBatch3" value="noNo">
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch4">Material 4 :</label>
                        <c:if test="${not empty faRequest.material4}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material4}</label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="inputBatch4" placeholder="Batch Number." autocomplete="off" required>
                            </div>
                        </c:if>
                        <c:if test="${empty faRequest.material4}">
                            <div class="col-sm-4">
                                <label class="form-control-static"> n/a </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control hidden" id="inputBatch4" value="noNo">
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch5">Material 5 :</label>
                        <c:if test="${not empty faRequest.material5}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material5}</label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="inputBatch5" placeholder="Batch Number." autocomplete="off" required>
                            </div>
                        </c:if>
                        <c:if test="${empty faRequest.material5}">
                            <div class="col-sm-4">
                                <label class="form-control-static"> n/a </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control hidden" id="inputBatch5" value="noNo">
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch2">Material 6 :</label>
                        <c:if test="${not empty faRequest.material6}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material6}</label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="inputBatch6" placeholder="Batch Number." autocomplete="off" required>
                            </div>
                        </c:if>
                        <c:if test="${empty faRequest.material6}">
                            <div class="col-sm-4">
                                <label class="form-control-static"> n/a </label>
                            </div>
                            <div class="col-sm-4">
                                <input type="text" class="form-control hidden" id="inputBatch6" value="noNo">
                            </div>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Document Request :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.documentRequest}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Tools :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.tool}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Cutting Type :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.cuttingType}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRemark" class="col-sm-4 control-label">Remark :</label>
                        <div class="col-sm-8">
                            <textarea id="inputRemark" rows="2" class="form-control"  disabled>${faRequest.remark}</textarea>
                        </div>
                    </div>
                    <c:if test="${not empty faRequest.fileDrawing}">
                        <div class="form-group form-inline">
                            <label class="col-sm-4 control-label">Drawing :</label>
                            <div class="col-sm-8">
                                <p class="form-control-static">
                                    <a class="btn btn-info" href="${home}resources/filePDFFARequest${faRequest.fileDrawing}" target="_blank" role="button"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                </p>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${not empty faRequest.fileOther}">
                        <div class="form-group form-inline">
                            <label class="col-sm-4 control-label">Other :</label>
                            <div class="col-sm-8">
                                <p class="form-control-static">
                                    <a class="btn btn-info" href="${home}resources/filePDFFARequest${faRequest.fileOther}" target="_blank" role="button"><span class="glyphicon glyphicon-download-alt" aria-hidden="true"></span></a>
                                </p>
                            </div>
                        </div>
                    </c:if>
                    <div class="form-group">
                        <label for="inputCommitDate" class="col-sm-4 control-label">Commit Date :</label>
                        <div class="col-sm-8">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputCommitDate" placeholder="dd/MM/yyyy" autocomplete="off" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputProcess" class="col-sm-4 control-label">Process :</label>
                        <div class="col-sm-8">
                            <textarea id="inputProcess" rows="4" class="form-control"><jsp:text/></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <c:choose>
                                <c:when test="${faRequest.status eq 'saleCreate' or faRequest.status eq 'saleUpdate'}">
                                    <button type="button" id="btnApprove" class="btn btn-success">Approve</button>
                                    <button type="button" id="btnReject" class="btn btn-danger col-sm-offset-1">Reject</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="button" id="btnApprove" class="btn btn-success" disabled>Approve</button>
                                    <button type="button" id="btnReject" class="btn btn-danger col-sm-offset-1" disabled>Reject</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>
<div class="modal fade" id="alertApproveModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title">Approve</h4>
            </div>
            <div class="modal-body">
                Confirm approve
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="btnApproveReason">confirm</button>
            </div>
        </div>
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
        $("#inputCommitDate").datepicker({ dateFormat: "dd/mm/yy" });

        $("#btnApprove").click(function() {
            var inputBatch1 = $("#inputBatch1").val();
            if(inputBatch1.length <= 0) {
                $("#inputBatch1").focus();
                return false;
            }
            var inputBatch2 = $("#inputBatch2").val();
            if(inputBatch2.length <= 0) {
                $("#inputBatch2").focus();
                return false;
            }
            var inputBatch3 = $("#inputBatch3").val();
            if(inputBatch3.length <= 0) {
                $("#inputBatch3").focus();
                return false;
            }
            var inputBatch4 = $("#inputBatch4").val();
            if(inputBatch4.length <= 0) {
                $("#inputBatch4").focus();
                return false;
            }
            var inputBatch5 = $("#inputBatch5").val();
            if(inputBatch5.length <= 0) {
                $("#inputBatch5").focus();
                return false;
            }
            var inputBatch6 = $("#inputBatch6").val();
            if(inputBatch6.length <= 0) {
                $("#inputBatch6").focus();
                return false;
            }
            var inputCommitDate = $("#inputCommitDate").val();
            if(inputCommitDate.length <= 0) {
                $("#inputCommitDate").focus();
                return false;
            }
            var inputProcess = $("#inputProcess").val();
            if(inputProcess.length <= 0) {
                $("#inputProcess").focus();
                return false;
            }
            $("#alertApproveModal").modal({show:true});
        });

        $("#btnApproveReason").click(function() {
            var data = {
                inputId : "${faRequest.id}",
                action : "engApprove",
                inputCommitDate : $("#inputCommitDate").val(),
                inputProcess : $("#inputProcess").val(),
                inputBatch1 : $("#inputBatch1").val(),
                inputBatch2 : $("#inputBatch2").val(),
                inputBatch3 : $("#inputBatch3").val(),
                inputBatch4 : $("#inputBatch4").val(),
                inputBatch5 : $("#inputBatch5").val(),
                inputBatch6 : $("#inputBatch6").val()
            };
            $.ajax({
                url: "${home}fams/engineerPrivate/approve",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}fams/engineerPrivate?engineerView";
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
                inputId : "${faRequest.id}",
                action : "engReject",
                inputReason : $("#inputReason").val()
            };
            $.ajax({
                url: "${home}fams/engineerPrivate/reject",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}fams/engineerPrivate?engineerView";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });
    });
</script>