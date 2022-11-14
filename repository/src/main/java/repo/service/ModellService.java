package repo.service;

import java.util.List;

import repo.model.Modell;

public interface ModellService {
	Modell saveModell(Modell overview);
	List<Modell> getAllModells();
	Modell getModellById(long id);
	Modell updateModell(Modell overview, long id);
	void deleteModell(long id);
}
