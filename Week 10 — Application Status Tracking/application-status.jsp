<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Application Status</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            max-width: 800px;
            margin: 40px auto;
        }

        .card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }

        h1 {
            margin-top: 0;
            color: #333;
        }

        .job-title {
            font-size: 22px;
            font-weight: bold;
            color: #1976d2;
        }

        .info {
            margin: 12px 0;
            color: #555;
        }

        .status {
            margin-top: 25px;
            padding: 18px;
            border-radius: 8px;
            text-align: center;
        }

        .pending {
            background: #fff8e1;
            color: #f57f17;
            border: 1px solid #ffca28;
        }

        .shortlisted {
            background: #e3f2fd;
            color: #1565c0;
            border: 1px solid #42a5f5;
        }

        .selected {
            background: #e8f5e9;
            color: #2e7d32;
            border: 1px solid #4caf50;
        }

        .rejected {
            background: #ffebee;
            color: #c62828;
            border: 1px solid #ef5350;
        }

        .unknown {
            background: #f5f5f5;
            color: #555;
            border: 1px solid #bbb;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 18px;
            background: #757575;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .error {
            background: #ffebee;
            color: #c62828;
            padding: 15px;
            border-radius: 6px;
            border: 1px solid #ef5350;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card">

        <h1>Application Status</h1>

        <!-- Error -->
        <c:if test="${not empty errorMessage}">

            <div class="error">
                ${errorMessage}
            </div>

        </c:if>


        <!-- Application Details -->
        <c:if test="${not empty applicationStatus}">

            <div class="job-title">
                ${applicationStatus.jobTitle}
            </div>

            <div class="info">
                <strong>Company:</strong>
                ${applicationStatus.company}
            </div>

            <div class="info">
                <strong>Job Type:</strong>
                ${applicationStatus.jobType}
            </div>

            <div class="info">
                <strong>Application Date:</strong>
                ${applicationStatus.applicationDate}
            </div>


            <!-- Status -->
            <c:choose>

                <c:when test="${applicationStatus.status == 'PENDING'}">

                    <div class="status pending">
                        <h2>Application Pending</h2>
                        <p>
                            Your application has been submitted
                            and is waiting for review.
                        </p>
                    </div>

                </c:when>


                <c:when test="${applicationStatus.status == 'SHORTLISTED'}">

                    <div class="status shortlisted">
                        <h2>Shortlisted</h2>
                        <p>
                            Congratulations! Your application
                            has been shortlisted.
                        </p>
                    </div>

                </c:when>


                <c:when test="${applicationStatus.status == 'SELECTED'}">

                    <div class="status selected">
                        <h2>Selected</h2>
                        <p>
                            Congratulations! You have been
                            selected for this opportunity.
                        </p>
                    </div>

                </c:when>


                <c:when test="${applicationStatus.status == 'REJECTED'}">

                    <div class="status rejected">
                        <h2>Application Rejected</h2>
                        <p>
                            Your application was not selected
                            for this opportunity.
                        </p>
                    </div>

                </c:when>


                <c:otherwise>

                    <div class="status unknown">
                        <h2>
                            Status:
                            ${applicationStatus.status}
                        </h2>

                        <p>
                            Your application status is currently
                            being processed.
                        </p>
                    </div>

                </c:otherwise>

            </c:choose>

        </c:if>


        <a class="back-button"
           href="${pageContext.request.contextPath}/student/application-history">
            Back to Application History
        </a>

    </div>

</div>

</body>

</html>
