package dao;

import bean.TdSysFuncBean;
import bean.in.TdSysFuncInBean;
import bean.out.TdSysFuncOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysFuncDao extends IBaseMyBatisDao<TdSysFuncBean,TdSysFuncInBean,TdSysFuncOutBean>,IBaseMyBatisByIdDao<TdSysFuncBean>{

}