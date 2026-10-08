<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Apply for Opportunity</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f7fa;
        }

        .application-container {
            width: 90%;
            max-width: 800px;
            margin: 50px auto;
        }

        .application-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        }

        h1 {
            margin-top: 0;
        }

        .company {
            font-weight: bold;
            margin-bottom: 20px;
        }

        .job-details {
            padding: 20px;
            background: #f7f7f7;
            border-radius: 8px;
            margin-bottom: 25px;
        }

        .job-details p {
            line-height: 1.6;
        }

        .warning {
            padding: 12px;
            background: #fff3cd;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .error {
            color: #b00020;
            margin-bottom: 20px;
        }

        .actions {
            margin-top: 25px;
        }

        button {
            border: none;
            padding: 12px 22px;
            border-radius: 6px;
            cursor: pointer;
            background: #333;
            color: white;
        }

        .back-link {
            margin-left: 15px;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="application-container">

    <div class="application-card">

        <h1>Apply for Opportunity</h1>

        <div class="company">
            ${job.company}
        </div>


        <div class="job-details">

            <h2>
                ${job.title}
            </h2>

            <p>
                <strong>Location:</strong>
                ${job.location}
            </p>

            <p>
                <strong>Type:</strong>
                ${job.jobType}
            </p>

            <p>
                <strong>Required Skills:</strong>
                ${job.skills}
            </p>

            <p>
                <strong>Minimum CGPA:</strong>
                ${job.minimumCgpa}
            </p>

            <p>
                <strong>Application Deadline:</strong>
                ${job.deadline}
            </p>

            <p>
                <strong>Description:</strong>
            </p>

            <p>
                ${job.description}
            </p>

        </div>


        <div class="warning">

            Please review the opportunity details before submitting
            your application.

        </div>


        <% if (request.getAttribute("error") != null) { %>

            <div class="error">
                <%= request.getAttribute("error") %>
            </div>

        <% } %>


        <div class="actions">

            <form method="post"
                  action="${pageContext.request.contextPath}/student/apply-job">

                <input type="hidden"
                       name="jobId"
                       value="${job.jobId}">

                <button type="submit">
                    Submit Application
                </button>

                <a class="back-link"
                   href="${pageContext.request.contextPath}/student/jobs">
                    Back to Jobs
                </a>

            </form>

        </div>

    </div>

</div>

</body>

</html>
