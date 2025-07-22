package top.kcaco.seckill.common.base;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 基本分页请求体
 *
 * @author kcaco
 */
@Data
public class BasePageReq implements Serializable {

    /**
     * 当前页
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;

}
