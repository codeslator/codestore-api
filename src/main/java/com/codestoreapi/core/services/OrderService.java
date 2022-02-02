package com.codestoreapi.core.services;

import com.codestoreapi.core.models.Order;
import com.codestoreapi.vo.OrderVO;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<List<OrderVO>> getAll();
    Optional<OrderVO> getById(Long id);
    Optional<OrderVO> save(OrderVO orderVO);
    Optional<OrderVO> update(OrderVO orderVO);
    Optional<Long> delete(Long id);
    Optional<List<OrderVO>> getAllByUser(Long userId);
}
