package dao;

import bean.TdSysBoardTypeBean;
import bean.in.TdSysBoardTypeInBean;
import bean.out.TdSysBoardTypeOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysBoardTypeDao extends IBaseMyBatisDao<TdSysBoardTypeBean,TdSysBoardTypeInBean,TdSysBoardTypeOutBean>,IBaseMyBatisByIdDao<TdSysBoardTypeBean>{

}