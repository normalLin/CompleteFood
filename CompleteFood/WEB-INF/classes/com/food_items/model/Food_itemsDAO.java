package com.food_items.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

import com.store.model.StoreVO;

public class Food_itemsDAO implements Food_itemsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SQLSERVERDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO Food_items (species_id , item_name , price , store_id) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT items_id , species_id , item_name , price , store_id from Food_items order by store_id";
	private static final String GET_ONE_STMT = 
			"SELECT Food_items.store_id , item_name , items_id , price , food_items.species_id  from store_info Join food_items on store_info.store_id = food_items.store_id where food_items.store_id = ? ";
	private static final String GET_ONE_MENU = 
			"SELECT  item_name ,  price , species_id  from food_items where items_id = ? ";
	private static final String DELETE = 
			"DELETE FROM Food_items where items_id = ?";
	private static final String UPDATE = 
			"UPDATE Food_items set species_id=?,item_name=?, price=? where items_id = ?";

	@Override
	public void insert(Food_itemsVO food_itemsVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, food_itemsVO.getSpecies_id());
//			pstmt.setBytes(2, food_itemsVO.getStore_images());
			pstmt.setString(2, food_itemsVO.getItem_name());
			pstmt.setDouble(3, food_itemsVO.getPrice());
			pstmt.setInt(4, food_itemsVO.getStore_id());

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured"
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null){
				try{
					con.close();
				} catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}

	}
		@Override
		public void update(Food_itemsVO food_itemsVO){
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				

				pstmt.setInt(1, food_itemsVO.getSpecies_id());
//				pstmt.setBytes(2, food_itemsVO.getStore_images());
				pstmt.setString(2, food_itemsVO.getItem_name());
				pstmt.setDouble(3, food_itemsVO.getPrice());
				pstmt.setInt(4, food_itemsVO.getItems_id());

				pstmt.executeUpdate();
								
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured" + se.getMessage());
			} finally {
				if (pstmt != null){
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con !=null){
					try{
						con.close();
					} catch (Exception e){
						e.printStackTrace(System.err);
					}
				}
			}
		}
		
		@Override
		public void delete(Integer items_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, items_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
		}
		
		@Override
		  public List<Food_itemsVO>  findByStoreId(Integer store_id) {
			  
			List<Food_itemsVO> list = new ArrayList<Food_itemsVO>();
			Food_itemsVO food_itemsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, store_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					food_itemsVO = new Food_itemsVO();
					food_itemsVO.setStore_id(rs.getInt("store_id"));
					food_itemsVO.setItem_name(rs.getString("item_name"));
					food_itemsVO.setItems_id(rs.getInt("items_id"));
					food_itemsVO.setPrice(rs.getDouble("price"));
					food_itemsVO.setSpecies_id(rs.getInt("species_id"));
					list.add(food_itemsVO);
					
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
		  
		  @Override
		  public Food_itemsVO findByPrimaryKey(Integer items_id) {

			Food_itemsVO food_itemsVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_MENU);

				pstmt.setInt(1, items_id);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					food_itemsVO = new Food_itemsVO();
					food_itemsVO.setItem_name(rs.getString("item_name"));				
					food_itemsVO.setPrice(rs.getDouble("price"));
					food_itemsVO.setSpecies_id(rs.getInt("species_id"));
					food_itemsVO.setItems_id(items_id);
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return food_itemsVO;
		}
		
		@Override
		  public List<Food_itemsVO> getAll() {
			List<Food_itemsVO> list = new ArrayList<Food_itemsVO>();
			Food_itemsVO food_itemsVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					food_itemsVO = new Food_itemsVO();
					food_itemsVO.setItems_id(rs.getInt("items_id"));
					food_itemsVO.setSpecies_id(rs.getInt("species_id"));
					food_itemsVO.setItem_name(rs.getString("item_name"));
					food_itemsVO.setPrice(rs.getDouble("price"));
					food_itemsVO.setStore_id(rs.getInt("store_id"));								
					list.add(food_itemsVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			return list;
		}
}
