<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Jobs & Internships</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f7fa;
        }

        .jobs-container {
            width: 90%;
            max-width: 1100px;
            margin: 40px auto;
        }

        .page-header {
            margin-bottom: 30px;
        }

        .page-header h1 {
            margin-bottom: 8px;
        }

        .page-header p {
            color: #666;
        }

        .success-message {
            background: #d4edda;
            color: #155724;
            padding: 12px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .job-card {
            background: white;
            padding: 25px;
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .job-card h2 {
            margin-top: 0;
            margin-bottom: 8px;
        }

        .company {
            font-weight: bold;
            margin-bottom: 10px;
        }

        .job-info {
            color: #555;
            margin-bottom: 12px;
        }

        .description {
            line-height: 1.6;
        }

        .skills {
            margin-top: 15px;
            padding: 12px;
            background: #f5f5f5;
            border-radius: 6px;
        }

        .deadline {
            margin-top: 15px;
            font-weight: bold;
        }

        .apply-button {
            display: inline-block;
            margin-top: 20px;
            padding: 11px 20px;
            background: #333;
            color: white;
            text-decoration: none;
            border-radius: 6px;
        }

        .apply-button:hover {
            background: #555;
        }

        .empty-state {
            background: white;
            text-align: center;
            padding: 40px;
            border-radius: 10px;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="jobs-container">


    <!-- Page Header -->

    <div class="page-header">

        <h1>Jobs & Internships</h1>

        <p>
            Browse available placement opportunities.
        </p>

    </div>


    <!-- Application Success Message -->

    <c:if test="${param.applied == 'true'}">

        <div class="success-message">

            Your application has been submitted successfully.

        </div>

    </c:if>


    <!-- Jobs List -->

    <c:choose>

        <c:when test="${not empty jobs}">

            <c:forEach var="job" items="${jobs}">

                <div class="job-card">


                    <!-- Job Title -->

                    <h2>
                        ${job.title}
                    </h2>


                    <!-- Company -->

                    <div class="company">
                        ${job.company}
                    </div>


                    <!-- Location and Job Type -->

                    <div class="job-info">

                        Location:
                        ${job.location}

                        &nbsp; | &nbsp;

                        Type:
                        ${job.jobType}

                    </div>


                    <!-- Job Description -->

                    <div class="description">

                        <strong>Description:</strong>

                        <p>
                            ${job.description}
                        </p>

                    </div>


                    <!-- Required Skills -->

                    <div class="skills">

                        <strong>Required Skills:</strong>

                        ${job.skills}

                    </div>


                    <!-- Minimum CGPA -->

                    <div>

                        <strong>Minimum CGPA:</strong>

                        ${job.minimumCgpa}

                    </div>


                    <!-- Application Deadline -->

                    <div class="deadline">

                        Application Deadline:

                        ${job.deadline}

                    </div>


                    <!-- Apply Button -->

                    <a class="apply-button"
                       href="${pageContext.request.contextPath}/student/apply-job?jobId=${job.jobId}">

                        Apply Now

                    </a>


                </div>

            </c:forEach>

        </c:when>


        <!-- No Jobs -->

        <c:otherwise>

            <div class="empty-state">

                <h2>No Opportunities Available</h2>

                <p>
                    There are currently no jobs or internships available.
                </p>

            </div>

        </c:otherwise>

    </c:choose>


    <!-- Back to Dashboard -->

    <a class="back-link"
       href="${pageContext.request.contextPath}/student/dashboard">

        Back to Dashboard

    </a>


</div>

</body>

</html>
