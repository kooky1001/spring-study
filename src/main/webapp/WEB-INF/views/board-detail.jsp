<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="layout/header.jsp"/>
<article>
    <h2>글 상세보기</h2>
    <div class="grid">
        <span>${board.author}</span>
        <fmt:parseDate value="${board.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both"/>
        <span><fmt:formatDate value="${parseDate}" pattern="yyyy.MM.dd HH:mm"/></span>
    </div>
    <hr/>
    <form>
        <input type="text" name="id" id="id" value="${board.id}" readonly/>
        <label for="title">제목</label>
        <input type="text" name="title" id="title" value="${board.title}" readonly/>
        <label for="content">내용</label>
        <textarea name="content" id="content" readonly>${board.content}</textarea>
    </form>
</article>
<div class="grid">
    <a href="/board/${board.id}/update">
        <button>수정하기</button>
    </a>
    <form action="/board/${board.id}/delete" method="post">
        <input type="hidden" name="_method" value="delete"/>
        <a href="/">
            <button type="submit" style="width: 130px">삭제하기</button>
        </a>
    </form>
    <a href="/">
        <button>뒤로가기</button>
    </a>
</div>
<jsp:include page="layout/footer.jsp"/>
