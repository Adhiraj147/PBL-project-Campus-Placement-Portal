<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"
    uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Notifications</title>

    <style>

        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 90%;
            max-width: 900px;
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

        .notification {
            padding: 18px;
            margin-bottom: 15px;
            border-radius: 8px;
            border: 1px solid #ddd;
            background: #fafafa;
        }

        .notification.unread {
            background: #e3f2fd;
            border-left: 5px solid #1976d2;
        }

        .notification h2 {
            margin-top: 0;
            color: #333;
            font-size: 18px;
        }

        .notification p {
            color: #555;
            line-height: 1.5;
        }

        .date {
            font-size: 13px;
            color: #777;
        }

        .empty {
            padding: 25px;
            text-align: center;
            color: #777;
            background: #f5f5f5;
            border-radius: 8px;
        }

        .error {
            padding: 15px;
            background: #ffebee;
            color: #c62828;
            border: 1px solid #ef5350;
            border-radius: 6px;
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

        .back-button:hover {
            background: #616161;
        }

    </style>

</head>

<body>

<div class="container">

    <div class="card">

        <h1>Notifications</h1>

        <!-- Error -->

        <c:if test="${not empty errorMessage}">

            <div class="error">
                ${errorMessage}
            </div>

        </c:if>


        <!-- Notifications -->

        <c:choose>

            <c:when test="${not empty notifications}">

                <c:forEach
                    var="notification"
                    items="${notifications}">

                    <div class="notification
                        ${notification.read ? '' : 'unread'}">

                        <h2>
                            ${notification.title}
                        </h2>

                        <p>
                            ${notification.message}
                        </p>

                        <div class="date">
                            ${notification.notificationDate}
                        </div>

                    </div>

                </c:forEach>

            </c:when>

            <c:otherwise>

                <div class="empty">

                    <p>
                        You currently have no notifications.
                    </p>

                </div>

            </c:otherwise>

        </c:choose>


        <a class="back-button"
           href="${pageContext.request.contextPath}/student/dashboard">
            Back to Dashboard
        </a>

    </div>

</div>

</body>

</html>
