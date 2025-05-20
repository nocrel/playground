<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <link rel="stylesheet" href="/user.css">
</head>
<body>
    <div class="container">
        <h2>로그인</h2>
        <form id="loginForm">
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="userid" required></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="userpw" required></td>
                </tr>
                <td colspan="2">
                    <button type="button" onclick="submitLoginForm()">로그인</button>
                </td>
            </table>
        </form>
        <a href="/user/signup">회원가입</a>
    </div>

    <script>
        // 폼 제출 처리
        function submitLoginForm() {
            const loginFormData = {
                userid: document.querySelector('input[name="userid"]').value,
                userpw: document.querySelector('input[name="userpw"]').value
            };

            // AJAX를 사용하여 로그인 요청
            fetch('/api/user/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(loginFormData)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('로그인에 실패했습니다.');
                })
                .then(data => {
                    // 로그인 성공
                    window.location.href = '/board/posts';
                })
                .catch(error => {
                    alert('로그인에 실패했습니다.');
                    console.error('Error:', error);
                });
        }
    </script>
</body>
</html>