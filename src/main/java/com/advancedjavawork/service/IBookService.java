package com.advancedjavawork.service;

import com.advancedjavawork.entity.Book;
import com.advancedjavawork.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    List<Book> getAllBook();

    CompletableFuture<String> save(List<Book> books,String fileName);
}
