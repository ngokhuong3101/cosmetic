package cosmetic.repository;

import cosmetic.entities.Cart;
import cosmetic.entities.Order;
import cosmetic.entities.User;

import java.util.List;

public interface OrderRepository {
	void save(Order order);
    Order findById(Long id);
    List<Order> findByUserId(Long userId); 
    List<Order> findAll(); 
    Cart findCartByUserId(Long userId);
    void deleteCart(Long userId);
    User findUserById(Long userId);

}
