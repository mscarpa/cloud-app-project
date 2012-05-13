package ie.cit.appdev.web;

import ie.cit.appdev.JdbcCollectionRepository;
import ie.cit.appdev.Movies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("list")
@Controller
public class CollectionController {

	@Autowired
	private JdbcCollectionRepository repo;

	@RequestMapping(method = RequestMethod.GET)
	public void listMovies(Model model) {
		model.addAttribute("list", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createMovies(Model model, @RequestParam String text,
			@RequestParam String tipe, @RequestParam int year) {
		Movies mov = new Movies();
		mov.setText(text);
		mov.setTipe(tipe);
		mov.setYear(year);
		repo.save(mov);
		model.addAttribute("list", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteMovies(Model model, @RequestParam int mid) {
		repo.delete(mid);
		model.addAttribute("list", repo.getAll());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateTodo(Model model, @RequestParam int mid) {
		Movies mov = repo.get(mid);
		mov.setDone(!mov.isDone());
		repo.update(mov);
		model.addAttribute("list", repo.getAll());
	}

}
