// 게시글 삭제 함수
function deletePost(id) {
    // 삭제 확인 메시지
    const confirmDelete = confirm("게시글을 삭제하시겠습니까?");

    if (confirmDelete) {
        fetch(`/api/post/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error('삭제 권한이 없습니다.');
            })
            .then(data => {
                if (data.success) {
                    alert("삭제되었습니다.");
                    window.location.href = "/board/posts";
                } else {
                    alert("삭제에 실패했습니다.");
                }
            })
            .catch(error => {
                alert(error.message);
                console.error('Error:', error);
            });
    }
}

// 페이지 로드 시 실행되는 함수
document.addEventListener('DOMContentLoaded', function() {
    console.log('게시판 페이지가 로드되었습니다.');
});
