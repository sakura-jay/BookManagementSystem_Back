package com.advancedjavawork.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookVo {
    private Integer pageNum;
    private Integer pageSize;
    private String bookTitle;
    private String bookAuthor;
    private String bookPress;
    private LocalDate startTime;
    private LocalDate endTime;
}
