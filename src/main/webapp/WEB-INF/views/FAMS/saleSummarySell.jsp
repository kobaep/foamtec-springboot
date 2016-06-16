<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <div class="col-sm-12"><h2 align="center">Summary Of Sell</h2></div>
    <div class="row">
        <div class="col-sm-6 alert alert-warning" role="alert">
            <canvas id="sellSell" width="70" height="30"></canvas>
        </div>
        <div class="col-sm-6 alert alert-danger" role="alert">
            <canvas id="sellCustomer" width="70" height="30"></canvas>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Sale Out</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                            <c:forEach var="saleSell" items="${saleSells}" varStatus="loop">
                                <div class="panel panel-info">
                                    <div class="panel-heading" role="tab" id="sale_${loop.index}">
                                        <h4 class="panel-title">
                                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse_${loop.index}" aria-expanded="true" aria-controls="collapse_${loop.index}">
                                                <span class="badge">${fn:length(saleSell.value)}</span>
                                                    ${saleSell.key}
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <table class="table table-hover">
                                                <thead>
                                                <th>FA No.</th>
                                                <th>Part No.</th>
                                                <th>Customer</th>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="faPart" items="${saleSell.value}" varStatus="loop">
                                                    <tr>
                                                        <td>${faPart.faNumber}</td>
                                                        <td>${faPart.partNo}</td>
                                                        <td>${faPart.customer}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Customer</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
                            <c:forEach var="customer" items="${saleSellCustomers}" varStatus="loop">
                                <div class="panel panel-info">
                                    <div class="panel-heading" role="tab" id="sale_${loop.index}">
                                        <h4 class="panel-title">
                                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseC_${loop.index}" aria-expanded="true" aria-controls="collapseC_${loop.index}">
                                                <span class="badge">${fn:length(customer.value)}</span>
                                                    ${customer.key}
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapseC_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                                        <div class="panel-body">
                                            <table class="table table-hover">
                                                <thead>
                                                <th>FA No.</th>
                                                <th>Part No.</th>
                                                <th>Customer</th>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="faPart" items="${customer.value}" varStatus="loop">
                                                    <tr>
                                                        <td>${faPart.faNumber}</td>
                                                        <td>${faPart.partNo}</td>
                                                        <td>${faPart.customer}</td>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {

        var labelSell = [];
        var dataSell = [];

        $.each( ${saleSellsJSON}, function( key, value ) {
            $.each(value, function(key, value){
                labelSell.push(key);
                dataSell.push(value);
            });
        });

        var dataSellForG = {
            labels: labelSell,
            datasets: [
                {
                    label: "Sale Out Summary Of Sell",
                    backgroundColor: "#2ecc71",
                    borderColor: "rgba(255,99,132,1)",
                    borderWidth: 1,
                    hoverBackgroundColor: "rgba(255,99,132,0.4)",
                    hoverBorderColor: "rgba(255,99,132,1)",
                    data: dataSell
                }
            ]
        };

        var ctxSell = document.getElementById("sellSell");
        var myChartSell = new Chart(ctxSell, {
            type: 'bar',
            data: dataSellForG,
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });

        var labelCustomerSell = [];
        var dataCustomerSell = [];

        $.each( ${saleCustomersJSON}, function( key, value ) {
            $.each(value, function(key, value){
                labelCustomerSell.push(key);
                dataCustomerSell.push(value);
            });
        });

        var dataCustomerSellForG = {
            labels: labelCustomerSell,
            datasets: [
                {
                    label: "Customers Summary Of Sell",
                    backgroundColor: "#2ecc71",
                    borderColor: "rgba(255,99,132,1)",
                    borderWidth: 1,
                    hoverBackgroundColor: "rgba(255,99,132,0.4)",
                    hoverBorderColor: "rgba(255,99,132,1)",
                    data: dataCustomerSell
                }
            ]
        };

        var ctxCustomerSell = document.getElementById("sellCustomer");
        var myChartCustomerSell = new Chart(ctxCustomerSell, {
            type: 'bar',
            data: dataCustomerSellForG,
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero:true
                        }
                    }]
                }
            }
        });
    });
</script>