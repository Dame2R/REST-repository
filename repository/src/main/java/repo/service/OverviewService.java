package repo.service;

import java.util.List;

import repo.model.Overview;

public interface OverviewService {
	Overview saveOverview(Overview overview);
	List<Overview> getAllOverviews();
	Overview getOverviewById(long id);
	Overview updateOverview(Overview overview, long id);
	void deleteOverview(long id);
}
