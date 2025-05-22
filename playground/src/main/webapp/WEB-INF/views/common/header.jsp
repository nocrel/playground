<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="site-header">
    <div class="logo">
        <a href="/board/posts">Playground Platform</a>
    </div>
    <nav class="main-nav">
        <ul>
            <li><a href="/board/posts">게시판</a></li>
        </ul>
    </nav>
    <div class="user-area">
        <c:choose>
            <c:when test="${not empty sessionScope.userid}">
                <span class="user-info">${sessionScope.userid}님</span>
                <button class="logout-btn" onclick="logout()">로그아웃</button>
            </c:when>
            <c:otherwise>
                <a href="/user/login" class="login-btn">로그인</a>
                <a href="/user/signup" class="signup-btn">회원가입</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<script>
    function logout() {
        fetch('/api/user/logout')
            .then(response => {
                if (response.ok) {
                    alert('로그아웃 되었습니다.');
                    window.location.href = '/user/login';
                } else {
                    throw new Error('로그아웃 실패');
                }
            })
            .catch(error => {
                alert('로그아웃 중 오류가 발생했습니다.');
                console.error('Error:', error);
            });
    }
</script>

<%--헤더는 그냥 스타일 여기에--%>
<style>
    .site-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px 20px;
        background-color: #f8f9fa;
        border-bottom: 1px solid #e9ecef;
    }

    .logo a {
        font-size: 1.5rem;
        font-weight: bold;
        color: #343a40;
        text-decoration: none;
    }

    .main-nav ul {
        display: flex;
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .main-nav li {
        margin-right: 20px;
    }

    .main-nav a {
        color: #495057;
        text-decoration: none;
    }

    .user-area {
        display: flex;
        align-items: center;
    }

    .user-info {
        margin-right: 10px;
        font-weight: 500;
    }

    .logout-btn {
        padding: 5px 10px;
        background-color: #838edc;
        border: 1px solid #ced4da;
        border-radius: 4px;
        cursor: pointer;
    }

    .logout-btn:hover {
        background-color: #e9ecef;
    }

    .login-btn, .signup-btn {
        margin-left: 10px;
        padding: 5px 10px;
        background-color: #f8f9fa;
        border: 1px solid #ced4da;
        border-radius: 4px;
        text-decoration: none;
        color: #495057;
    }

    .login-btn:hover, .signup-btn:hover {
        background-color: #e9ecef;
    }
</style>