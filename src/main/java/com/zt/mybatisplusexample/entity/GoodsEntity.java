package com.zt.mybatisplusexample.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.zt.mybatisplusexample.entity.enums.GoodsFlagEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * @author zt
 * @since 2022-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shop_goods")
public class GoodsEntity extends Model<GoodsEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    @TableField("name")
    private String name;

    /**
     * 商品类别ID
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 库存数量
     */
    @TableField("stock_num")
    private Long stockNum;

    /**
     * 商品单价
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 上下架。0：下架；1：上架。默认0。
     */
    @TableField("flag")
    private GoodsFlagEnum flag;

    /**
     * 逻辑删除状态。0：正常；1：删除。默认0。
     */
    @TableField(value = "delete_status",fill =  FieldFill.INSERT)
    @TableLogic
    private Boolean deleteStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    /**
     * 版本。默认：0。
     */
    @TableField("version")
    @Version
    private Integer version;


}
