package com.changgou.goods.service;

import com.changgou.goods.pojo.Spec;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName SpecService
 * @Date 2020/3/27 15:30
 * @Version 1.0
 */
public interface SpecService {

    /***
     * 查询所有
     * @return
     */
    List<Spec> findAll();

    /**
     * 根据ID查询
     *
     * @param id 主键id
     * @return
     */
    Spec findById(Integer id);

    /***
     * 新增
     * @param spec 参数实体
     */
    void add(Spec spec);

    /***
     * 修改
     * @param spec 参数实体
     */
    void update(Spec spec);

    /***
     * 删除
     * @param id 主键id
     */
    void delete(Integer id);

    /***
     * 多条件搜索
     * @param searchMap 查询参数
     * @return
     */
    List<Spec> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page 当前页码
     * @param size 每页显示的条数
     * @return
     */
    Page<Spec> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap 查询参数
     * @param page  当前页码
     * @param size  每页显示的条数
     * @return
     */
    Page<Spec> findPage(Map<String, Object> searchMap, int page, int size);

    /**
     * 根据商品分类名称查询规格列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    List<Map> findSpecListByCategoryName(String categoryName);
}
