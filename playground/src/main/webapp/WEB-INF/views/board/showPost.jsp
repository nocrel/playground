<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@ include file="../common/header.jsp" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세</title>
    <link rel="stylesheet" href="/board.css">
</head>
<body>
    <h2>게시글 상세</h2>

    <table>
        <tr>
            <th>제목</th>
            <td>${board.title}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${board.author}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td style="min-height: 200px; white-space: pre-wrap;">${board.content}</td>
        </tr>
        <tr>
            <th>작성일</th>
            <td>${board.createdAt}</td>
        </tr>
        <tr>
            <th>수정일</th>
            <td>${board.modifiedAt}</td>
        </tr>
    </table>

    <div class="btn-container">
        <a href="/board/posts" class="button">목록</a>
        <a href="/board/edit/post/${board.id}" class="button">수정</a>
        <button onclick="deletePost(${board.id})">삭제</button>
    </div>

    <script src="/deletePost.js"></script>
</body>
</html>