package dao;

import bean.ABean;
import bean.in.AInBean;
import bean.out.AOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface IADao extends IBaseMyBatisDao<ABean,AInBean,AOutBean>,IBaseMyBatisByIdDao<ABean>{

}