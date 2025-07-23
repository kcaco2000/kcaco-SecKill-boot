package top.kcaco.seckill.modules.skorder.domain.entity;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.kcaco.seckill.common.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀订单表(SeckillOrder)表实体类
 *
 * @author kcaco
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "seckill_order")
public class SeckillOrder extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    private String orderNo;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 秒杀商品id
     */
    @TableField(value = "seckill_goods_id")
    private String seckillGoodsId;

    /**
     * 活动id
     */
    @TableField(value = "activity_id")
    private String activityId;

    /**
     * 秒杀商品标题
     */
    @TableField(value = "seckill_goods_title")
    private String seckillGoodsTitle;

    /**
     * 秒杀价格
     */
    @TableField(value = "seckill_price")
    private Double seckillPrice;

    /**
     * 数量
     */
    @TableField(value = "quantity")
    private Double quantity;

    /**
     * 总金额
     */
    @TableField(value = "total_amount")
    private Double totalAmount;

    /**
     * 实付金额
     */
    @TableField(value = "actual_pay_amount")
    private Double actualPayAmount;

    /**
     * 支付类型，见枚举
     */
    @TableField(value = "pay_type")
    private String payType;

    /**
     * 状态,见枚举
     */
    @TableField(value = "status")
    private String status;

    /**
     * 取消原因
     */
    @TableField(value = "cancel_reason")
    private String cancelReason;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 逻辑删除
     */
    @TableLogic
    @TableField(value = "deleted")
    private Boolean deleted;

    public void check() {
        if (StrUtil.isBlank(orderNo)) {
            throw new RuntimeException("订单号不能为空");
        }

        if (StrUtil.isBlank(userId)) {
            throw new RuntimeException("用户id不能为空");
        }

        if (StrUtil.isBlank(seckillGoodsId)) {
            throw new RuntimeException("秒杀商品id不能为空");
        }

        if (StrUtil.isBlank(activityId)) {
            throw new RuntimeException("活动id不能为空");
        }

        if (StrUtil.isBlank(seckillGoodsTitle)) {
            throw new RuntimeException("秒杀商品标题不能为空");
        }

        if (ObjectUtil.isNull(seckillPrice)) {
            throw new RuntimeException("秒杀价格不能为空");
        }

        if (ObjectUtil.isNull(quantity)) {
            throw new RuntimeException("数量不能为空");
        }

        if (ObjectUtil.isNull(totalAmount)) {
            throw new RuntimeException("总金额不能为空");
        }

        if (ObjectUtil.isNull(actualPayAmount)) {
            throw new RuntimeException("实付金额不能为空");
        }

        if (StrUtil.isBlank(payType)) {
            throw new RuntimeException("支付类型，见枚举不能为空");
        }

        if (StrUtil.isBlank(status)) {
            throw new RuntimeException("状态,见枚举不能为空");
        }

        if (StrUtil.isBlank(cancelReason)) {
            throw new RuntimeException("取消原因不能为空");
        }

        if (StrUtil.isBlank(remark)) {
            throw new RuntimeException("备注不能为空");
        }

        if (ObjectUtil.isNull(deleted)) {
            throw new RuntimeException("逻辑删除不能为空");
        }

    }

}

