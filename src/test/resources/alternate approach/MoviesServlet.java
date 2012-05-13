package ie.cit.appdev;

import ie.cit.appdev.MoviesRepository;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			req.setAttribute("list", repo.getList());
			doForward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			MoviesRepository repo = getRepo(req);
			
			Movies movie = new Movies();
			movie.setText(req.getParameter("text"));
			repo.addMovies(movie);
			req.setAttribute("list", repo.getList());
			doForward(req, resp);
	
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			Integer id = Integer.valueOf(req.getParameter("mId"));
			MoviesRepository repo = getRepo(req);
			Movies movie = repo.getList().get(id - 1);
			movie.setDone(!movie.isDone());
			req.setAttribute("list", repo.getList());
			doForward(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			
			Integer id = Integer.valueOf(req.getParameter("mId"));
			MoviesRepository repo = getRepo(req);
			repo.getList().remove(id - 1);
			req.setAttribute("list", repo.getList());
			doForward(req, resp);
		}
	
	private MoviesRepository getRepo(HttpServletRequest req) {
			return contx.getBean(MoviesRepository.class);
	}
	
	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rdisp = getServletContext().getRequestDispatcher("/list.jsp");
		rdisp.forward(req, resp);
	}
	
}
