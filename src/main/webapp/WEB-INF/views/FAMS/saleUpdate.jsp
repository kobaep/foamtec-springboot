<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <form class="form-horizontal" id="materialFormId" action="" method="POST">
        <fieldset>
            <legend>FA Detail</legend>
            <div class="row">
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputCustomer" class="col-sm-3 control-label">Customer :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputCustomer" placeholder="Customer" autocomplete="off" value="${faRequest.customer}" required>
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputProductGroup" class="col-sm-3 control-label">Product Group :</label>
                        <div class="col-sm-9">
                            <select id="inputProductGroup" class="form-control">
                                <option value="Electronics">Electronics</option>
                                <option value="HDD">HDD</option>
                                <option value="Consumer">Consumer</option>
                                <option value="Automotive">Automotive</option>
                                <option value="Cosmetic">Cosmetic</option>
                                <option value="General">General</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPartNo" class="col-sm-3 control-label">Part No. :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputPartNo" placeholder="Part Number" autocomplete="off" value="${faRequest.partNo}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPartName" class="col-sm-3 control-label">Part Name. :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputPartName" placeholder="Part Name" autocomplete="off" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRevision" class="col-sm-3 control-label">Revision :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputRevision" placeholder="Revision" autocomplete="off" value="${faRequest.revision}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSaleOut" class="col-sm-3 control-label">Sale Out :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputSaleOut" placeholder="Sale out door" autocomplete="off" value="${faRequest.saleOut}" required>
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputQwsNo" class="col-sm-3 control-label">QWS No. :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputQwsNo" placeholder="Quatation Work Sheet Number" autocomplete="off" value="${faRequest.qwsNo}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputApqpNo" class="col-sm-3 control-label">APQP No. :</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="inputApqpNo" placeholder="APQP Number" autocomplete="off" value="${faRequest.apqpNo}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputNeedDate" class="col-sm-3 control-label">Need Date :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <fmt:formatDate pattern="dd/MM/yyyy" var="needDate" value="${faRequest.needDate}" />
                                <input type="text" class="form-control" id="inputNeedDate" placeholder="dd/MM/yyyy" autocomplete="off" value="${needDate}" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFaApprove" class="col-sm-3 control-label">FA Approve :</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="inputFaApprove" placeholder="Qty" value="${faRequest.faApproveQty}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFaForSell" class="col-sm-3 control-label">FA For Sell :</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="inputFaForSell" placeholder="Qty" value="${faRequest.faForSellQty}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSampleTest" class="col-sm-3 control-label">Sample Test :</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="inputSampleTest" placeholder="Qty" value="${faRequest.samplTestQty}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputSamplePcc" class="col-sm-3 control-label">Sample PCC :</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="inputSamplePcc" placeholder="Qty" value="${faRequest.samplePccQty}" autocomplete="off">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Type Request:</label>
                        <div class="col-sm-9">
                            <label class="radio-inline">
                                <input type="radio" name="typeRequest" value="New FA Request" checked>New FA Request
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="typeRequest" value="Re Submit">Re Submit
                            </label>
                        </div>
                    </div>
                    <div id="reSubmitDiv" class="form-group hidden">
                        <label for="inputReSubmitDetail" class="col-sm-3 control-label">Re Submit Detail :</label>
                        <div class="col-sm-9">
                            <textarea id="inputReSubmitDetail" rows="2" class="form-control"><jsp:text/></textarea>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label for="inputMat1" class="col-sm-3 control-label">Material 1 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat1" placeholder="Material 1" autocomplete="off" value="${faRequest.material1}" required>
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMat2" class="col-sm-3 control-label">Material 2 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat2" placeholder="Material 2" autocomplete="off" value="${faRequest.material2}">
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMat3" class="col-sm-3 control-label">Material 3 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat3" placeholder="Material 3" autocomplete="off" value="${faRequest.material3}">
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMat4" class="col-sm-3 control-label">Material 4 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat4" placeholder="Material 4" autocomplete="off" value="${faRequest.material4}">
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMat5" class="col-sm-3 control-label">Material 5 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat5" placeholder="Material 5" autocomplete="off" value="${faRequest.material5}">
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputMat6" class="col-sm-3 control-label">Material 6 :</label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input type="text" class="form-control" id="inputMat6" placeholder="Material 6" autocomplete="off" value="${faRequest.material6}">
                                <span class="input-group-addon">Search</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Document Request :</label>
                        <div class="col-sm-9">
                            <select id="inputDocumentRequest" class="form-control">
                                <option value="No Need Data">No Need Data</option>
                                <option value="Inspection Only">Inspection Only</option>
                                <option value="Full FA Package">Full FA Package</option>
                                <option value="PPAP">PPAP</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputTools" class="col-sm-3 control-label">Tools :</label>
                        <div class="col-sm-9">
                            <select id="inputTools" class="form-control">
                                <option value="Tooling">Tooling</option>
                                <option value="CNC">CNC</option>
                                <option value="LASER">LASER</option>
                                <option value="Other">Other</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">Cutting Type :</label>
                        <div class="col-sm-9">
                            <label class="radio-inline">
                                <input type="radio" name="cuttingType" value="Full Cut" checked>Full Cut
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="cuttingType" value="Half Cut">Half Cut
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputRemark" class="col-sm-3 control-label">Remark :</label>
                        <div class="col-sm-9">
                            <textarea id="inputRemark" rows="2" class="form-control"><jsp:text/>${faRequest.remark}</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFile1" class="col-sm-3 control-label">Drawing :</label>
                        <div class="col-sm-9 form-inline">
                            <span class="btn btn-file"><input type="file" id="inputFile1"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputFile2" class="col-sm-3 control-label">Other File :</label>
                        <div class="col-sm-9 form-inline">
                            <span class="btn btn-file"><input type="file" id="inputFile2"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-9">
                            <c:choose>
                                <c:when test="${faRequest.status eq 'saleCreate' or faRequest.status eq 'saleUpdate' or faRequest.status eq 'engReject'}">
                                    <button type="submit" id="submit" class="btn btn-primary">Update</button>
                                    <button type="button" id="btnCancel" class="btn btn-danger col-sm-offset-1">Cancel</button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" id="submit" class="btn btn-primary" disabled>Update</button>
                                    <button type="button" id="btnCancel" class="btn btn-danger col-sm-offset-1" disabled>Cancel</button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
        <div class="row">

        </div>
    </form>
