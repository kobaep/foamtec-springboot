<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#saleRequest">Sale Request FA</a></li>
        <li><a data-toggle="tab" href="#engineerApprove">Engineer Approve</a></li>
        <li><a data-toggle="tab" href="#qaFirst">QA First Inspection</a></li>
    </ul>
    <div class="tab-content">
        <div id="saleRequest" class="tab-pane fade in active">
            <div class="panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title" align="center">FA Request</h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="height: 540px;">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>No.</th>
                                    <th>FA No.</th>
                                    <th>Request Date</th>
                                    <th>Need Date</th>
                                    <th>Customer</th>
                                    <th>Part No.</th>
                                    <th>Sale Out</th>
                                    <th>Request By</th>
                                </tr>
                            </thead>
                            <tbody>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div id="engineerApprove" class="tab-pane fade">
            Engineer Approve
        </div>
        <div id="qaFirst" class="tab-pane fade">
            QA First
        </div>
    </div>
</div>