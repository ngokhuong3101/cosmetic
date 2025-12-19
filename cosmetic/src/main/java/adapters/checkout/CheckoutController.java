package adapters.checkout;

import cosmetic.entities.Order;
import cosmetic.entities.User;
import cosmetic.usecase.checkout.CheckoutReq;
import cosmetic.usecase.checkout.CheckoutRes;
import cosmetic.usecase.checkout.CheckoutUseCase;

public class CheckoutController {
	
	    private final CheckoutUseCase checkoutUseCase;
	    private final CheckoutPresenter presenter;

	    public CheckoutController(CheckoutUseCase checkoutUseCase, CheckoutPresenter presenter) {
	        this.checkoutUseCase = checkoutUseCase;
	        this.presenter = presenter;
	    }

	    public CheckoutViewModel handle(CheckoutReq req, User user) {
	        try {
	            CheckoutRes res = checkoutUseCase.execute(req, user);
	            presenter.present(res);
	        } catch (Exception e) {
	            presenter.presentError(e.getMessage());
	        }
	        return presenter.getViewModel();
	    
	}
}
