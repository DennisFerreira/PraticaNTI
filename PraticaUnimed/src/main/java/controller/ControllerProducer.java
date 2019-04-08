package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.ProducerDAO;
import dto.ProducerDTO;

@RestController
@RequestMapping("/producers")
public class ControllerProducer {
	
	private ProducerDAO producerDAO = new ProducerDAO();
	
	@GetMapping
	public List<ProducerDTO> list(){
		return producerDAO.listProducer();
	}

}
