package cosmetic.usecase.checkout;

import cosmetic.entities.Cart;
import cosmetic.entities.Order;
import cosmetic.entities.User;
import cosmetic.repository.CartRepository;
import cosmetic.repository.OrderRepository;

public class CheckoutUseCase {
	
	    private final CartRepository cartRepo;
	    private final OrderRepository orderRepo;

	    public CheckoutUseCase(CartRepository cartRepo, OrderRepository orderRepo) {
	        this.cartRepo = cartRepo;
	        this.orderRepo = orderRepo;
	    }

	    public Order execute(Long userId, User user, String address, String phone, String paymentMethod) {
	        Cart cart = cartRepo.findByUserId(userId);
	        if (cart == null || cart.isEmpty()) {
	            throw new RuntimeException("Giỏ hàng trống");
	        }

	        Order order = Order.createFromCart(cart, user, address, phone, paymentMethod);
	        orderRepo.save(order);
	        cartRepo.clear(userId);

	        return order;
	    }
	}

	


