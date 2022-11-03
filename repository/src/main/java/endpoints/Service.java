package endpoints;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import javax.transaction.Transactional;

//@Service
@Transactional
public class Service {
    private static ModellRepository modellRepository;
    public List<Modell> listAllModells() {
        return modellRepository.findAll();
    }

    public static void saveModell(Modell modell) {
        modellRepository.save(modell);
    }

    public static Modell getModell(Integer id) {
        return modellRepository.findById(id).get();
    }

    public static void deleteModell(Integer id) {
        modellRepository.deleteById(id);
    }
}
