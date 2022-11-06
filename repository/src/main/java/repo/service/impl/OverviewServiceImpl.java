package repo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import repo.exception.ResourceNotFoundException;
import repo.model.Overview;
import repo.repository.OverviewRepository;
import repo.service.OverviewService;

@Service
public class OverviewServiceImpl implements OverviewService {

	private OverviewRepository overviewRepository;
	
	public OverviewServiceImpl(OverviewRepository overviewRepository) {
		super();
		this.overviewRepository = overviewRepository;
	}

	@Override
	public Overview saveOverview(Overview overview) {
		return overviewRepository.save(overview);
	}

	@Override
	public List<Overview> getAllOverviews() {
		return overviewRepository.findAll();
	}

	@Override
	public Overview getOverviewById(long id) {
//		Optional<Overview> overview = overviewRepository.findById(id);
//		if(overview.isPresent()) {
//			return overview.get();
//		}else {
//			throw new ResourceNotFoundException("Overview", "Id", id);
//		}
		return overviewRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Overview", "Id", id));
		
	}

	@Override
	public Overview updateOverview(Overview overview, long id) {

		// we need to check whether overview with given id is exist in DB or not
		//Overview existingOverview = overviewRepository.findById(id).orElseThrow(
		//		() -> new ResourceNotFoundException("Overview", "Id", id));

		//existingOverview.setID(overview.getID());
		//existingOverview.setName(overview.getName());
		// save existing overview to DB
		//overviewRepository.save(existingOverview);
		//return existingOverview;
	return null;
	}

	@Override
	public void deleteOverview(long id) {

		overviewRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Overview", "Id", id));
		overviewRepository.deleteById(id);
	}
	
}
