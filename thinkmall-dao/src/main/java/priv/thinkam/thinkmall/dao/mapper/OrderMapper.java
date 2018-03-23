package priv.thinkam.thinkmall.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import priv.thinkam.thinkmall.dao.entity.Order;
import priv.thinkam.thinkmall.dao.entity.OrderExample;

public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}