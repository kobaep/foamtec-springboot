<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<spring:url value="/" var="home" />
<div class="container">
    <div class="row">
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
                            <label class="col-sm-4 control-label">Material 1 :</label>
                            <div class="col-sm-8">
                                <label class="form-control-static">${faRequest.material1}</label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Material 2 :</label>
                            <div class="col-sm-8">
                                <c:if test="${not empty faRequest.material2}">
                                    <label class="form-control-static">${faRequest.material2}</label>
                                </c:if>
                                <c:if test="${empty faRequest.material2}">
                                    <label class="form-control-static"> n/a </label>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Material 3 :</label>
                            <div class="col-sm-8">
                                <c:if test="${not empty faRequest.material3}">
                                    <label class="form-control-static">${faRequest.material3}</label>
                                </c:if>
                                <c:if test="${empty faRequest.material3}">
                                    <label class="form-control-static"> n/a </label>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Material 4 :</label>
                            <div class="col-sm-8">
                                <c:if test="${not empty faRequest.material4}">
                                    <label class="form-control-static">${faRequest.material4}</label>
                                </c:if>
                                <c:if test="${empty faRequest.material4}">
                                    <label class="form-control-static"> n/a </label>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Material 5 :</label>
                            <div class="col-sm-8">
                                <c:if test="${not empty faRequest.material5}">
                                    <label class="form-control-static">${faRequest.material5}</label>
                                </c:if>
                                <c:if test="${empty faRequest.material5}">
                                    <label class="form-control-static"> n/a </label>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-4 control-label">Material 6 :</label>
                            <div class="col-sm-8">
                                <c:if test="${not empty faRequest.material6}">
                                    <label class="form-control-static">${faRequest.material6}</label>
                                </c:if>
                                <c:if test="${empty faRequest.material6}">
                                    <label class="form-control-static"> n/a </label>
                                </c:if>
                            </div>
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
                    </div>
                </fieldset>
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