package top.kcaco.seckill.modules.skgoods.domain.entity;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.kcaco.seckill.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品(SeckillGoods)表实体类
 *
 * @author kcaco
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "seckill_goods")
public class SeckillGoods extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private String activityId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 副标题
     */
    @TableField(value = "sub_title")
    private String subTitle;

    /**
     * 富文本介绍
     */
    @TableField(value = "introduction")
    private String introduction;

    /**
     * 初始库存
     */
    @TableField(value = "initial_stock")
    private Integer initialStock;

    /**
     * 可用库存
     */
    @TableField(value = "available_stock")
    private Integer availableStock;

    /**
     * 库存预热标识
     */
    @TableField(value = "stock_warm_up")
    private Integer stockWarmUp;

    /**
     * 原价
     */
    @TableField(value = "original_price")
    private Double originalPrice;

    /**
     * 秒杀价格
     */
    @TableField(value = "seckill_price")
    private Double seckillPrice;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 秒杀可配规则，JSON格式
     */
    @TableField(value = "rules")
    private String rules;

    /**
     * 状态,见枚举
     */
    @TableField(value = "status")
    private String status;


    public void check() {
        if (StrUtil.isBlank(activityId)) {
            throw new RuntimeException("活动id不能为空");
        }

        if (StrUtil.isBlank(title)) {
            throw new RuntimeException("标题不能为空");
        }

        if (StrUtil.isBlank(subTitle)) {
            throw new RuntimeException("副标题不能为空");
        }

        if (StrUtil.isBlank(introduction)) {
            throw new RuntimeException("富文本介绍不能为空");
        }

        if (ObjectUtil.isNull(initialStock)) {
            throw new RuntimeException("初始库存不能为空");
        }

        if (ObjectUtil.isNull(availableStock)) {
            throw new RuntimeException("可用库存不能为空");
        }

        if (ObjectUtil.isNull(stockWarmUp)) {
            throw new RuntimeException("库存预热标识不能为空");
        }

        if (ObjectUtil.isNull(originalPrice)) {
            throw new RuntimeException("原价不能为空");
        }

        if (ObjectUtil.isNull(seckillPrice)) {
            throw new RuntimeException("秒杀价格不能为空");
        }

        if (ObjectUtil.isNull(startTime)) {
            throw new RuntimeException("开始时间不能为空");
        }

        if (ObjectUtil.isNull(endTime)) {
            throw new RuntimeException("结束时间不能为空");
        }

        if (StrUtil.isBlank(rules)) {
            throw new RuntimeException("秒杀可配规则，JSON格式不能为空");
        }

        if (StrUtil.isBlank(status)) {
            throw new RuntimeException("状态,见枚举不能为空");
        }
    }

}

