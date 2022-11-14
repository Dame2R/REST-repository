package repo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import repo.exception.ResourceNotFoundException;
import repo.model.Modell;
import repo.repository.ModellRepository;
import repo.service.ModellService;

@Service
public class ModellServiceImpl implements ModellService {

	private ModellRepository modellRepository;
	
	public ModellServiceImpl(ModellRepository modellRepository) {
		super();
		this.modellRepository = modellRepository;
	}

	@Override
	public Modell saveModell(Modell overview) {
		return modellRepository.save(overview);
	}

	@Override
	public List<Modell> getAllModells() {
		return modellRepository.findAll();
	}

	@Override
	public Modell getModellById(long id) {
//		Optional<Overview> overview = overviewRepository.findById(id);
//		if(overview.isPresent()) {
//			return overview.get();
//		}else {
//			throw new ResourceNotFoundException("Overview", "Id", id);
//		}
		return modellRepository.findById(id).orElseThrow(() ->
						new ResourceNotFoundException("Modell", "Id", id));
		
	}

	@Override
	public Modell updateModell(Modell modell, long id) {

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
	public void deleteModell(long id) {

		modellRepository.findById(id).orElseThrow(() ->
								new ResourceNotFoundException("Modell", "Id", id));
		modellRepository.deleteById(id);
	}
	
}
