package priv.thinkam.thinkmall.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import priv.thinkam.thinkmall.dao.entity.ShippingAddress;
import priv.thinkam.thinkmall.dao.entity.ShippingAddressExample;

public interface ShippingAddressMapper {
    long countByExample(ShippingAddressExample example);

    int deleteByExample(ShippingAddressExample example);

    int deleteByPrimaryKey(Long shippingAddressId);

    int insert(ShippingAddress record);

    int insertSelective(ShippingAddress record);

    List<ShippingAddress> selectByExample(ShippingAddressExample example);

    ShippingAddress selectByPrimaryKey(Long shippingAddressId);

    int updateByExampleSelective(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByExample(@Param("record") ShippingAddress record, @Param("example") ShippingAddressExample example);

    int updateByPrimaryKeySelective(ShippingAddress record);

    int updateByPrimaryKey(ShippingAddress record);
}