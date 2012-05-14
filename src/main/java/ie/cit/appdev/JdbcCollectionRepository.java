package ie.cit.appdev;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


public class JdbcCollectionRepository {
	
	private JdbcTemplate jdbcTemplate;

	public JdbcCollectionRepository (DataSource dbsource){
		this.jdbcTemplate = new JdbcTemplate (dbsource);
	}

	public void save(Movies movies) {
		jdbcTemplate.update("insert into COLL (text, tipe, year, done) values(?,?,?,?)", 
				movies.getText(), movies.getTipe(), movies.getYear(), movies.isDone());
	}

	public Movies get(int id) {
		return jdbcTemplate.queryForObject("select id, text, tipe, year, done from COLL where id=?", new CollMapper(), id);
	}

	public List<Movies> getAll() {
		return jdbcTemplate.query("select id, text, tipe, year, done from COLL ", new CollMapper());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from COLL where id=?", id);
	}

	public void update(Movies movies) {
		jdbcTemplate.update("update COLL set text=?, tipe=?, year=?, done=? where id=?", 
				movies.getText(), movies.getTipe(), movies.getYear(), movies.isDone(), movies.getId());
	}

}

class CollMapper implements RowMapper<Movies>{

	public Movies mapRow(ResultSet res, int rowNum) throws SQLException {
		Movies mov = new Movies();
		mov.setId(res.getInt("id"));
		mov.setText(res.getString("text"));
		mov.setTipe(res.getString("tipe"));
		mov.setYear(res.getInt("year"));
		mov.setDone(res.getBoolean("done"));
		return mov;
	}

}	
	
	
	
	