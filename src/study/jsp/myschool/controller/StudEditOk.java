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

@WebServlet("/prof_edit_ok.do")
public class StudEditOk extends BaseController{
	private static final long serialVersionUID = -3020413062902502546L;

	WebHelper web;
	SqlSession sqlSession;
	StudentService studentService;
	
	@Override
	public String doRun(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/** (1) 필요한 Helper + Log4j 객체 생성하기 */
		// --> study.jsp.helper.WebHelper
		web = WebHelper.getInstance(request, response);
		
		/** (2) 입력값 전달받기 */
		// input 태그의 name속성에 명시된 값을 사용한다.
		String name = web.getString("name");
		String userid = web.getString("userid");
		int grade = web.getInt("grade");
		String idnum = web.getString("idnum");
		String birthdate = web.getString("birthdate");
		String tel = web.getString("tel");
		int height = web.getInt("height");
		int weight = web.getInt("weight");
		int deptno = web.getInt("deptno");
		int profno = web.getInt("profno");
		
		// 전달 받은 파라미터는 로그로 값을 확인하는 것이 좋다.
		logger.debug("name=" + name);
		logger.debug("userid=" + userid);
		logger.debug(grade);
		logger.debug(idnum);
		logger.debug(birthdate);
		logger.debug(tel);
		logger.debug(height);
		logger.debug(weight);
		logger.debug(deptno);
		logger.debug(profno);
		
		/** (3) 필수항목에 대한 입력 여부 검사하기 */
		if (name == null) {
			web.redirect(null, "이름을 입력하세요.");
			return null;
		}
		
		if (userid == null) {
			web.redirect(null, "아이디를 입력하세요.");
			return null;
		}
		if (grade == 0) {
			web.redirect(null, "학년을 입력하세요.");
			return null;
		}
		if (birthdate == null) {
			web.redirect(null, "생년월일을 입력하세요.");
			return null;
		}
		if (tel == null) {
			web.redirect(null, "전화번호를 입력하세요.");
			return null;
		}
		if (height == 0) {
			web.redirect(null, "키를 입력하세요.");
			return null;
		}
		if (weight == 0) {
			web.redirect(null, "몸무게를 입력하세요.");
			return null;
		}
		if (deptno == 0) {
			web.redirect(null, "학과이름을 입력하세요.");
			return null;
		}
		if (profno == 0) {
			web.redirect(null, "교수이름을 입력하세요.");
			return null;
		}
		
		/** (4) 저장을 위한 JavaBeans 구성하기 */
		// --> study.jsp.myschool.model.Professor
		Student student = new Student();
		student.setName(name);
		student.setUserid(userid);
		student.setGrade(grade);
		student.setIdnum(idnum);
		student.setBirthdate(birthdate);
		student.setTel(tel);
		student.setHeight(height);
		student.setWeight(weight);
		student.setDeptno(deptno);
		student.setProfno(profno);
		
		/** (5) Service 객체 생성하기 --> DB접속 */
		sqlSession = MyBatisConnectionFactory.getSqlSession();
		studentService = new StudentServiceImpl(sqlSession, logger);
		
		/** (6) Service를 통한 SQL 수행 */
		try {
			studentService.editStudent(student);
		} catch (Exception e) {
			web.redirect(null, e.getLocalizedMessage());
			return null;
		} finally {
			sqlSession.close();
		}
		
		/** (7) 결과를 확인하기 위한 페이지로 이동하기 */
		// 저장결과를 확인하기 위해서 상세페이지로 이동한다.
		// 상세페이지에서 읽어올 데이터를 식별하기 위해서는 PrimaryKey값을 전달해야 한다.
		String url = request.getContextPath()+"/stud_view.do?studno=" + student.getStudno();
		web.redirect(url, "수정되었습니다.");
		
		return null;
	}

}
