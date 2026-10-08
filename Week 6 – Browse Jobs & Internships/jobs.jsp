<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Jobs & Internships</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            max-width: 1100px;
            margin: 40px auto;
        }

        h1 {
            color: #333;
            margin-bottom: 25px;
        }

        .success-message {
            background: #e8f5e9;
            color: #2e7d32;
            border: 1px solid #4caf50;
            padding: 12px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .job-card {
            background: white;
            padding: 25px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        .job-card h2 {
            margin-top: 0;
            color: #333;
        }

        .company {
            font-weight: bold;
            color: #555;
        }

        .job-info {
            margin: 8px 0;
            color: #666;
        }

        .description {
            margin-top: 15px;
            line-height: 1.6;
            color: #444;
        }

        .skills {
            margin-top: 10px;
            color: #555;
        }

        .deadline {
            margin-top: 10px;
            color: #c62828;
        }

        .apply-button {
            display: inline-block;
            margin-top: 18px;
            padding: 11px 20px;
            background: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .apply-button:hover {
            background: #1565c0;
        }

        .empty-message {
            background: white;
            padding: 25px;
            text-align: center;
            border-radius: 8px;
            color: #666;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Available Jobs & Internships</h1>

    <!-- Success Message -->
    <c:if test="${param.applied == 'true'}">
        <div class="success-message">
            Application submitted successfully.
        </div>
    </c:if>


    <!-- Job List -->
    <c:choose>

        <c:when test="${not empty jobs}">

            <c:forEach var="job" items="${jobs}">

                <div class="job-card">

                    <h2>${job.title}</h2>

                    <div class="company">
                        Company: ${job.company}
                    </div>

                    <div class="job-info">
                        Location: ${job.location}
                    </div>

                    <div class="job-info">
                        Type: ${job.jobType}
                    </div>

                    <div class="description">
                        <strong>Description:</strong>
                        <br>
                        ${job.description}
                    </div>

                    <div class="skills">
                        <strong>Required Skills:</strong>
                        ${job.skills}
                    </div>

                    <div class="job-info">
                        <strong>Minimum CGPA:</strong>
                        ${job.minimumCgpa}
                    </div>

                    <div class="deadline">
                        <strong>Application Deadline:</strong>
                        ${job.deadline}
                    </div>

                    <!-- Week 9: Eligibility Check -->
                    <a class="apply-button"
                       href="${pageContext.request.contextPath}/student/eligibility?jobId=${job.jobId}">
                        Check Eligibility
                    </a>

                </div>

            </c:forEach>

        </c:when>

        <c:otherwise>

            <div class="empty-message">
                No jobs or internships are currently available.
            </div>

        </c:otherwise>

    </c:choose>

</div>

</body>
</html>
