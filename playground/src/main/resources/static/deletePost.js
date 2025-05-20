// 게시글 삭제 함수
function deletePost(id) {
    const password = prompt("비밀번호를 입력하세요");
    if (password) {
        fetch(`/api/post/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                password: password
            })
        })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    alert("삭제되었습니다.");
                    window.location.href = "/board/posts";
                } else {
                    alert("삭제에 실패했습니다.");
                }
            })
            .catch(error => {
                alert("오류가 발생했습니다: " + error);
            });
    }
}

// 페이지 로드 시 실행되는 함수
document.addEventListener('DOMContentLoaded', function() {
    console.log('게시판 페이지가 로드되었습니다.');
});
