package adapters.checkout;

public class CheckoutViewModel {
	
	    private Long orderId;
	    private double totalAmount;
	    private String statusOrMessage;

	    public CheckoutViewModel(Long orderId, double totalAmount, String statusOrMessage) {
	        this.orderId = orderId;
	        this.totalAmount = totalAmount;
	        this.statusOrMessage = statusOrMessage;
	    }

	    // getters
	}


