package com.changgou.goods.service;

import com.changgou.goods.pojo.Template;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName TemplateService
 * @Date 2020/3/27 10:02
 * @Version 1.0
 */
public interface TemplateService {

    /**
     * 查询所有
     *
     * @return
     */
    List<Template> findAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    Template findById(Integer id);

    /**
     * 新增
     *
     * @param template
     */
    void add(Template template);

    /**
     * 修改
     *
     * @param template
     */
    void update(Template template);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 多条件搜索
     *
     * @param searchMap 查询条件
     * @return
     */
    List<Template> findList(Map<String, Object> searchMap);

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    Page<Template> findPage(Integer page, Integer size);

    /**
     * 多条件分页查询
     *
     * @param searchMap 查询条件
     * @param page      当前页
     * @param size      每页显示的条数
     * @return
     */
    Page<Template> findPage(Map<String, Object> searchMap, Integer page, Integer size);
}
