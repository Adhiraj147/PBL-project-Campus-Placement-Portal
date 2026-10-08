<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Student Portal</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/student-portal.css">

</head>

<body>

    <!-- Header -->
    <jsp:include page="student-header.jsp" />


    <!-- Main Content -->

    <main class="portal-container">

        <section class="welcome-section">

            <h1>
                Welcome to Student Portal
            </h1>

            <p>
                Manage your profile, explore placement opportunities,
                submit applications and track your application status
                from one place.
            </p>

        </section>


        <!-- Portal Features -->

        <section class="portal-grid">


            <!-- Profile -->

            <div class="portal-card">

                <h2>My Profile</h2>

                <p>
                    View and manage your personal information,
                    education, skills, projects and certifications.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/student/profile">
                    View Profile
                </a>

            </div>


            <!-- Certifications -->

            <div class="portal-card">

                <h2>Certifications</h2>

                <p>
                    Manage your professional certifications
                    and achievements.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/jsp/student/certifications.jsp">
                    Manage Certifications
                </a>

            </div>


            <!-- Resume -->

            <div class="portal-card">

                <h2>Resume</h2>

                <p>
                    Upload, view and manage your latest resume
                    for placement applications.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/jsp/student/resume.jsp">
                    Manage Resume
                </a>

            </div>


            <!-- Jobs -->

            <div class="portal-card">

                <h2>Jobs & Internships</h2>

                <p>
                    Browse available jobs and internships
                    and check your eligibility.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/student/jobs">
                    Browse Opportunities
                </a>

            </div>


            <!-- Applications -->

            <div class="portal-card">

                <h2>My Applications</h2>

                <p>
                    View all your submitted applications
                    and their current status.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/student/application-history">
                    View Applications
                </a>

            </div>


            <!-- Dashboard -->

            <div class="portal-card">

                <h2>Dashboard</h2>

                <p>
                    Return to your student dashboard
                    and view your placement information.
                </p>

                <a class="portal-button"
                   href="${pageContext.request.contextPath}/student/dashboard">
                    Open Dashboard
                </a>

            </div>


        </section>

    </main>


    <!-- Footer -->

    <jsp:include page="student-footer.jsp" />

</body>

</html>
