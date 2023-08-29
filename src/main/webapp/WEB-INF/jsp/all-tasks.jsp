<%-- Created by IntelliJ IDEA. --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <!-- Local Bootstrap CSS -->
  <link href="/static/css/bootstrap.min.css" rel="stylesheet">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <style>
    .btn-container {
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    .btn-container .btn {
      margin: 0 5px; /* Adjust spacing between buttons as needed */
    }
  </style>
  <title>Task List</title>
</head>
<body>
<div class="d-flex justify-content-end mt-3">
  <a href="/logout" class="btn btn-primary">logout</a>
</div>
<div class="container">
  <h1 class="text-center my-4">My Task List</h1>

  <table class="table table-bordered">
    <thead class="thead-dark">
    <tr>
      <th>ID</th>
      <th>Name</th>
      <th>Start Date</th>
      <th>End Date</th>
      <th>Status</th>
      <th>Note</th>
      <th>Actions</th> <!-- New column header for actions -->
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}">
      <tr>
        <td>${task.id}</td>
        <td>${task.name}</td>
        <td>${task.startDate}</td>
        <td>${task.endDate}</td>
        <td>${task.status}</td>
        <td>${task.note}</td>
        <td>
          <div class="btn-container">
            <!-- Update button linking to the update page for the task -->
            <a href="${contextPath}/update_task/${task.id}" class="btn btn-sm btn-warning" title="Update">
              <i class="fas fa-edit"></i>
            </a>
            <!-- Delete button to trigger the delete action for the task -->
            <a href="${contextPath}/delete_task/${task.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this task?');" title="Delete">
              <i class="fas fa-trash"></i>
            </a>
          </div>
        </td>

      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="d-flex justify-content-end mt-3">
    <a href="/create_task" class="btn btn-primary">Add New Task</a>
  </div>
</div>
<!-- jQuery, Popper.js, and Local Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.1/umd/popper.min.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>
