package com.ixchou.annotation;

import java.lang.annotation.*;

/**
 * <b>Author</b>: Xiang Liguo<br/>
 * <b>Date</b>: 2019/11/15 11:31<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: Controller子类中的主服务<br/>
 * <b>Description</b>:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MasterService {
}
