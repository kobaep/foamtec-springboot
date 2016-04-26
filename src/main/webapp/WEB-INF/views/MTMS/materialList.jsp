<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<spring:url value="/" var="home" />
<div class="container">
    <div class="row">
        <div class="col-sm-5">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Option ${materialType.typeName}</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">
                        <a href="${home}mtms/materialPrivate/${materialType.id}?form" class="list-group-item list-group-item-info">
                            Create Material
                        </a>
                        <a href="${home}mtms/materialPrivate/${materialType.id}?update" class="list-group-item list-group-item-info">
                            Update Material
                        </a>
                        <a href="${home}mtms/materialSapPrivate/${materialType.id}?form" class="list-group-item list-group-item-info">
                            Create SAP Code
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-7">
            <div class="panel panel-danger">
                <div class="panel-heading">
                    <h3 class="panel-title">${materialType.typeName} Name</h3>
                </div>
                <div class="panel-body">
                    <div class="list-group">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>