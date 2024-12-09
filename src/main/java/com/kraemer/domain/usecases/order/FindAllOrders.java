package com.kraemer.domain.usecases.order;

import java.util.List;

import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.mappers.OrderMapper;
import com.kraemer.domain.entities.repositories.IOrderRepository;

public class FindAllOrders {
    
    private IOrderRepository repository;

    public FindAllOrders(IOrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderDTO> execute() {
        return repository.findAll()
                    .stream()
                    .map(OrderMapper::toDTO)
                    .toList();
    }

}
