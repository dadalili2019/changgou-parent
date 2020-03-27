package com.changgou.goods.dao;

import com.changgou.goods.pojo.Spec;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName SpecMapper 规格表dao
 * @Date 2020/3/27 15:25
 * @Version 1.0
 */
public interface SpecMapper extends Mapper<Spec> {

    /**
     * 根据商品分类名称查询规格列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    @Select("SELECT name,options FROM tb_spec WHERE template_id in(SELECT template_id FROM tb_category WHERE name=#{categoryName})")
    public List<Map> findSpecListByCategoryName(@Param("categoryName") String categoryName);
}
