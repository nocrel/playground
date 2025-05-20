<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!Doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <link rel="stylesheet" href="/user.css">
</head>
<body>
    <div class="container">
        <h2>회원가입</h2>
        <form id="signupForm">
            <table>
                <tr>
                    <td>아이디</td>
                    <td><input type="text" name="userid" required></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><input type="password" name="userpw" required></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <button type="button" onclick="submitSignupForm()">가입하기</button>
                    </td>
                </tr>
            </table>
        </form>
        <a href="/user/login">로그인</a>
    </div>

    <script>
        // 폼 제출 처리
        function submitSignupForm() {

            const signupFormData = {
                userid: document.querySelector('input[name="userid"]').value,
                userpw: document.querySelector('input[name="userpw"]').value
            };

            // AJAX를 사용하여 회원가입 요청
            fetch('/api/user/signup', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(signupFormData)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error('회원가입에 실패했습니다');
                })
                .then(data => {
                    // 회원가입 성공 메시지 표시
                    alert('회원가입이 완료되었습니다.');
                    // 로그인 페이지로 리다이렉트
                    window.location.href = '/user/login';
                })
                .catch(error => {
                    alert('회원가입에 실패했습니다.');
                    console.error('Error:', error);
                });
        }
    </script>
</body>
</html>
