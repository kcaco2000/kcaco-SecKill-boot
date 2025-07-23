package top.kcaco.seckill.modules.skactivity.controller;


import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.common.result.Result;
import top.kcaco.seckill.modules.skactivity.domain.entity.SeckillActivity;
import top.kcaco.seckill.modules.skactivity.domain.request.SeckillActivityPageReq;
import top.kcaco.seckill.modules.skactivity.service.SeckillActivityService;

import java.util.List;

/**
 * 秒杀活动(SeckillActivity)表控制层
 *
 * @author kcaco
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("seckillActivity")
public class SeckillActivityController {

    private final SeckillActivityService seckillActivityService;

    /**
     * 分页查询
     *
     * @param seckillActivityPageReq 查询实体
     * @return 分页数据
     */
    @GetMapping("listSeckillActivityPage")
    public Result listSeckillActivityPage(SeckillActivityPageReq seckillActivityPageReq) {
        return Result.success(this.seckillActivityService.listSeckillActivityPage(seckillActivityPageReq));
    }

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return {@link Result<>}
     */
    @GetMapping("/detail")
    public Result detail(@RequestParam("id") String id) {
        return Result.success(seckillActivityService.getSeckillActivityDetail(id));
    }

    /**
     * 新增或修改
     *
     * @param seckillActivity 请求体
     * @return {@link Result<>}
     */
    @PostMapping("/saveOrUpdateSeckillActivity")
    public Result saveOrUpdateSeckillActivity(@RequestBody SeckillActivity seckillActivity) {
        seckillActivityService.saveOrUpdateSeckillActivity(seckillActivity);
        return Result.success();
    }

    /**
     * 删除单个
     *
     * @param id 主键
     * @return 删除结果
     */
    @DeleteMapping("/deleteSeckillActivity/{id}")
    public Result deleteSeckillActivity(@PathVariable String id) {
        seckillActivityService.deleteSeckillActivity(id);
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
//        return Result.success(this.seckillActivityService.removeByIds(idList));
//    }

}

