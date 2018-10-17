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
import study.jsp.myschool.model.Student;
import study.jsp.myschool.service.StudentService;
import study.jsp.myschool.service.impl.StudentServiceImpl;


@WebServlet("/prof_delete.do")
public class StudDelete extends BaseController{

	private static final long serialVersionUID = -4155481076427252232L;

	WebHelper web;
	SqlSession sqlSession;
	StudentService studentService;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** (1) 이전 페이지에서 전달된 교수번호 받기 */
		// --> import study.jsp.helper.WebHelper
		web = WebHelper.getInstance(request, response);
				
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if (studno == 0) {
			web.redirect(null, "학생번호가 없습니다.");
			return null;
		}
		
		/** (2) 삭제를 위한 Beans 생성 */
		// MyBatis의 WHERE절에 사용할 값을 담은 객체
		// --> import study.jsp.myschool.model.Professor
		Student student = new Student();
		student.setStudno(studno);
		
		/** (3) Service 객체 생성하기 --> DB접속 */
		// --> import org.apache.ibatis.session.SqlSession;
		// --> import study.jsp.myschool.dao.MyBatisConnectionFactory;
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		// --> import study.jsp.myschool.service.ProfessorService;
		// --> import study.jsp.myschool.service.ProfessorServiceImpl;
		studentService = new StudentServiceImpl(sqlSession, logger);
		
		/** (4) Service를 통한 SQL 수행 */
		try {
			studentService.deleteStudent(student);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		/** (5) 목록페이지로 이동 */
		web.redirect(request.getContextPath()+"/stud_list.do", "삭제되었습니다.");
		
		return null;
	}
	
	
	
}
