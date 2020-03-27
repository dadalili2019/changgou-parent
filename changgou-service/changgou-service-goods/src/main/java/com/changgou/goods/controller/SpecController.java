package com.changgou.goods.controller;

import com.changgou.goods.pojo.Spec;
import com.changgou.goods.service.SpecService;
import com.github.pagehelper.Page;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName SpecController
 * @Date 2020/3/27 15:37
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/spec")
public class SpecController {

    @Autowired(required = false)
    private SpecService specService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Spec> specList = specService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", specList);
    }

    /***
     * 根据ID查询数据
     * @param id 主键id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        Spec spec = specService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", spec);
    }


    /***
     * 新增数据
     * @param spec 实体参数
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Spec spec) {
        specService.add(spec);
        return new Result(true, StatusCode.OK, "添加成功");
    }


    /***
     * 修改数据
     * @param spec 实体参数
     * @param id 主键id
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result update(@RequestBody Spec spec, @PathVariable Integer id) {
        spec.setId(id);
        specService.update(spec);
        return new Result(true, StatusCode.OK, "修改成功");
    }


    /***
     * 根据ID删除品牌数据
     * @param id 主键
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result delete(@PathVariable Integer id) {
        specService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /***
     * 多条件搜索品牌数据
     * @param searchMap 查询参数
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Spec> list = specService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", list);
    }


    /***
     * 分页搜索实现
     * @param searchMap 查询参数
     * @param page 当前页
     * @param size 每页显示的条数
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Spec> pageList = specService.findPage(searchMap, page, size);
        PageResult pageResult = new PageResult(pageList.getTotal(), pageList.getResult());
        return new Result(true, StatusCode.OK, "查询成功", pageResult);
    }

    /**
     * 根据商品分类名称查询规格列表
     *
     * @param categoryName 商品分类名称
     * @return
     */
    @GetMapping("/category/{categoryName}")
    public Result<List<Map>> findSpecListByCategoryName(@PathVariable("categoryName") String categoryName) {
        List<Map> specList = specService.findSpecListByCategoryName(categoryName);
        return new Result<>(true, StatusCode.OK, "查询成功", specList);
    }
}
