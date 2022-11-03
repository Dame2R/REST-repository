package modellController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/Modell")
public class ModellController {
    @Autowired
    Service modellService;

    @GetMapping("")
    public List<Modell> list() {
        return modellService.listAllModells();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modell> get(@PathVariable Integer id) {
        try {
            Modell modell = Service.getModell(id);
            return new ResponseEntity<Modell>(modell, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Modell>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/")
    public void add(@RequestBody Modell modell) {
        Service.saveModell(modell);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Modell modell, @PathVariable Integer id) {
        try {
           
            modell.setId(id);            
            Service.saveModell(modell);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        }
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {

        Service.deleteModell(id);
    }
}