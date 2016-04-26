<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/" var="home" />
<%@page session="true"%>
<div class="container">
	<div class="row visible-lg">
		<div class="col-md-12 bs-docs-masthead" align="center">
			<span
				class="bs-docs-booticon bs-docs-booticon-lg bs-docs-booticon-outline">FOAMTEC</span>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<h2>FAMS</h2>
			<p>FA Management System</p>
			<p>ระบบบริหารจัดการส่วนงาน FA</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View
					details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>MTMS</h2>
			<p>Material Management System</p>
			<p>ระบบบริหารจัดการส่วนงาน Material</p>
			<p>
				<a class="btn btn-default" href="${home}mtms"
					role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>ESTS</h2>
			<p>Employee Self Test System</p>
			<p>ระบบสอบด้วยตัวเอง</p>
			<p>
				<a class="btn btn-default" href="${home}mtms/materialPdf/1.pdf" target="_blank" role="button">View details</a>
			</p>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Heading</h2>
			<p>ABC</p>
			<p>
				<a class="btn btn-default" href="#" role="button">View details</a>
			</p>
		</div>
	</div>
</div>