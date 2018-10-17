package study.jsp.myschool.model;

/**
 * 하나의 테이블 구조를 표현하기위한 클래스. 
 * 멤버변수는 테이블의 컬럼이름이 된다. 
 * ex) 테이블의 데이터타입이 INT인 경우 멤버변수도 int
 *     그 밖의 경우는 모두 String으로 설정
 */
public class Student {
	private int studno; // 학생번호
	private String name; // 학생이름
	private String userid; // 아이디
	private int grade; // 학년
	private String idnum; // 주민번호
	private String birthdate; // 생년월일
	private String tel; // 전화번호
	private int height; // 키
	private int weight; // 몸무게
	private int deptno; // 소속학과번호 - department 테이블을 참조하는 키
	private int profno; // 담당교수번호 - professor 테이블을 참조하는 키
	public int getStudno() {
		return studno;
	}
	public void setStudno(int studno) {
		this.studno = studno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public int getProfno() {
		return profno;
	}
	public void setProfno(int profno) {
		this.profno = profno;
	}
	@Override
	public String toString() {
		return "Student [studno=" + studno + ", name=" + name + ", userid=" + userid + ", grade=" + grade + ", idnum="
				+ idnum + ", birthdate=" + birthdate + ", tel=" + tel + ", height=" + height + ", weight=" + weight
				+ ", deptno=" + deptno + ", profno=" + profno + "]";
	}
	
}
