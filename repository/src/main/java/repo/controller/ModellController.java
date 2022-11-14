package repo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import repo.model.Modell;
import repo.service.ModellService;

@RestController
@RequestMapping("/modell")
public class ModellController {
	
	private ModellService modellService;

	public ModellController(ModellService modellService) {
		super();
		this.modellService = modellService;
	}

	@PostMapping()
	public ResponseEntity<Modell> saveModell(@RequestBody Modell modell){
		return new ResponseEntity<Modell>(modellService.saveModell(modell), HttpStatus.CREATED);
	}

	@GetMapping
	public List<Modell> getAllModell(){
		return modellService.getAllModells();
	}

	@GetMapping("{id}")
	public ResponseEntity<Modell> getModellById(@PathVariable("id") long modellId){
		return new ResponseEntity<Modell>(modellService.getModellById(modellId), HttpStatus.OK);
	}

	@PutMapping("{id}")
	public ResponseEntity<Modell> updateModell(@PathVariable("id") long id
												  , @RequestBody Modell modell){
		return new ResponseEntity<Modell>(modellService.updateModell(modell, id), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteModell(@PathVariable("id") long id){

		modellService.deleteModell(id);
		
		return new ResponseEntity<String>("Modell deleted successfully!.", HttpStatus.OK);
	}
	
}
