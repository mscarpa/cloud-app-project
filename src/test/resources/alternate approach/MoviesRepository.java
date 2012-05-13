package ie.cit.appdev;

import java.util.ArrayList;
import java.util.List;

public class MoviesRepository {

	private List<Movies> list = new ArrayList<Movies>();

	public List<Movies> getList() {
		return list;
	}

	public void addMovies(Movies mov) {
		list.add(mov);
	}

}
