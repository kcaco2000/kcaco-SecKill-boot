package top.kcaco.seckill.modules.skorder.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.modules.skorder.domain.request.SeckillOrderPageReq;
import top.kcaco.seckill.modules.skorder.mapper.SeckillOrderMapper;
import top.kcaco.seckill.modules.skorder.domain.entity.SeckillOrder;
import top.kcaco.seckill.modules.skorder.service.SeckillOrderService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀订单表(SeckillOrder)表服务实现类
 *
 * @author kcaco
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements SeckillOrderService {

    @Override
    public Page<SeckillOrder> listSeckillOrderPage(SeckillOrderPageReq pageReq) {
        Page<SeckillOrder> page = new Page<>(pageReq.getPageNum(), pageReq.getPageSize());
        Page<SeckillOrder> resultPage = baseMapper.listSeckillOrderPage(page, pageReq);
        return resultPage;
    }

    @Override
    public SeckillOrder getSeckillOrderDetail(String id) {
        return null;
    }

    @Override
    public void saveOrUpdateSeckillOrder(SeckillOrder seckillOrder) {
        seckillOrder.check();

        if (StrUtil.isBlank(seckillOrder.getId())) {

            baseMapper.insert(seckillOrder);
        } else {

            baseMapper.updateById(seckillOrder);
        }

    }

    @Override
    public void deleteSeckillOrder(String id) {
        if (StrUtil.isBlank(id)) {
            throw new RuntimeException("请选择要删除的数据");
        }

        throw new RuntimeException("暂时不支持删除");
    }


}

