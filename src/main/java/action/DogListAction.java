package action;

import java.util.ArrayList;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 오늘 본 상품 이미지를 저장할 ArrayList 객체 생성
		ArrayList<String> todayImageList = new ArrayList<String>();
		Cookie[] cookieArray = request.getCookies();
		//요청에서 넘어온 쿠키,즉 오늘 본 이미지의 이름을 요소로 추가함
		if(cookieArray != null){
			for (int i = 0; i < cookieArray.length; i++) {
				if(cookieArray[i].getName().startsWith("today")){
					todayImageList.add(cookieArray[i].getValue());
				}
			}
		}
		
		DogListService dogListService = new DogListService();
		ArrayList<Dog> dogList = dogListService.getDogList();
		request.setAttribute("dogList", dogList);
		request.setAttribute("todayImageList", todayImageList);
		ActionForward forward = new ActionForward("dogList.jsp", false);
		
		return forward;
	}
	
}
