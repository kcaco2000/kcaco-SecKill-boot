package top.kcaco.seckill.modules.skorder.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.kcaco.seckill.modules.skorder.domain.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skorder.domain.request.SeckillOrderPageReq;

/**
 * 秒杀订单表(SeckillOrder)表数据库访问层
 *
 * @author kcaco
 * @since 2025-07-22 20:41:44
 */
public interface SeckillOrderMapper extends BaseMapper<SeckillOrder> {

    /**
     * 分页查询
     *
     * @param page    分页
     * @param pageReq 请求参数
     */
    Page<SeckillOrder> listSeckillOrderPage(Page<SeckillOrder> page,
                                            @Param("request") SeckillOrderPageReq pageReq);


}

