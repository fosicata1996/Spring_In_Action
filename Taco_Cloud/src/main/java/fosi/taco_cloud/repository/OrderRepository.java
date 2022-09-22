package fosi.taco_cloud.repository;

import fosi.taco_cloud.entity.TacoOrder;
import fosi.taco_cloud.entity.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long>
{
    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

    List<TacoOrder> findByDeliveryNameAndDeliveryCityAllIgnoreCase(String deliveryName, String deliveryCity);

    List<TacoOrder> findByDeliveryCityOrderByDeliveryName(String city);

    @Query("SELECT o from TacoOrder o where o.deliveryCity='Seattle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();

    List<TacoOrder> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

    /**
     * @ PostAuthorize ("hasRole('ADMIN') || returnObject.user.username == authentication.name")
     * TacoOrder getOrder(long id);
     */
}
