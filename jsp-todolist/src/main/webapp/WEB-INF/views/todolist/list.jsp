<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<h2>TodoList</h2>
<%--        <input type="date" name="toDate" id="toDate">--%>
<hr/>
<div role="group">
    <article style="width: 30%;">
        <div class="grid">
            <h2>카테고리</h2>
        </div>
        <hr/>
        <div id="categoryList"></div>
        <footer>
            <fieldset role="group">
                <input type="text" name="content" id="categoryNameInput" placeholder="카테고리 추가"/>
                <input type="submit" value="등록" onclick="saveCategory()"/>
            </fieldset>
        </footer>
    </article>
    <article style="width: 70%">
        <div class="grid">
            <h2>목록</h2>
            <h4 style="text-align: end;padding-right: 10%">
                <input type="hidden" name="categoryId" id="categoryId"/>
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
    $(() => {
        /* date로 구분
        $("#toDate").change(() => {
            let toDate = $("#toDate").val();
            findAll(toDate);
        });

        // $("#toDate").val(new Date().toJSON().slice(0, 10));
        $("#toDate").val(dateFormat());
        findAllByDate();
        */
        findAllCategory();

        $("#list").on('change', "input[name='complete']", (e) => {
            let item = e.target.closest(".todoItem");
            let id = $(item).find("input[name='id']").val();
            let checked = $(item).find("input:checkbox").is(":checked");
            let todoContent = $(item).find(".todoContent").html();
            updateTodo(id, todoContent, checked);
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
                updateTodo(id, content, checked);
            }
        });

        $("#list").on('click', ".deleteBtn", (e) => {
            let item = e.target.closest(".todoItem");
            let id = $(item).find("input[name='id']").val();
            if (confirm("삭제하시겠습니까?")) {
                deleteTodo(id);
            }
        });

        $("#categoryList").on('click', ".categoryName", (e) => {
            let item = e.target.closest(".categoryItem");
            let id = $(item).find("input[name='id']").val();
            let categoryName = $(item).find(".categoryName").html();
            findAllByCategory(id, categoryName);
        });

        $("#categoryList").on('click', ".deleteBtn", (e) => {
            let item = e.target.closest(".categoryItem");
            let id = $(item).find("input[name='id']").val();
            if (confirm("삭제하시겠습니까?")) {
                deleteCategory(id);
            }
        });
    });

    function listOfTodoByCategory(list) {
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
                                    <li><button class="deleteBtn secondary">삭제</button></li>
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
                                <li><button class="deleteBtn secondary">삭제</button></li>
                            </ul>
                        </nav>`;
            }
            $("#list").append(todoItem);
        }
    }

    function findAllByCategory(category, categoryName) {
        if (categoryName) {
            $("#categoryId").val(category);
            $("#title").html(categoryName);
        }

        $.get({
            url: `todolist/\${category}`,
            contentType: "application/json; charset=utf-8",
            success: (data) => {
                listOfTodoByCategory(data);
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function save() {
        let content = $("#content").val();
        let category = $("#categoryId").val();

        if (!category) {
            alert("카테고리를 선택해주세요.");
            return;
        }
        if (!content) {
            return;
        }

        $.post({
            url: "todolist",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({content: content, category: category}),
            success: (data) => {
                findAllByCategory(data.category);
                $("#content").val("");
                alert("등록되었습니다.")
            }, error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function updateTodo(id, content, checked) {
        $.ajax({
            url: `todolist/\${id}`,
            method: "put",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({content: content, completed: checked}),
            success: (data) => {
                findAllByCategory(data.category);
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function deleteTodo(id) {
        $.ajax({
            url: `todolist/\${id}`,
            method: "delete",
            contentType: "application/json; charset=utf-8",
            success: (data) => {
                findAllByCategory(data.category);
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function errMessage(err) {
        err = err.responseJSON;
        return `\${err.error} - \${err.status}
        \${err.message}`;
    }

    function findAllCategory() {
        $.get({
            url: "todolist/category",
            success: (data) => {
                listOfCategory(data);

                if (data.length > 0 && !$("#categoryId").val()) {
                    findAllByCategory(data[0].id, data[0].name);
                }
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
                                <li><strong> * <a href="javascript:void(0)" class="categoryName">\${category.name}</a></strong></li>
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

    function deleteCategory(id) {
        $.ajax({
            url: `todolist/category/\${id}`,
            method: "delete",
            contentType: "application/json; charset=utf-8",
            success: (data) => {
                findAllCategory();
                let categoryId = $("#categoryId").val();
                if (data.id == categoryId) {
                    $("#categoryId").val("");
                    $("#title").html("");
                    findAllByCategory(data.id);
                }
            },
            error: (err) => {
                alert(errMessage(err));
            }
        });
    }

    function saveCategory() {
        let name = $("#categoryNameInput").val();

        if (!name) {
            return;
        }

        if (!confirm("카테고리를 등록하시겠습니까?")) {
            return;
        }

        $.post({
            url: "todolist/category",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({name: name}),
            success: (data) => {
                findAllCategory();
                $("#categoryNameInput").val("");
                alert("등록되었습니다.")
            }, error: (err) => {
                alert(errMessage(err));
            }
        });
    }

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

    /*
    function dateFormat() {
        let date = new Date();
        let year = date.getFullYear();
        let month = ('0' + (date.getMonth() + 1)).slice(-2);
        let day = ('0' + date.getDate()).slice(-2);
        return `\${year}-\${month}-\${day}`;
    }
     */

</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
