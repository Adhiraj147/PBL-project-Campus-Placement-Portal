<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="../common/header.jsp" />

<div class="row mt-4 mb-5">

    <!-- Sidebar -->
    <div class="col-md-3">

        <div class="list-group shadow-sm">

            <a href="${pageContext.request.contextPath}/student/dashboard"
               class="list-group-item list-group-item-action">
                Dashboard
            </a>

            <a href="${pageContext.request.contextPath}/student/profile"
               class="list-group-item list-group-item-action active">
                My Profile
            </a>

            <a href="${pageContext.request.contextPath}/student/opportunities"
               class="list-group-item list-group-item-action">
                Jobs &amp; Internships
            </a>

            <a href="${pageContext.request.contextPath}/student/applications"
               class="list-group-item list-group-item-action">
                My Applications
            </a>

        </div>

    </div>


    <!-- Profile Content -->
    <div class="col-md-9">

        <h3 class="mb-3">
            My Profile
        </h3>


        <!-- Profile Completion -->
        <div class="card shadow-sm mb-4">

            <div class="card-body">

                <h5 class="mb-3">
                    Profile Completion
                </h5>

                <progress
                    value="${completionScore}"
                    max="100"
                    class="w-100">
                </progress>

                <div class="mt-2">

                    <strong>
                        ${completionScore}%
                    </strong>

                </div>

                <c:if test="${completionScore < 100}">

                    <small class="text-muted d-block mt-2">
                        Complete all sections to improve your profile.
                    </small>

                </c:if>

                <c:if test="${completionScore >= 100}">

                    <small class="text-success d-block mt-2">
                        Your profile is complete.
                    </small>

                </c:if>

            </div>

        </div>


        <!-- Basic Information -->
        <div class="card shadow-sm mb-4">

            <div class="card-header">

                <h5 class="mb-0">
                    Basic Information
                </h5>

            </div>

            <div class="card-body">

                <form
                    action="${pageContext.request.contextPath}/student/profile"
                    method="post">

                    <input
                        type="hidden"
                        name="action"
                        value="updateBasic">


                    <!-- First Name and Last Name -->
                    <div class="row mb-3">

                        <div class="col-md-6">

                            <label class="form-label">
                                First Name
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="firstName"
                                value="${student.firstName}"
                                required>

                        </div>


                        <div class="col-md-6">

                            <label class="form-label">
                                Last Name
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="lastName"
                                value="${student.lastName}"
                                required>

                        </div>

                    </div>


                    <!-- Academic Information -->
                    <div class="row mb-3">

                        <div class="col-md-4">

                            <label class="form-label">
                                Roll Number
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="rollNo"
                                value="${student.rollNo}"
                                required>

                        </div>


                        <div class="col-md-4">

                            <label class="form-label">
                                Branch
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="branch"
                                value="${student.branch}"
                                placeholder="CSE / IT"
                                required>

                        </div>


                        <div class="col-md-4">

                            <label class="form-label">
                                Graduation Year
                            </label>

                            <input
                                type="number"
                                class="form-control"
                                name="graduationYear"
                                value="${student.graduationYear}"
                                required>

                        </div>

                    </div>


                    <!-- CGPA, Phone and Resume -->
                    <div class="row mb-3">

                        <div class="col-md-4">

                            <label class="form-label">
                                CGPA
                            </label>

                            <input
                                type="number"
                                step="0.01"
                                min="0"
                                max="10"
                                class="form-control"
                                name="cgpa"
                                value="${student.cgpa}"
                                required>

                        </div>


                        <div class="col-md-4">

                            <label class="form-label">
                                Phone
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="phone"
                                value="${student.phone}">

                        </div>


                        <div class="col-md-4">

                            <label class="form-label">
                                Resume URL
                            </label>

                            <input
                                type="url"
                                class="form-control"
                                name="resumeUrl"
                                value="${student.resumeUrl}">

                        </div>

                    </div>


                    <button
                        type="submit"
                        class="btn btn-primary">

                        Save Basic Information

                    </button>

                </form>

            </div>

        </div>


        <!-- Skills -->
        <div class="card shadow-sm mb-4">

            <div class="card-header">

                <h5 class="mb-0">
                    Skills
                </h5>

            </div>

            <div class="card-body">

                <c:choose>

                    <c:when test="${not empty student.skills}">

                        <div class="mb-3">

                            <c:forEach
                                var="skill"
                                items="${student.skills}">

                                <span class="badge bg-primary me-2 mb-2">

                                    ${skill}

                                </span>

                            </c:forEach>

                        </div>

                    </c:when>

                    <c:otherwise>

                        <p class="text-muted">
                            No skills added yet.
                        </p>

                    </c:otherwise>

                </c:choose>


                <!-- Add Skill -->
                <form
                    action="${pageContext.request.contextPath}/student/profile"
                    method="post">

                    <input
                        type="hidden"
                        name="action"
                        value="addSkill">


                    <div class="input-group">

                        <input
                            type="text"
                            class="form-control"
                            name="skillName"
                            placeholder="Example: Java, Python, SQL"
                            required>

                        <button
                            type="submit"
                            class="btn btn-primary">

                            Add Skill

                        </button>

                    </div>

                </form>

            </div>

        </div>


        <!-- Education -->
        <div class="card shadow-sm mb-4">

            <div class="card-header">

                <h5 class="mb-0">
                    Education
                </h5>

            </div>

            <div class="card-body">

                <c:choose>

                    <c:when test="${not empty student.educationList}">

                        <c:forEach
                            var="education"
                            items="${student.educationList}">

                            <div class="border rounded p-3 mb-3">

                                <h6>
                                    ${education.degree}
                                </h6>

                                <p class="mb-1">

                                    <strong>
                                        Institution:
                                    </strong>

                                    ${education.institution}

                                </p>

                                <p class="mb-1">

                                    <strong>
                                        Passing Year:
                                    </strong>

                                    ${education.passingYear}

                                </p>

                                <p class="mb-0">

                                    <strong>
                                        Percentage:
                                    </strong>

                                    ${education.percentage}%

                                </p>

                            </div>

                        </c:forEach>

                    </c:when>

                    <c:otherwise>

                        <p class="text-muted">
                            No education details added yet.
                        </p>

                    </c:otherwise>

                </c:choose>


                <!-- Add Education -->
                <form
                    action="${pageContext.request.contextPath}/student/profile"
                    method="post"
                    class="mt-3">

                    <input
                        type="hidden"
                        name="action"
                        value="addEducation">


                    <div class="row mb-3">

                        <div class="col-md-3">

                            <label class="form-label">
                                Degree
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="degree"
                                placeholder="B.Tech"
                                required>

                        </div>


                        <div class="col-md-3">

                            <label class="form-label">
                                Institution
                            </label>

                            <input
                                type="text"
                                class="form-control"
                                name="institution"
                                placeholder="College Name"
                                required>

                        </div>


                        <div class="col-md-3">

                            <label class="form-label">
                                Passing Year
                            </label>

                            <input
                                type="number"
                                class="form-control"
                                name="passingYear"
                                placeholder="2027"
                                required>

                        </div>


                        <div class="col-md-3">

                            <label class="form-label">
                                Percentage
                            </label>

                            <input
                                type="number"
                                step="0.01"
                                class="form-control"
                                name="percentage"
                                placeholder="85"
                                required>

                        </div>

                    </div>


                    <button
                        type="submit"
                        class="btn btn-success">

                        Add Education

                    </button>

                </form>

            </div>

        </div>


        <!-- Projects -->
        <div class="card shadow-sm mb-4">

            <div class="card-header">

                <h5 class="mb-0">
                    Projects
                </h5>

            </div>

            <div class="card-body">

                <c:choose>

                    <c:when test="${not empty student.projectList}">

                        <c:forEach
                            var="project"
                            items="${student.projectList}">

                            <div class="border rounded p-3 mb-3">

                                <h6>
                                    ${project.title}
                                </h6>

                                <p>
                                    ${project.description}
                                </p>

                                <c:if test="${not empty project.link}">

                                    <a
                                        href="${project.link}"
                                        target="_blank"
                                        class="btn btn-sm btn-outline-primary">

                                        View Project

                                    </a>

                                </c:if>

                            </div>

                        </c:forEach>

                    </c:when>

                    <c:otherwise>

                        <p class="text-muted">
                            No projects added yet.
                        </p>

                    </c:otherwise>

                </c:choose>


                <!-- Add Project -->
                <form
                    action="${pageContext.request.contextPath}/student/profile"
                    method="post"
                    class="mt-3">

                    <input
                        type="hidden"
                        name="action"
                        value="addProject">


                    <div class="mb-3">

                        <label class="form-label">
                            Project Title
                        </label>

                        <input
                            type="text"
                            class="form-control"
                            name="title"
                            placeholder="Project Title"
                            required>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Project Description
                        </label>

                        <textarea
                            class="form-control"
                            name="description"
                            rows="3"
                            placeholder="Describe your project"
                            required></textarea>

                    </div>


                    <div class="mb-3">

                        <label class="form-label">
                            Project Link
                        </label>

                        <input
                            type="url"
                            class="form-control"
                            name="link"
                            placeholder="https://github.com/...">

                    </div>


                    <button
                        type="submit"
                        class="btn btn-success">

                        Add Project

                    </button>

                </form>

            </div>

        </div>

    </div>

</div>

<jsp:include page="../common/footer.jsp" />
