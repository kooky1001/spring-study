<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />
<article>
    <h2>글 수정하기</h2>
    <hr />
    <form action="/board/${board.id}/update" method="post">
    <%--    <input type="hidden" name="_method" value="put"/>--%>
        <input type="hidden" name="id" value="${board.id}"/>
        <textarea name="content">${board.content}</textarea>
        <button type="submit">수정하기</button>
    </form>
</article>
<a href="/board/${board.id}"><button>뒤로가기</button></a>
<jsp:include page="layout/footer.jsp" />
