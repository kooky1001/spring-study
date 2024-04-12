<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="layout/header.jsp"/>
<article>
    <h2>글 수정하기</h2>
    <div class="grid">
        <span>${board.author}</span>
        <fmt:parseDate value="${board.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both"/>
        <span><fmt:formatDate value="${parseDate}" pattern="yyyy.MM.dd HH:mm"/></span>
    </div>
    <hr/>
    <form action="/board/${board.id}/update" method="post">
        <%--    <input type="hidden" name="_method" value="put"/>--%>
        <input type="hidden" name="id" value="${board.id}"/>
        <label for="title">제목</label>
        <input type="text" name="title" id="title" value="${board.title}"/>
        <label for="content">내용</label>
        <textarea name="content" id="content">${board.content}</textarea>
        <button type="submit">수정하기</button>
    </form>
</article>
<a href="/board/${board.id}">
    <button>뒤로가기</button>
</a>
<jsp:include page="layout/footer.jsp"/>
