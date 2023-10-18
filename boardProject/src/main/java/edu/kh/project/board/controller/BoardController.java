package edu.kh.project.board.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.service.BoardService;
import oracle.jdbc.proxy.annotation.GetCreator;

@SessionAttributes({"loginMember"})
@RequestMapping("/board")
@Controller
public class BoardController {
	@Autowired
	private BoardService service;

	/* 목록 조회 : /board/1?cp=1 ( cp : current Page(현재 페이지) )
	 * 상세 조회 : /board/1/1500?cp=1
	 * 
	 * << 컨트롤러 따로 생성 >> 
	 * 1) 삽입 : /board2/1/insert
	 * 2) 수정 : /board2/1/1500/update
	 * 3) 삭제 : /board2/1/1500/delete
	 * 
	 */
	
	
	/* ******************** @PathVariable 사용 시 문제점과 해결법 *********************
	 * 
	 * 문제점 : 요청 주소와 @PathVariable로 가져다 쓸 주소의 레벨이 같다면 (ex) board/1 , board/이거)
	 * 			구분하지 않고 모두 매핑되는 문제가 발생
	 * 
	 * 해결방법 : @PathVariable 지정 시 정규 표현식 사용 
	 * {키:정규표현식}
	 * 
	 * 
	 * @PathVariable : URL 경로에 있는 값을 매개변수로 이용할 수 있게하는 어노테이션
	 * 	+ request scope에 세팅
	 * 
	 * 
	 * 	ex)   / board/1      /board?code=1
	 * 	사용하는 용도의 차이ㅏ
	 * 
	 *  - 자원(resource) 구분 / 식별
	 *  ex) git.com/eunseo
	 *  ex) github.com/testUser
	 *  ex) /board/1 -> 1번 (공지사항) 게시판
	 *  ex) /borad/2 -> 2번 (자유 게시판) 게시판
	 *  
	 *  query string을 사용하는 경우
	 *  * 검색 , 정렬, 필터링
	 *  ex) search.naver.com?query=날씨
	 *  ex) search.naver.com?query=종로맛집
	 *  ==> URL은 동일한데 검색한 내용만 다름
	 *  
	 *  ex) /board2/insert?code=1
	 *  ex) /board2/insert?code=2
	 *  --> 삽입이라는 공통된 동작 수행
	 *  	단, code에 따라 어디에 삽입할지 지정(필터링)
	 *  
	 *  ex) /board/list?order=recent (최신순)
	 *  ex) /board/list?order=most (인기순)
	 * 
	 * 
	 */
	
	// 게시글 목록 조회 
	@GetMapping("/{boardCode:[0-9]+}")  // 정규표현식 : 1자리 이상 숫자 
	public String selectBoardList(@PathVariable("boardCode") int boardCode, 
								  @RequestParam(value="cp", required=false, defaultValue = "1") int cp /*현재 페이지*/,
								  Model model) {
		
		// boardCode 확인
//		System.out.println("boardCode : " + boardCode);
		
		
		// 게시글 목록 조회 서비스 호출
		Map<String, Object> map = service.selectBoardList(boardCode, cp);
		
		// 조회 결과를 request scope에 세팅 후 forward
		model.addAttribute("map", map);
		
		return "board/boardList";
	}
	
	
	// @PathVariable : 주소에 지정된 부분을 변수에 저장 + request scope 저장
	
	// 게시글 상세 조회
	// PathVariable 사용
	@GetMapping("/{boardCode}/{boardNo}")
	public String boardDetail(
						@PathVariable("boardCode") int boardCode,
						@PathVariable("boardNo") int boardNo,
						Model model, // 데이터 전달용 객체 
						RedirectAttributes ra // 리다이렉트 시 데이터 전달 객체
			) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardCode", boardCode);
		map.put("boardNo", boardNo);
		
		
		
		// 게시글 상세 조회 서비스 호출
		Board board = service.selectBoardList(map);
		
		// null이 나오는 경우
		// -> 주소창에 게시글 번호를 아무거나 넣은 경우 
		// -> 삭제된 게시글인 경우
		
		String path = null;
		
		if(board != null) { // 조회 결과가 있을 경우
			
			// 좋아요
			
			// 로그인 상태인 경우
				// 회원번호를 얻어와야 함.
				// 좋아요 여부 확인 서비스 호출
				// 결과값을 통해 분기처리
					// 누른적이 있을 경우 처리 
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			path = "board/boardDetail"; // forward 할 jsp 경로
			
			model.addAttribute("board",board);
			
		}else { // 조회 결과가 없을 경우
			
			path = "redirect:/board/" + boardCode;
			// 게시판 첫 페이지로 리다이렉트 
			ra.addFlashAttribute("message", "해당 게시글이 존재하지 않습니다.");
		}
		
		
		return path;
	}
	
	
	
	
}
