package study.jsp.myschool.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import study.jsp.helper.BaseController;
import study.jsp.helper.PageHelper;
import study.jsp.helper.WebHelper;
import study.jsp.myschool.dao.MyBatisConnectionFactory;
import study.jsp.myschool.model.StudentProfessorDepartment;
import study.jsp.myschool.service.StudentJoinService;
import study.jsp.myschool.service.impl.StudentJoinServiceImpl;


//Helper, Service 객체는 멤버변수 형태로 선언, logger는 상위에 있으므로 삭제
@WebServlet("/student_list.do")
public class Student_List extends BaseController{
	
	private static final long serialVersionUID = -6659079133343390198L;
	
	WebHelper web;
	SqlSession sqlSession;
	StudentJoinService studentJoinService;
	PageHelper pageHelper;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** (1) 필요한 Helper + Log4j 객체 생성하기 */
		// --> study.jsp.helper.WebHelper
		web = WebHelper.getInstance(request, response);
		StudentProfessorDepartment student = new StudentProfessorDepartment();
		
		// 검색어 파라미터 받기 + Beans 설정
		String keyword = web.getString("keyword", "");
		student.setName(keyword);
		
		// 현재 페이지 번호에 대한 파라미터 받기
		int nowPage = web.getInt("page", 1);

		/** (2) Service 객체 생성하기 --> DB접속 */
		// --> import org.apache.ibatis.session.SqlSession;
		// --> import study.jsp.myschool.dao.MyBatisConnectionFactory;
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		// --> import study.jsp.myschool.model.ProfessorJoinService;
		// --> import study.jsp.myschool.service.ProfessorJoinServiceImpl;
		studentJoinService = new StudentJoinServiceImpl(sqlSession, logger);
		
		// 전체 데이터 수 조회하기
		int totalCount = 0;
		try {
			totalCount = studentJoinService.getStudentCount(student);
		}  catch (Exception e) {
			sqlSession.close();
			web.redirect(null, e.getLocalizedMessage());
			return null;
		}
		
		// 페이지 번호에 대한 연산 수행 후 조회조건값 지정을 위한 Beans에 추가하기
		pageHelper = PageHelper.getInstance(nowPage, totalCount, 10, 5);
		student.setLimitStart(pageHelper.getLimitStart());
		student.setListCount(pageHelper.getListCount());
		
		/** (3) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<StudentProfessorDepartment> list = null;
		try {
			list = studentJoinService.getStudentJoinList(student);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		//View에게 전달할 데이터를 request 객체에 담는다.
		request.setAttribute("list", list);
		request.setAttribute("pageHelper", pageHelper);
		request.setAttribute("keyword", keyword);
		
		//사용하고자 하는 View의 이름을 리턴
		return "stud_list";
	}
	
}
