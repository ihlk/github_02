package entity;

import java.util.Date;
import java.util.EnumSet;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import org.junit.Test;

public class TestStudent {

	@Test
	public void testSchemaExport(){
		//�������ö���
		Configuration config=new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//����sessionFactory
		SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
		//����session����
		Session session=sessionFactory.getCurrentSession();
		//����SchemaExport����
		SchemaExport export=new SchemaExport(config);
		export.create(true,true);
	}
	@Test
	public void testSaveStudents(){
		//�������ö���
		Configuration config=new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//����sessionFactory
		SessionFactory sessionFactory=config.buildSessionFactory(serviceRegistry);
		//����session����
		Session session=sessionFactory.getCurrentSession();
		//�����������
		Transaction tx=session.beginTransaction();
		Students s1=new Students("001","�޼�","��",new Date(),"�䵱");
		Students s2=new Students("002","ҹ��","��",new Date(),"����");
		Students s3=new Students("003","��ǳ","Ů",new Date(),"����");
		session.save(s1);
		session.save(s2);
		session.save(s3);
		tx.commit();
		sessionFactory.close();
	}
}
