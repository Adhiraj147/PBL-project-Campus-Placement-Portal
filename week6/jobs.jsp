<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.placement.model.Job" %>

<%
    List<Job> jobs = (List<Job>) request.getAttribute("jobs");
%>

<!DOCTYPE html>
<html>

<head>

    <title>Jobs & Internships</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f7fb;
            margin: 0;
        }

        .header {
            background: #1f3c88;
            color: white;
            padding: 20px;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 30px auto;
        }

        .job-card {
            background: white;
            padding: 22px;
            margin-bottom: 20px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
        }

        .job-card h2 {
            margin-top: 0;
        }

        .btn {
            display: inline-block;
            padding: 10px 18px;
            background: #1f3c88;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

    </style>

</head>

<body>

<div class="header">
    <h1>Jobs & Internships</h1>
</div>

<div class="container">

    <%
        if (jobs != null && !jobs.isEmpty()) {

            for (Job job : jobs) {
    %>

        <div class="job-card">

            <h2>
                <%= job.getTitle() %>
            </h2>

            <p>
                <strong>Type:</strong>
                <%= job.getJobType() %>
            </p>

            <p>
                <strong>Location:</strong>
                <%= job.getLocation() %>
            </p>

            <p>
                <strong>Salary:</strong>
                <%= job.getSalary() %>
            </p>

            <p>
                <strong>Deadline:</strong>
                <%= job.getDeadline() %>
            </p>

            <a class="btn"
               href="<%= request.getContextPath() %>/student/job-details?jobId=<%= job.getJobId() %>">
                View Details
            </a>

        </div>

    <%
            }

        } else {
    %>

        <p>No active jobs or internships are currently available.</p>

    <%
        }
    %>

</div>

</body>

</html>