<%@page import="com.Billing" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/billing.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Billing Management</h1>
<form id="formBilling" name="formBilling">
 User id:
 <input id="uid" name="uid" type="text"
 class="form-control form-control-sm">
 <br> Units:
 <input id="units" name="units" type="text"
 class="form-control form-control-sm">
 <br>  month:
 <input id="month" name="month" type="text"
 class="form-control form-control-sm">
 <br> year:
 <input id="year" name="year" type="text"
 class="form-control form-control-sm">
 <br> amount:
 <input id="amount" name="amount" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
 <input type="hidden" id="hidBillingIDSave" name="hidBillingIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divIBillingGrid">
 <%
 Billing billingObj = new Billing();
 out.print(billingObj.readbill());
 %>
</div>
</div> </div> </div>
</body>
</html>