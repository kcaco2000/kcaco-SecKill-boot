package top.kcaco.seckill.modules.skgoods.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.kcaco.seckill.modules.skgoods.domain.entity.SeckillGoods;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skgoods.domain.request.SeckillGoodsPageReq;

/**
 * 秒杀商品(SeckillGoods)表数据库访问层
 *
 * @author kcaco
 */
public interface SeckillGoodsMapper extends BaseMapper<SeckillGoods> {

    /**
     * 分页查询
     *
     * @param page    分页
     * @param pageReq 请求参数
     */
    Page<SeckillGoods> listSeckillGoodsPage(Page<SeckillGoods> page,
                                            @Param("request") SeckillGoodsPageReq pageReq);


}

