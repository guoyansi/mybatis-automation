package dao;

import bean.TdSysRoleBean;
import bean.in.TdSysRoleInBean;
import bean.out.TdSysRoleOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysRoleDao extends IBaseMyBatisDao<TdSysRoleBean,TdSysRoleInBean,TdSysRoleOutBean>,IBaseMyBatisByIdDao<TdSysRoleBean>{

}