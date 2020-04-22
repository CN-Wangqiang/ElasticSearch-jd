package com.wangqiang.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version : V1.0
 * @ClassName: Content
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/4/22 17:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Content {
    private String title;
    private String img;
    private String price;
    private String shop;

}
