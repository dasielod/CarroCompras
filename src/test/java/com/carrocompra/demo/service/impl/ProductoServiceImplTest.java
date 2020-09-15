package com.carrocompra.demo.service.impl;

import com.carrocompra.demo.DemoApplication;
import com.carrocompra.demo.domain.Product;
import com.carrocompra.demo.exceptions.ProductException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ProductServiceImplTest implements TestConstants {

    @Autowired
    private ProductServiceImpl service;

    @Before
    public void setUp() throws Exception {

    }

    @Test(expected = InvalidDataAccessApiUsageException.class)
    public void findOneById() throws ProductException {
      Product prod =  service.findById(null);
      }
}