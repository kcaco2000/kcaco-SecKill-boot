package top.kcaco.seckill.modules.skactivity.domain.entity;


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
 * 秒杀活动(SeckillActivity)表实体类
 *
 * @author kcaco
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "seckill_activity")
public class SeckillActivity extends BaseEntity implements Serializable {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 描述
     */
    @TableField(value = "description")
    private String description;

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
     * 状态,见枚举
     */
    @TableField(value = "status")
    private String status;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    private Integer deleted;

    public void check() {
        if (StrUtil.isBlank(title)) {
            throw new RuntimeException("标题不能为空");
        }

        if (StrUtil.isBlank(description)) {
            throw new RuntimeException("描述不能为空");
        }

        if (ObjectUtil.isNull(startTime)) {
            throw new RuntimeException("开始时间不能为空");
        }

        if (ObjectUtil.isNull(endTime)) {
            throw new RuntimeException("结束时间不能为空");
        }

        if (StrUtil.isBlank(status)) {
            throw new RuntimeException("状态,见枚举不能为空");
        }
    }

}

