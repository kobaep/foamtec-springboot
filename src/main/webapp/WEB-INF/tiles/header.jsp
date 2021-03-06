<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/" var="home" />
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="${home}" class="navbar-brand">FOAMTEC</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li id="linkFAMS" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						FAMS <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${home}fams">Status</a></li>
						<li><a href="${home}fams/search?search">Search</a></li>
						<li><a href="${home}fams/search?faSummary">FA Summary</a></li>
						<li><a href="${home}fams/search?saleSummary">Sale Summary</a></li>
						<c:if test="${roleName eq 'admin' or roleName eq 'saleCo' or roleName eq 'saleOut'}">
                            <li><a href="${home}fams/requestPrivate?form">Request</a></li>
                            <li><a href="${home}fams/requestPrivate?updateList">List</a></li>
                            <li><a href="${home}fams/requestPrivate?updateListSaleOut">Follow Customer</a></li>
                        </c:if>
                        <c:if test="${roleName eq 'admin' or roleName eq 'engineer'}">
                            <li><a href="${home}fams/engineerPrivate?engineerView">Engineer View</a></li>
                        </c:if>
                        <c:if test="${roleName eq 'admin' or roleName eq 'qa'}">
                            <li><a href="${home}fams/qaPrivate?qaView">Qa View</a></li>
                        </c:if>
						<c:if test="${roleName eq 'admin' or roleName eq 'qaEngineer'or roleName eq 'qaManager'}">
							<li><a href="${home}fams/qaPrivate?reviewDocument">Review Document</a></li>
						</c:if>
					</ul>
				</li>
				<li id="linkMTMS"><a href="${home}mtms">MTMS <span class="sr-only">(current)</span></a></li>
				<li id="linkCustomer" class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						CUSTOMER <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${home}customer/">Detail</a></li>
						<li><a href="${home}customer/createPrivate?form">Create</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
						<span id="username">
							<c:if test="${not empty name}" >
								${name}
							</c:if>
						</span>
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu" role="menu">
						<c:if test="${not empty login}">
							<li id="login"><a href="${home}login">Login</a></li>
						</c:if>
						<c:if test="${roleName eq 'admin' or roleName eq 'user'}">
							<li id="createUser"><a href="${home}appuser?form">Create User</a></li>
							<li id="listUser"><a href="${home}appuser?list">List User</a></li>
						</c:if>
						<c:if test="${not empty logout}">
							<li id="logout"><a href="${home}resources/j_spring_security_logout">Logout</a></li>
						</c:if>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>