package repo.controller;

import java.io.File;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import javax.sql.*;

import io.swagger.models.Xml;
import org.apache.maven.model.Model;
import org.cyberneko.html.parsers.DOMParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import repo.dtos.ModellDto;
import repo.dtos.OverviewDto;
import repo.model.Modell;
import repo.model.Overview;
import repo.service.ModellService;

import javax.sql.rowset.serial.SerialBlob;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

@RestController
@RequestMapping()
public class ModellController {
	
	private ModellService modellService;

	public ModellController(ModellService modellService) {
		super();
		this.modellService = modellService;
	}

	@PostMapping(path = "/modell", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public String saveModel(@RequestBody String xml){

		Modell modell = new Modell();

		try{

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			Document document = builder.parse(is);
			document.getDocumentElement().normalize();

			//id  auslesen und in modell schreiben
			Node nodeId = document.getElementsByTagName("process").item(0).getAttributes().item(0);
			modell.setId(nodeId.getNodeValue());

			//startEvent finden
			NodeList startEvent = document.getElementsByTagName("startEvent");
			Node startEventNodeName = startEvent.item(0); //start_knoten name finden
			modell.setStartKnoten(startEventNodeName.getAttributes().item(1).getNodeValue());

			//Wenn kein endEvent in der XML gefunden wurde, soll das Feld in der DB null bleiben
			if(document.getElementsByTagName("endEvent").getLength() != 0){
				NodeList endEvent = document.getElementsByTagName("endEvent");
				Node item1 = endEvent.item(0);
				modell.setEndKnoten(item1.getAttributes().item(1).getNodeValue());
			}

			//XML als BLOB einfügen
			modell.setXml(new SerialBlob(xml.getBytes()));


			//CO2 einfügen
			modell.setEnergySumYear(200);
			modell.setDepartment("tbd");
			modell.setProcessDescription("tbd");
			modell.setProcessType("tbd");
			modell.setParentProcess("tbd");

			modellService.saveModell(modell);



		}catch(Exception e){
			HttpStatus.BAD_REQUEST.toString();
		}
		return HttpStatus.CREATED.toString();
	}

	@GetMapping("/modell/{id}")
	public ResponseEntity<String> getModellById(@PathVariable("id") String modellId) throws SQLException {

		List<Modell> modelle = modellService.getAllModells();

		for(int i=0; i<modelle.size(); i++){
			if(modelle.get(i).getId().equals(modellId)){
				Blob blob = modelle.get(i).getXml();
				String string = new String(blob.getBytes(1, (int) blob.length()));
				return new ResponseEntity<String>(string, HttpStatus.OK);
			}

		}
		return new ResponseEntity<String>("Fehler!", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/overview/{id}")
	public ResponseEntity<OverviewDto> getOverviewById(@PathVariable("id") String overviewId){

		List<Modell> modelle = modellService.getAllModells();
		OverviewDto overviewDto = new OverviewDto();

		for(int i=0; i<modelle.size(); i++){
			if(modelle.get(i).getId().equals(overviewId)){
				overviewDto.setDepartment(modelle.get(i).getDepartment());
				overviewDto.setEndKnoten(modelle.get(i).getEndKnoten());
				overviewDto.setId(modelle.get(i).getId());
				overviewDto.setParentProcess(modelle.get(i).getParentProcess());
				overviewDto.setProcessDescription(modelle.get(i).getProcessDescription());
				overviewDto.setEnergySumYear(modelle.get(i).getEnergySumYear());
				overviewDto.setProcessType(modelle.get(i).getProcessType());
				overviewDto.setStartKnoten(modelle.get(i).getStartKnoten());
				return new ResponseEntity<OverviewDto>(overviewDto, HttpStatus.OK);

			}
		}
		return new ResponseEntity<OverviewDto>(overviewDto, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/overviews")
	public List<OverviewDto> getAllOverviewsWithOptionalRequestParam(@RequestParam(required = false) String sortBy){
		if(sortBy == null){
			return getAllOverviews();
		}
		else if(sortBy.equals("CO2")) {
			var allOverviews = getAllOverviews();
			allOverviews.sort( (o1, o2) -> o2.getEnergySumYear() - o1.getEnergySumYear());
			return allOverviews;
		}

		return getAllOverviews();

	}

	private List<OverviewDto> getAllOverviews(){

		List<Modell> allModells = modellService.getAllModells();
		List<OverviewDto> overviewDtos = new ArrayList<>();

		for(int i=0; i<allModells.size(); i++){
			OverviewDto overviewDto = new OverviewDto();
			overviewDto.setStartKnoten(allModells.get(i).getStartKnoten());
			overviewDto.setDepartment(allModells.get(i).getDepartment());
			overviewDto.setProcessType(allModells.get(i).getProcessType());
			overviewDto.setId(allModells.get(i).getId());
			overviewDto.setEnergySumYear(allModells.get(i).getEnergySumYear());
			overviewDto.setProcessDescription(allModells.get(i).getProcessDescription());
			overviewDto.setParentProcess(allModells.get(i).getParentProcess());
			overviewDto.setEndKnoten(allModells.get(i).getEndKnoten());
			overviewDtos.add(overviewDto);
		}
		return overviewDtos;
	}



	/**
	 * Diese Methoden werden Stück für Stück an die UserStories angepasst. Bis dahin bleiben Sie auskommentiert.


	@PutMapping("/modell/{id}")
	public ResponseEntity<Modell> updateModell(@PathVariable("id") long id
												  , @RequestBody Modell modell){
		return new ResponseEntity<Modell>(modellService.updateModell(modell, id), HttpStatus.OK);
	}

	@DeleteMapping("/modell/{id}")
	public ResponseEntity<String> deleteModell(@PathVariable("id") long id){

		modellService.deleteModell(id);

		return new ResponseEntity<String>("Modell deleted successfully!.", HttpStatus.OK);
	}
	*/

	/** Brauchen wir nicht vom UseCase her

	 @GetMapping("/modelle")
	 public List<Modell> getAllModell(){
	 return modellService.getAllModells();
	 }
	 */

}
