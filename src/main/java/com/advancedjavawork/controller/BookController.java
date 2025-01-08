package com.advancedjavawork.controller;

import com.advancedjavawork.entity.Book;
import com.advancedjavawork.service.IBookService;
import com.advancedjavawork.utils.Result;
import com.advancedjavawork.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author qihui
 * @since 2025-01-07
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Resource
    private IBookService bookService;

    @GetMapping("/bookList")
    public Result bookList(BookVo vo){
        if (vo.getPageNum() == null || vo.getPageSize() == null) return Result.fail();
        Page<Book> page = bookService.getBookList(vo);
        return Result.success(page);
    }
}
