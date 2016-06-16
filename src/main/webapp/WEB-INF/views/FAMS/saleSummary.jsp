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
            <h2 align="center">Sale Summary</h2>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th></th>
                    <th>January</th>
                    <th>February</th>
                    <th>March</th>
                    <th>April</th>
                    <th>May</th>
                    <th>June</th>
                    <th>July</th>
                    <th>August</th>
                    <th>September</th>
                    <th>October</th>
                    <th>November</th>
                    <th>December</th>
                </tr>
                </thead>
                <tbody>
                <tr class="alert-danger">
                    <th>Free</th>
                    <td><a href="${home}fams/saleSummaryFree/0">${freeJanuary}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/1">${freeFebruary}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/2">${freeMarch}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/3">${freeApril}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/4">${freeMay}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/5">${freeJune}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/6">${freeJuly}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/7">${freeAugust}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/8">${freeSeptember}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/9">${freeOctober}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/10">${freeNovember}</a></td>
                    <td><a href="${home}fams/saleSummaryFree/11">${freeDecember}</a></td>
                </tr>
                <tr class="alert-success">
                    <th>Sell</th>
                    <td><a href="${home}fams/saleSummarySell/0">${sellJanuary}</a></td>
                    <td><a href="${home}fams/saleSummarySell/1">${sellFebruary}</a></td>
                    <td><a href="${home}fams/saleSummarySell/2">${sellMarch}</a></td>
                    <td><a href="${home}fams/saleSummarySell/3">${sellApril}</a></td>
                    <td><a href="${home}fams/saleSummarySell/4">${sellMay}</a></td>
                    <td><a href="${home}fams/saleSummarySell/5">${sellJune}</a></td>
                    <td><a href="${home}fams/saleSummarySell/6">${sellJuly}</a></td>
                    <td><a href="${home}fams/saleSummarySell/7">${sellAugust}</a></td>
                    <td><a href="${home}fams/saleSummarySell/8">${sellSeptember}</a></td>
                    <td><a href="${home}fams/saleSummarySell/9">${sellOctober}</a></td>
                    <td><a href="${home}fams/saleSummarySell/10">${sellNovember}</a></td>
                    <td><a href="${home}fams/saleSummarySell/11">${sellDecember}</a></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6 alert alert-warning" role="alert">
            <canvas id="free" width="70" height="30"></canvas>
        </div>
        <div class="col-sm-6 alert alert-info" role="alert">
            <canvas id="sell" width="70" height="30"></canvas>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {

        var dataFree = {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            datasets: [
                {
                    label: "Free",
                    backgroundColor: "#c0392b",
                    borderColor: "rgba(255,99,132,1)",
                    borderWidth: 1,
                    hoverBackgroundColor: "rgba(255,99,132,0.4)",
                    hoverBorderColor: "rgba(255,99,132,1)",
                    data: [${freeJanuary}, ${freeFebruary}, ${freeMarch}, ${freeApril}, ${freeMay}, ${freeJune}, ${freeJuly}, ${freeAugust}, ${freeSeptember}, ${freeOctober}, ${freeNovember}, ${freeDecember}]
                }
            ]
        };

        var ctxFree = document.getElementById("free");
        var myChartFree = new Chart(ctxFree, {
            type: 'bar',
            data: dataFree,
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

        var dataSell = {
            labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
            datasets: [
                {
                    label: "First Shot Approve",
                    backgroundColor: "#2ecc71",
                    borderColor: "rgba(255,99,132,1)",
                    borderWidth: 1,
                    hoverBackgroundColor: "rgba(255,99,132,0.4)",
                    hoverBorderColor: "rgba(255,99,132,1)",
                    data: [${sellJanuary}, ${sellFebruary}, ${sellMarch}, ${sellApril}, ${sellMay}, ${sellJune}, ${sellJuly}, ${sellAugust}, ${sellSeptember}, ${sellOctober}, ${sellNovember}, ${sellDecember}]
                }
            ]
        };

        var ctxSell = document.getElementById("sell");
        var myCharSell = new Chart(ctxSell, {
            type: 'bar',
            data: dataSell,
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
