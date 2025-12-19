package usecase;
import cosmetic.entities.Cart;
import cosmetic.entities.CartItem;
import cosmetic.entities.Product;
import cosmetic.entities.User;
import cosmetic.entities.Order;
import cosmetic.repository.CartRepository;
import cosmetic.repository.OrderRepository;
import cosmetic.usecase.checkout.CheckoutReq;
import cosmetic.usecase.checkout.CheckoutRes;
import cosmetic.usecase.checkout.CheckoutUseCase;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;



public class TestCheckOutUseCase {


	    // Repository giả cho Cart
	    class MockCartRepository implements CartRepository {
	        private Cart cart;
	        public void setCart(Cart cart) { this.cart = cart; }
	        @Override public Cart findByUserId(Long userId) { return cart; }
	        @Override public void clear(Long userId) { cart = null; }
	        @Override public void save(Cart cart) { this.cart = cart; }
	        @Override public void deleteByUserId(Long userId) { this.cart = null;}
	    }

	    // Repository giả cho Order
	    class MockOrderRepository implements OrderRepository {
	        boolean saveCalled = false;
	        @Override public void save(Order order) { saveCalled = true; }
	        @Override public Order findById(Long id) { return null; }
	        @Override public List<Order> findByUserId(Long userId) { return null; }
	        @Override public List<Order> findAll() { return null; }
	        @Override public User findUserById(Long userId) { return null; }
	        @Override public Cart findCartByUserId(Long userId) { return null; }
	        @Override public void deleteCart(Long userId) {}
	    }

	    @Test
	    public void testCheckout_ThanhCong() {
	        MockCartRepository cartRepo = new MockCartRepository();
	        MockOrderRepository orderRepo = new MockOrderRepository();
	        CheckoutUseCase useCase = new CheckoutUseCase(cartRepo, orderRepo);

	        // Giỏ hàng có sản phẩm
	        Cart cart = new Cart();
	        List<CartItem> items = new ArrayList<>();
	        items.add(new CartItem(new Product(1L, "Son", 100.0, 10, 1L), 2));
	        cart.addItem(new Product(1L, "Son", 100.0, 10, 1L), 2);
	        cartRepo.setCart(cart);

	        CheckoutReq req = new CheckoutReq(1L, "HCM", "0123456789", "COD");
	        User user = new User(1L, "username", "email@example.com", "password", "fullName", null);

	        CheckoutRes res = useCase.execute(req, user);

	        assertNotNull(res);
	        assertEquals(200.0, res.getTotalAmount());
	        assertEquals("CREATED", res.getStatus());
	        assertTrue(orderRepo.saveCalled);
	    }

	    @Test
	    public void testCheckout_ThatBai_DoGioHangTrong() {
	        MockCartRepository cartRepo = new MockCartRepository();
	        MockOrderRepository orderRepo = new MockOrderRepository();
	        CheckoutUseCase useCase = new CheckoutUseCase(cartRepo, orderRepo);

	        cartRepo.setCart(new Cart()); // giỏ hàng trống

	        CheckoutReq req = new CheckoutReq(1L, "HCM", "0123456789", "COD");
	        User user = new User(1L, "username", "email@example.com", "password", "fullName", null);

	        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
	            useCase.execute(req, user);
	        });

	        assertEquals("Giỏ hàng trống", ex.getMessage());
	        assertFalse(orderRepo.saveCalled);
	    }

	    @Test
	    public void testCheckout_ThatBai_DoThieuThongTin() {
	        MockCartRepository cartRepo = new MockCartRepository();
	        MockOrderRepository orderRepo = new MockOrderRepository();
	        CheckoutUseCase useCase = new CheckoutUseCase(cartRepo, orderRepo);

	        // Giỏ hàng có sản phẩm
	        Cart cart = new Cart();
	        List<CartItem> items = new ArrayList<>();
	        items.add(new CartItem(new Product(1L, "Son", 100.0, 10, 1L), 1));
	        cart.addItem(new Product(1L, "Son", 100.0, 10, 1L), 1);
	        cartRepo.setCart(cart);

	        // Request thiếu address và phone
	        CheckoutReq req = new CheckoutReq(1L, "", "", "COD");
	        User user = new User(1L, "username", "email@example.com", "password", "fullName", null);

	        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
	            useCase.execute(req, user);
	        });

	        assertEquals("Dữ liệu checkout không hợp lệ", ex.getMessage());
	        assertFalse(orderRepo.saveCalled);
	    }
	}

	


