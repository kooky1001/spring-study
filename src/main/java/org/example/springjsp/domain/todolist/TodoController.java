package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
		@Parameter(name = "category", description = "할 일의 카테고리 id", example = "1", required = true)
	})
	@ApiResponses({
		@ApiResponse(responseCode = "201")
	})
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Todo save(@RequestBody Todo todo) {
		if (todo.getContent() == null || todo.getContent().isBlank() || todo.getCategory() == null) {
			throw new IllegalArgumentException("내용 및 카테고리는 필수입니다.");
		}
		return todoService.save(todo);
	}

	// 날짜별 조회
	// @ResponseBody
	// @GetMapping("list")
	public List<Todo> listByDate(@RequestParam LocalDate toDate) {
		return todoService.findAll(toDate);
	}

	@Operation(summary = "할 일 조회", description = "카테고리별 할 일을 조회")
	@Parameter(name = "category", description = "카테고리 id", example = "1", in = ParameterIn.PATH)
	@GetMapping("{id}")
	public List<Todo> listByCategory(@PathVariable("id") Long category) {
		return todoService.findAllByCategory(category);
	}

	// 완료목록만 조회
	// @GetMapping("complete")
	public List<Todo> listBycomplete() {
		return todoService.findAllByComplete();
	}

	@Operation(summary = "할 일 체크", description = "완료 또는 취소 시 할 일을 체크")
	@Parameters({
		@Parameter(name = "id", description = "할 일 id", example = "1", in = ParameterIn.PATH),
		@Parameter(name = "completed", description = "체크여부", example = "true")
	})
	@PutMapping("{id}/checked")
	public Todo check(@PathVariable Long id, @RequestBody Todo todo) {
		return todoService.check(id, todo.isCompleted());
	}

	@Operation(summary = "할 일 삭제", description = "할 일을 리스트에서 삭제")
	@Parameter(name = "id", description = "할 일 id", example = "1", in = ParameterIn.PATH)
	@DeleteMapping("{id}")
	public Todo delete(@PathVariable Long id) {
		Todo todo = todoService.findById(id);
		todoService.delete(id);
		return todo;
	}

	@Operation(summary = "할 일 수정", description = "이미 저장된 할 일의 내용을 변경")
	@Parameters({
		@Parameter(name = "id", description = "할 일 id", example = "1", in = ParameterIn.PATH),
		@Parameter(name = "content", description = "변경될 내용", example = "변경될 할 일 내용입니다.")
	})
	@PutMapping("{id}")
	public Todo update(@PathVariable Long id, @RequestBody Todo updateParam) {
		return todoService.update(id, updateParam.getContent());
	}

}
