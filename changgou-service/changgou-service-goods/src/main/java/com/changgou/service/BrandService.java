package com.changgou.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Author caoqian
 * @ClassName BrandService
 * @Date 2020/3/17 9:59
 * @Version 1.0
 */
public interface BrandService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 添加品牌
     *
     * @param brand
     */
    void add(Brand brand);

    /**
     * 根据id修改品牌
     *
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据id删除品牌信息
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 多条件搜索品牌信息
     *
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 分页搜索
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    PageInfo<Brand> findPage(Integer page,Integer size);

    /**
     * 分页+条件搜索
     * @param brand 查询条件
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    PageInfo<Brand> findPage(Brand brand,Integer page,Integer size);
}
