<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 작성</title>
    <link rel="stylesheet" href="/board.css">
</head>
<body>
    <h2> 게시글 작성</h2>
    <form id="createForm">
        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" id="title" name="title" required></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" id="author" name="author" value="${sessionScope.userid}" readonly></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="password" id="password" name="password" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea id="content" name="content" required></textarea></td>
            </tr>
        </table>

        <div class="btn-container">
            <button type="button" onclick="location.href='/board/posts'">취소</button>
            <button type="button" onclick="submitCreateForm()">등록</button>
        </div>
    </form>

    <script>
        function submitCreateForm() {
            const formData = {
                title: document.querySelector('#title').value,
                author: document.querySelector('#author').value,
                password: document.querySelector('#password').value,
                content: document.querySelector('#content').value
            };

            fetch('/api/post', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(response => response.json())
                .then(data => {
                    alert("게시글이 등록되었습니다.");
                    window.location.href = '/board/posts';
                })
                .catch(error => {
                    alert("오류가 발생했습니다: " + error);
                });
        }
    </script>
</body>
</html>