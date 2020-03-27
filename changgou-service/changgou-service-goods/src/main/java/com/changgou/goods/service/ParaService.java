package com.changgou.goods.service;

import com.changgou.goods.pojo.Para;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName ParaService
 * @Date 2020/3/27 14:24
 * @Version 1.0
 */
public interface ParaService {

    /***
     * 查询所有
     * @return
     */
    List<Para> findAll();

    /**
     * 根据ID查询
     *
     * @param id 主键id
     * @return
     */
    Para findById(Integer id);

    /***
     * 新增
     * @param para 实体参数
     */
    void add(Para para);

    /***
     * 修改
     * @param para 实体参数
     */
    void update(Para para);

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
    List<Para> findList(Map<String, Object> searchMap);

    /***
     * 分页查询
     * @param page 当前页码
     * @param size 每页显示的条数
     * @return
     */
    Page<Para> findPage(int page, int size);

    /***
     * 多条件分页查询
     * @param searchMap 查询参数
     * @param page 当前页码
     * @param size 每页显示的条数
     * @return
     */
    Page<Para> findPage(Map<String, Object> searchMap, int page, int size);
}
