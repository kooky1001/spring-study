package org.example.springjsp.domain.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	@GetMapping("/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Board board = boardService.findOne(id);
		model.addAttribute("board", board);
		return "board-detail";
	}

	@PostMapping
	public String save(@ModelAttribute Board board) {
		Board savedBoard = boardService.save(board);
		//        return "redirect:/";
		return "redirect:/board/" + savedBoard.getId();
	}

	@GetMapping("/{id}/update")
	public String updatePage(@PathVariable("id") Long id, Model model) {
		Board board = boardService.findOne(id);
		model.addAttribute("board", board);
		return "board-update";
	}

	//    @PutMapping("/{id}/update")
	@PostMapping("/{id}/update")
	public String update(@ModelAttribute Board updateParam) {
		boardService.update(updateParam.getId(), updateParam);
		return "redirect:/board/" + updateParam.getId();
	}

	//    @DeleteMapping("/{id}/delete")
	@PostMapping("/{id}/delete")
	public String delete(@PathVariable("id") Long id) {
		boardService.delete(id);
		return "redirect:/";
	}
}
