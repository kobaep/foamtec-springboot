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
        <div class="col-sm-12" align="center">
            <form class="form-inline">
                <div class="form-group">
                    <div class="col-sm-9">
                        <input class="form-control" id="inputStartDate" placeholder="Start Date" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9">
                        <input class="form-control" id="inputEndDate" placeholder="End Date" type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-9">
                        <input class="form-control" id="inputTextSearch" placeholder="Text Search" type="text"/>
                    </div>
                </div>
                <button type="button" id="btnSearch" class="btn btn-default"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
            </form>
        </div>
    </div></br>
    <div class="row">
        <div class="col-sm-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">Search Result</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="height: 540px;">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>FA No.</th>
                                    <th>Customer</th>
                                    <th>Part No.</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody id="tableCreate">
                                <jsp:text/>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        $("#inputStartDate").datepicker({ dateFormat: 'dd/mm/yy' });
        $("#inputEndDate").datepicker({ dateFormat: 'dd/mm/yy' });

        var d = new Date();
        var currMonth = d.getMonth();
        var currYear = d.getFullYear();
        var startDate = new Date(currYear, currMonth, 1);
        var endDate = new Date(currYear, currMonth + 1, 1) - 1;

        $("#inputStartDate").datepicker().datepicker('setDate', startDate);
        $("#inputEndDate").datepicker().datepicker('setDate', endDate);

        var datanow = {
            startDate : $("#inputStartDate").val(),
            endDate : $("#inputEndDate").val(),
            status : "na_" + $("#inputTextSearch").val()
        };

        $.ajax({
            url: "${home}fams/search/searchJson",
            type: "POST",
            headers: {
                Accept: "application/json"
            },
            data : {
                data : JSON.stringify(datanow)
            },
            dataType: "json",
            success: function(data){
                $(".dataTable").remove();
                $.each(data, function (i, item) {
                    var dataT1 = '<tr class="dataTable"><td>'+item.no+'</td><td>'+item.faNo+'</td><td>'+item.customer+'</td><td>'+item.partNo+'</td><td>'+item.status+'</td><td><a class="btn btn-primary btn-sm" href="${home}fams/showSaleCo/'+item.id+'?detail" role="button"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a></td></tr>';
                    $("#tableCreate").append(dataT1);
                });
            },
            error: function(data){
                alert("data error.");
            }
        });
    });
</script>