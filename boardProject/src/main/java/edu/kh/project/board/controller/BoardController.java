package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	
	
	
	
}
