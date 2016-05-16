<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
    <form class="form-horizontal" id="materialTypeFormId" action="" method="POST">
        <div class="form-group">
            <label for="inputCustomer" class="col-sm-2 control-label">Customer</label>
            <div class="col-sm-10">
                <input type="text" class="form-control input-lg" id="inputCustomer" placeholder="Customer Name" autocomplete="off" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default btn-lg">Submit</button>
            </div>
        </div>
    </form>
</div>
<script>
    $(document).ready(function() {
        $("#materialTypeFormId").submit(function() {
            var data = {
                inputCustomer : $("#inputCustomer").val()
            };
            $.ajax({
                url: '${home}customer/createPrivate/create',
                type: "POST",
                headers: {
                    Accept: "application/json"
                },
                data: {
                    data : JSON.stringify(data)
                },
                dataType: "json",
                success: function(data){
                    window.location.href = "${home}customer";
                },
                error: function(data){
                    alert("saved error.");
                }
            });
            return false;
        });
    });
</script>