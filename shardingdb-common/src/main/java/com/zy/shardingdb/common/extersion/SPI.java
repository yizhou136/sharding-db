package com.zy.shardingdb.common.extersion;

import java.lang.annotation.*;

/**
 * copy from Dubbo
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * 缺省扩展点名。
     */
    String value() default "";

}