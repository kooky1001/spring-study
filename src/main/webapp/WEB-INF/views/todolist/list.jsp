<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<h2>TodoList</h2>
<%--        <input type="date" name="toDate" id="toDate">--%>
<hr/>
<%--    <fieldset role="group">--%>
<%--        <input type="text" name="content" id="content" placeholder="할 일을 입력하세요."/>--%>
<%--        <input type="submit" value="등록" onclick="save()"/>--%>
<%--    </fieldset>--%>
<div role="group">
    <article style="width: 30%;">
        <div class="grid">
            <h2>카테고리</h2>
        </div>
        <hr/>
        <div id="categoryList"></div>
        <footer>
            <fieldset role="group">
                <input type="text" name="content" id="aaa" placeholder="카테고리 추가"/>
                <input type="submit" value="등록" onclick="save()"/>
            </fieldset>
        </footer>
    </article>
    <article style="width: 70%">
        <div class="grid">
            <h2>목록</h2>
            <h4 style="text-align: end;padding-right: 10%">
                <i id="title"></i>
            </h4>
        </div>
        <hr/>
        <div id="list"></div>
        <footer>
            <fieldset role="group">
                <input type="text" name="content" id="content" placeholder="할 일을 입력하세요."/>
                <input type="submit" value="등록" onclick="save()"/>
            </fieldset>
        </footer>
    </article>
</div>

<script>
    $(document).ready(() => {
        /* date로 구분
        $("#toDate").change(() => {
            let toDate = $("#toDate").val();
            findAll(toDate);
        });

        // $("#toDate").val(new Date().toJSON().slice(0, 10));
        $("#toDate").val(dateFormat());
        findAllByDate();
        */
        findAllCategories();
        // initCategory();

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
    });

    /*
    function listByDate(list) {
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
     */

    function listByCategory(list) {
        let categoryId = "#" + list[0].category;
        let categoryName = $(`option[value=\${list[0].category}]`).html();
        $(categoryId).empty();
        $(categoryId).append(`<h4><i>\${categoryName}</i></h4>`);
        for (let item of list) {
            let todoItem = '';
            if (item.completed) {
                todoItem = `<nav class="todoItem">
                                <input type="hidden" name="id" value="\${item.id}"/>
                                <ul>
                                    <li><label><input type="checkbox" name="complete" checked/><s><span class="todoContent">\${item.content}</span></s></label></li>
                                </ul>
                                <ul>
                                    <li><button class="deleteBtn">삭제</button></li>
                                </ul>
                            </nav>`;
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
                        </nav>`;
            }
            $(categoryId).append(todoItem);
        }
        $(categoryId).append("<hr/>");
    }

    function findAllByCategory(category) {
        $.ajax({
            url: "todolist/list",
            method: "get",
            data: {category: category},
            success: (data) => {
                if (data.length <= 0) {
                    return;
                }
                listByCategory(data);
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    /*
    function findAllByDate(toDate) {
        if (!toDate) {
            toDate = $("#toDate").val();
        }
        $.ajax({
            url: "todolist/list",
            method: "get",
            data: {toDate: toDate},
            success: (data) => {
                listByDate(data);
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }
    */

    function save() {
        let toDate = $("#toDate").val();
        let content = $("#content").val();
        let category = $("#category").val();

        if (!content) {
            return;
        }

        $.ajax({
            url: "todolist",
            method: "post",
            data: {content: content, toDate: toDate, category: category},
            success: (data) => {
                findAllByCategory(data.category);
                // findAllByDate();
                $("#content").val("");
                alert("등록되었습니다.")
            }, error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function check(id, checked) {
        $.ajax({
            url: "todolist/checked",
            method: "put",
            data: {id: id, completed: checked},
            success: (data) => {
                $("#" + data.category).empty();
                findAllByCategory(data.category);
                // findAllByDate();
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function deleteTodo(id) {
        $.ajax({
            url: "todolist",
            method: "delete",
            data: {id: id},
            success: (data) => {
                // 마지막 항목 삭제의 경우 div내용이 삭제되지 않으므로 로직 추가
                $("#" + data.category).empty();
                findAllByCategory(data.category);
                // findAllByDate();
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function updateTodo(id, content) {
        $.ajax({
            url: "todolist",
            method: "put",
            data: {id: id, content: content},
            success: (data) => {
                findAllByCategory(data.category);
                // findAllByDate();
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    /*
    function dateFormat() {
        let date = new Date();
        let year = date.getFullYear();
        let month = ('0' + (date.getMonth() + 1)).slice(-2);
        let day = ('0' + date.getDate()).slice(-2);
        return `\${year}-\${month}-\${day}`;
    }
     */

    function initCategory() {
        let categoryList = $.map($("#category option"), e => e.value);
        for (let name of categoryList) {
            let div = $("<div></div>").attr("id", name);
            $("#list").append(div);
            // findAllByCategory(name);
        }
    }

    function errMessage(err) {
        err = err.responseJSON;
        return `\${err.error} - \${err.status}
        \${err.message}`;
    }

    function findAllCategories() {
        $.get({
            url: "todolist/category",
            success: (data) => {
                listOfCategory(data);
            }, error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function listOfCategory(list) {
        $("#categoryList").empty();
        for (let category of list) {
            let item = `<nav class="categoryItem">
                            <input type="hidden" name="id" value="\${category.id}"/>
                            <ul>
                                <li><strong> * <a href="#" class="description">\${category.description}</a></strong></li>
                            </ul>
                            <ul>
                                <li>
                                    <button class="deleteBtn secondary">X</button>
                                </li>
                            </ul>
                        </nav>`;
            $("#categoryList").append(item);
        }
    }

</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
