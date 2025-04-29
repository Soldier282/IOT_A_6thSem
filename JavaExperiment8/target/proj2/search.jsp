<%@ page import="com.example.Employee" %>
<html>
    <head><title>Search Result</title></head>
<body>
<% Employee emp = (Employee) request.getAttribute("employee");
        if (emp != null) {
%>
            <h2>Employee Details</h2>
            <p> ID: <%= emp.getId() %></p>
            <p> ID: <%= emp.getName() %>
            </p>
            <p> ID: <%= emp.getEmail() %>
            </p>
            <p> ID: <%= emp.getDepartment() %>
            </p>

            <% } else{ %>
                <p>Employee not found.</p>
            <% } %>

            <a href="index.jsp">Back to Home</a>

</body>
</html>
