<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <form class="form-horizontal" id="materialFormId" action="" method="POST">
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="inputCustomer" class="col-sm-3 control-label">Customer :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputCustomer" placeholder="Customer" autocomplete="off" required>
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputProductGroup" class="col-sm-3 control-label">Product Group :</label>
                    <div class="col-sm-9">
                        <select id="inputProductGroup" class="form-control">
                            <option>Electronics</option>
                            <option>HDD</option>
                            <option>Consumer</option>
                            <option>Automotive</option>
                            <option>Cosmetic</option>
                            <option>General</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPartNo" class="col-sm-3 control-label">Part No. :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputPartNo" placeholder="Part Number" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputRevision" class="col-sm-3 control-label">Revision :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputRevision" placeholder="Revision" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputSaleOut" class="col-sm-3 control-label">Sale Out :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputSaleOut" placeholder="Sale out door" autocomplete="off" required>
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputQwsNo" class="col-sm-3 control-label">QWS No. :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputQwsNo" placeholder="Quatation Work Sheet Number" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputApqpNo" class="col-sm-3 control-label">APQP No. :</label>
                    <div class="col-sm-9">
                        <input type="text" class="form-control" id="inputApqpNo" placeholder="APQP Number" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputNeedDate" class="col-sm-3 control-label">Need Date :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputNeedDate" placeholder="dd/MM/yyyy" autocomplete="off" required>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFaApprove" class="col-sm-3 control-label">FA Approve :</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="inputFaApprove" placeholder="Qty" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputFaForSell" class="col-sm-3 control-label">FA For Sell :</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="inputFaForSell" placeholder="Qty" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputSampleTest" class="col-sm-3 control-label">Sample Test :</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="inputSampleTest" placeholder="Qty" autocomplete="off" required>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputSamplePcc" class="col-sm-3 control-label">Sample PCC :</label>
                    <div class="col-sm-9">
                        <input type="number" class="form-control" id="inputSamplePcc" placeholder="Qty" autocomplete="off" required>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label for="inputMat1" class="col-sm-3 control-label">Material 1 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat1" placeholder="Material 1" autocomplete="off" required>
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMat2" class="col-sm-3 control-label">Material 2 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat2" placeholder="Material 2" autocomplete="off">
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMat3" class="col-sm-3 control-label">Material 3 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat3" placeholder="Material 3" autocomplete="off">
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMat4" class="col-sm-3 control-label">Material 4 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat4" placeholder="Material 4" autocomplete="off">
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMat5" class="col-sm-3 control-label">Material 5 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat5" placeholder="Material 5" autocomplete="off">
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputMat6" class="col-sm-3 control-label">Material 6 :</label>
                    <div class="col-sm-9">
                        <div class="input-group">
                            <input type="text" class="form-control" id="inputMat6" placeholder="Material 6" autocomplete="off">
                            <span class="input-group-addon">Search</span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">Document Request :</label>
                    <div class="col-sm-9">
                        <select id="inputDocumentRequest" class="form-control">
                            <option>No Need Data</option>
                            <option>Inspection Only</option>
                            <option>Full FA Package</option>
                            <option>PPAP</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputTools" class="col-sm-3 control-label">Tools :</label>
                    <div class="col-sm-9">
                        <select id="inputTools" class="form-control">
                            <option>Tooling</option>
                            <option>CNC</option>
                            <option>LASER</option>
                            <option>Other</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputRemark" class="col-sm-3 control-label">Remark :</label>
                    <div class="col-sm-9">
                        <textarea id="inputRemark" rows="2" class="form-control"><jsp:text/></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" id="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">

        </div>
    </form>
</div>
<script>
    $(document).ready(function() {

        $("#inputNeedDate").datepicker({ dateFormat: "dd/mm/yy" });

        var customers = [
            "FOAMTEC",
            "CANON",
            "TOYOTA",
            "DELTA",
        ];
        $("#inputCustomer").autocomplete({
            source: customers
        });

        $("#inputMat1").autocomplete({
            source: customers
        });
    });
</script>