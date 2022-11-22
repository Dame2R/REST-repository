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

import repo.model.Overview;
import repo.service.OverviewService;

@RestController
@RequestMapping()
public class OverviewController {
	
	private OverviewService overviewService;

	public OverviewController(OverviewService overviewService) {
		super();
		this.overviewService = overviewService;
	}

	@GetMapping("/overviews")
	public List<Overview> getAllOverviews(){
		return overviewService.getAllOverviews();
	}

	@GetMapping("/overview/{id}")
	public ResponseEntity<Overview> getOverviewById(@PathVariable("id") long overviewId){
		return new ResponseEntity<Overview>(overviewService.getOverviewById(overviewId), HttpStatus.OK);
	}


	/** Ungenutzte Methoden vorerst auskommentiert.

	 @PostMapping("/overview")
	         public ResponseEntity<Overview> saveOverview(@RequestBody Overview overview){
	  		return new ResponseEntity<Overview>(overviewService.saveOverview(overview), HttpStatus.CREATED);
	     }
	@PutMapping("/overview/{id}")
	public ResponseEntity<Overview> updateOverview(@PathVariable("id") long id
												  , @RequestBody Overview overview){
		return new ResponseEntity<Overview>(overviewService.updateOverview(overview, id), HttpStatus.OK);
	}

	@DeleteMapping("/overview/{id}")
	public ResponseEntity<String> deleteOverview(@PathVariable("id") long id){

		overviewService.deleteOverview(id);
		
		return new ResponseEntity<String>("Overview deleted successfully!.", HttpStatus.OK);
	}
	*/
}
