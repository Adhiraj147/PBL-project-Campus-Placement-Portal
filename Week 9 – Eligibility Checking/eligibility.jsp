<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>Eligibility Check</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f7fa;
        }

        .eligibility-container {
            width: 90%;
            max-width: 800px;
            margin: 50px auto;
        }

        .eligibility-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        }

        h1 {
            margin-top: 0;
        }

        .requirement {
            padding: 15px;
            margin-bottom: 12px;
            border-radius: 6px;
            background: #f5f5f5;
        }

        .eligible {
            background: #d4edda;
            color: #155724;
        }

        .not-eligible {
            background: #f8d7da;
            color: #721c24;
        }

        .result {
            margin-top: 25px;
            padding: 18px;
            border-radius: 8px;
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

        .back-link {
            display: inline-block;
            margin-top: 20px;
            margin-left: 10px;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="eligibility-container">

    <div class="eligibility-card">

        <h1>Eligibility Check</h1>

        <p>
            Your eligibility has been checked against the
            requirements of this opportunity.
        </p>


        <!-- CGPA Check -->

        <div class="requirement">

            <strong>CGPA Requirement</strong>

            <p>
                Your CGPA:
                ${eligibility.studentCgpa}
            </p>

            <p>
                Required CGPA:
                ${eligibility.requiredCgpa}
            </p>

            <c:choose>

                <c:when test="${eligibility.cgpaEligible}">

                    <div class="eligible">
                        ✓ CGPA requirement satisfied
                    </div>

                </c:when>

                <c:otherwise>

                    <div class="not-eligible">
                        ✗ CGPA requirement not satisfied
                    </div>

                </c:otherwise>

            </c:choose>

        </div>


        <!-- Skills Check -->

        <div class="requirement">

            <strong>Skills Requirement</strong>

            <p>
                Required Skills:
                ${eligibility.requiredSkills}
            </p>

            <p>
                Your Skills:
                ${eligibility.studentSkills}
            </p>

            <c:choose>

                <c:when test="${eligibility.skillsEligible}">

                    <div class="eligible">
                        ✓ Required skills satisfied
                    </div>

                </c:when>

                <c:otherwise>

                    <div class="not-eligible">
                        ✗ Required skills not satisfied
                    </div>

                </c:otherwise>

            </c:choose>

        </div>


        <!-- Final Result -->

        <c:choose>

            <c:when test="${eligibility.eligible}">

                <div class="result eligible">

                    You are eligible for this opportunity.

                </div>

                <a class="apply-button"
                   href="${pageContext.request.contextPath}/student/apply-job?jobId=${eligibility.jobId}">

                    Continue to Application

                </a>

            </c:when>


            <c:otherwise>

                <div class="result not-eligible">

                    You are not eligible for this opportunity.

                </div>

            </c:otherwise>

        </c:choose>


        <a class="back-link"
           href="${pageContext.request.contextPath}/student/jobs">

            Back to Jobs

        </a>

    </div>

</div>

</body>

</html>
