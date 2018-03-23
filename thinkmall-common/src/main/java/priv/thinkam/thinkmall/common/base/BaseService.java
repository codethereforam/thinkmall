package priv.thinkam.thinkmall.common.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseService接口
 *
 * @author thinkam
 * @date 2018/03/22
 */
public interface BaseService<Record, Example> {
    /**
     * 计算符合实例条件的记录数量
     * @param example 实例
     * @return 数量
     */
    int countByExample(Example example);

    /**
     * 删除符合实例条件的记录
     * @param example 实例
     * @return 影响的行数
     */
    int deleteByExample(Example example);

    /**
     * 根据主键删除记录
     * @param id 主键
     * @return 影响的行数
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据多个主键删除对象
     * @param ids 多个id，多个id用"-"隔开
     * @return 影响的行数
     */
    int deleteByPrimaryKeys(String ids);

    /**
     * 插入一条记录
     * @param record 一条记录
     * @return 影响的行数
     */
    int insert(Record record);

    /**
     * 插入一条记录，保存非空字段
     * @param record 一条记录
     * @return 影响的行数
     */
    int insertSelective(Record record);

    /**
     * 查询符合实例条件的记录
     * @param example 实例
     * @return 对象集合
     */
    List<Record> selectByExample(Example example);

    /**
     * 查询符合实例条件的第pageNum页的pageSize条内容
     * @param example 实例
     * @param pageNum 第几页
     * @param pageSize 每页的记录数
     * @return 符合条件的对象集合
     */
    List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize);

    /**
     * 查询符合实例条件的第offset条记录开始的limit条内容
     * @param example 实例
     * @param offset 起始的记录
     * @param limit 查询的条数
     * @return 符合条件的对象集合
     */
    List<Record> selectByExampleForOffsetPage(Example example, Integer offset, Integer limit);

    /**
     * 查询符合实例条件的第一条记录
     * @param example 实例
     * @return 对象
     */
    Record selectFirstByExample(Example example);

    /**
     * 根据主键查询对象
     * @param id 主键
     * @return 对象
     */
    Record selectByPrimaryKey(Long id);

    /**
     * 根据实例条件更新对象非空字段
     * @param record 对象
     * @param example 实例
     * @return 影响的行数
     */
    int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

    /**
     * 根据实例条件更新对象
     * @param record 对象
     * @param example 实例
     * @return 影响的行数
     */
    int updateByExample(@Param("record") Record record, @Param("example") Example example);

    /**
     * 根据对象主键更新对象非空字段
     * @param record 对象
     * @return 影响的行数
     */
    int updateByPrimaryKeySelective(Record record);

    /**
     * 根据对象主键更新对象
     * @param record 对象
     * @return 影响的行数
     */
    int updateByPrimaryKey(Record record);

    /**
     * 初始化mapper对象
     */
    void initMapper();
}
