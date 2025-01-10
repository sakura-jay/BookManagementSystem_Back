package com.advancedjavawork.vo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserVo {
    private Integer pageNum;
    private Integer pageSize;
    private LocalDate startTIme;
    private LocalDate endTIme;
    private String phone;
    private String userName;
}
