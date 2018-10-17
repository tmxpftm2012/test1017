package study.jsp.myschool.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.Logger;

import study.jsp.myschool.model.Department;
import study.jsp.myschool.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService {

	/** 처리 결과를 기록할 Log4J 객체 생성 */
	// --> import org.apache.logging.log4j.Logger;
	Logger logger;

	/** MyBatis */
	// --> import org.apache.ibatis.session.SqlSession
	SqlSession sqlSession;

	/** 생성자를 통한 객체 생성 */
	public DepartmentServiceImpl(SqlSession sqlSession, Logger logger) {
		this.sqlSession = sqlSession;
		this.logger = logger;
	}

	@Override
	public List<Department> getDepartmentList(Department department) throws Exception {
		List<Department> result = null;
		
		try {
			result = sqlSession.selectList("DepartmentMapper.selectDepartmentList", department);
			if (result == null) {
				throw new NullPointerException();
			}
		} catch (NullPointerException e) {
			throw new Exception("조회된 데이터가 없습니다.");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			throw new Exception("데이터 조회에 실패했습니다.");
		}
		
		return result;
	}

	@Override
	public void addDepartment(Department department) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editDepartment(Department department) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDepartment(Department department) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department getDepartmentItem(Department department) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDepartmentCount(Department department) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}
