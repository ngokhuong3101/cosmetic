package cosmetic.usecase;

public interface OutputBoundary<Res extends ResponseData> {
	void present(Res responseData); 
	void presentError(String message);
}
