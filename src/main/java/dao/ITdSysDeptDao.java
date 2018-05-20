package dao;

import bean.TdSysDeptBean;
import bean.in.TdSysDeptInBean;
import bean.out.TdSysDeptOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysDeptDao extends IBaseMyBatisDao<TdSysDeptBean,TdSysDeptInBean,TdSysDeptOutBean>,IBaseMyBatisByIdDao<TdSysDeptBean>{

}