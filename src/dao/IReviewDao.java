package dao;

import java.util.HashMap;
import java.util.List;

public interface IReviewDao {

	public List<HashMap<String, String>> topSelectFestival();
	
	public List<HashMap<String, String>> topSelectCountry();
	
	public List<HashMap<String, String>> topSelectRestaurant();
}
