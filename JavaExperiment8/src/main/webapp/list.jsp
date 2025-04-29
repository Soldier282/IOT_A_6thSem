<%@ page import="java.util.*, com.example.Employee" %>
<html>
<head><title>All Employees</title></head>
<body>
    <h2>Employee List</h2>
    <table border="1">
        <tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th></tr>
        <%
            List<Employee> empList = (List<Employee>) request.getAttribute("employeeList");
            if (empList != null) {
                for (Employee emp : empList) {
        %>
        <tr>
            <td><%= emp.getId() %></td>
            <td><%= emp.getName() %></td>
            <td><%= emp.getEmail() %></td>
            <td><%= emp.getDepartment() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
