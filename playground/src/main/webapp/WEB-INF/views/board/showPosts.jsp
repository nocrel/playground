<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시판 목록</title>
    <link rel="stylesheet" href="/board.css">
</head>
<body>
    <h2>게시판 목록</h2>

    <table>
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>수정일</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${posts}" var="post">
                <tr>
                    <td>${post.id}</td>
                    <td><a href="/board/post/${post.id}">${post.title}</a></td>
                    <td>${post.author}</td>
                    <td>${post.createdAt}</td>
                    <td>${post.modifiedAt}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

        <div class="btn-container">
            <a href="/board/create" class="button">글쓰기</a>
        </div>
</body>
</html>