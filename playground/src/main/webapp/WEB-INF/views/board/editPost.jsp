<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 수정</title>
    <link rel="stylesheet" href="/board.css">
</head>
<body>
    <h2>게시글 수정</h2>

    <form id="editForm">
        <table>
            <tr>
                <th>제목</th>
                <td><input type="text" name="title" value="${board.title}" required></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><input type="text" name="author" value="${board.author}" required readonly></td>
            </tr>
            <tr>
                <th>비밀번호</th>
                <td><input type="text" name="password" required></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><textarea name="content" required>${board.content}</textarea></td>
            </tr>
        </table>

        <div class="btn-container">
            <button type="button" onclick="location.href='/board/post/${board.id}'">취소</button>
            <button type="button" onclick="submitEditForm(${board.id})">수정</button>
        </div>
    </form>

    <script>
        function submitEditForm(id) {
            const formData = {
                title: document.querySelector('input[name="title"]').value,
                author: document.querySelector('input[name="author"]').value,
                password: document.querySelector('input[name="password"]').value,
                content: document.querySelector('textarea[name="content"]').value
            };

            fetch('/api/post/${id}', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            })
                .then(data => {
                    alert("게시글이 수정되었습니다.");
                    window.location.href = '/board/post/${board.id}';
                })
                .catch(error => {
                    alert(error.message);
                });
        }
    </script>
</body>
</html>