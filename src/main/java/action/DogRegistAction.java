package action;

import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

public class DogRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DogRegistService DogRegistService = new DogRegistService();
		String realFolder = "";
		//파일 업로드될 서버 상의 물리적인 경로
		
		String saveFolder = "/images";
		String encType = "UTF-8";
		int maxSize = 5*1024*1024;
		//한번에 업로드 할 수 있는 파일의 크기
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		//요청에 전송되더온 파일을 서버 상에 업로드
		MultipartRequest multi = new MultipartRequest(request,
					realFolder, maxSize, encType,
					new DefaultFileRenamePolicy());
		//서버상에 업로드된 파일 이름을 얻어옴
		String image = multi.getFilesystemName("image");
		//전송된 파라미터들을 등록할 Dog 객체를 생성하는 부분
		Dog dog = new Dog(
				0, 
				multi.getParameter("kind"), 
				Integer.parseInt(multi.getParameter("price")), 
				image, 
				multi.getParameter("nation"), 
				Integer.parseInt(multi.getParameter("height")), 
				Integer.parseInt(multi.getParameter("weight")), 
				multi.getParameter("content"), 
				0);
		boolean isRegistSuccess = DogRegistService.registDog(dog);
		ActionForward forward = null;
		
		//개 상품 등록요청이 성공하면 리다이렉트 방식으로 개 목록보기를 요청함.
		if(isRegistSuccess){
			//컨트롤러의 action.execute(request, response); 부분으로 반환되어 포워딩 처리
			forward = new ActionForward("dogList.dog", true);
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		
		return forward;
	}

}
