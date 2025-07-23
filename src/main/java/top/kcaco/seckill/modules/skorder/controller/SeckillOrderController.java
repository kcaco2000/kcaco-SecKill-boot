package top.kcaco.seckill.modules.skorder.controller;


import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.modules.skorder.domain.entity.SeckillOrder;
import top.kcaco.seckill.modules.skorder.domain.request.SeckillOrderPageReq;
import top.kcaco.seckill.modules.skorder.service.SeckillOrderService;

import java.util.List;

/**
 * 秒杀订单表(SeckillOrder)表控制层
 *
 * @author kcaco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("seckillOrder")
public class SeckillOrderController {

    private final SeckillOrderService seckillOrderService;

    /**
     * 分页查询
     *
     * @param seckillOrderPageReq 查询实体
     * @return 分页数据
     */
    @GetMapping("listSeckillOrderPage")
    public Result listSeckillOrderPage(SeckillOrderPageReq seckillOrderPageReq) {
        return Result.success(this.seckillOrderService.listSeckillOrderPage(seckillOrderPageReq));
    }

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return {@link Result<>}
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam("id") String id) {
        return Result.success(seckillOrderService.getSeckillOrderDetail(id));
    }

    /**
     * 新增或修改
     *
     * @param seckillOrder 请求体
     * @return {@link Result<>}
     */
    @PostMapping("/saveOrUpdateSeckillOrder")
    public Result saveOrUpdateSeckillOrder(@RequestBody SeckillOrder seckillOrder) {
        seckillOrderService.saveOrUpdateSeckillOrder(seckillOrder);
        return Result.success();
    }

    /**
     * 删除单个
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/deleteSeckillOrder/{id}")
    public Result deleteSeckillOrder(@PathVariable String id) {
        seckillOrderService.deleteSeckillOrder(id);
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
//        return Result.success(this.seckillOrderService.removeByIds(idList));
//    }

}

