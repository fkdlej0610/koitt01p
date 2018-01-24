package service;

import java.util.List;

import dao.IReviewDAO;

public class ReviewService {

	private IReviewDAO rDao;
	
	public List<String> topSelect(String contentTypeID){
		return rDao.topSelect(contentTypeID);
	}
	
}
