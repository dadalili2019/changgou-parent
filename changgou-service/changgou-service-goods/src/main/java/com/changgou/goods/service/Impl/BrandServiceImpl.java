package com.changgou.goods.service.Impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName BrandServiceImpl
 * @Date 2020/3/17 9:58
 * @Version 1.0
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired(required = false)
    private BrandMapper brandMapper;


    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Brand> findAll() {
        //通用mapper
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加品牌
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 根据id修改品牌
     *
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 根据id删除品牌信息
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 多条件查询品牌信息
     *
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        //调用方法构建条件
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 分页查询
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Brand> brandList = brandMapper.selectAll();
        return new PageInfo<Brand>(brandList);
    }


    /**
     * 分页+条件搜索
     *
     * @param brand 查询条件
     * @param page  当前页
     * @param size  每页显示的条数
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, Integer page, Integer size) {
        //开启分页
        PageHelper.startPage(page, size);
        //自定义搜索条件
        Example example = createExample(brand);
        List<Brand> brandList = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brandList);
    }

    /**
     * 根据分类名称查询品牌列表
     * @param categoryName 商品分类名称
     * @return
     */
    @Override
    public List<Map> findBrandListByCategoryName(String categoryName) {
        List<Map> brandList = brandMapper.findBrandListByCategoryName(categoryName);
        return brandList;
    }

    /**
     * 查询条件构建
     *
     * @param brand 搜索参数
     * @return Example 自定义搜索参数对象
     */
    public Example createExample(Brand brand) {
        //自定义条件搜索对象 Example
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) {
            //根据名字模糊搜索 where name like
            if (!StringUtil.isEmpty(brand.getName())) {
                /*
                 * 1、Brand的属性名
                 * 2、占位符参数，搜索的条件
                 */
                criteria.andLike("name", "%" + brand.getName() + "%");
            }
            //brand.letter!=null根据首字母搜索 and letter=""
            if (!StringUtil.isEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
        }
        return example;
    }
}
