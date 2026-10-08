<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Application History</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f7fa;
        }

        .history-container {
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

        .application-card {
            background: white;
            padding: 25px;
            margin-bottom: 18px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .application-card h2 {
            margin-top: 0;
            margin-bottom: 8px;
        }

        .company {
            font-weight: bold;
            margin-bottom: 12px;
        }

        .application-info {
            color: #555;
            line-height: 1.8;
        }

        .status {
            display: inline-block;
            margin-top: 12px;
            padding: 7px 12px;
            border-radius: 6px;
            font-weight: bold;
            background: #eeeeee;
        }

        .pending {
            background: #fff3cd;
            color: #856404;
        }

        .shortlisted {
            background: #d4edda;
            color: #155724;
        }

        .rejected {
            background: #f8d7da;
            color: #721c24;
        }

        .selected {
            background: #d1ecf1;
            color: #0c5460;
        }

        .empty-state {
            background: white;
            text-align: center;
            padding: 45px;
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

<div class="history-container">


    <!-- Page Header -->

    <div class="page-header">

        <h1>Application History</h1>

        <p>
            View your submitted applications and their current status.
        </p>

    </div>


    <!-- Application List -->

    <c:choose>

        <c:when test="${not empty applications}">

            <c:forEach var="application"
                       items="${applications}">

                <div class="application-card">


                    <h2>
                        ${application.jobTitle}
                    </h2>


                    <div class="company">
                        ${application.company}
                    </div>


                    <div class="application-info">

                        <strong>Job Type:</strong>
                        ${application.jobType}

                        <br>

                        <strong>Application Date:</strong>
                        ${application.applicationDate}

                        <br>

                        <strong>Application ID:</strong>
                        ${application.applicationId}

                    </div>


                    <!-- Application Status -->

                    <c:choose>

                        <c:when test="${application.status == 'PENDING'}">

                            <span class="status pending">
                                Pending
                            </span>

                        </c:when>


                        <c:when test="${application.status == 'SHORTLISTED'}">

                            <span class="status shortlisted">
                                Shortlisted
                            </span>

                        </c:when>


                        <c:when test="${application.status == 'REJECTED'}">

                            <span class="status rejected">
                                Rejected
                            </span>

                        </c:when>


                        <c:when test="${application.status == 'SELECTED'}">

                            <span class="status selected">
                                Selected
                            </span>

                        </c:when>


                        <c:otherwise>

                            <span class="status">
                                ${application.status}
                            </span>

                        </c:otherwise>

                    </c:choose>


                </div>

            </c:forEach>

        </c:when>


        <!-- No Applications -->

        <c:otherwise>

            <div class="empty-state">

                <h2>No Applications Yet</h2>

                <p>
                    You have not applied for any jobs or internships yet.
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
