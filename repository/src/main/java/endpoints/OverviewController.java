package endpoints;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/overview")
public class OverviewController {
    @Autowired
    Service modellService;

    @GetMapping("")
    public List<Overview> list() {
        return modellService.listAllOverviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Overview> get(@PathVariable Integer id) {
        try {
            Overview overview = Service.getOverview(id);
            return new ResponseEntity<Overview>(overview, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Overview>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Overview overview) {
        Service.saveOverview(overview);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Overview overview, @PathVariable Integer id) {
        try {
           
            overview.setId(id);            
            Service.saveOverview(overview);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        Service.deleteOverview(id);
    }
}
