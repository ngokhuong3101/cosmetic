package adapters;

import java.util.ArrayList;
import java.util.List;

import adapters.Subscriber.CheckoutSubscriber;
import cosmetic.entities.Order;

public class Publisher {

	    private final List<CheckoutSubscriber> subscribers = new ArrayList<>();

	    // Đăng ký subscriber
	    public void subscribe(CheckoutSubscriber s) {
	        if (s != null && !subscribers.contains(s)) {
	            subscribers.add(s);
	        }
	    }

	    // Hủy đăng ký
	    public void unsubscribe(CheckoutSubscriber s) {
	        subscribers.remove(s);
	    }

	    // Phát sự kiện khi thanh toán thành công
	    public void publish(Order order) {
	        for (CheckoutSubscriber s : subscribers) {
	            s.onCheckout(order);
	        }
	    }
	}


