package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.IReviewDao;
@Service
public class ReviewService {
	
	@Autowired
	private IReviewDao rDao;
	
	public List<String> topSelect(String contentTypeID){
		return rDao.topSelect(contentTypeID);
	}
	
}
