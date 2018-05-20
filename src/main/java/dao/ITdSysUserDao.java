package dao;

import bean.TdSysUserBean;
import bean.in.TdSysUserInBean;
import bean.out.TdSysUserOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysUserDao extends IBaseMyBatisDao<TdSysUserBean,TdSysUserInBean,TdSysUserOutBean>,IBaseMyBatisByIdDao<TdSysUserBean>{

}