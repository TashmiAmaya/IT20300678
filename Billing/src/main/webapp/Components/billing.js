$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//SAVE============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-------------------
var status = validateBillingForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidBillingIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "BillingAPI",
 type : type,
 data : $("#formBilling").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onBillingSaveComplete(response.responseText, status);
 }
 });
});

function onBillingSaveComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully saved.");
 $("#alertSuccess").show();
 $("#divIBillingGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while saving.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while saving..");
 $("#alertError").show();
 } 
 $("#hidBillingIDSave").val("");
 $("#formBilling")[0].reset();
}

// UPDATE==========================================

$(document).on("click", ".btnUpdate", function(event)
{
$("#hidBillingIDSave").val($(this).data("id"));
 $("#uid").val($(this).closest("tr").find('td:eq(0)').text());
 $("#units").val($(this).closest("tr").find('td:eq(1)').text());
 $("#ammount").val($(this).closest("tr").find('td:eq(2)').text());
});


//REMOVE==============================================

$(document).on("click", ".btnRemove", function(event)
{
 $.ajax(
 {
 url : "BillingAPI",
 type : "DELETE",
 data : "uid=" + $(this).data("uid"),
 dataType : "text",
 complete : function(response, status)
 {
 onBillingDeleteComplete(response.responseText, status);
 }
 });
});


function onBillingDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divIBillingGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

// CLIENT-MODEL================================================================
function validateBillingForm()
{
// USERID
if ($("#uid").val().trim() == "")
 {
 return "Insert uid.";
 }
// UNITS
if ($("#units").val().trim() == "")
 {
 return "Insert units.";
 } 
 
// MONTH-------------------------------
if ($("#month").val().trim() == "")
 {
 return "Insert month.";
 }

// YEAR------------------------
if ($("#year").val().trim() == "")
 {
 return "Insert year.";
 }
 
// AMOUNT-------------------------------
if ($("#amount").val().trim() == "")
 {
 return "Insert amount.";
 }
return true;
}

