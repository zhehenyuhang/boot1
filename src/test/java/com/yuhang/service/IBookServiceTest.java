package com.yuhang.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuhang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IBookServiceTest {

    @Autowired
    private IBookService bookService;

    @Test
    void testGetById(){
        System.out.println(bookService.getById(5));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setMoney(100);
        book.setName("水浒");
        bookService.save(book);
    }

    @Test
    void testDelect(){
        bookService.removeById(5);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(3);
        book.setMoney(60);
        book.setName("三国2");
        bookService.updateById(book);
    }

    @Test
    void testGetAll(){
        bookService.list();
    }

    @Test
    void testGetByPage(){

        IPage<Book> page = new Page<>(1,2);
        bookService.page(page);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }


}
