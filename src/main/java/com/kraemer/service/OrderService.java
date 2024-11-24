package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.OrderDTO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.usecases.order.CreateOrder;
import com.kraemer.domain.usecases.order.DeleteOrder;
import com.kraemer.domain.usecases.order.FindAllOrders;
import com.kraemer.domain.usecases.order.UpdateOrder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrderService extends AbstractService {

    public List<OrderDTO> findAll(EnumDBImpl dbImpl) {
        var repository = dbFactory.getOrderRepositoryImpl(dbImpl);

        var findAllOrders = new FindAllOrders(repository);

        return findAllOrders.execute();
    } 

    @Transactional
    public OrderDTO create(OrderDTO dto, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOrderRepositoryImpl(dbImpl);

        var createOrder = new CreateOrder(repository);

        return createOrder.execute(dto);
    }

    @Transactional
    public OrderDTO update(OrderDTO dto, Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOrderRepositoryImpl(dbImpl);

        var updateOrder = new UpdateOrder(repository);

        return updateOrder.execute(dto, id);
    }

    @Transactional
    public void delete(Long id, EnumDBImpl dbImpl) {
        var repository = dbFactory.getOrderRepositoryImpl(dbImpl);

        var deleteOrder = new DeleteOrder(repository);

        deleteOrder.execute(id);
    } 

}
