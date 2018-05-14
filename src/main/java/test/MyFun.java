package test;

import table.DataSource;

public class MyFun {

	public static void main(String[] args) {
		try {
			DataSource ds=new DataSource("test/run-mybatis-config.xml");
			ds.openSession();
			/*ITable<AInBean,AOutBean> dao=ds.getDao(IADao.class);
			AInBean aInBean=new AInBean();
			//aBean.setSqlLimit(false);
			List<AOutBean> list=dao.selectList(aInBean);
			for(AOutBean a:list){
				System.out.println(a.getF_tinytext());
			}*/
			ds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
