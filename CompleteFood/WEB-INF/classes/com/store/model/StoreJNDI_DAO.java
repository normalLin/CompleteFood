package com.store.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class StoreJNDI_DAO implements StoreDAO_interface {

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
			"INSERT INTO store_info(store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT store_id , store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id FROM store_info order by store_id";
	private static final String GET_ONE_STMT = 
			"SELECT  store_id,store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id FROM store_info WHERE store_id=? ";
	private static final String DELETE = 
			"DELETE FROM store_info where store_id = ?";
	private static final String UPDATE = 
			"UPDATE store_info set store_name=?  , phone_number=? , shop_address=? , web_url=? , SubTotal=? , Mini_Price=? , DeliveryOperationTime=? , Style_id=? where store_id=?";

	@Override
	public void insert(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, storeVO.getStore_name());
//			pstmt.setBytes(2, storeVO.getStore_images());
			pstmt.setInt(2, storeVO.getPhone_number());
			pstmt.setString(3, storeVO.getShop_address());
			pstmt.setString(4, storeVO.getWeb_url());
			pstmt.setDouble(5, storeVO.getSubTotal());
			pstmt.setDouble(6, storeVO.getMini_Price());
			pstmt.setString(7, storeVO.getDeliveryOperationTime());
			pstmt.setInt(8, storeVO.getStyle_id());
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
		public void update(StoreVO storeVO){
			
			Connection con = null ;
			PreparedStatement pstmt = null ;
			
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);
				

				pstmt.setString(1, storeVO.getStore_name());
//				pstmt.setBytes(2, storeVO.getStore_images());
				pstmt.setInt(2, storeVO.getPhone_number());
				pstmt.setString(3, storeVO.getShop_address());
				pstmt.setString(4, storeVO.getWeb_url());
				pstmt.setDouble(5, storeVO.getSubTotal());
				pstmt.setDouble(6, storeVO.getMini_Price());
				pstmt.setString(7, storeVO.getDeliveryOperationTime());
				pstmt.setInt(8, storeVO.getStyle_id());
				pstmt.setInt(9, storeVO.getStore_id());
				
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
		public void delete(Integer store_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, store_id);

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
		  public StoreVO findByPrimaryKey(Integer store_id) {

			StoreVO storeVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, store_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					storeVO = new StoreVO();
					storeVO.setStore_id(rs.getInt("store_id"));
					storeVO.setStore_name(rs.getString("store_name"));
//					storeVO.setStore_images(rs.getBytes("store_images"));
					storeVO.setPhone_number(rs.getInt("phone_number"));
					storeVO.setShop_address(rs.getString("shop_address"));
					storeVO.setWeb_url(rs.getString("web_url"));
					storeVO.setSubTotal(rs.getDouble("subTotal"));
					storeVO.setMini_Price(rs.getDouble("mini_Price"));
					storeVO.setDeliveryOperationTime(rs.getString("deliveryOperationTime"));
					storeVO.setStyle_id(rs.getInt("style_id"));
					
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
			return storeVO;
		}
		
		@Override
		  public List<StoreVO> getAll() {
			List<StoreVO> list = new ArrayList<StoreVO>();
			StoreVO storeVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO 也稱為 Domain objects
					storeVO = new StoreVO();
					storeVO.setStore_id(rs.getInt("store_id"));
					storeVO.setStore_name(rs.getString("store_name"));
//					storeVO.setStore_images(rs.getString("store_images"));
					storeVO.setPhone_number(rs.getInt("phone_number"));
					storeVO.setShop_address(rs.getString("shop_address"));
					storeVO.setWeb_url(rs.getString("web_url"));
					storeVO.setSubTotal(rs.getDouble("subTotal"));
					storeVO.setMini_Price(rs.getDouble("mini_Price"));
					storeVO.setDeliveryOperationTime(rs.getString("deliveryOperationTime"));
					storeVO.setStyle_id(rs.getInt("style_id"));					
					list.add(storeVO); // Store the row in the list
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
