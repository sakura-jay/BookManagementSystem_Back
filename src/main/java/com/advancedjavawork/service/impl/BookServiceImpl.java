package com.advancedjavawork.service.impl;

import com.advancedjavawork.entity.Book;
import com.advancedjavawork.mapper.BookMapper;
import com.advancedjavawork.service.IBookService;
import com.advancedjavawork.vo.BookVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author qihui
 * @since 2025-01-07
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {
    @Resource
    private BookMapper mapper;
    @Override
    public Page<Book> getBookList(BookVo vo) {
        Page<Book> page = new Page<>(vo.getPageNum(),vo.getPageSize());
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        if (vo.getBookTitle()!=null&&!"".equals(vo.getBookTitle())){
            wrapper.like("book_title",vo.getBookTitle());
        }
        if (vo.getBookAuthor()!=null&&"".equals(vo.getBookAuthor())){
            wrapper.like("book_author",vo.getBookAuthor());
        }
        if (vo.getBookPress()!=null&&!"".equals(vo.getBookPress())){
            wrapper.like("book_press",vo.getBookPress());
        }
        if (vo.getStartTime()!=null&&!"".equals(vo.getStartTime())){
            wrapper.between("publish_time",vo.getStartTime(),vo.getEndTime());
        }

        return mapper.selectPage(page, wrapper);
    }

    @Override
    public List<Book> getAllBook() {
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        return mapper.selectList(wrapper);
    }

    @Override
    @Async("async")
    @SneakyThrows
    public CompletableFuture<String> save(List<Book> books,String fileName) {
        for (Book b:books){
            mapper.insert(b);
        }
        return CompletableFuture.completedFuture("文件"+fileName+"写入成功！");
    }
}
