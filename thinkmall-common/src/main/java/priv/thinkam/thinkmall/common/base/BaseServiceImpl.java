package priv.thinkam.thinkmall.common.base;

import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import priv.thinkam.thinkmall.common.util.SpringContextUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 实现BaseService抽象类
 *
 * @author thinkam
 * @date 2018/03/22
 */
public class BaseServiceImpl<Mapper, Record, Example> implements BaseService<Record, Example> {
    public Mapper mapper;

    @Override
    public int countByExample(Example example) {
        try {
            Method countByExample = mapper.getClass().getDeclaredMethod("countByExample", example.getClass());
            Object result = countByExample.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByExample(Example example) {
        try {
            Method deleteByExample = mapper.getClass().getDeclaredMethod("deleteByExample", example.getClass());
            Object result = deleteByExample.invoke(mapper, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        try {
            Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
            Object result = deleteByPrimaryKey.invoke(mapper, id);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insert(Record record) {
        try {
            Method insert = mapper.getClass().getDeclaredMethod("insert", record.getClass());
            Object result = insert.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertSelective(Record record) {
        try {
            Method insertSelective = mapper.getClass().getDeclaredMethod("insertSelective", record.getClass());
            Object result = insertSelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Record> selectByExample(Example example) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            Object result = selectByExample.invoke(mapper, example);
            return (List<Record>) result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            PageHelper.startPage(pageNum, pageSize, false);
            Object result = selectByExample.invoke(mapper, example);
            return (List<Record>) result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Record> selectByExampleForOffsetPage(Example example, Integer offset, Integer limit) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            PageHelper.offsetPage(offset, limit, false);
            Object result = selectByExample.invoke(mapper, example);
            return (List<Record>) result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Record selectFirstByExample(Example example) {
        try {
            Method selectByExample = mapper.getClass().getDeclaredMethod("selectByExample", example.getClass());
            List<Record> result = (List<Record>) selectByExample.invoke(mapper, example);
            if (null != result && result.size() > 0) {
                return result.get(0);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Record selectByPrimaryKey(Long id) {
        try {
            Method selectByPrimaryKey = mapper.getClass().getDeclaredMethod("selectByPrimaryKey", id.getClass());
            Object result = selectByPrimaryKey.invoke(mapper, id);
            return (Record) result;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example) {
        try {
            Method updateByExampleSelective = mapper.getClass().getDeclaredMethod("updateByExampleSelective", record.getClass(), example.getClass());
            Object result = updateByExampleSelective.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByExample(@Param("record") Record record, @Param("example") Example example) {
        try {
            Method updateByExample = mapper.getClass().getDeclaredMethod("updateByExample", record.getClass(), example.getClass());
            Object result = updateByExample.invoke(mapper, record, example);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Record record) {
        try {
            Method updateByPrimaryKeySelective = mapper.getClass().getDeclaredMethod("updateByPrimaryKeySelective", record.getClass());
            Object result = updateByPrimaryKeySelective.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Record record) {
        try {
            Method updateByPrimaryKey = mapper.getClass().getDeclaredMethod("updateByPrimaryKey", record.getClass());
            Object result = updateByPrimaryKey.invoke(mapper, record);
            return Integer.parseInt(String.valueOf(result));
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteByPrimaryKeys(String ids) {
        try {
            if (StringUtils.isBlank(ids)) {
                return 0;
            }
            String[] idArray = ids.split("-");
            int count = 0;
            for (String idStr : idArray) {
                if (StringUtils.isBlank(idStr)) {
                    continue;
                }
                Long id = Long.parseLong(idStr);
                Method deleteByPrimaryKey = mapper.getClass().getDeclaredMethod("deleteByPrimaryKey", id.getClass());
                Object result = deleteByPrimaryKey.invoke(mapper, id);
                count += Integer.parseInt(String.valueOf(result));
            }
            return count;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void initMapper() {
        this.mapper = SpringContextUtil.getBean(getMapperClass());
    }

    /**
     * 获取类泛型class
     *
     * @return mapper class
     */
    private Class<Mapper> getMapperClass() {
        return (Class<Mapper>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

}
