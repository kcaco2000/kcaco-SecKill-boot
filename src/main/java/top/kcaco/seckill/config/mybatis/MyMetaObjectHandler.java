package top.kcaco.seckill.config.mybatis;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 插入时的填充策略
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        // 起始版本 3.3.3   推荐
        this.strictInsertFill(metaObject, "createBy", StpUtil::getLoginIdAsString, String.class);
        this.strictInsertFill(metaObject, "createTime", Date::new, Date.class);
        this.strictInsertFill(metaObject, "updateBy", StpUtil::getLoginIdAsString, String.class);
        this.strictInsertFill(metaObject, "updateTime", Date::new, Date.class);
    }

    /**
     * 更新时的填充策略
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        // 起始版本 3.3.3   推荐
        this.strictUpdateFill(metaObject, "updateBy", StpUtil::getLoginIdAsString, String.class);
        this.strictUpdateFill(metaObject, "updateTime", Date::new, Date.class);
    }

}
