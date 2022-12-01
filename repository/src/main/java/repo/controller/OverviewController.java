package repo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import repo.dtos.OverviewDto;
import repo.exception.ResourceNotFoundException;
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





	@GetMapping("/overviewsTest")
	public List<Overview> getAllOverviewsWithProcessTypeCore(@RequestParam(required = false) String type) throws ResourceNotFoundException{

		if(type.equalsIgnoreCase("core")) {
			var allOverviews = overviewService.getAllOverviews();

			for (int i = 0; i < allOverviews.size(); i++) {
				String over = allOverviews.get(i).getProcessType();
				if (!over.equals("CORE")) {
					allOverviews.remove(i);
				}
			}
			return allOverviews;
		}
		throw new ResourceNotFoundException("Keine Core Prozesse","Fehler", null );

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