</div>
<div class="modal fade" id="alertCancelModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                <h4 class="modal-title">Cancel</h4>
            </div>
            <div class="modal-body">
                Confirm cancel
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="btnAlertCancel">confirm</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {

        $('input[type=radio][name=typeRequest]').change(function() {
            if (this.value == 'New FA Request') {
                $("#reSubmitDiv").addClass("hidden");
                $("#inputReSubmitDetail").val("");
            }
            else {
                $("#reSubmitDiv").removeClass("hidden");
            }
        });

        $("#inputNeedDate").datepicker({ dateFormat: "dd/mm/yy" });

        $('#inputProductGroup option[value="${faRequest.productGroup}"]').attr('selected', 'selected');
        $('#inputDocumentRequest option[value="${faRequest.documentRequest}"]').attr('selected', 'selected');
        $('#inputTools option[value="${faRequest.tool}"]').attr('selected', 'selected');

        var customers = [];

        $.ajax({
            url: '${home}customer/createPrivate/customers',
            type: "POST",
            headers: {
                Accept: "application/json"
            },
            data: {
                data : JSON.stringify("")
            },
            dataType: "json",
            success: function(data){
                $.each(data, function(key, value) {
                    customers.push(value.name);
                });
            },
            error: function(data){
                alert("no data");
            }
        });

        $("#inputCustomer").autocomplete({
            source: customers
        });

        var appUsers = [];

        $.ajax({
            url: '${home}appuser/appusersSaleOut',
            type: "POST",
            headers: {
                Accept: "application/json"
            },
            data: {
                data : JSON.stringify("")
            },
            dataType: "json",
            success: function(data){
                $.each(data, function(key, value) {
                    appUsers.push(value.name);
                });
            },
            error: function(data){
                alert("no data");
            }
        });

        $("#inputSaleOut").autocomplete({
            source: appUsers
        });

        var materials = [];

        $.ajax({
            url: '${home}mtms/materialPrivate/allMatAndSap',
            type: "POST",
            headers: {
                Accept: "application/json"
            },
            data: {
                data : JSON.stringify("")
            },
            dataType: "json",
            success: function(data){
                $.each(data, function(key, value) {
                    materials.push(value.name);
                });
            },
            error: function(data){
                alert("no data");
            }
        });

        $("#inputMat1").autocomplete({
            source: materials
        });

        $("#inputMat2").autocomplete({
            source: materials
        });

        $("#inputMat3").autocomplete({
            source: materials
        });

        $("#inputMat4").autocomplete({
            source: materials
        });

        $("#inputMat5").autocomplete({
            source: materials
        });

        $("#inputMat6").autocomplete({
            source: materials
        });

        $("#materialFormId").submit(function() {

            if(customers.indexOf($("#inputCustomer").val()) < 0) {
                $("#inputCustomer").focus();
                return false;
            }

            if(appUsers.indexOf($("#inputSaleOut").val()) < 0) {
                $("#inputSaleOut").focus();
                return false;
            }

            var inputMat1 = $("#inputMat1").val();
            if(materials.indexOf(inputMat1) < 0) {
                $("#inputMat1").focus();
                return false;
            }
            var inputMat2 = $("#inputMat2").val();
            if(materials.indexOf(inputMat2) < 0 && inputMat2.length > 0) {
                $("#inputMat2").focus();
                return false;
            }
            var inputMat3 = $("#inputMat3").val();
            if(materials.indexOf(inputMat3) < 0 && inputMat3.length > 0) {
                $("#inputMat3").focus();
                return false;
            }
            var inputMat4 = $("#inputMat4").val();
            if(materials.indexOf(inputMat4) < 0 && inputMat4.length > 0) {
                $("#inputMat4").focus();
                return false;
            }
            var inputMat5 = $("#inputMat5").val();
            if(materials.indexOf(inputMat5) < 0 && inputMat5.length > 0) {
                $("#inputMat5").focus();
                return false;
            }
            var inputMat6 = $("#inputMat6").val();
            if(materials.indexOf(inputMat6) < 0 && inputMat6.length > 0) {
                $("#inputMat6").focus();
                return false;
            }

            if(inputMat1.length > 0) {
                console.log("1");
                var data = {
                    inputMat : $("#inputMat1").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        console.log("2");
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            console.log("3");
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            if(inputMat2.length > 0) {
                var data = {
                    inputMat : $("#inputMat2").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            if(inputMat3.length > 0) {
                var data = {
                    inputMat : $("#inputMat3").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            if(inputMat4.length > 0) {
                var data = {
                    inputMat : $("#inputMat4").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            if(inputMat5.length > 0) {
                var data = {
                    inputMat : $("#inputMat5").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            if(inputMat6.length > 0) {
                var data = {
                    inputMat : $("#inputMat6").val()
                }
                $.ajax({
                    url: '${home}mtms/materialPrivate/getDataMatOrSap',
                    type: "POST",
                    headers: {
                        Accept: "application/json"
                    },
                    data: {
                        data : JSON.stringify(data)
                    },
                    dataType: "json",
                    success: function(data){
                        if(data["document"] == "ok") {
                            console.log(data["document"]);
                        } else {
                            if($("#inputDocumentRequest").val() == "Full FA Package" || $("#inputDocumentRequest").val() == "PPAP") {
                                alert("เอกสารไม่ครบไม่สามารถเลือก Full FA หรือ PPAP ได้");
                                return false;
                            }
                        }
                    },
                    error: function(data){
                        alert("no data");
                    }
                });
            }

            var data = {
                inputCustomer : $("#inputCustomer").val(),
                inputProductGroup : $("#inputProductGroup").val(),
                inputPartNo : $("#inputPartNo").val(),
                inputPartName : $("#inputPartName").val(),
                inputRevision : $("#inputRevision").val(),
                inputSaleOut : $("#inputSaleOut").val(),
                inputQwsNo : $("#inputQwsNo").val(),
                inputApqpNo : $("#inputApqpNo").val(),
                inputNeedDate : $("#inputNeedDate").val(),
                inputFaApprove : $("#inputFaApprove").val(),
                inputFaForSell : $("#inputFaForSell").val(),
                inputSampleTest : $("#inputSampleTest").val(),
                inputSamplePcc : $("#inputSamplePcc").val(),
                inputMat1 : $("#inputMat1").val(),
                inputMat1 : $("#inputMat1").val(),
                inputMat2 : $("#inputMat2").val(),
                inputMat3 : $("#inputMat3").val(),
                inputMat4 : $("#inputMat4").val(),
                inputMat5 : $("#inputMat5").val(),
                inputMat6 : $("#inputMat6").val(),
                inputDocumentRequest : $("#inputDocumentRequest").val(),
                inputTools : $("#inputTools").val(),
                inputTypeRequest : $('input[name=typeRequest]:checked').val(),
                inputReSubmitDetail : $("#inputReSubmitDetail").val(),
                inputCuttingType : $('input[name=cuttingType]:checked').val(),
                inputRemark : $("#inputRemark").val()
            }

            var formData = new FormData();
            formData.append("inputFile1", $("#inputFile1")[0].files[0]);
            formData.append("inputFile2", $("#inputFile2")[0].files[0]);
            formData.append("data", JSON.stringify(data));

            $.ajax({
                url: '${home}fams/requestPrivate/saleUpdate',
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: formData,
                dataType: "json",
                contentType: false,
                processData: false,
                async: false,
                success: function(data){
                    window.location.href = "${home}fams/requestPrivate?updateList";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });

        $("#btnCancel").click(function() {
            $("#alertCancelModal").modal({show:true});
        });

        $("#btnAlertCancel").click(function() {
            var data = {
                inputId : "${faRequest.id}",
                action : "saleCancel"
            };
            $.ajax({
                url: "${home}fams/requestPrivate/saleCancel",
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}fams/requestPrivate?updateList";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });

    });
</script>