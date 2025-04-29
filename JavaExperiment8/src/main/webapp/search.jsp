<%@ page import="com.example.Employee" %>
<html>
<head><title>Search Employee</title></head>
<body>
    <h2>Search Employee by ID</h2>
    <form action="EmployeeServlet" method="post">
        Enter Employee ID: <input type="text" name="searchId" />
        <input type="submit" value="Search" />
        <input type="hidden" name="action" value="search" />
    </form>

    <%
        Employee emp = (Employee) request.getAttribute("searchedEmployee");
        if (emp != null) {
    %>
        <h3>Employee Found:</h3>
        <table border="1">
            <tr><th>ID</th><th>Name</th><th>Email</th><th>Department</th></tr>
            <tr>
                <td><%= emp.getId() %></td>
                <td><%= emp.getName() %></td>
                <td><%= emp.getEmail() %></td>
                <td><%= emp.getDepartment() %></td>
            </tr>
        </table>
    <%
        } else if (request.getAttribute("notFound") != null) {
    %>
        <p style="color:red;"><%= request.getAttribute("notFound") %></p>
    <%
        }
    %>
</body>
</html>
