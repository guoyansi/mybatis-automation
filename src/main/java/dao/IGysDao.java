package dao;

import bean.GysBean;
import bean.in.GysInBean;
import bean.out.GysOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface IGysDao extends IBaseMyBatisDao<GysBean,GysInBean,GysOutBean>,IBaseMyBatisByIdDao<GysBean>{

}