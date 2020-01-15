package com.hanhuide.core.input;

import lombok.Data;

/**
 * @ProjectName: maven
 * @Package: com.hanhuide.core.controller
 * @ClassName: PageInput
 * @Author: 韩惠德
 * @Description:
 * @Date: 2020/1/15 16:50
 * @Version: 1.0
 */
@Data
public class PageInput {
    private Integer current;
    private Integer num;
}
