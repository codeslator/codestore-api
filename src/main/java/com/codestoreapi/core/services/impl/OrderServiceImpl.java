package com.codestoreapi.core.services.impl;

import com.codestoreapi.core.models.Order;
import com.codestoreapi.core.repositories.OrderRepository;
import com.codestoreapi.core.repositories.UserRepository;
import com.codestoreapi.core.services.OrderService;
import com.codestoreapi.vo.OrderVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Optional<List<OrderVO>> getAll() {
        List<Order> orders = orderRepository.findAll();
        List<OrderVO> mappedOrders = new ArrayList<>();
        orders.forEach(order -> mappedOrders.add(modelMapper.map(order, OrderVO.class)));
        return Optional.of(mappedOrders);
    }

    @Override
    public Optional<OrderVO> getById(Long id) {
        OrderVO mappedOrder = modelMapper.map(orderRepository.findById(id).get(), OrderVO.class);
        return Optional.of(mappedOrder);
    }

    @Override
    public Optional<OrderVO> save(OrderVO orderVO) {
        Order order = modelMapper.map(orderVO, Order.class);
        OrderVO mappedOrder = modelMapper.map(orderRepository.save(order), OrderVO.class);
        return Optional.of(mappedOrder);
    }

    @Override
    public Optional<OrderVO> update(OrderVO orderVO) {
        Order order = modelMapper.map(orderVO, Order.class);
        OrderVO mappedOrder = modelMapper.map(orderRepository.save(order), OrderVO.class);
        return Optional.of(mappedOrder);
    }

    @Override
    public Optional<Long> delete(Long id) {
        orderRepository.deleteById(id);
        return Optional.of(id);
    }

    @Override
    public Optional<List<OrderVO>> getAllByUser(Long userId) {
        List<Order> orders = orderRepository.findAllByUser(userRepository.findById(userId).get());
        List<OrderVO> mappedOrders = new ArrayList<>();
        orders.forEach(order -> mappedOrders.add(modelMapper.map(order, OrderVO.class)));
        return Optional.of(mappedOrders);
    }
}
