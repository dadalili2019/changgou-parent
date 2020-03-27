package com.changgou.goods.service.Impl;

import com.changgou.goods.dao.SpecMapper;
import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName SpecServiceImpl
 * @Date 2020/3/27 15:39
 * @Version 1.0
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired(required = false)
    private SpecMapper specMapper;

    /**
     * 查询全部列表
     *
     * @return
     */
    @Override
    public List<Spec> findAll() {
        return specMapper.selectAll();
    }

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Spec findById(Integer id) {
        return specMapper.selectByPrimaryKey(id);
    }


    /**
     * 增加
     *
     * @param spec 参数实体
     */
    @Override
    public void add(Spec spec) {
        specMapper.insert(spec);
    }


    /**
     * 更新
     *
     * @param spec 参数实体
     */
    @Override
    public void update(Spec spec) {
        specMapper.updateByPrimaryKey(spec);
    }

    /**
     * 根据id删除
     *
     * @param id 主键id
     */
    @Override
    public void delete(Integer id) {
        specMapper.deleteByPrimaryKey(id);
    }


    /**
     * 条件查询
     *
     * @param searchMap 查询参数
     * @return
     */
    @Override
    public List<Spec> findList(Map<String, Object> searchMap) {
        Example example = createExample(searchMap);
        return specMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page 当前页码
     * @param size 每页显示的条数
     * @return
     */
    @Override
    public Page<Spec> findPage(int page, int size) {
        PageHelper.startPage(page, size);
        return (Page<Spec>) specMapper.selectAll();
    }

    /**
     * 条件+分页查询
     *
     * @param searchMap 查询条件
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @Override
    public Page<Spec> findPage(Map<String, Object> searchMap, int page, int size) {
        PageHelper.startPage(page, size);
        Example example = createExample(searchMap);
        return (Page<Spec>) specMapper.selectByExample(example);
    }

    /**
     * 根据商品分类名称查询规格列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    @Override
    public List<Map> findSpecListByCategoryName(String categoryName) {
        List<Map> specList = specMapper.findSpecListByCategoryName(categoryName);
        for (Map map : specList) {
            String[] options = ((String) map.get("options")).split(",");
            map.put("options", options);
        }
        return specList;
    }

    /**
     * 查询条件构建
     *
     * @param searchMap 搜索参数
     * @return Example 自定义搜索参数对象
     */
    private Example createExample(Map<String, Object> searchMap) {
        Example example = new Example(Spec.class);
        Example.Criteria criteria = example.createCriteria();
        if (searchMap != null) {
            // 名称
            if (searchMap.get("name") != null && !"".equals(searchMap.get("name"))) {
                criteria.andLike("name", "%" + searchMap.get("name") + "%");
            }
            // 规格选项
            if (searchMap.get("options") != null && !"".equals(searchMap.get("options"))) {
                criteria.andLike("options", "%" + searchMap.get("options") + "%");
            }

            // ID
            if (searchMap.get("id") != null) {
                criteria.andEqualTo("id", searchMap.get("id"));
            }
            // 排序
            if (searchMap.get("seq") != null) {
                criteria.andEqualTo("seq", searchMap.get("seq"));
            }
            // 模板ID
            if (searchMap.get("templateId") != null) {
                criteria.andEqualTo("templateId", searchMap.get("templateId"));
            }

        }
        return example;
    }
}
