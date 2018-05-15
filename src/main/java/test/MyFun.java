package test;

import java.util.List;

import table.DataSource;
import table.ITable;
import bean.in.GysInBean;
import bean.in.TdSysUserInBean;
import bean.out.GysOutBean;
import bean.out.TdSysUserOutBean;
import dao.IGysDao;
import dao.ITdSysUserDao;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			ITable<GysInBean, GysOutBean> dao=ds.getDao(IGysDao.class);
			GysInBean t=new GysInBean();
			t.setSqlWhere("age=20");
			int count=dao.getCount(t);
			System.out.println("总数："+count);
			ds.commit();
			List<GysOutBean> list=dao.selectList(t);
			for(GysOutBean g:list){
				System.out.println("序号："+g.getRowNum()+";主键："+g.getId()+";name:"+g.getName()+";age:"+g.getAge()+";weight"+g.getWeight());
			}
			System.out.println("==============");
			ITable<TdSysUserInBean,TdSysUserOutBean> userDao=ds.getDao(ITdSysUserDao.class);
			TdSysUserInBean userbean=new TdSysUserInBean();
			userbean.setUserCode("admin");
			//userbean.setSqlWhere("user_code='admin'");
			count=userDao.getCount(userbean);
			System.out.println("总数："+count);
			//aBean.setSqlLimit(false);
			List<TdSysUserOutBean> userList=userDao.selectList(userbean);
			for(TdSysUserOutBean a:userList){
				System.out.println("序号："+a.getRowNum()+";name:"+a.getUserName());
			}
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
