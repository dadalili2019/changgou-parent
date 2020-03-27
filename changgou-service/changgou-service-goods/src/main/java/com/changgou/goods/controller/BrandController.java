package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName BrandController
 * @Date 2020/3/17 9:59
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌信息
     *
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        List<Brand> brandList = brandService.findAll();
        return new Result<List<Brand>>(true, StatusCode.OK, "查询品牌集合成功", brandList);
    }

    /**
     * 根据id查询品牌信息
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result<Brand> findById(@PathVariable(value = "id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<Brand>(true, StatusCode.OK, "根据id查询品牌成功", brand);
    }

    /**
     * 添加品牌信息
     *
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "添加品牌成功");
    }

    /**
     * 根据id修改品牌
     *
     * @param id
     * @param brand
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Brand brand) {
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "根据id修改品牌成功");
    }

    /**
     * 根据id删除品牌信息
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id) {
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK, "根据id删除品牌信息成功");
    }

    /**
     * 根据条件查询品牌信息
     *
     * @param brand
     * @return
     */
    @PostMapping(value = "/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findList(brand);
        return new Result<List<Brand>>(true, StatusCode.OK, "条件搜索查询", brandList);
    }

    /**
     * 分页查询品牌信息
     *
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size
    ) {
        PageInfo<Brand> brandPageInfo = brandService.findPage(page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页查询成功", brandPageInfo);
    }

    /**
     * 条件查询品牌信息 +分页
     *
     * @param page  当前页
     * @param size  每页显示得条数
     * @param brand 查询条件参数
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable(value = "page") Integer page,
                                            @PathVariable(value = "size") Integer size,
                                            @RequestBody Brand brand) {
        PageInfo<Brand> pageInfo = brandService.findPage(brand, page, size);
        return new Result<PageInfo<Brand>>(true, StatusCode.OK, "分页条件查询成功", pageInfo);
    }

    /**
     * 根据分类名称查询品牌列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    @GetMapping("/category/{categoryName}")
    public Result<List<Map>> findBrandListByCategoryName(@PathVariable("categoryName") String categoryName) {
        List<Map> brandList = brandService.findBrandListByCategoryName(categoryName);
        return new Result<>(true, StatusCode.OK, "查询成功", brandList);
    }
}
