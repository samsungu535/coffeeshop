package com.trandofilidzi.coffeeshop.repository;

import com.trandofilidzi.coffeeshop.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {

}
