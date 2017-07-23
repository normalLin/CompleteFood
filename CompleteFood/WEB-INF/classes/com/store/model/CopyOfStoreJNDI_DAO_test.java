package com.store.model;

import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.*;

public class CopyOfStoreJNDI_DAO_test implements StoreDAO_interface {

	String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Complete_Meal";
	String userid = "sa";
	String passwd = "P@ssw0rd";

/*
 
  jdbc 版本，測試用!
 
*/	
	
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	private static final String INSERT_STMT = "INSERT INTO store_info(store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT store_id , store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id FROM store_info order by store_id";
	private static final String GET_ONE_STMT = "SELECT  store_id,store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id FROM store_info WHERE store_id=? ";
	private static final String DELETE = "DELETE FROM store_info where store_id = ?";
	private static final String UPDATE = "UPDATE store_info set store_name=?  , phone_number=? , shop_address=? , web_url=? , SubTotal=? , Mini_Price=? , DeliveryOperationTime=? , Style_id=? where store_id=?";

	@Override
	public void insert(StoreVO storeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, storeVO.getStore_name());
//			pstmt.setString(3, storeVO.getStore_images());
			pstmt.setInt(2, storeVO.getPhone_number());
			pstmt.setString(3, storeVO.getShop_address());
			pstmt.setString(4, storeVO.getWeb_url());
			pstmt.setDouble(5, storeVO.getSubTotal());
			pstmt.setDouble(6, storeVO.getMini_Price());
			pstmt.setString(7, storeVO.getDeliveryOperationTime());
			pstmt.setInt(8, storeVO.getStyle_id());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured"+ se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE);
				
				
//				
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(UPDATE);
				
				pstmt.setString(1, storeVO.getStore_name());
//				pstmt.setString(2, storeVO.getStore_images());
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
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE);
				

//				con = ds.getConnection();
//				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, store_id);

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_STMT);
				
				
				
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, store_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					storeVO = new StoreVO();
					storeVO.setStore_id(rs.getInt("store_id"));
					storeVO.setStore_name(rs.getString("store_name"));
//					storeVO.setStore_images(rs.getString("store_images"));
					storeVO.setPhone_number(rs.getInt("phone_number"));
					storeVO.setShop_address(rs.getString("shop_address"));
					storeVO.setWeb_url(rs.getString("web_url"));
					storeVO.setSubTotal(rs.getDouble("subTotal"));
					storeVO.setMini_Price(rs.getDouble("mini_Price"));
					storeVO.setDeliveryOperationTime(rs.getString("DeliveryOperationTime"));
					storeVO.setStyle_id(rs.getInt("style_id"));
					
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "+ se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
				
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
//				con = ds.getConnection();
//				pstmt = con.prepareStatement(GET_ALL_STMT);
//				rs = pstmt.executeQuery();

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
					storeVO.setDeliveryOperationTime(rs.getString("DeliveryOperationTime"));
					storeVO.setStyle_id(rs.getInt("style_id"));					
					list.add(storeVO); // Store the row in the list
				}

				// Handle any driver errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
				// Clean up JDBC resources
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
		
		public static void main(String[] args) {

			CopyOfStoreJNDI_DAO_test dao = new CopyOfStoreJNDI_DAO_test();

//			 新增
/*			StoreVO storeVO1 = new StoreVO();
			storeVO1.setStore_name("kfuckc");
			storeVO1.setPhone_number(7533967);
			storeVO1.setShop_address("u.s.a");
	    	storeVO1.setWeb_url("http://kfuckc.com.tw");
	    	storeVO1.setSubTotal(12459.0);
			storeVO1.setMini_Price(300300.0);
			storeVO1.setDeliveryOperationTime("24小時104天供應!");
			storeVO1.setStyle_id(5);
			dao.insert(storeVO1);
*/	
					

			 //修改
/*			StoreVO storeVO2 = new StoreVO();
	    	storeVO2.setStore_id(3);
			storeVO2.setStore_name("麥當勞mcdon");
			storeVO2.setPhone_number(3939889);
			storeVO2.setShop_address("台北市內湖yoooooooo");
			storeVO2.setWeb_url("http://mcdonald.com.tw");
			storeVO2.setSubTotal(59.0);
			storeVO2.setMini_Price(300.0);
			storeVO2.setDeliveryOperationTime("32424小時全天供應!");
			storeVO2.setStyle_id(3);
			dao.update(storeVO2);
*/
					

			 //刪除
//			dao.delete(4);
//			 System.out.println(updateCount_delete);

			// 查詢
/*			StoreVO storeVO3 = dao.findByPrimaryKey(4);
			System.out.print(storeVO3.getStore_id() + ",");
			System.out.print(storeVO3.getStore_name() + ",");
			System.out.print(storeVO3.getPhone_number() + ",");
			System.out.print(storeVO3.getShop_address() );
			System.out.println(storeVO3.getWeb_url() + ",");
			System.out.print(storeVO3.getSubTotal() + ",");
			System.out.println(storeVO3.getMini_Price()+",");
			System.out.println(storeVO3.getDeliveryOperationTime()+",");
			System.out.println(storeVO3.getStyle_id());
			
			System.out.println("--------------------------------------------");
  */  
			// 查詢
/*		List<StoreVO> list = dao.getAll();
			for (StoreVO aStore : list) {
				System.out.print(aStore.getStore_id() + ",");
				System.out.print(aStore.getStore_name() + ",");
				System.out.print(aStore.getPhone_number() + ",");
				System.out.print(aStore.getShop_address() );
				System.out.println(aStore.getWeb_url()+"," );
				System.out.print(aStore.getSubTotal() + ",");
				System.out.print(aStore.getMini_Price()+",");
				System.out.print(aStore.getDeliveryOperationTime()+",");
				System.out.print(aStore.getStyle_id());
				System.out.println();
			} */
		}  
}
