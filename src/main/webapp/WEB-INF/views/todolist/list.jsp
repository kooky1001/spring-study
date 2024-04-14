<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/layout/header.jsp"/>

<article>
    <div class="grid">
        <h2>할 일</h2>
        <input type="date" name="date" id="date">
    </div>
    <hr/>
    <form method="post">
        <fieldset role="group">
            <input type="text" name="content" id="content" placeholder="할 일을 입력하세요."/>
            <input type="submit" value="등록"/>
        </fieldset>
    </form>
</article>

<article>
    <h2>목록</h2>
    <hr/>
    <div>
        <nav>
            <ul>
                <li><label><input type="checkbox">해야할 일 3</label></li>
            </ul>
            <ul>
                <li>
                    <button>수정</button>
                </li>
                <li>
                    <button>삭제</button>
                </li>
            </ul>
        </nav>
    </div>
    <div>
        <nav>
            <ul>
                <li><label><input type="checkbox" checked/><s>해야할 일 4</s></label></li>
            </ul>
            <ul>
                <li>
                    <button>수정</button>
                </li>
                <li>
                    <button>삭제</button>
                </li>
            </ul>
        </nav>
    </div>
</article>

<script>
    $(document).ready(() => {
        $("#date").val(new Date().toJSON().slice(0, 10));

        $("form").submit((event) => {
            event.preventDefault();
            let form = {content: $("#content").val()}
        });

        findAll();
    })

    function initList(data) {
        $("#selectList").clear();
        for (var i = 0; i < date.length; i++) {
            let nav = $("<nav></nav>").html("test");
            $("#selectList").append("<li></li>");
        }
    }

    function findAll() {
        $.ajax({
            url: "todolist/list",
            method: "get",
            success: (data) => {
                alert(data);
            },
            error: (err) => {
                alert(err);
            }
        })
    }
</script>

<jsp:include page="/WEB-INF/views/layout/footer.jsp"/>
