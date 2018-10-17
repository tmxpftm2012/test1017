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
import study.jsp.myschool.service.DepartmentService;
import study.jsp.myschool.service.impl.DepartmentServiceImpl;


@WebServlet("/stud_add.do")
public class StudAdd extends BaseController {

	private static final long serialVersionUID = 9077056232047659354L;
	// --> study.jsp.helper.WebHelper
	WebHelper web;
	// --> import org.apache.ibatis.session.SqlSession;
	// --> import study.jsp.myschool.dao.MyBatisConnectionFactory;
	SqlSession sqlSession;
	// --> import study.jsp.myschool.model.DepartmentService;
	// --> import study.jsp.myschool.service.DepartmentServiceImpl;
	DepartmentService departmentService;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/** (1) 필요한 Helper 객체 생성하기 */
		web = WebHelper.getInstance(request, response);
		
		/** (2) Service 객체 생성하기 --> DB접속 */
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		departmentService = new DepartmentServiceImpl(sqlSession, logger);
		
		/** (3) Service를 통한 SQL 수행 */
		// 조회 결과를 저장하기 위한 객체
		List<Department> deptList = null;
		try {
			deptList = departmentService.getDepartmentList(null);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		/** (4) 데이터 조회 결과를 View에게 전달 */
		request.setAttribute("dept_list", deptList);
		
		/** (5) 사용할 view의 이름을 리턴 */
		return "stud_add";
	}

}