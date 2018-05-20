package dao;

import bean.TdSysBoardBean;
import bean.in.TdSysBoardInBean;
import bean.out.TdSysBoardOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysBoardDao extends IBaseMyBatisDao<TdSysBoardBean,TdSysBoardInBean,TdSysBoardOutBean>,IBaseMyBatisByIdDao<TdSysBoardBean>{

}