<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<article>
    <div class="grid">
        <h2>할 일</h2>
        <input type="date" name="date" id="date">
    </div>
    <hr/>
    <fieldset role="group">
        <input type="text" name="content" id="content" placeholder="할 일을 입력하세요."/>
        <input type="submit" value="등록" onclick="save()"/>
    </fieldset>
</article>

<article>
    <h2>목록</h2>
    <hr/>
    <div id="list">
    </div>
</article>

<script>
    $(document).ready(() => {
        $("#date").change(() => {
            let date = $("#date").val();
            findAll(date);
        });

        $("#date").val(new Date().toJSON().slice(0, 10));
        findAll();

    })

    function initList(list) {
        $("#list").empty();
        for (let item of list) {
            let todoItem = '';
            if (item.completed) {
                todoItem = `<nav class="todoItem">
                                <input type="hidden" name="id" value="\${item.id}"/>
                                <ul>
                                    <li><label><input type="checkbox" name="completed" checked/><s>\${item.content}</s></label></li>
                                </ul>
                                <ul>
                                    <li><button class="updateBtn">수정</button></li>
                                    <li><button class="deleteBtn">삭제</button></li>
                                </ul>
                            </nav>`
            } else {
                todoItem = `<nav class="todoItem">
                                <input type="hidden" name="id" value="\${item.id}"/>
                                <ul>
                                    <li><label><input type="checkbox" name="completed">\${item.content}</label></li>
                                </ul>
                                <ul>
                                    <li><button class="updateBtn">수정</button></li>
                                    <li><button class="deleteBtn">삭제</button></li>
                                </ul>
                            </nav>`
            }
            $("#list").append(todoItem);
        }
    }

    function save() {
        let date = $("#date").val();
        let content = $("#content").val();

        if (!content) {
            return;
        }

        $.ajax({
            url: "todolist",
            method: "post",
            data: {content: content, date: date},
            success: (data) => {
                findAll();
                $("#content").val("");
                alert("등록되었습니다.")
            }, error: (err) => {
                alert(err.toString());
            }
        });
    }

    function findAll(date) {
        if (!date) {
            date = $("#date").val();
        }
        $.ajax({
            url: "todolist/list",
            method: "get",
            data: {date: date},
            success: (data) => {
                initList(data);
            },
            error: (err) => {
                alert(err.toString());
            }
        });
    }
</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
