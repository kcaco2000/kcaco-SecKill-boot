package top.kcaco.seckill.modules.skgoods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skgoods.domain.entity.SeckillGoods;
import top.kcaco.seckill.modules.skgoods.domain.request.SeckillGoodsPageReq;

/**
 * 秒杀商品(SeckillGoods)表服务接口
 *
 * @author kcaco
 */
public interface SeckillGoodsService extends IService<SeckillGoods> {

    /**
     * 分页查询
     *
     * @param seckillGoodsPageReq 请求体
     * @return 分页查询结果
     */
    Page<SeckillGoods> listSeckillGoodsPage(SeckillGoodsPageReq seckillGoodsPageReq);

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return 详情
     */
    SeckillGoods getSeckillGoodsDetail(String id);

    /**
     * 保存或更新
     *
     * @param seckillGoods 请求体
     */
    void saveOrUpdateSeckillGoods(SeckillGoods seckillGoods);

    /**
     * 删除
     *
     * @param id 主键
     */
    void deleteSeckillGoods(String id);
}

