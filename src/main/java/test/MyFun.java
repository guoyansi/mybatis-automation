package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.javassist.expr.NewArray;

import dao.IGysDao;
import bean.GysBean;
import bean.in.GysInBean;
import bean.out.GysOutBean;
import mybatisauto.create.IBaseMyBatisByIdDao;
import mybatisauto.create.IBaseMyBatisDao;
import table.DataSource;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			//IBaseMyBatisDao<GysBean, GysInBean, GysOutBean> dao=ds.getDao(IGysDao.class);
			//ITable<GysInBean, GysOutBean> dao=ds.getDao(IGysDao.class);
			//GysInBean t=new GysInBean();
//			t.setId(2);
//			List<Integer> idList=new ArrayList<Integer>();
//			idList.add(1);
//			idList.add(2);
			//t.setNote("a");
			//t.setSqlNoteLike(true);
			//t.setSqlNoteLeftLike(true);
			//t.setSqlNoteRightLike(true);
			//idList.add(3);
			//t.setSqlIdInList(idList);
			//t.setSqlWhere("age=20");
			//t.setSqlIdD(true);
//			t.setSqlIdX(true);
//			int count=dao.getCount(t);
//			System.out.println("总数："+count);
//			ds.commit();
//			List<GysOutBean> list=dao.selectList(t);
//			for(GysOutBean g:list){
//				System.out.println("序号："+g.getRn()+";主键："+g.getId()+";name:"+g.getRoleName()+";note:"+g.getNote()+";");
//			}
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
			
			IBaseMyBatisByIdDao<GysBean> dao=ds.getDao(IGysDao.class);
			GysBean t=new GysBean();
			t.setNote("noteTest");
			t.setRoleName("roleTes");
			//dao.insertGetId(t);
			//System.err.println("返回id:"+t.getId());
			//ds.commit();
			IBaseMyBatisDao<GysBean, GysInBean, GysOutBean> dao1=ds.getDao(IGysDao.class);
			List<GysOutBean> list=dao1.selectList(new GysInBean());
			for(GysOutBean g:list){
				System.out.println("序号："+g.getRn()+";主键："+g.getId()+";name:"+g.getRoleName()+";note:"+g.getNote()+";");
			}
			System.out.println("======delete");
			//dao.deleteById(5);
			GysBean t1=new GysBean();
			t1.setId(4);
			t1.setNote("1111");
			t1.setRoleName("33");
			dao.updateById(t1);
			ds.commit();
			list=dao1.selectList(new GysInBean());
			for(GysOutBean g:list){
				System.out.println("序号："+g.getRn()+";主键："+g.getId()+";name:"+g.getRoleName()+";note:"+g.getNote()+";");
			}
			
			
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
