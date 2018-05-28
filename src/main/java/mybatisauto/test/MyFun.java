package mybatisauto.test;



public class MyFun {

	public static void main(String[] args) {
		try {
			MybatisDataSoruce mds=new MybatisDataSoruce("mybatisauto/test/run-mybatis-config.xml");
			mds.openSession();
			/*IBaseMyBatisDao<GysBean, GysInBean, GysOutBean> dao=mds.getDao(IGysDao.class);
			List<GysOutBean> list=dao.selectList(new GysInBean());
			for(GysOutBean g:list){
				System.out.println("id:"+g.getId()+";roleName:"+g.getRoleName()+";rn:"+g.getRn());
			}*/
			mds.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
