package controller;
// 모든 클라이언트의 요청을 받아서 제어하는 컨트롤러 클래스
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;
import action.Action;
import action.DogCartAddAction;
import action.DogCartListAction;
import action.DogCartQtyDownAction;
import action.DogCartQtyUpAction;
import action.DogCartRemoveAction;
import action.DogCartSearchAction;
import action.DogListAction;
import action.DogRegistAction;
import action.DogRegistFormAction;
import action.DogViewAction;


//확장자가 .dog 로 끝나는 url 요청이 들어오면 처리하겠다는 설정
@WebServlet("*.dog")
public class DogFrontController extends HttpServlet {
// 컨트롤러 서블릿으로 들어오는 모든 요청은 doProcess 메소드를 호출함
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//1. 요청파악
		String requestURI = request.getRequestURI();
		//요청 URL : http://localhost:8088/boardProject/boardWriteFrom.bo
		//requestURI : /boardProject/boardWriteForm.bo 반환
		
		String contextPath = request.getContextPath();
		//   /boardProject 반환
		
		String command = requestURI.substring(contextPath.length());
		Action action = null;
		ActionForward forward = null;
		
		//2.각 요청별로 비지니스로직 호출
		if(command.equals("/dogList.dog")){
			action = new DogListAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogView.dog")){
			action = new DogViewAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartAdd.dog")){
			action = new DogCartAddAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartList.dog")){
			action = new DogCartListAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartSearch.dog")){
			action = new DogCartSearchAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartRemove.dog")){
			action = new DogCartRemoveAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartQtyUp.dog")){
			action = new DogCartQtyUpAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogCartQtyDown.dog")){
			action = new DogCartQtyDownAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogRegist.dog")){
			action = new DogRegistAction();
			//프로젝트명 + 기능 + 형태(?)
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/dogRegistForm.dog")){
			action = new DogRegistFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//3. 포워딩
		if(forward !=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				
			}
		}
		
	}
	
}
