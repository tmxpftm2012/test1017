package study.jsp.myschool.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import study.jsp.helper.BaseController;
import study.jsp.helper.WebHelper;
import study.jsp.myschool.dao.MyBatisConnectionFactory;
import study.jsp.myschool.model.Department;
import study.jsp.myschool.model.Professor;
import study.jsp.myschool.model.Student;
import study.jsp.myschool.service.DepartmentService;
import study.jsp.myschool.service.ProfessorService;
import study.jsp.myschool.service.StudentService;
import study.jsp.myschool.service.impl.DepartmentServiceImpl;
import study.jsp.myschool.service.impl.ProfessorServiceImpl;
import study.jsp.myschool.service.impl.StudentServiceImpl;


@WebServlet("/stud_edit.do")
public class StudEdit extends BaseController{
	private static final long serialVersionUID = -5405601905118992243L;
	
	WebHelper web;
	SqlSession sqlSession;
	StudentService studentService;
	ProfessorService professorService;
	DepartmentService departmentService;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** (1) Helper,Logger객체 생성 + 이전 페이지에서 전달된 교수번호 받기 */
		// --> import study.jsp.helper.WebHelper
		web = WebHelper.getInstance(request, response);
		
		int studno = web.getInt("studno");
		logger.debug("studno=" + studno);
		
		if (studno == 0) {
			web.redirect(null, "학생번호가 없습니다.");
			return null;
		}
		
		/** (2) 수정 대상을 조회하기 위한 Beans 생성 */
		// MyBatis의 WHERE절에 사용할 값을 담은 객체
		// --> import study.jsp.myschool.model.Professor
		Student student = new Student();
		student.setStudno(studno);
		
		/** (3) Service 객체 생성하기 --> DB접속 */
		// --> import org.apache.ibatis.session.SqlSession;
		// --> import study.jsp.myschool.dao.MyBatisConnectionFactory;
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		// --> import study.jsp.myschool.model.Professor;
		// --> import study.jsp.myschool.service.ProfessorService;
		studentService = new StudentServiceImpl(sqlSession, logger);
		// --> import study.jsp.myschool.model.DepartmentService;
		// --> import study.jsp.myschool.service.DepartmentServiceImpl;
		departmentService = new DepartmentServiceImpl(sqlSession, logger);
		professorService = new ProfessorServiceImpl(sqlSession, logger);
		
		/** (4) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		Student item = null;
		List<Department> deptList = null;
		List<Professor> profList = null;
		try {
			item = studentService.getStudentItem(student);
			deptList = departmentService.getDepartmentList(null);
			profList = professorService.getProfessorList(null);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		request.setAttribute("item", item);
		request.setAttribute("dept_list", deptList);
		request.setAttribute("prof_list", profList);
		
		return "stud_edit";
	}

}
