package com.advancedjavawork.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.advancedjavawork.entity.Book;
import com.advancedjavawork.service.IBookService;
import com.advancedjavawork.utils.Result;
import com.advancedjavawork.vo.BookVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/outPut")
    public void outPut(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);//hutool提供的方法
        List<Book> list = bookService.getAllBook();
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode("图书信息表","UTF-8")+".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream,true);
        writer.close();
        outputStream.flush();
        outputStream.close();
    }

    /**
     *
     * @param files 传入的Excel文件对象
     */
    @PostMapping("/import")
    public Result importData(MultipartFile[] files) throws IOException,InterruptedException, ExecutionException {
        CompletableFuture<String>[] futures = new CompletableFuture[files.length];
        int i = 0;
        for (MultipartFile f:files){
            if (!f.isEmpty()){
                InputStream inputStream = f.getInputStream();
                ExcelReader reader = ExcelUtil.getReader(inputStream);
                List<Book> books = reader.readAll(Book.class);
                futures[i] = bookService.save(books,f.getName());
                i++;
//                bookService.saveBatch(books);
            }
        }
        //阻塞等待所有CompletableFuture响应结果
        CompletableFuture.allOf(futures);
        Map<String,String> result = new HashMap<>();
        for (int j = 0;j<files.length;j++){
            result.put("msg",futures[j].get());
        }
        return Result.success(result);
    }
}
