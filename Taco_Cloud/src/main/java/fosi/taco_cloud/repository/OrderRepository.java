package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.TacoOrder;

public interface OrderRepository
{
    TacoOrder save(TacoOrder order);
}
