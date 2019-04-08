package controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dao.StudioDAO;
import dto.StudioDTO;

@RestController
@RequestMapping("/studios")
public class ControllerStudio {

	private StudioDAO studioDAO = new StudioDAO();
	
	@GetMapping
	public List<StudioDTO> listar(){
		return studioDAO.listStudio();
	}
}
