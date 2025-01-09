package com.advancedjavawork.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author qihui
 * @since 2025-01-07
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("book")
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "book_id", type = IdType.AUTO)
    @Alias("图书ID")
    private Integer bookId;
    @Alias("图书标题")
    private String bookTitle;
    @Alias("图书作者")
    private String bookAuthor;
    @Alias("图书库存")
    private Integer bookNumber;
    @Alias("出版时间")
    private LocalDate publishTime;
    @Alias("出版社")
    private String bookPress;
}
