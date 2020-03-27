package com.changgou.goods.dao;

import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName BrandMapper  品牌dao
 * @Date 2020/3/17 9:57
 * @Version 1.0
 * <p>
 * dao使用通用mapper
 */
public interface BrandMapper extends Mapper<Brand> {

    /**
     * 根据分类名称查询品牌列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    @Select("SELECT name,image FROM tb_brand where id in( SELECT brand_id FROM tb_category_brand WHERE category_id in ( SELECT id from tb_category where name=#{categoryName}))")
    public List<Map> findBrandListByCategoryName(@Param("categoryName") String categoryName);
}
