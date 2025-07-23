package top.kcaco.seckill.modules.skgoods.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.modules.skgoods.domain.request.SeckillGoodsPageReq;
import top.kcaco.seckill.modules.skgoods.mapper.SeckillGoodsMapper;
import top.kcaco.seckill.modules.skgoods.domain.entity.SeckillGoods;
import top.kcaco.seckill.modules.skgoods.service.SeckillGoodsService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀商品(SeckillGoods)表服务实现类
 *
 * @author kcaco
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods> implements SeckillGoodsService {

    @Override
    public Page<SeckillGoods> listSeckillGoodsPage(SeckillGoodsPageReq pageReq) {
        Page<SeckillGoods> page = new Page<>(pageReq.getPageNum(), pageReq.getPageSize());
        Page<SeckillGoods> resultPage = baseMapper.listSeckillGoodsPage(page, pageReq);
        return resultPage;
    }

    @Override
    public SeckillGoods getSeckillGoodsDetail(String id) {
        return null;
    }

    @Override
    public void saveOrUpdateSeckillGoods(SeckillGoods seckillGoods) {
        seckillGoods.check();

        if (StrUtil.isBlank(seckillGoods.getId())) {

            baseMapper.insert(seckillGoods);
        } else {

            baseMapper.updateById(seckillGoods);
        }

    }

    @Override
    public void deleteSeckillGoods(String id) {
        if (StrUtil.isBlank(id)) {
            throw new RuntimeException("请选择要删除的数据");
        }

        throw new RuntimeException("暂时不支持删除");
    }


}

