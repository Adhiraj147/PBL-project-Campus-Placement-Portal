<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <title>My Resume</title>

    <style>

        body {
            margin: 0;
            font-family: Arial, sans-serif;
            background: #f5f7fa;
        }

        .resume-container {
            width: 90%;
            max-width: 800px;
            margin: 50px auto;
        }

        .resume-card {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.08);
        }

        h1 {
            margin-top: 0;
        }

        .description {
            color: #666;
            margin-bottom: 25px;
        }

        .resume-details {
            padding: 20px;
            background: #f7f7f7;
            border-radius: 8px;
            margin-bottom: 25px;
        }

        .upload-section {
            margin-top: 20px;
        }

        input[type="file"] {
            margin: 15px 0;
            display: block;
        }

        button {
            border: none;
            padding: 11px 20px;
            border-radius: 6px;
            cursor: pointer;
            background: #333;
            color: white;
        }

        .error {
            color: #b00020;
            margin-bottom: 15px;
        }

        .back-link {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
        }

    </style>

</head>

<body>

<div class="resume-container">

    <div class="resume-card">

        <h1>My Resume</h1>

        <p class="description">
            Upload or update your resume for placement opportunities.
        </p>


        <% if (request.getAttribute("error") != null) { %>

            <div class="error">
                <%= request.getAttribute("error") %>
            </div>

        <% } %>


        <% if (request.getAttribute("resume") != null) { %>

            <div class="resume-details">

                <h3>Current Resume</h3>

                <p>
                    File:
                    ${resume.fileName}
                </p>

                <p>
                    Uploaded:
                    ${resume.uploadedDate}
                </p>

            </div>

        <% } else { %>

            <div class="resume-details">

                <h3>No Resume Uploaded</h3>

                <p>
                    Upload your resume to complete your placement profile.
                </p>

            </div>

        <% } %>


        <div class="upload-section">

            <h2>
                Upload / Update Resume
            </h2>

            <form method="post"
                  action="${pageContext.request.contextPath}/student/resume"
                  enctype="multipart/form-data">

                <label for="resumeFile">
                    Select Resume
                </label>

                <input type="file"
                       id="resumeFile"
                       name="resumeFile"
                       accept=".pdf,.doc,.docx"
                       required>

                <button type="submit">
                    Upload Resume
                </button>

            </form>

        </div>


        <a class="back-link"
           href="${pageContext.request.contextPath}/student/profile">
            Back to Profile
        </a>

    </div>

</div>

</body>

</html>
