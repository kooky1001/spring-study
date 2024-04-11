<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />
<article>
    <h2>글 상세보기</h2>
    <hr />
    <form>
        <input type="text" name="id" value="${board.id}" readonly>
        <textarea name="content" readonly>${board.content}</textarea>
    </form>
</article>
<div class="grid">
<a href="/board/${board.id}/update"><button>수정하기</button></a>
<form action="/board/${board.id}/delete" method="post">
    <input type="hidden" name="_method" value="delete" />
    <a href="/"><button type="submit" style="width: 130px">삭제하기</button></a>
</form>
<a href="/"><button>뒤로가기</button></a>
</div>
<jsp:include page="layout/footer.jsp" />
