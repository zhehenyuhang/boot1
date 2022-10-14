package com.yuhang.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuhang.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById(){
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setMoney(100);
        book.setName("西游记2");
        bookDao.insert(book);
    }

    @Test
    void testDelect(){
        bookDao.deleteById(4);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(4);
        book.setMoney(60);
        book.setName("水浒传");
        bookDao.updateById(book);
    }

    @Test
    void testGetAll(){
        // System.out.println(bookDao.selectList(null));
        /**
         * Creating a new SqlSession
         * SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34c70b5e] was not registered for synchronization because synchronization is not active
         * JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@72eb6200] will not be managed by Spring
         * ==>  Preparing: SELECT id,name,money FROM tbl_book
         * ==> Parameters:
         * <==    Columns: id, name, money
         * <==        Row: 1, 西游记, 100
         * <==        Row: 2, 红楼梦, 50
         * <==        Row: 3, 三国, 80
         * <==      Total: 3
         * Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@34c70b5e]
         * [Book(id=1, name=西游记, money=100), Book(id=2, name=红楼梦, money=50), Book(id=3, name=三国, money=80)]
         */
        bookDao.selectList(null);
    }

    @Test
    void testGetByPage(){
        IPage page = new Page(1,2);
        bookDao.selectPage(page,null);
    }

    @Test
    void testGetBy(){
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("name","西");
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy2(){
        String name = null;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();
        lqw.like(name != null,Book::getName,name);
        bookDao.selectList(lqw);
    }

}
