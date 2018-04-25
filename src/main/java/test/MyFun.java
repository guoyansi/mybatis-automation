package test;

import java.util.List;

import bean.ABean;
import dao.IADao;
import table.DataSource;
import table.ITable;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			ITable<ABean> dao=ds.getDao(IADao.class);
			ABean aBean=new ABean();
			aBean.setSqlWhere("1=1");
			//aBean.setSqlLimit(false);
			List<ABean> list=dao.selectList(aBean);
			for(ABean a:list){
				System.out.println(a.getF_tinytext());
			}
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
