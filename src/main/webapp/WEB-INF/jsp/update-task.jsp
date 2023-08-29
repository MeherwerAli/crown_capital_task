<%--
  Created by IntelliJ IDEA.
  User: meherwerali
  Date: 29/08/2023
  To change this template use File | Settings | File Templates.
--%>
<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Update Task</title>

  <!-- Local Bootstrap CSS -->
  <link href="/static/css/bootstrap.min.css" rel="stylesheet">

  <!-- Toastr CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet"/>

  <!-- Toastr Custom Settings -->
  <script src="/static/js/toastr-settings.js"></script>
</head>
<body>

<div class="container mt-5">
  <h1 class="text-center mb-4">Update Task</h1>

  <form:form action="/update_task" method="post" modelAttribute="task" class="needs-validation" novalidate="novalidate">
    <form:hidden path="id"/>
    <div class="form-group">
      <label for="name">Name:</label>
      <form:input path="name" class="form-control" required="true" id="name"/>
    </div>
    <div class="form-group">
      <label for="startDate">Start Date:</label>
      <form:input type="date" path="startDate" class="form-control" required="true" id="startDate"/>
    </div>
    <div class="form-group">
      <label for="endDate">End Date:</label>
      <form:input type="date" path="endDate" class="form-control" required="true" id="endDate"/>
    </div>
    <div class="form-group">
      <label for="status">Status:</label>
      <form:select path="status" class="form-control" id="status">
        <form:option value="CREATED" label="Created"/>
        <form:option value="IN_PROGRESS" label="In Progress"/>
        <form:option value="FINISHED" label="Finished"/>
      </form:select>
    </div>
    <div class="form-group">
      <label for="note">Note:</label>
      <form:textarea path="note" rows="4" class="form-control" id="note"/>
    </div>
    <div class="form-group text-center">
      <input type="submit" value="Update Task" class="btn btn-primary"/>
    </div>
  </form:form>

  <div class="text-center mt-4">
    <a href="/list_task" class="btn btn-secondary">Back to Task List</a>
  </div>
</div>

<!-- jQuery, Popper.js, Toastr JS, and Local Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

<script>
  $(document).ready(function() {
    var errorMessage = "${errorMessage}";
    if(errorMessage) {
      toastr.error(errorMessage);
    }
  });
</script>
</body>
</html>
