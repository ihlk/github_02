package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;

import entity.Students;
import service.StudentsDAO;

public class StudentsDAOImpl implements StudentsDAO{

	//��ѯ����ѧ������
	public List<Students> queryAllStudents() {
		
		Transaction tx=null;
		List<Students> list=null;
		String hql="";
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();//��������
			hql="from Students";
			Query query=session.createQuery(hql);
			list=query.list();
			session.close();//�ǵùر�����
			return list;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.rollback();
			return list;
			
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
	}

	//����ѧ�Ų�ѯѧ������
	public Students queryStudentsById(String sid) {
		
		Transaction tx=null;
		Students s=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();//��������
			s=(Students)session.get(Students.class, sid);
			tx.commit();
			
			return s;
		}catch(Exception ex)
		{
			ex.printStackTrace();
			tx.rollback();
			return s;
			
		}finally{
			if(tx!=null){
				tx=null;
			}
			
		}
	}

	//����ѧ������
	public boolean addStudents(Students s) {
		
		s.setSid(getNewSid());
		Transaction tx=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.save(s);
			tx.commit();//�����ύ
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();//����ع�
			return false;
		}finally{
			
			if(tx!=null){
				tx=null;
			}
		}
	}

	//����ѧ������
	public boolean updateStudents(Students s) {
		
		Transaction tx=null;
		
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			session.update(s);
			tx.commit();//�����ύ
			
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();//����ع�
			return false;
		}finally{
			
			if(tx!=null){
				tx=null;
			}
		}
	}

	//ɾ��ѧ������
	public boolean deleteStudents(String id) {
		Transaction tx=null;
		//String hql="";
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			Students s=(Students)session.get(Students.class, id);
			session.delete(s);
			tx.commit();
			return true;
			
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return false;
		}finally{
			if(tx!=null){
				tx=null;
			}
		}
		
	}
	//����ѧ����ѧ��
	public String getNewSid(){
		Transaction tx=null;
		String hql="";
		String sid=null;
		try{
			Session session=MyHibernateSessionFactory.getSessionFactory().getCurrentSession();
			tx=session.beginTransaction();
			//��õ�ǰѧ���������
			hql="select max(sid) from Students";
			Query query=session.createQuery(hql);
			sid=(String)query.uniqueResult();
			if(sid==null||"".equals(sid)){
				//��һ��Ĭ�ϵ������
				sid="001";
			}else{
				//String temp=sid.substring(1);//ȡ����λ
				String temp="";
				int i=Integer.parseInt(sid);//תΪ����
				i++;
				//�ٻ�ԭΪ�ַ���
				temp=String.valueOf(i);
				int len=temp.length();
				//�չ���λ
				for(int j=0;j<3-len;j++){
					temp="0"+temp;
				}
				//sid="s"+temp;
				sid=temp;
			}
	
			tx.commit();
			return sid;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
			return null;
		}finally{
			if(tx!=null){
				tx=null;
			}
			
		}
	}

	
}
