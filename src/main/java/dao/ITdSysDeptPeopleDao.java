package dao;

import bean.TdSysDeptPeopleBean;
import bean.in.TdSysDeptPeopleInBean;
import bean.out.TdSysDeptPeopleOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysDeptPeopleDao extends IBaseMyBatisDao<TdSysDeptPeopleBean,TdSysDeptPeopleInBean,TdSysDeptPeopleOutBean>,IBaseMyBatisByIdDao<TdSysDeptPeopleBean>{

}