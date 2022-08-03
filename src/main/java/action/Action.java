package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vo.ActionForward;


//ActionŬ�������� ������ Action �������̽� ����
public interface Action {
	//�� ��û�� ó���ϰ� �����ϱ����� request , response ���� ó��
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception;
}	
