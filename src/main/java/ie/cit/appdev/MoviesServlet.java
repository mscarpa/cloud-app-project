package ie.cit.appdev;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class MoviesServlet extends HttpServlet {
	
	private WebApplicationContext contx;
	
	@Override
	public void init() throws ServletException {
		contx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			MoviesRepository repo = getRepo(req);
			req.setAttribute("movies", repo.getList());
			RequestDispatcher rdisp = getServletContext().getRequestDispatcher("/main.jsp");
			rdisp.forward(req, resp);
		
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			MoviesRepository repo = getRepo(req);
			
			Movies movie = new Movies ();
			movie.setText(req.getParameter("text"));
			repo.addMovies(movie);
			req.setAttribute("movies", repo.getList());
			RequestDispatcher rdisp = getServletContext().getRequestDispatcher("/main.jsp");
			rdisp.forward(req, resp);
	
	}
	
	
	private MoviesRepository getRepo(HttpServletRequest req) {
		HttpSession ssn = req.getSession(true);
		MoviesRepository repo = (MoviesRepository) ssn.getAttribute("repo");
		if (repo==null){
			repo = new MoviesRepository();
			ssn.setAttribute("repo", repo);				
		}
		return repo;
	}
	
}
