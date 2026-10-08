<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>My Certifications</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/student-certifications.css">

</head>

<body>

<div class="certification-container">

    <div class="page-header">

        <div>
            <h1>My Certifications</h1>

            <p>
                Add and manage your professional certifications.
            </p>
        </div>

        <a href="${pageContext.request.contextPath}/student/profile"
           class="back-button">
            Back to Profile
        </a>

    </div>


    <div class="certification-form">

        <h2>Add Certification</h2>

        <form method="post"
              action="${pageContext.request.contextPath}/student/certifications">

            <div class="form-group">

                <label for="name">
                    Certification Name
                </label>

                <input type="text"
                       id="name"
                       name="name"
                       placeholder="Example: Java Programming"
                       required>

            </div>


            <div class="form-group">

                <label for="organization">
                    Issuing Organization
                </label>

                <input type="text"
                       id="organization"
                       name="issuingOrganization"
                       placeholder="Example: Oracle"
                       required>

            </div>


            <div class="form-group">

                <label for="issueDate">
                    Issue Date
                </label>

                <input type="date"
                       id="issueDate"
                       name="issueDate">

            </div>


            <div class="form-group">

                <label for="credentialId">
                    Credential ID
                </label>

                <input type="text"
                       id="credentialId"
                       name="credentialId">

            </div>


            <div class="form-group">

                <label for="credentialUrl">
                    Credential URL
                </label>

                <input type="url"
                       id="credentialUrl"
                       name="credentialUrl"
                       placeholder="https://example.com">

            </div>


            <button type="submit">
                Add Certification
            </button>

        </form>

    </div>


    <div class="certification-list">

        <h2>My Certifications</h2>

        <c:choose>

            <c:when test="${not empty certifications}">

                <c:forEach var="certification"
                           items="${certifications}">

                    <div class="certification-card">

                        <div class="certification-content">

                            <h3>
                                ${certification.name}
                            </h3>

                            <p class="organization">
                                ${certification.issuingOrganization}
                            </p>

                            <p>
                                Issue Date:
                                ${certification.issueDate}
                            </p>

                            <c:if test="${not empty certification.credentialId}">
                                <p>
                                    Credential ID:
                                    ${certification.credentialId}
                                </p>
                            </c:if>

                            <c:if test="${not empty certification.credentialUrl}">

                                <a href="${certification.credentialUrl}"
                                   target="_blank"
                                   rel="noopener noreferrer">
                                    View Credential
                                </a>

                            </c:if>

                        </div>

                    </div>

                </c:forEach>

            </c:when>

            <c:otherwise>

                <div class="empty-state">

                    <h3>No Certifications Added</h3>

                    <p>
                        Add your certifications to strengthen your student profile.
                    </p>

                </div>

            </c:otherwise>

        </c:choose>

    </div>

</div>

</body>
</html>
