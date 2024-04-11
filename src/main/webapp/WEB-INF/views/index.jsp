<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="layout/header.jsp" />

    <article>
        <h2>글 등록</h2>
        <hr />
        <form method="post" action="/board">
<%--            <input type="text" name="content">--%>
            <textarea name="content"></textarea>
            <input type="submit" value="등록">
        </form>
    </article>

    <article>
        <table class="striped">
            <thead>
                <tr>
                    <th>#</th>
                    <th>내용</th>
<%--                    <th>수정일자</th>--%>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="board" items="${boardList}">
                <tr>
                    <td><a href="/board/${board.id}">${board.id}</a></td>
                    <td><a href="/board/${board.id}">${board.content}</a></td>
<%--                    <td>${board.updatedAt}</td>--%>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </article>

<jsp:include page="layout/footer.jsp" />