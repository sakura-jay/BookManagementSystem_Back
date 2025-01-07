package com.advancedjavawork.service;

import com.advancedjavawork.entity.Book;
import com.advancedjavawork.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author qihui
 * @since 2025-01-07
 */
public interface IBookService extends IService<Book> {

    Page<Book> getBookList(BookVo vo);
}
