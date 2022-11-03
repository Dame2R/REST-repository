package endpoints;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import javax.transaction.Transactional;

//@Service
@Transactional
public class Service {
    private static OverviewRepository overviewRepository;
    public List<Overview> listAllOverviews() {
        return overviewRepository.findAll();
    }

    public static void saveOverview(Overview overview) {
        overviewRepository.save(overview);
    }

    public static Overview getOverview(Integer id) {
        return overviewRepository.findById(id).get();
    }

    public static void deleteOverview(Integer id) {
        overviewRepository.deleteById(id);
    }
}
