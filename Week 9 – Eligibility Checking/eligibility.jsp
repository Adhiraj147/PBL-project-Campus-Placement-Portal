<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Eligibility Check</title>

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
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
        }

        h1 {
            margin-top: 0;
            color: #333;
        }

        h2 {
            color: #444;
            margin-bottom: 20px;
        }

        .check {
            padding: 15px;
            margin: 12px 0;
            border-radius: 6px;
        }

        .eligible {
            background: #e8f5e9;
            border: 1px solid #4caf50;
            color: #2e7d32;
        }

        .not-eligible {
            background: #ffebee;
            border: 1px solid #f44336;
            color: #c62828;
        }

        .result {
            margin-top: 25px;
            padding: 20px;
            text-align: center;
            border-radius: 8px;
        }

        .result h2 {
            margin-top: 0;
        }

        .apply-button {
            display: inline-block;
            margin-top: 15px;
            padding: 12px 22px;
            background: #1976d2;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .apply-button:hover {
            background: #1565c0;
        }

        .back-button {
            display: inline-block;
            margin-top: 15px;
            padding: 10px 18px;
            background: #757575;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }

        .back-button:hover {
            background: #616161;
        }

        .error {
            background: #ffebee;
            color: #c62828;
            padding: 15px;
            border-radius: 6px;
            border: 1px solid #f44336;
        }
    </style>
</head>

<body>

<div class="container">

    <div class="card">

        <h1>Job Eligibility Check</h1>

        <c:choose>

            <!-- Error Message -->
            <c:when test="${not empty errorMessage}">

                <div class="error">
                    ${errorMessage}
                </div>

                <a class="back-button"
                   href="${pageContext.request.contextPath}/student/jobs">
                    Back to Jobs
                </a>

            </c:when>

            <!-- Eligibility Result -->
            <c:otherwise>

                <h2>Eligibility Requirements</h2>

                <!-- CGPA Check -->
                <c:choose>

                    <c:when test="${eligibility.cgpaEligible}">
                        <div class="check eligible">
                            <strong>CGPA Requirement: Eligible</strong>
                            <br>
                            Your CGPA:
                            ${eligibility.studentCgpa}
                            <br>
                            Required CGPA:
                            ${eligibility.requiredCgpa}
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="check not-eligible">
                            <strong>CGPA Requirement: Not Eligible</strong>
                            <br>
                            Your CGPA:
                            ${eligibility.studentCgpa}
                            <br>
                            Required CGPA:
                            ${eligibility.requiredCgpa}
                        </div>
                    </c:otherwise>

                </c:choose>


                <!-- Skills Check -->
                <c:choose>

                    <c:when test="${eligibility.skillsEligible}">
                        <div class="check eligible">
                            <strong>Skills Requirement: Eligible</strong>
                            <br>
                            Required Skills:
                            ${eligibility.requiredSkills}
                        </div>
                    </c:when>

                    <c:otherwise>
                        <div class="check not-eligible">
                            <strong>Skills Requirement: Not Eligible</strong>
                            <br>
                            Required Skills:
                            ${eligibility.requiredSkills}
                        </div>
                    </c:otherwise>

                </c:choose>


                <!-- Final Eligibility -->
                <c:choose>

                    <c:when test="${eligibility.eligible}">

                        <div class="result eligible">

                            <h2>You are Eligible!</h2>

                            <p>
                                You meet the required CGPA and skill
                                requirements for this opportunity.
                            </p>

                            <a class="apply-button"
                               href="${pageContext.request.contextPath}/student/apply-job?jobId=${eligibility.jobId}">
                                Continue to Application
                            </a>

                        </div>

                    </c:when>

                    <c:otherwise>

                        <div class="result not-eligible">

                            <h2>You are Not Eligible</h2>

                            <p>
                                You do not currently meet all the
                                requirements for this opportunity.
                            </p>

                        </div>

                    </c:otherwise>

                </c:choose>


                <a class="back-button"
                   href="${pageContext.request.contextPath}/student/jobs">
                    Back to Jobs
                </a>

            </c:otherwise>

        </c:choose>

    </div>

</div>

</body>
</html>
