package top.kcaco.seckill.modules.skgoods.controller;


import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.modules.skgoods.domain.entity.SeckillGoods;
import top.kcaco.seckill.modules.skgoods.domain.request.SeckillGoodsPageReq;
import top.kcaco.seckill.modules.skgoods.service.SeckillGoodsService;

import java.util.List;

/**
 * 秒杀商品(SeckillGoods)表控制层
 *
 * @author kcaco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("seckillGoods")
public class SeckillGoodsController {

    private final SeckillGoodsService seckillGoodsService;

    /**
     * 分页查询
     *
     * @param seckillGoodsPageReq 查询实体
     * @return 分页数据
     */
    @GetMapping("listSeckillGoodsPage")
    public Result listSeckillGoodsPage(SeckillGoodsPageReq seckillGoodsPageReq) {
        return Result.success(this.seckillGoodsService.listSeckillGoodsPage(seckillGoodsPageReq));
    }

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return {@link Result<>}
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam("id") String id) {
        return Result.success(seckillGoodsService.getSeckillGoodsDetail(id));
    }

    /**
     * 新增或修改
     *
     * @param seckillGoods 请求体
     * @return {@link Result<>}
     */
    @PostMapping("/saveOrUpdateSeckillGoods")
    public Result saveOrUpdateSeckillGoods(@RequestBody SeckillGoods seckillGoods) {
        seckillGoodsService.saveOrUpdateSeckillGoods(seckillGoods);
        return Result.success();
    }

    /**
     * 删除单个
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/deleteSeckillGoods/{id}")
    public Result deleteSeckillGoods(@PathVariable String id) {
        seckillGoodsService.deleteSeckillGoods(id);
        return Result.success();
    }

//    /**
//     * 删除数据
//     *
//     * @param idList 主键集合
//     * @return 删除结果
//     */
//    @DeleteMapping("/deleteBatch")
//    public Result deleteBatch(@RequestBody List<String> idList) {
//        return Result.success(this.seckillGoodsService.removeByIds(idList));
//    }

}

