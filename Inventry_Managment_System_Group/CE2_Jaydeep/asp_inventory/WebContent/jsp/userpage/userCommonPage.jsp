<%@page import="com.inventory.model.UserRegistration"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>InventoryManagmentSystem</title>
<link href="<%=request.getContextPath() %>/assets/css/bootstrap.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/assets/css/font-awesome.css" rel="stylesheet" />
    <link href="<%=request.getContextPath() %>/assets/css/style.css" rel="stylesheet" />
     <script src="<%=request.getContextPath() %>/assets/js/jquery-1.11.1.js"></script>
    <script src="<%=request.getContextPath() %>/assets/js/bootstrap.js"></script>
</head>

<body>
<!-- HEADER START -->
   <%@ include file="/jsp/common/commonHeader.jsp" %>
<!-- HEADER END-->
	 <section class="menu-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar-collapse collapse ">
                        <ul id="menu-top" class="nav navbar-nav navbar-right">
                            <li><a  href="dashboard.htm">Welcome, ${userdtls.name }</a></li>
                            <li><a href="logout.htm">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>
<!-- MENU SECTION END-->
    <div class="content-wrapper">
    <%if(request.getAttribute("success") != null){ %>\
    <div class="container">
    <div class="col-md-3"></div>
                <div class="col-md-6">
                    <div class="alert alert-success" id="success">
                        <%=request.getAttribute("success").toString() %>
                    </div>
                </div>
                <div class="col-md-3"></div>
    </div>
    <script>
    $(document).ready(function(){
    	$("#success").delay(5000).fadeOut(400);
    });
    </script>
    <%} %>
    
    <%if(request.getAttribute("error") != null){ %>\
    <div class="container">
    	<div class="col-md-3"></div>
    	<div class="col-md-6">
    		<div class="alert alert-danger" id="error">
            	<%= request.getAttribute("error").toString() %>            
            </div>
    	</div>
    	<div class="col-md-3"></div>
    </div>
    <script>
    $(document).ready(function(){
    	$("#error").delay(5000).fadeOut(400);
    });
    </script>
    <%} %>
    <div class="row">
    	<div class="col-md-3">
    	<jsp:include page="/jsp/userpage/userLeftMenu.jsp"></jsp:include>
    	</div>
    	<div class="col-md-9">
        <jsp:include page="/jsp/userpage/${filename }.jsp" />
        </div>
    </div>
    </div>
    <!-- CONTENT-WRAPPER SECTION END-->
<!--     FOOTER START -->
	<%@ include file="/jsp/common/commonFooter.jsp" %>
<!-- FOOTER ENDS -->
</body>
</html>
