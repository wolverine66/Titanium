package servicesinterfaces.amazon;

public interface IHeaderService {

	public boolean isLogoDisplayed();
	
	public boolean isLocationDisplayed();
	
	public String getLocationText();
	
	public void selectLocationByPincode(String pincode);
	
	public void locationApply();
}
