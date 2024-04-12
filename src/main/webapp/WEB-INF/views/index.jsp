<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="layout/header.jsp"/>

<article>
    <h2>글 등록</h2>
    <hr/>
    <form method="post" action="/board">
        <label for="title">제목</label>
        <input type="text" name="title" id="title"/>
        <label for="content">내용</label>
        <textarea name="content" id="content"></textarea>
        <input type="submit" value="등록"/>
    </form>
</article>

<article>
    <table class="striped">
        <thead>
        <tr>
            <th>#</th>
            <th>제목</th>
            <th>내용</th>
            <th>작성자</th>
            <th>수정일자</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="board" items="${boardList}">
            <tr>
                <td>${board.id}</td>
                <td><a href="/board/${board.id}">${board.title}</a></td>
                <td><a href="/board/${board.id}">${board.content}</a></td>
                <td>${board.author}</td>
                <fmt:parseDate value="${board.modifiedDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parseDate" type="both"/>
                <td><fmt:formatDate value="${parseDate}" pattern="yyyy.MM.dd"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</article>

<jsp:include page="layout/footer.jsp"/>
