package dao;

import bean.TdSysDirectoryBean;
import bean.in.TdSysDirectoryInBean;
import bean.out.TdSysDirectoryOutBean;
import mybatisauto.create.IBaseMyBatisDao;
import mybatisauto.create.IBaseMyBatisByIdDao;

public interface ITdSysDirectoryDao extends IBaseMyBatisDao<TdSysDirectoryBean,TdSysDirectoryInBean,TdSysDirectoryOutBean>,IBaseMyBatisByIdDao<TdSysDirectoryBean>{

}