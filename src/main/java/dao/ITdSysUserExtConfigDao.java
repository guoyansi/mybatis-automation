package dao;

import bean.TdSysUserExtConfigBean;
import bean.in.TdSysUserExtConfigInBean;
import bean.out.TdSysUserExtConfigOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysUserExtConfigDao extends IBaseMyBatisDao<TdSysUserExtConfigBean,TdSysUserExtConfigInBean,TdSysUserExtConfigOutBean>,IBaseMyBatisByIdDao<TdSysUserExtConfigBean>{

}