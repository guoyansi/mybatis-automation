package test;

import java.util.ArrayList;
import java.util.List;

import dao.ITdSysUserDao;
import bean.TdSysUserBean;
import bean.in.TdSysUserInBean;
import bean.out.TdSysUserOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import table.DataSource;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			
			/*IBaseMyBatisByIdDao<GysBean> dao=ds.getDao(IGysDao.class);
			GysBean t=new GysBean();
			t.setNote("noteTest");
			t.setRoleName("roleTes");
			GysBean t1=new GysBean();
			t1.setNote("noteTest1");
			t1.setRoleName("roleTes1");
			GysBean t2=new GysBean();
			t2.setNote("noteTest2");
			t2.setRoleName("roleTes2");
			List<GysBean> list=new ArrayList<GysBean>();
			list.add(t);
			list.add(t1);
			list.add(t2);
			dao.batchInsertGetId(list);
			//System.err.println("返回id:"+t.getId());
			ds.commit();
			for(GysBean g:list){
				System.out.println("主键："+g.getId());
			}
			IBaseMyBatisDao<GysBean, GysInBean, GysOutBean> dao1=ds.getDao(IGysDao.class);
			List<GysOutBean> list1=dao1.selectList(new GysInBean());
			for(GysOutBean g:list1){
				System.out.println("主键："+g.getId()+";name:"+g.getRoleName()+";note:"+g.getNote()+";");
			}*/
			System.out.println("======delete");
			IBaseMyBatisDao<TdSysUserBean, TdSysUserInBean, TdSysUserOutBean> dao=ds.getDao(ITdSysUserDao.class);
			List<TdSysUserBean> list=new ArrayList<TdSysUserBean>();
			TdSysUserBean t1=new TdSysUserBean();
			t1.setSid("c");
			t1.setUserName("g");
			
			TdSysUserBean t2=new TdSysUserBean();
			t2.setSid("d");
			t2.setUserName("g");
			
			TdSysUserBean t3=new TdSysUserBean();
			t3.setSid("e");
			t3.setUserName("g");
			
			list.add(t1);
			list.add(t2);
			list.add(t3);
			dao.batchInsert(list,null);
			ds.commit();
			for(TdSysUserBean t:list){
				System.out.println("主键："+t.getSid());
			}
			/*List<TdSysUserOutBean> ls=dao.selectList(new TdSysUserInBean());
			for(){}*/
			
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
