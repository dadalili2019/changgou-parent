package com.changgou.goods.service.Impl;

import com.changgou.goods.dao.TemplateMapper;
import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName TemplateServiceImpl
 * @Date 2020/3/27 10:06
 * @Version 1.0
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired(required = false)
    private TemplateMapper templateMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    @Override
    public List<Template> findAll() {
        return templateMapper.selectAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Template findById(Integer id) {
        return templateMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     *
     * @param template
     */
    @Override
    public void add(Template template) {
        templateMapper.insertSelective(template);
    }


    /**
     * 更新
     *
     * @param template
     */
    @Override
    public void update(Template template) {
        templateMapper.updateByPrimaryKeySelective(template);
    }

    /**
     * 根据id删除
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        templateMapper.deleteByPrimaryKey(id);
    }

    /**
     * 条件查询
     *
     * @param searchMap 查询条件
     * @return
     */
    @Override
    public List<Template> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return templateMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    @Override
    public Page<Template> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        return (Page<Template>) templateMapper.selectAll();
    }

    /**
     * 分页查询+条件
     *
     * @param searchMap 查询条件
     * @param page      当前页
     * @param size      每页显示的条数
     * @return
     */
    @Override
    public Page<Template> findPage(Map<String, Object> searchMap, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Template>) templateMapper.selectByExample(example);
    }


    /**
     * 查询条件构建
     *
     * @param searchMap 搜索参数
     * @return Example 自定义搜索参数对象
     */
    public Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Template.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 模板名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }

            // ID
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 规格数量
            if (searchMap.get("paraNum") != null) {
                criteria.andEqualTo("paraNum", searchMap.get("paraNum"));
            }
            // 参数数量
            if (searchMap.get("paraNum") != null) {
                criteria.andEqualTo("paraNum", searchMap.get("paraNum"));
            }

        }
        return example;
    }
}
