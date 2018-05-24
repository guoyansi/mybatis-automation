package test;

import java.util.List;

import dao.IGysDao;
import bean.GysBean;
import bean.in.GysInBean;
import bean.out.GysOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import table.DataSource;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			IBaseMyBatisDao<GysBean, GysInBean, GysOutBean> dao=ds.getDao(IGysDao.class);
			//ITable<GysInBean, GysOutBean> dao=ds.getDao(IGysDao.class);
			GysInBean t=new GysInBean();
			//t.setSqlWhere("age=20");
			int count=dao.getCount(t);
			System.out.println("总数："+count);
			ds.commit();
			List<GysOutBean> list=dao.selectList(t);
			for(GysOutBean g:list){
				System.out.println("序号："+g.getRn()+";主键："+g.getId()+";name:"+g.getRoleName()+";note:"+g.getNote()+";");
			}
			/*System.out.println("==============");
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
			}*/
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
