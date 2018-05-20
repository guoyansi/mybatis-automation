package dao;

import bean.TdSysUserExtBean;
import bean.in.TdSysUserExtInBean;
import bean.out.TdSysUserExtOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysUserExtDao extends IBaseMyBatisDao<TdSysUserExtBean,TdSysUserExtInBean,TdSysUserExtOutBean>,IBaseMyBatisByIdDao<TdSysUserExtBean>{

}