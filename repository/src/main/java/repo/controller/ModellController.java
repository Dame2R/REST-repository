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
import repo.exception.ResourceNotFoundException;
import repo.model.Modell;
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

			System.out.println("Test1");
			//DOM-Parser sortiert intern nach alphabet

			modell.setDepartment(document.getElementsByTagName("bpmn:process").item(0).getAttributes().item(0).getNodeValue());
			modell.setId(document.getElementsByTagName("bpmn:process").item(0).getAttributes().item(1).getNodeValue());
			modell.setProcessType(document.getElementsByTagName("bpmn:process").item(0).getAttributes().item(3).getNodeValue());
			//startEvent finden
			System.out.println("Test2");
			NodeList startEvent = document.getElementsByTagName("bpmn:startEvent");

			String startEventString = "";
			for(int i=0; i<startEvent.getLength(); i++){
				Node startEventNodeName = startEvent.item(i);
				startEventString += startEventNodeName.getAttributes().item(0).getNodeValue() + ";";
			}
			modell.setStartKnoten(startEventString);
			System.out.println("Test3");

			//Wenn kein endEvent in der XML gefunden wurde, soll das Feld in der DB null bleiben
			if(document.getElementsByTagName("bpmn:endEvent").getLength() != 0){
				NodeList endEvent = document.getElementsByTagName("bpmn:endEvent");
				String endEventStrings = "";
				for(int i=0; i<endEvent.getLength(); i++){
					Node item1 = endEvent.item(i);
					endEventStrings += item1.getAttributes().item(0).getNodeValue() + ";";
				}
				modell.setEndKnoten(endEventStrings);
			}
			System.out.println("Test4");
//			XML als BLOB einfügen
			modell.setXml(new SerialBlob(xml.getBytes()));

			//Child Prozesse speichern wenn vorhanden
			if(document.getElementsByTagName("zeebe:calledElement").getLength() != 0){
				NodeList callActivity = document.getElementsByTagName("zeebe:calledElement");
				String callActivityString = "";
				for(int i=0; i<callActivity.getLength(); i++){
					Node item1 = callActivity.item(i);
					callActivityString += item1.getAttributes().item(0).getNodeValue() + ";";
				}
				modell.setChildProcess(callActivityString);
			}
			System.out.println("Test5");
			//CO2 summieren
			int co2 = 0;
			if(document.getElementsByTagName("zeebe:property").getLength() != 0){
				NodeList task = document.getElementsByTagName("zeebe:property");
				for(int i=0; i<task.getLength(); i++) {
					//Es dürfen keine leeren Properties vorkommen.
					if (task.item(i).getAttributes().item(0).getNodeValue().equals("CO2")) {
						System.out.println(task.item(i).getAttributes().item(1).getNodeValue());
						co2 += Integer.parseInt(task.item(i).getAttributes().item(1).getNodeValue());
						System.out.println(task.item(i).getAttributes().item(1).getNodeValue());
					}
				}
				System.out.println("Tttt");
				modell.setEnergySumYear(co2);
				System.out.println(modell.getEnergySumYear());
			}
			System.out.println("Test6");

			if(document.getElementsByTagName("bpmn:documentation").getLength() != 0){
				NodeList documentation = document.getElementsByTagName("bpmn:documentation");
				modell.setProcessDescription( documentation.item(0).getTextContent() );
			}

			modellService.saveModell(modell);
			System.out.println("Test7");


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
				overviewDto.setProcessDescription(modelle.get(i).getProcessDescription());
				overviewDto.setEnergySumYear(modelle.get(i).getEnergySumYear());
				overviewDto.setProcessType(modelle.get(i).getProcessType());
				overviewDto.setStartKnoten(modelle.get(i).getStartKnoten());
				overviewDto.setChildProcess(modelle.get(i).getChildProcess());
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

	@GetMapping("/overviews/processType")
	public List<OverviewDto> getAllOverviewsWithProcessTypeCore(@RequestParam(required = false) String type) throws ResourceNotFoundException {

		if(type.equalsIgnoreCase("core")) {
			var allOverviews = getAllOverviews();

			List<OverviewDto> overviewDtos = new ArrayList<>();

			for (int i = 0; i < allOverviews.size(); i++) {
				String processType = allOverviews.get(i).getProcessType();
				if (processType.equalsIgnoreCase("CORE")) {
					overviewDtos.add(allOverviews.get(i));
				}
			}
			return overviewDtos;
		}
		throw new ResourceNotFoundException("Keine Core Prozesse","Fehler", null );

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
			overviewDto.setEndKnoten(allModells.get(i).getEndKnoten());
			overviewDto.setChildProcess(allModells.get(i).getChildProcess());
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
