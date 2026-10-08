<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<jsp:include page="../common/header.jsp" />

<link rel="stylesheet"
      href="${pageContext.request.contextPath}/css/student-dashboard.css">

<div class="student-dashboard">

    <!-- Sidebar -->
    <div class="student-sidebar">

        <div class="sidebar-title">
            Student Portal
        </div>

        <a href="${pageContext.request.contextPath}/student/dashboard"
           class="sidebar-link active">
            Dashboard
        </a>

        <a href="${pageContext.request.contextPath}/student/profile"
           class="sidebar-link">
            My Profile
        </a>

        <a href="${pageContext.request.contextPath}/student/opportunities"
           class="sidebar-link">
            Jobs & Internships
        </a>

        <a href="${pageContext.request.contextPath}/student/applications"
           class="sidebar-link">
            My Applications
        </a>

        <a href="${pageContext.request.contextPath}/student/interviews"
           class="sidebar-link">
            Interviews
        </a>

    </div>


    <!-- Main Content -->
    <div class="student-content">

        <!-- Welcome Card -->
        <div class="welcome-card">

            <h2>
                Welcome to Student Dashboard
            </h2>

            <p>
                Manage your profile, explore placement opportunities
                and track your applications from one place.
            </p>

            <a href="${pageContext.request.contextPath}/student/profile"
               class="profile-button">
                Complete My Profile
            </a>

        </div>


        <!-- Error Message -->
        <% if (request.getAttribute("dashboardError") != null) { %>

            <div class="dashboard-error">
                <%= request.getAttribute("dashboardError") %>
            </div>

        <% } %>


        <!-- Dashboard Statistics -->
        <div class="statistics-row">

            <!-- Total Applications -->
            <div class="stat-card">

                <div class="stat-title">
                    Total Applications
                </div>

                <div class="stat-value">
                    ${totalApplications != null ? totalApplications : 0}
                </div>

                <div class="stat-description">
                    Applications submitted
                </div>

            </div>


            <!-- Shortlisted -->
            <div class="stat-card">

                <div class="stat-title">
                    Shortlisted
                </div>

                <div class="stat-value">
                    ${shortlisted != null ? shortlisted : 0}
                </div>

                <div class="stat-description">
                    Applications shortlisted
                </div>

            </div>


            <!-- Interviews -->
            <div class="stat-card">

                <div class="stat-title">
                    Pending Interviews
                </div>

                <div class="stat-value">
                    ${pendingInterviews != null ? pendingInterviews : 0}
                </div>

                <div class="stat-description">
                    Interviews scheduled
                </div>

            </div>

        </div>


        <!-- Quick Actions -->
        <div class="quick-actions">

            <h3>
                Quick Actions
            </h3>

            <div class="action-row">

                <a href="${pageContext.request.contextPath}/student/profile"
                   class="action-card">

                    <strong>
                        Update Profile
                    </strong>

                    <span>
                        Add education, skills and projects.
                    </span>

                </a>


                <a href="${pageContext.request.contextPath}/student/opportunities"
                   class="action-card">

                    <strong>
                        Browse Opportunities
                    </strong>

                    <span>
                        Find available jobs and internships.
                    </span>

                </a>


                <a href="${pageContext.request.contextPath}/student/applications"
                   class="action-card">

                    <strong>
                        Track Applications
                    </strong>

                    <span>
                        Check your application status.
                    </span>

                </a>

            </div>

        </div>

    </div>

</div>

<jsp:include page="../common/footer.jsp" />
