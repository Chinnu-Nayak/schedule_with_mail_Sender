package com.chinu.Service;

import com.chinu.Entity.Order;
import com.chinu.Repo.OrderRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @PostConstruct
    public void initDataToDb(){
        List<Order> collect = IntStream.range(1, 5).mapToObj(
                i -> new Order("order"+String.valueOf(i), new Random().nextInt(10),
                        new Random().nextDouble()*100)).collect(Collectors.toList());
        orderRepo.saveAll(collect);
    }

    public Order  saveOrder(Order order){
        return orderRepo.save(order);
    }
}
