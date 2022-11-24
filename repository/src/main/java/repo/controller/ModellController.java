package repo.controller;

import java.io.File;
import java.io.StringReader;
import java.sql.Blob;
import java.sql.SQLOutput;
import java.util.List;
import javax.sql.*;

import io.swagger.models.Xml;
import org.apache.maven.model.Model;
import org.cyberneko.html.parsers.DOMParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
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

	@GetMapping("/modelle")
	public List<Modell> getAllModell(){
		return modellService.getAllModells();
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
			modell.setCo2("Test");

			modellService.saveModell(modell);

		}catch(Exception e){
			HttpStatus.BAD_REQUEST.toString();
		}
		return HttpStatus.CREATED.toString();
	}


	private void readXML(String xml){
		Modell modell = null;

		try{
			String URL = "http://www.greenbpmn.io";
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			doc.getDocumentElement().normalize();

//			modell = new Modell();
//			modell.setXml(xml);
//			modell.setStartKnoten(String.valueOf(doc.getElementsByTagName("startEvent").item(0)));
//			modell.setCo2(String.valueOf(doc.getElementsByTagName("co2").item(0)));

			System.out.println("Ausgabe:::::");
			System.out.println(doc.getElementsByTagName("startEvent"));



		}catch(Exception e){

		}


	}



	/**
	 * Diese Methoden werden Stück für Stück an die UserStories angepasst. Bis dahin bleiben Sie auskommentiert.
	@GetMapping("/modell/{id}")
	public ResponseEntity<Modell> getModellById(@PathVariable("id") long modellId){
		return new ResponseEntity<Modell>(modellService.getModellById(modellId), HttpStatus.OK);
	}

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
}
