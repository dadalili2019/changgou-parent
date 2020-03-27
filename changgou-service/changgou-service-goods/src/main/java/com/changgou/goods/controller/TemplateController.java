package com.changgou.goods.controller;

import com.changgou.goods.pojo.Template;
import com.changgou.goods.service.TemplateService;
import com.github.pagehelper.Page;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author caoqian
 * @ClassName TemplateController
 * @Date 2020/3/27 10:29
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping
    public Result findAll() {
        List<Template> templateList = templateService.findAll();
        return new Result(true, StatusCode.OK, "查询成功", templateList);
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") Integer id) {
        Template templateServiceById = templateService.findById(id);
        return new Result(true, StatusCode.OK, "查询成功", templateServiceById);
    }

    /**
     * 添加
     *
     * @param template 添加参数
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Template template) {
        templateService.add(template);
        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 根据id修改
     *
     * @param id       需要修改的id
     * @param template 修改的实体
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Template template) {
        template.setId(id);
        templateService.update(template);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 多条件搜索数据
     *
     * @param searchMap 查询参数
     * @return
     */
    @GetMapping(value = "/search")
    public Result findList(@RequestParam Map searchMap) {
        List<Template> templateList = templateService.findList(searchMap);
        return new Result(true, StatusCode.OK, "查询成功", templateList);
    }

    /**
     * 分页查询+条件
     *
     * @param searchMap 查询参数
     * @param page      每页显示的条数
     * @param size      当前页码
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result findPage(@RequestParam Map searchMap,
                           @PathVariable(value = "page") Integer page,
                           @PathVariable(value = "size") Integer size) {
        Page<Template> pageList = templateService.findPage(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", pageList);
    }

}
