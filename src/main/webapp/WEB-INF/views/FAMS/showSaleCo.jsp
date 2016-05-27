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
        <div class="col-sm-12">
            <form class="form-horizontal">
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
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch1">Material 1 :</label>
                        <div class="col-sm-4">
                            <label class="form-control-static">${faRequest.material1}</label>
                        </div>
                        <div class="col-sm-4">
                            <label class="form-control-static">Batch : ${faRequest.batch1}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label" for="inputBatch2">Material 2 :</label>
                        <c:if test="${not empty faRequest.material2}">
                            <div class="col-sm-4">
                                <label class="form-control-static">${faRequest.material2}</label>
                            </div>
                            <div class="col-sm-4">
                                <label class="form-control-static">Batch : ${faRequest.batch2}</label>
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
                                <label class="form-control-static">Batch : ${faRequest.batch3}</label>
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
                                <label class="form-control-static">Batch : ${faRequest.batch4}</label>
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
                                <label class="form-control-static">Batch : ${faRequest.batch5}</label>
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
                                <label class="form-control-static">Batch : ${faRequest.batch6}</label>
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
                        <label for="inputRemark" class="col-sm-4 control-label">Remark :</label>
                        <div class="col-sm-8">
                            <textarea id="inputRemark" rows="2" class="form-control" disabled>${faRequest.remark}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputCommitDate" class="col-sm-4 control-label">Commit Date :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static"><fmt:formatDate pattern="dd/MM/yyyy"  value="${faRequest.engCommitDate}" /></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputProcess" class="col-sm-4 control-label">Process :</label>
                        <div class="col-sm-8">
                            <textarea id="inputProcess" rows="4" class="form-control" disabled><jsp:text/>${faRequest.process}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Contract :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.saleCoContract}</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">Invoice No :</label>
                        <div class="col-sm-8">
                            <label class="form-control-static">${faRequest.invoiceNo}</label>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">History</div>
                <div class="panel-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Update Date</th>
                                <th>Status</th>
                                <th>Remark</th>
                                <th>Tooling No</th>
                                <th>Qty</th>
                                <th>Update By</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="documentHistorys" value="${faRequest.documentHistorys}"/>
                            <c:forEach var="documentHistory" items="${documentHistorys}" varStatus="loop">
                                <tr>
                                    <td>${loop.index + 1}</td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy [hh:mm]"  value="${documentHistory.createDate}" /></td>
                                    <td>${documentHistory.status}</td>
                                    <td>${documentHistory.remark}</td>
                                    <td>${documentHistory.methodFirst}</td>
                                    <td>${documentHistory.qtyFirst}</td>
                                    <c:set var="createBy" value="${documentHistory.createBy}"/>
                                    <td>${createBy.name}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>