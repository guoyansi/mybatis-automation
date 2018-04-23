package dao;

import org.springframework.stereotype.Repository;
import bean.ABean;
import table.ITable;

@Repository 
public interface IADao extends ITable<ABean>{

}