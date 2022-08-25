/**
 * board.js
 */
 
$(document).on("click", "a.deleteBtn", (e) => {
//$("a.deleteBtn").click((e)=>{
	e.preventDefault();
	var deleteBtn = e.target;
	var url = e.target.getAttribute("href");
	$.ajax({
		type: "delete",
		url: url,
		dataType: "json",
		error: (request, status, error) => {
			alert(status);
		},
		success: (data) => {
			if (!data.valid){
				alert(data.message);
			} else {
				$(deleteBtn).closest("div.replyBox").remove();
			}
		}
	})
});
	
document.querySelector("form[name='replyForm']").querySelector("button").onclick = (e) => {
	e.preventDefault();
	let form = document.querySelector("form[name='replyForm']");
	let formData = serialize(form);
	let url = form.getAttribute("action");
	
	fetch(url, {
		method: "POST",
  		headers: {
    		"Content-Type": "application/json"
  		},
  		body: JSON.stringify(formData)
	})
	.then((res) => res.json())
	.then((data) => {
		console.log(data);
		alert(JSON.stringify(data));
	}).catch(error => alert(error));
}

/**
$("form[name='replyForm'").find("button").click((e)=>{
	e.preventDefault();
	var form = $("form[name='replyForm']");
	var queryString = form.serialize();
	var url = form.attr("action");
	$.ajax({
		type: "post",
		url: url,
		data: queryString,
		error: (request, status, error) => {
			alert(status);
		},
		success: (data, status) => {
			if(data === '' || data === undefined) {
				alert("Sorry! It's failed");
				return;
			}
			//var replyTemplate = $("#replyTemplate").html();
			var template = makeTemplate(data.content, data.updateDate, data.user.name, data.board.id, data.id);
			$("#reply").append(template);
			$("form[name='replyForm']").find("textarea[name='content']").val('');
		}
	});
});
 */
	
function makeTemplate(content, updateDate, username, boardId, replyId) {
	let template = 
		`<div class="replyBox">
		<br>
		<p class="border rounded px-2 py-2 pb-5" id="content">${content}</p>
		<div class="justify-content-end d-flex my-2">
			<div class="justify-content-end d-flex mb-2">
				<span class="me-3">${updateDate}</span><strong class="text-secondary me-3">${username}</strong>
			</div>
			<a href="/board/${boardId}/reply/${replyId}/delete" class="btn btn-secondary deleteBtn">삭제</a>
		</div>
		</div>`
	return template;
}