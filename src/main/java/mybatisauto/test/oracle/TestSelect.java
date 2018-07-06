package mybatisauto.test.oracle;

import java.util.Date;
import java.util.List;

import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.test.MybatisDataSoruce;
import bean.TbBalShSelfScheduleBean;
import bean.in.TbBalShSelfScheduleInBean;
import bean.out.TbBalShSelfScheduleOutBean;
import dao.ITbBalShSelfScheduleDao;

/**
 * select测试
 * @author guoyansi
 *
 */
public class TestSelect {
	public static void main(String[] args)  throws Exception{
		//MybatisDataSoruce mds=new MybatisDataSoruce("run-mybatis-config.xml");
		MybatisDataSoruce mds=new MybatisDataSoruce("mybatisauto/test/run-mybatis-config.xml");
		mds.openSession();
		IBaseMyBatisDao<TbBalShSelfScheduleBean, TbBalShSelfScheduleInBean, TbBalShSelfScheduleOutBean> dao=mds.getDao(ITbBalShSelfScheduleDao.class);
		TbBalShSelfScheduleInBean gys=new TbBalShSelfScheduleInBean();
		gys.setStartDate("2018-3-2");
		gys.setSqlStartDateXd(true);
		//gys.setSqlRoleNameLike(true);
		//gys.setSqlRoleNameLeftLike(true);
		//gys.setSqlRoleNameRightLike(true);
		//gys.setSqlRoleNameD(true);
		//gys.setSqlRoleNameDd(true);
		//gys.setSqlRoleNameX(true);
		//gys.setSqlRoleNameXd(true);gys.setSqlRoleNameInList(new ArrayList<String>());
		//gys.setSqlOrderBy("id"+SqlE.desc.getValue());
//		gys.setSqlLimit(true);
//		gys.setSqlCurrentPage(1);
//		gys.setSqlPageSize(10);
		//gys.setRoleName("7");
//		List<Integer> idList=new ArrayList<Integer>();
//		idList.add(53);
//		idList.add(54);
//		idList.add(55);
//		idList.add(56);
//		gys.setSqlIdInList(idList);
		//selectList
		List<TbBalShSelfScheduleOutBean> list=dao.selectList(gys);
		for(TbBalShSelfScheduleOutBean g:list){
			//System.out.println("id:"+g.getId()+";roleName:"+g.getRoleName()+";note:"+g.getNote()+";rn:"+g.getRn());
			System.out.println(g.getStartDate());
		}
//		System.out.println("=============");
//		IBaseMyBatisByIdDao<GysBean> daoById=mds.getDao(IGysDao.class);
//		GysBean bean=daoById.selectOneById(22);
//		System.out.println("id:"+bean.getId()+";roleName:"+bean.getRoleName()+";");
		mds.closeSession();
	}
}
