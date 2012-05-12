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
		jdbcTemplate.update("insert into CALL (text, done) values(?,?)", movies.getText(), movies.isDone());
	}

	public Movies get(int id) {
		return jdbcTemplate.queryForObject("select id, text, done from CALL where id=?", new CollMapper(), id);
	}

	public List<Movies> getAll() {
		return jdbcTemplate.query("select id, text, done from CALL", new CollMapper());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from CALL where id=?", id);
	}

	public void update(Movies movies) {
		jdbcTemplate.update("update CALL set text=?, done=? where id=?", 
				movies.getText(), movies.isDone(), movies.getId());
	}

}

class CollMapper implements RowMapper<Movies>{

	public Movies mapRow(ResultSet res, int rowNum) throws SQLException {
		Movies mov = new Movies();
		mov.setId(res.getInt("id"));
		mov.setText(res.getString("text"));
		mov.setDone(res.getBoolean("done"));
		return mov;
		
	}

}	
	
	
	
	