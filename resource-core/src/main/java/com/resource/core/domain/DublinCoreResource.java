package com.resource.core.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class DublinCoreResource {
    /**
     * 资源唯一id
     */
    private String id;
    /**
     * 赋予资源的名称
     */
    private String title;
    /**
     * 创建者
     * 创建资源内容的主要责任者。
     */
    private String creator;
    /**
     * 主题
     * 资源内容的主题描述。
     */
    private String subject;
    /**
     * 描述
     * 资源内容的解释。
     */
    private String description;
    /**
     * 出版者
     * 使资源成为可获得的责任实体。
     */
    private String publisher;
    /**
     * 其他责任者
     * 资源生存期中做出贡献的其他实体，除制作者/创作者之外的其他撰稿人和贡献者，如插图绘制者、编辑等。
     */
    private List<String> contributor;
    /**
     * 日期
     * 资源生存周期中的一些事件的相关时间
     */
    private Date date;
    /**
     * 类型
     * 资源所属的类别，包括种类、体裁、作品级别等描述性术语。
     */
    private String type;
    /**
     * 格式
     * 资源的物理或数字表现，可包括媒体类型或资源容量，可用于限定资源显示或操作所需要的软件、硬件或其他设备，容量表示数据所占的空间大小等。
     */
    private String format;
    /**
     * 标识符
     * 资源的唯一标识，如URI（统一资源标识符）、URL（统一资源定位符）、DoI（数字对象标识符）、ISBN（国际标准书号）、ISSN（国际标准刊号）等。
     */
    private String identifier;
    /**
     * 语种
     * 描述资源知识内容的语种
     */
    private String language;
    /**
     * 来源
     * 对当前资源来源的参照。
     * 使用来源标识符identifier进行关联
     */
    private String source;
    /**
     * 关联
     * 与其他资源的索引关系，用来标识系统来标引参考的相关资源。
     */
    private List<String> relation;
    /**
     * 覆盖范围
     * 资源应用的范围，包括空间位置（地名或地理坐标）、时代（年代、Et期或日期范围）或权限范围。
     */
//    private Coverage coverage;
    /**
     * 权限
     * 使用资源的权限信息，它包括知识产权、著作权和各种拥有权。如果没有此项，则表明放弃上述权力。
     */
//    private Rights rights;
}
