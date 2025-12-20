package cosmetic.usecase.checkout;

import cosmetic.entities.Cart;
import cosmetic.entities.Order;
import cosmetic.entities.OrderStatus;
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

	    public CheckoutRes execute(CheckoutReq req, User user) {
	    	if (req.getAddress() == null || req.getAddress().isBlank()
	    			|| req.getPhone() == null || req.getPhone().isBlank()) {
	    		throw new RuntimeException("Dữ liệu checkout không hợp lệ");
	    	}
	        Cart cart = cartRepo.findByUserId(req.getUserId());
	        if (cart == null || cart.isEmpty()) {
	            throw new RuntimeException("Giỏ hàng trống");
	        }

	        Order order = Order.createFromCart(cart, user, req.getAddress(), req.getPhone(), req.getPaymentMethod());
	        
	        order.setStatus(OrderStatus.CREATED);
	        orderRepo.save(order);
	        cartRepo.clear(req.getUserId());
	        

	        return new CheckoutRes(order.getId(), order.getTotalAmount(), order.getStatus().name());
	    }
	}


	


