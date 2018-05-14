package table;

import java.util.List;

import table.bean.BaseInBean;
import table.bean.BaseOutBean;


public interface ITable<I extends BaseInBean,O extends BaseOutBean> {
	/**
	 * 获取list
	 * @param t
	 * @return
	 * @throws Exception
	 */
	List<O> selectList(I t) throws Exception;
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	O selectOne(I t)throws Exception;
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	O selectOneById(Object id)throws Exception;
	/**
	 * 获取数量
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int getCount(I t) throws Exception;
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int update(I t) throws Exception;
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int updateById(Object id) throws Exception;
	/**
	 * 插入时不获取主键id
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insert(I t) throws Exception;
	/**
	 * 插入时获取主键id,主键是字段中的第一个值
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insertGetId(I t) throws Exception;
	/**
	 * 删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int delete(I t) throws Exception;
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(Object id) throws Exception;
}
