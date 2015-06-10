package dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entities.Petition;
import dao.PetitionDao;

public class JDBCPetitionDao implements PetitionDao {
	@Override
	public void create(Petition petition) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("INSERT INTO petition (id_express_servise, id_scale_work, time, idUser) values (?, ?, ?, ?)"
                            , Statement.RETURN_GENERATED_KEYS);
					st.setInt(1, new JDBCDaoFactory().createExpressServiceDao().find(petition.getExpressService().name()));
					st.setInt(2, new JDBCDaoFactory().createScaleWorkDao().find(petition.getScaleWork().name()));
					st.setLong(3, petition.getTime());
					st.setInt(4, petition.getIdUser());

		            st.executeUpdate();

					ResultSet key = null;
					try {
						key = st.getGeneratedKeys();
						int userId = 0;
			            if( key.next() ){
			            	userId = key.getInt(1);
			            }
			            petition.setId(userId);
					} finally {
						if (key != null)
							key.close();
							key=null;
					}
				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Petition find(int id) {
		Petition petition = null;
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("SELECT * FROM petition WHERE ID = ?");
					st.setInt(1, id);
					ResultSet rs = null;
					try {
						rs = st.executeQuery();
						 if( rs.next() ){
							 petition = new Petition(rs.getInt(1), new JDBCDaoFactory().createExpressServiceDao().find(rs.getInt(2)), 
									 new JDBCDaoFactory().createScaleWorkDao().find(rs.getInt(3)), rs.getLong(4), rs.getInt(5));
				            }
					} finally {
						if (rs != null)
							rs.close();
							rs=null;
					}
				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return petition;
	}

	@Override
	public List<Petition> findAll() {
		List<Petition> petition = new ArrayList<Petition>();
		 
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				Statement st = null;
				try {
					st = cn.createStatement();
					ResultSet rs = null;
					try {
						rs = st.executeQuery("SELECT * FROM petition ");
						 while (rs.next()) {
				                petition.add(new Petition(rs.getInt(1), new JDBCDaoFactory().createExpressServiceDao().find(rs.getInt(2)), 
				                		new JDBCDaoFactory().createScaleWorkDao().find(rs.getInt(3)), rs.getLong(4), rs.getInt(5)));
				            }
					} finally {
						if (rs != null)
							rs.close();
							rs=null;
					}
				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return petition;
	}

	@Override
	public void update(Petition petition) {
		try {
			Connection cn = null;
			try {
				JdbcConnection connection = JdbcConnection.getInstance();
				cn = connection.getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("UPDATE petition SET id_express_servise = ?, id_scale_work = ?,"
							+ " time = ?, idUser = ? WHERE id = ? ");
					st.setInt(1, new JDBCDaoFactory().createExpressServiceDao().find(petition.getExpressService().name()));
					st.setInt(2, new JDBCDaoFactory().createScaleWorkDao().find(petition.getScaleWork().name()));
					st.setLong(3, petition.getTime());
					st.setInt(4, petition.getIdUser());
					st.setInt(5, petition.getId());
		            st.executeUpdate();

				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		try {
			Connection cn = null;
			try {
				cn = JdbcConnection.getInstance().getConnection();
				PreparedStatement st = null;
				try {
					st = cn.prepareStatement("DELETE FROM petition WHERE id = ?");
					st.setInt(1, id);
		            st.executeUpdate();

				} finally {
					if (st != null)
						st.close();
						st=null;
				}
			} finally {
				if (cn != null)
					cn.close();
					cn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
