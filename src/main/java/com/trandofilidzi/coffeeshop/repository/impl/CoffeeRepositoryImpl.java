package com.trandofilidzi.coffeeshop.repository.impl;

import com.trandofilidzi.coffeeshop.model.Coffee;
import com.trandofilidzi.coffeeshop.repository.CoffeeRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class CoffeeRepositoryImpl implements CoffeeRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoffeeRepositoryImpl.class);

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public Coffee getCoffeeById(Long id) {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        Coffee coffee = session.load(Coffee.class, new Long(id));
        LOGGER.info("Coffee has loaded. Coffee details: = {}", coffee);
        return coffee;
    }

    @Override
    public List<Coffee> listCoffee() {
        Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
        List<Coffee> coffeeList = session.createQuery("from Coffee").list();
        LOGGER.info("All coffee has loaded from db. Total list size = {}", coffeeList.size());
        return coffeeList;
    }
}
