package table;

import java.util.List;


public interface ITable<T> {
	/**
	 * 插入时获取主键id,主键是字段中的第一个值
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insertGetId(T t) throws Exception;
	/**
	 * 插入时不获取主键id
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insert(T t) throws Exception;
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int update(T t) throws Exception;
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int updateById(Object id) throws Exception;
	/**
	 * 获取list
	 * @param t
	 * @return
	 * @throws Exception
	 */
	List<T> selectList(T t) throws Exception;
	/**
	 * 获取数量
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int count(T t) throws Exception;
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T selectOne(T t)throws Exception;
	/**
	 * 删除
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int delete(T t) throws Exception;
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(Object id) throws Exception;
}
