package adapters;
import cosmetic.entities.Order;
public class Subscriber {

	public interface CheckoutSubscriber {
	    void onCheckout(Order order);
	}


}
