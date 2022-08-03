package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;


//Action클래스들이 구현할 Action 인터페이스 정리
public interface Action {
	//웹 요청을 처리하고 응답하기위한 request , response 변수 처리
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception;
}	
