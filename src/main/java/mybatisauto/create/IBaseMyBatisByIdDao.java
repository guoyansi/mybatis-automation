package mybatisauto.create;


public interface IBaseMyBatisByIdDao<T> {
	/**
	 * 获取单条数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	T selectOneById(Object id)throws Exception;
	
	/**
	 * 更新数据
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int updateById(T t) throws Exception;
	
	/**
	 * 插入时获取主键id,主键是字段中的第一个值
	 * @param t
	 * @return
	 * @throws Exception
	 */
	int insertGetId(T t) throws Exception;
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 * @throws Exception
	 */
	int deleteById(Object id) throws Exception;
}
