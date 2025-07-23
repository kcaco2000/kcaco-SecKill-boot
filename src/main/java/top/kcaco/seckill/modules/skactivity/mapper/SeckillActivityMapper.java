package top.kcaco.seckill.modules.skactivity.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.kcaco.seckill.modules.skactivity.domain.entity.SeckillActivity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.kcaco.seckill.modules.skactivity.domain.request.SeckillActivityPageReq;

/**
 * 秒杀活动(SeckillActivity)表数据库访问层
 *
 * @author kcaco
 */
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    /**
     * 分页查询
     *
     * @param page    分页
     * @param pageReq 请求参数
     */
    Page<SeckillActivity> listSeckillActivityPage(Page<SeckillActivity> page,
                                                  @Param("request") SeckillActivityPageReq pageReq);


}

