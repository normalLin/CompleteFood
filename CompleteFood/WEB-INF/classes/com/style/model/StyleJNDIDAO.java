package com.style.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.store.model.*;
public class StyleJNDIDAO implements StyleDAO_interface{

	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SQLSERVERDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO Style (style_name) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT style_id , style_name FROM Style";
	private static final String GET_ONE_STMT = "SELECT style_name FROM Style where style_id = ?";
	private static final String GET_Stores_ByStyle_id_STMT = "SELECT store_id , store_name  , phone_number , shop_address , web_url , SubTotal , Mini_Price , DeliveryOperationTime , Style_id FROM store_info where style_id = ? order by store_id";
	
	private static final String UPDATE = "UPDATE Style set  style_name =? where style_id = ?";

	@Override
	public void insert(StyleVO styleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, styleVO.getStyle_name());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public void update(StyleVO styleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, styleVO.getStyle_name());
			pstmt.setInt(2, styleVO.getStyle_id());

			pstmt.executeUpdate();

			// Handle any SQL errors
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
	public StyleVO findByPrimaryKey(Integer style_id) {

		StyleVO styleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, style_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// deptVO 也稱為 Domain objects
				styleVO = new StyleVO();
				styleVO.setStyle_id(rs.getInt("style_id"));
				styleVO.setStyle_name(rs.getString("style_name"));

			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return styleVO;
	}

	@Override
	public List<StyleVO> getAll() {
		List<StyleVO> list = new ArrayList<StyleVO>();
		StyleVO styleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				styleVO = new StyleVO();
				styleVO.setStyle_id(rs.getInt("style_id"));
				styleVO.setStyle_name(rs.getString("style_name"));

				list.add(styleVO); // Store the row in the list
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public Set<StoreVO> getStoresByStyle_id(Integer style_id) {
		Set<StoreVO> set = new LinkedHashSet<StoreVO>();
		StoreVO storeVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
	
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_Stores_ByStyle_id_STMT);
			pstmt.setInt(1, style_id);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				storeVO = new StoreVO();
				storeVO.setStore_id(rs.getInt("store_id"));
				storeVO.setStore_name(rs.getString("store_name"));
//				storeVO.setStore_images(rs.getString("store_images"));
				storeVO.setPhone_number(rs.getInt("phone_number"));
				storeVO.setShop_address(rs.getString("shop_address"));
				storeVO.setWeb_url(rs.getString("web_url"));
				storeVO.setSubTotal(rs.getDouble("subTotal"));
				storeVO.setMini_Price(rs.getDouble("mini_Price"));
				storeVO.setDeliveryOperationTime(rs.getString("DeliveryOperationTime"));
				storeVO.setStyle_id(rs.getInt("style_id"));		
				set.add(storeVO); // Store the row in the vector
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return set;
	}
}
