package top.kcaco.seckill.modules.skorder.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skorder.domain.entity.SeckillOrder;
import top.kcaco.seckill.modules.skorder.domain.request.SeckillOrderPageReq;

/**
 * 秒杀订单表(SeckillOrder)表服务接口
 *
 * @author kcaco
 */
public interface SeckillOrderService extends IService<SeckillOrder> {

    /**
     * 分页查询
     *
     * @param seckillOrderPageReq 请求体
     * @return 分页查询结果
     */
    Page<SeckillOrder> listSeckillOrderPage(SeckillOrderPageReq seckillOrderPageReq);

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return 详情
     */
    SeckillOrder getSeckillOrderDetail(String id);

    /**
     * 保存或更新
     *
     * @param seckillOrder 请求体
     */
    void saveOrUpdateSeckillOrder(SeckillOrder seckillOrder);

    /**
     * 删除
     *
     * @param id 主键
     */
    void deleteSeckillOrder(String id);
}

