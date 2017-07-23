package com.food_items.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.food_items.model.*;
import com.store.model.*;

public class Food_itemsServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("check_menu".equals(action)) { // 來自 select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求参數 - 輸入格式的例外處理 ****************************************/
				String str = req.getParameter("store_id");
				req.getSession().setAttribute("store_id", str);
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/check_store.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				Integer store_id = null;
				try {
					store_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("餐廳編號格式不正確");
				}
				// send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/check_store.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				Food_itemsService foodSrv = new Food_itemsService();
				List<Food_itemsVO> food_itemsVO = foodSrv.getOneStore(store_id);
				if (store_id == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/check_store.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("food_itemsVO", food_itemsVO); // 資料庫取出的food_itemsVO物件，存入req
				String url = "/store/listAllMenu.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/check_store.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getMenu_For_Update".equals(action)) { // 來自listAllMenu.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// store this set in the request scope,in case we need to
			// send the ErrorPage View.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer items_id = new Integer(req.getParameter("items_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Food_itemsService foodSrv = new Food_itemsService();
				Food_itemsVO food_itemsVO = foodSrv.getOneItem(items_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("food_itemsVO", food_itemsVO); // 資料庫取出的food_itemsVO物件,存入req
				String url = "/store/update_menu_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/restaurant.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_store_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
		
				Integer items_id = new Integer(req.getParameter("items_id").trim());

				String item_name = req.getParameter("item_name");
				if (item_name == null || item_name.trim().length() == 0) {
					errorMsgs.add("食物名稱: 請勿空白");
				}
			
				// 以下練習正則(規)表示式(regular-expression)
				String item_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (!item_name.matches(item_nameReg)) {
					errorMsgs.add("食物名稱:只能是中、英文字母、數字和_ , 且長度必需在2到220之間");
				}
			
				// String store_images =
				// req.getParameter("store_images").trim();

				Integer species_id = new Integer(req.getParameter("species_id").trim());

				Double price = null;
				try {
					price = new Double(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0.0;
					errorMsgs.add("請填數字ok?.");
				}
		
				Food_itemsVO food_itemsVO = new Food_itemsVO();

				food_itemsVO.setItems_id(items_id);
				food_itemsVO.setItem_name(item_name);
				food_itemsVO.setSpecies_id(species_id);
				food_itemsVO.setPrice(price);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("food_itemsVO", food_itemsVO); // 含有輸入格式錯誤的food_itemsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/update_menu_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				Food_itemsService FoodSrv = new Food_itemsService();
				food_itemsVO = FoodSrv.updateItem(items_id , species_id, item_name,  price );

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("food_itemsVO", food_itemsVO); // 資料庫update成功後,正確的的food_itemsVO物件,存入req
				String url = "/store/restaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料不成功躂!!!" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/update_store_input.jsp");
				failureView.forward(req, res);
			}
		}
			
		if ("insertmenu".equals(action)) { // 來自addMenu.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope , in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/			
				Integer species_id = new Integer(req.getParameter("species_id").trim());	
				
				String item_name = req.getParameter("item_name").trim();
				if (item_name == null || item_name.trim().length() == 0) {
				
					errorMsgs.add("請填入菜色名稱");
				}
				// 以下練習正則(規)表示式(regular-expression)
				String item_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (!item_name.trim().matches(item_nameReg)) {
					errorMsgs.add("菜色名稱:只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}
				Double price = null;
				try {
					price = new Double(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0.0;
					errorMsgs.add("請填數字ok?");
				}
				Integer store_id = new Integer(req.getParameter("store_id").trim());
				req.getSession().removeAttribute("store_id");
				
				Food_itemsVO food_itemsVO = new Food_itemsVO();

				food_itemsVO.setSpecies_id(species_id);
				food_itemsVO.setItem_name(item_name);
				food_itemsVO.setPrice(price);
				food_itemsVO.setStore_id(store_id);

				// Send the useback to the form , if there were errors.
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("food_itemsVO", food_itemsVO); // 含有輸入格式錯誤的food_itemsVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/addMenu.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				Food_itemsService FoodSrv = new Food_itemsService();
				food_itemsVO = FoodSrv.addItem( species_id, item_name,  price , store_id);				

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/store/restaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/addMenu.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllMenu.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer items_id = new Integer(req.getParameter("items_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				Food_itemsService FoodSrv = new Food_itemsService();
				 FoodSrv.deleteItem( items_id);		

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/store/restaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("oops!刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/item/restaurant.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
