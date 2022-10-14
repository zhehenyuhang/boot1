package com.yuhang.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuhang.controller.util.R;
import com.yuhang.domain.Book;
import com.yuhang.service.IBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;
import java.io.IOException;
import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;

    @GetMapping
    public R getAll(){
        return new R(true,bookService.list());
    }

    @PostMapping
    public R save(@RequestBody Book book) throws IOException {
        if (book.getName().equals("123")) throw new IOException();
        boolean flag = bookService.save(book);
        return new R(flag,flag ? "添加成功XD" : "添加失败QAQ");
    }


    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(bookService.removeById(id));
    }

    @PutMapping
    public R update(@RequestBody  Book book){
        return new R(bookService.updateById(book));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,bookService.getById(id));
    }

    // @GetMapping("{currentPage}/{pageSize}")
    // public R getPage(@PathVariable int currentPage, @PathVariable int pageSize){
    //     IPage<Book> page = bookService.getPage(currentPage, pageSize);
    //     //当前页查询大于总页数，使用最后一页查询
    //     if (currentPage > page.getPages()){
    //         page = bookService.getPage((int)page.getPages(), pageSize);
    //     }
    //     return new R(true,page);
    // }

    @GetMapping("{currentPage}/{pageSize}")
    public R getPage(@PathVariable int currentPage, @PathVariable int pageSize,Book book){

        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        //当前页查询大于总页数，使用最后一页查询
        if (currentPage > page.getPages()){
            page = bookService.getPage((int)page.getPages(), pageSize,book);
        }
        return new R(true,page);
    }
}
