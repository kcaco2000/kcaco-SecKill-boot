package top.kcaco.seckill.modules.skactivity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skactivity.domain.entity.SeckillActivity;
import top.kcaco.seckill.modules.skactivity.domain.request.SeckillActivityPageReq;

/**
 * 秒杀活动(SeckillActivity)表服务接口
 *
 * @author kcaco
 */
public interface SeckillActivityService extends IService<SeckillActivity> {

    /**
     * 分页查询
     *
     * @param seckillActivityPageReq 请求体
     * @return 分页查询结果
     */
    Page<SeckillActivity> listSeckillActivityPage(SeckillActivityPageReq seckillActivityPageReq);

    /**
     * 获取详情
     *
     * @param id 主键id
     * @return 详情
     */
    SeckillActivity getSeckillActivityDetail(String id);

    /**
     * 保存或更新
     *
     * @param seckillActivity 请求体
     */
    void saveOrUpdateSeckillActivity(SeckillActivity seckillActivity);

    /**
     * 删除
     *
     * @param id 主键
     */
    void deleteSeckillActivity(String id);
}

