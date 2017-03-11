package action;

import java.text.SimpleDateFormat;
import java.util.List;

import entity.Students;

import service.StudentsDAO;
import service.impl.StudentsDAOImpl;

//ѧ��Action��
public class StudentsAction extends SuperAction{

	//��ѯ����ѧ������
	public String query(){
		StudentsDAO sdao=new StudentsDAOImpl();
		List<Students> list=sdao.queryAllStudents();
		//�Ž�session��
		if(list!=null&&list.size()>0){
			session.setAttribute("students_list", list);
			//return "Students_query_success";
		}
		return "query_success";
	}
	//ɾ��ѧ������
	public String delete(){
		StudentsDAO sdao=new StudentsDAOImpl();
		String sid=request.getParameter("sid");
		sdao.deleteStudents(sid);
		return "delete_success";
		
	}
	//����ѧ��
	public String add() throws Exception{
		Students s=new Students();
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao=new StudentsDAOImpl();
		sdao.addStudents(s);
		return "add_success";
		
	}
	//�޸�ѧ����Ϣ����
	public String modify(){
		
		//��ô��ݹ�����ѧ�����
		String sid=request.getParameter("sid");
		StudentsDAO sdao=new StudentsDAOImpl();
		Students s=sdao.queryStudentsById(sid);
		//�����ڻỰ��
		session.setAttribute("modify_students",s);
		return "modify_success";
	}
	//�����޸�ѧ����Ϣ����
	public String save() throws Exception{
		Students s=new Students();
		s.setSid(request.getParameter("sid"));
		s.setSname(request.getParameter("sname"));
		s.setGender(request.getParameter("gender"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		s.setBirthday(sdf.parse(request.getParameter("birthday")));
		s.setAddress(request.getParameter("address"));
		StudentsDAO sdao=new StudentsDAOImpl();
		sdao.updateStudents(s);
		return "save_success";
	}
	public String find(){
		String sid=request.getParameter("sid");
		StudentsDAO sdao=new StudentsDAOImpl();
		Students s=sdao.queryStudentsById(sid);
		//���浽�Ự��
		session.setAttribute("modify_students", s);
		return "find_success";
	}
}




