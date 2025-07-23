package top.kcaco.seckill.modules.skactivity.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import top.kcaco.seckill.modules.skactivity.domain.request.SeckillActivityPageReq;
import top.kcaco.seckill.modules.skactivity.mapper.SeckillActivityMapper;
import top.kcaco.seckill.modules.skactivity.domain.entity.SeckillActivity;
import top.kcaco.seckill.modules.skactivity.service.SeckillActivityService;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * 秒杀活动(SeckillActivity)表服务实现类
 *
 * @author kcaco
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SeckillActivityServiceImpl extends ServiceImpl<SeckillActivityMapper, SeckillActivity> implements SeckillActivityService {

    @Override
    public Page<SeckillActivity> listSeckillActivityPage(SeckillActivityPageReq pageReq) {
        Page<SeckillActivity> page = new Page<>(pageReq.getPageNum(), pageReq.getPageSize());
        Page<SeckillActivity> resultPage = baseMapper.listSeckillActivityPage(page, pageReq);
        return resultPage;
    }

    @Override
    public SeckillActivity getSeckillActivityDetail(String id) {
        return null;
    }

    @Override
    public void saveOrUpdateSeckillActivity(SeckillActivity seckillActivity) {
        seckillActivity.check();

        if (StrUtil.isBlank(seckillActivity.getId())) {

            baseMapper.insert(seckillActivity);
        } else {

            baseMapper.updateById(seckillActivity);
        }

    }

    @Override
    public void deleteSeckillActivity(String id) {
        if (StrUtil.isBlank(id)) {
            throw new RuntimeException("请选择要删除的数据");
        }

        throw new RuntimeException("暂时不支持删除");
    }


}

