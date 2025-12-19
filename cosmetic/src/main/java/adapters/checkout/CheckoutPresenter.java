package adapters.checkout;

import cosmetic.usecase.checkout.CheckoutRes;

public class CheckoutPresenter {
	
	    private CheckoutViewModel viewModel;

	    public void present(CheckoutRes res) {
	        viewModel = new CheckoutViewModel(res.getOrderId(), res.getTotalAmount(), res.getStatus());
	    }

	    public void presentError(String message) {
	        viewModel = new CheckoutViewModel(null, 0, message);
	    }

	    public CheckoutViewModel getViewModel() {
	        return viewModel;
	    }
	}



