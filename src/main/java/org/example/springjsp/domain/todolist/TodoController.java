package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "TodoList", description = "투두리스트 관련 Api")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todolist")
@RestController
public class TodoController {

	private final TodoService todoService;

	@Operation(summary = "할 일 저장", description = "할 일을 투두리스트에 추가")
	@Parameters({
		@Parameter(name = "content", description = "할 일 내용", example = "할 일 내용입니다.", required = true),
		@Parameter(name = "category", description = "할 일의 카테고리", example = "1", required = true)
	})
	@PostMapping
	public Todo save(@RequestParam String content, @RequestParam Long category) {
		Todo todo = Todo.builder()
			.content(content)
			.category(category)
			.build();
		return todoService.save(todo);
	}

	// 날짜별 조회
	// @ResponseBody
	// @GetMapping("list")
	public List<Todo> listByDate(@RequestParam LocalDate toDate) {
		return todoService.findAll(toDate);
	}

	@Operation(summary = "할 일 조회", description = "카테고리별 할 일을 조회")
	@Parameter(name = "category", description = "카테고리 id", example = "1")
	@GetMapping("list")
	public List<Todo> listByCategory(@RequestParam Long category) {
		List<Todo> todoList = todoService.findAllByCategory(category);
		return todoList;
	}

	// @GetMapping("complete")
	public List<Todo> listBycomplete() {
		List<Todo> todoList = todoService.findAllByComplete();
		return todoList;
	}

	@Operation(summary = "할 일 체크", description = "완료 또는 취소 시 할 일을 체크")
	@Parameters({
		@Parameter(name = "id", description = "할 일 id", example = "1"),
		@Parameter(name = "completed", description = "체크여부", example = "true")
	})
	@PutMapping("checked")
	public Todo check(@RequestParam Long id, @RequestParam Boolean completed) {
		return todoService.check(id, completed);
	}

	@Operation(summary = "할 일 삭제", description = "할 일을 리스트에서 삭제")
	@Parameter(name = "id", description = "할 일 id", example = "1")
	@DeleteMapping
	public Todo delete(@RequestParam Long id) {
		Todo todo = todoService.findById(id);
		todoService.delete(id);
		return todo;
	}

	@Operation(summary = "할 일 수정", description = "이미 저장된 할 일의 내용을 변경")
	@Parameters({
		@Parameter(name = "id", description = "할 일 id", example = "1"),
		@Parameter(name = "content", description = "변경될 내용", example = "변경될 할 일 내용입니다.")
	})
	@PutMapping
	public Todo update(@RequestParam Long id, @RequestParam String content) {
		return todoService.update(id, content);
	}

}
