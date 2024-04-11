<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp" />

<h2>Hi~ ${name}</h2>
<img src="/cookie.jpg" width="50%">
<br />
<hr />
<button class="btn" id="get">get</button>
<button class="btn" id="post">post</button>
<button class="btn" id="put">put</button>
<button class="btn" id="delete">delete</button>
<br />
<br />
<input type="text" id="output">

<script>
    $("#get").click(() => {
        $.ajax({
            url: "/test",
            method: "GET",
            dataType: "text",
            success: function (data) {
                $('#output').val(data)
                console.log(data)
            }
        })
    })
    $("#post").click(() => {
        $.ajax({
            url: "/test",
            method: "POST",
            dataType: "text",
            success: function (data) {
                $('#output').val(data)
                console.log(data)
            }
        })
    })
    $("#put").click(() => {
        $.ajax({
            url: "/test",
            method: "PUT",
            dataType: "text",
            success: function (data) {
                $('#output').val(data)
                console.log(data)
            }
        })
    })
    $("#delete").click(() => {
        $.ajax({
            url: "/test",
            method: "DELETE",
            dataType: "text",
            success: function (data) {
                $('#output').val(data)
                console.log(data)
            }
        })
    })
</script>

<jsp:include page="layout/footer.jsp" />