package study.jsp.myschool.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import study.jsp.helper.BaseController;
import study.jsp.helper.WebHelper;
import study.jsp.myschool.dao.MyBatisConnectionFactory;
import study.jsp.myschool.model.StudentProfessorDepartment;
import study.jsp.myschool.service.StudentJoinService;
import study.jsp.myschool.service.impl.StudentJoinServiceImpl;


@WebServlet("/stud_view.do")
public class StudView extends BaseController {

	private static final long serialVersionUID = 804665786348666697L;

	// --> study.jsp.helper.WebHelper
	WebHelper web;
	SqlSession sqlSession;
	StudentJoinService studentJoinService;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** (1) 이전 페이지에서 전달된 교수번호 받기 */
		web = WebHelper.getInstance(request, response);
		
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if (studno == 0) {
			web.redirect(null, "학생번호가 없습니다.");
			return null;
		}
		
		/** (2) Beans 생성 */
		// MyBatis의 WHERE절에 사용할 값을 담은 객체
		StudentProfessorDepartment student = new StudentProfessorDepartment();
		student.setStudno(studno);
		
		/** (3) Service 객체 생성하기 --> DB접속 */
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		studentJoinService = new StudentJoinServiceImpl(sqlSession, logger);
		
		/** (4) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		StudentProfessorDepartment item = null;
		try {
			item = studentJoinService.getStudentJoinItem(student);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		request.setAttribute("item", item);
		
		/** (5) 사용할 view의 이름을 리턴 */
		return "stud_view";
	}

}
