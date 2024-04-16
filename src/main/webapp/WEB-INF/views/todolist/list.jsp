<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<article>
    <div class="grid">
        <h2>할 일</h2>
        <input type="date" name="toDate" id="toDate">
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
        $("#toDate").change(() => {
            let toDate = $("#toDate").val();
            findAll(toDate);
        });

        // $("#toDate").val(new Date().toJSON().slice(0, 10));
        $("#toDate").val(dateFormat());
        findAll();

        $("#list").on('change', "input[name='complete']", (e) => {
            let item = e.target.closest(".todoItem");
            let id = $(item).find("input[name='id']").val();
            let checked = $(item).find("input:checkbox").is(":checked");
            check(id, checked);
        });

        $("#list").on('click', ".deleteBtn", (e) => {
            let item = e.target.closest(".todoItem");
            let id = $(item).find("input[name='id']").val();
            if (confirm("삭제하시겠습니까?")) {
                deleteTodo(id);
            }
        });

        $("#list").on('click', ".updateBtn", (e) => {
            let item = e.target.closest(".todoItem");
            let checked = $(item).find("input:checkbox").is(":checked");
            if (checked) {
                alert("완료된 항목은 수정할 수 없습니다.");
                return;
            }
            let id = $(item).find("input[name='id']").val();
            let todoContent = $(item).find(".todoContent").html();
            let content = prompt("수정할 내용을 입력해주세요.", todoContent);
            if (content) {
                updateTodo(id, content);
            }
        });
    })

    function initList(list) {
        $("#list").empty();
        for (let item of list) {
            let todoItem = '';
            if (item.completed) {
                todoItem = `<nav class="todoItem">
                                <input type="hidden" name="id" value="\${item.id}"/>
                                <ul>
                                    <li><label><input type="checkbox" name="complete" checked/><s><span class="todoContent">\${item.content}</span></s></label></li>
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
                                    <li><label><input type="checkbox" name="complete"><span class="todoContent">\${item.content}</span></label></li>
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
        let toDate = $("#toDate").val();
        let content = $("#content").val();

        if (!content) {
            return;
        }

        $.ajax({
            url: "todolist",
            method: "post",
            data: {content: content, toDate: toDate},
            success: (data) => {
                findAll();
                $("#content").val("");
                alert("등록되었습니다.")
            }, error: (err) => {
                alert(err.toString());
            }
        });
    }

    function findAll(toDate) {
        if (!toDate) {
            toDate = $("#toDate").val();
        }
        $.ajax({
            url: "todolist/list",
            method: "get",
            data: {toDate: toDate},
            success: (data) => {
                initList(data);
            },
            error: (err) => {
                alert(err.toString());
            }
        });
    }

    function check(id, checked) {
        $.ajax({
            url: "todolist/checked",
            method: "put",
            data: {id: id, completed: checked},
            success: (data) => {
                findAll();
            },
            error: (err) => {
                alert(err.toString());
            }
        });
    }

    function deleteTodo(id, checked) {
        $.ajax({
            url: "todolist",
            method: "delete",
            data: {id: id},
            success: (data) => {
                findAll();
            },
            error: (err) => {
                alert(err.toString());
            }
        });
    }

    function updateTodo(id, content) {
        $.ajax({
            url: "todolist",
            method: "put",
            data: {id: id, content: content},
            success: (data) => {
                findAll();
            },
            error: (err) => {
                alert(err.toString());
            }
        });
    }

    function dateFormat() {
        let date = new Date();
        let year = date.getFullYear();
        let month = ('0' + (date.getMonth() + 1)).slice(-2);
        let day = ('0' + date.getDate()).slice(-2);
        return `\${year}-\${month}-\${day}`;
    }

</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
