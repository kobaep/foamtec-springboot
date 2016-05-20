<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <form class="form-horizontal" id="materialSapFormId" action="" method="POST">
        <div class="form-group">
            <label class="col-sm-2 control-label">Material Type</label>
            <div class="col-sm-10">
                <p class="form-control-static">${materialType.typeName}</p>
            </div>
        </div>
        <div class="form-group">
            <label for="inputMaterialName" class="col-sm-2 control-label">Material Name</label>
            <div class="col-sm-10">
                <select class="form-control input-lg" id="inputMaterialName">
                    <c:forEach var="material" items="${materialType.matters}">
                        <c:if test="${material.status eq 'APPROVE'}">
                            <option value="${material.id}">${material.materialName}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="inputSapCode" class="col-sm-2 control-label">SAP Code</label>
            <div class="col-sm-10">
                <input type="text" class="form-control input-lg" id="inputSapCode" placeholder="SAP Code" autocomplete="off" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" id="kkk" class="btn btn-default">Submit</button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function() {
        $("#materialSapFormId").submit(function() {
            var data = {
                inputMaterialId : $("#inputMaterialName").val(),
                inputSapCode : $("#inputSapCode").val()
            };
            $.ajax({
                url: "${home}mtms/materialSapPrivate/createMaterialSap",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}mtms/";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });
    });
</script>