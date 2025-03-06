package com.resource.api.endpoint;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * 基础操作终端控制器
 * namespace 命名空间
 * business  业务线
 */
@Data
public abstract class AbstractEndpoint {
    private String nameSpace;
    private String business;
    private String uri;

    @PostConstruct
    public void scanRequestMappings() {
        RequestMapping requestMapping = this.getClass().getAnnotation(RequestMapping.class);
        if (requestMapping != null) {
            String path = requestMapping.value()[0];
            // 去掉开头的 "/"
            String remainingPath = path.substring(1);

            // 找到第一个 "/" 的位置
            int firstSlashIndex = remainingPath.indexOf("/");

            // 提取第一个 "/" 后面的部分
            this.nameSpace = firstSlashIndex == -1 ? remainingPath : remainingPath.substring(0, firstSlashIndex);
            this.business = firstSlashIndex == -1 ? "" : remainingPath.substring(firstSlashIndex).replace("/", "-");
            this.uri = remainingPath.replace("/", "-");
        } else {
            System.out.println("No @RequestMapping annotation found on the class.");
        }
    }
}
