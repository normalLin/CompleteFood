package com.store.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.store.model.*;


public class StoreServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自 select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求参數 - 輸入格式的例外處理 ****************************************/
				String str = req.getParameter("store_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入餐廳編號");
				}
				// Send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
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
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				StoreService storeSrv = new StoreService();
				StoreVO storeVO = storeSrv.getOneStore(store_id);
				if (storeVO == null) {
					errorMsgs.add("查無資料");
				}
				// send the use back to the form , if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/store/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的storeVO物件，存入req
				String url = "/store/listOneStore.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllStore.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// store this set in the request scope,in case we need to
			// send the ErrorPage View.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_id = new Integer(req.getParameter("store_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeSrv = new StoreService();
				StoreVO storeVO = storeSrv.getOneStore(store_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的storeVO物件,存入req
				String url = "/store/update_store_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_store_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/restaurant.jsp");
				failureView.forward(req, res);
			}
		}
/*		以下開始為新增 */
		if ("check_store".equals(action)) { // 來自restaurant.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// store this set in the request scope,in case we need to
			// send the ErrorPage View.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer store_id = new Integer(req.getParameter("store_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				StoreService storeSrv = new StoreService();
				StoreVO storeVO = storeSrv.getOneStore(store_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("storeVO", storeVO); // 資料庫取出的storeVO物件,存入req
				
				String url = "/store/check_store.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_store_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/store/restaurant.jsp");
				failureView.forward(req, res);
			}
		}
/*		以上開始為新增 */
		if ("update".equals(action)) { // 來自update_store_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer store_id = new Integer(req.getParameter("store_id").trim());

				String store_name = req.getParameter("store_name");
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("餐廳名稱: 請勿空白");
				}
				// 以下練習正則(規)表示式(regular-expression)
				String store_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (!store_name.matches(store_nameReg)) {
					errorMsgs.add("餐廳名稱:只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
				}

				// String store_images =
				// req.getParameter("store_images").trim();

				Integer phone_number = new Integer(req.getParameter("phone_number").trim());

				String shop_address = req.getParameter("shop_address").trim();

				String web_url = req.getParameter("web_url").trim();

				Double subTotal = null;
				try {
					subTotal = new Double(req.getParameter("subTotal").trim());
				} catch (NumberFormatException e) {
					subTotal = 0.0;
					errorMsgs.add("請填數字ok?.");
				}

				Double mini_Price = null;
				try {
					mini_Price = new Double(req.getParameter("mini_Price").trim());
				} catch (NumberFormatException e) {
					mini_Price = 0.0;
					errorMsgs.add("請填數字好嗎?.");
				}
				String deliveryOperationTime = req.getParameter("deliveryOperationTime").trim();

				Integer style_id = null;
				try {
					style_id = new Integer(req.getParameter("style_id").trim());
				} catch (NumberFormatException e) {
					style_id = 0;
					errorMsgs.add("餐廳類型為數字.");
				}
				StoreVO storeVO = new StoreVO();

				storeVO.setStore_id(store_id);
				storeVO.setStore_name(store_name);
				// storeVO.setStore_images(store_images);
				storeVO.setPhone_number(phone_number);
				storeVO.setShop_address(shop_address);
				storeVO.setWeb_url(web_url);
				storeVO.setSubTotal(subTotal);
				storeVO.setMini_Price(mini_Price);
				storeVO.setDeliveryOperationTime(deliveryOperationTime);
				storeVO.setStyle_id(style_id);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的storeVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/store/update_store_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				StoreService storeSrv = new StoreService();
				storeVO = storeSrv.updateStore(store_id, store_name,phone_number, shop_address, web_url, subTotal,
						mini_Price, deliveryOperationTime, style_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("storeVO", storeVO); // 資料庫update成功後,正確的的storeVO物件,存入req
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
		
		if ("insert".equals(action)) { // 來自addStore.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope , in case we need to
			// send the ErrorPage view
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				String store_name = req.getParameter("store_name");
				if (store_name == null || store_name.trim().length() == 0) {
					errorMsgs.add("請填入餐廳名稱");
				}
				// 以下練習正則(規)表示式(regular-expression)
				String store_nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,20}$";
				if (!store_name.trim().matches(store_nameReg)) {
					errorMsgs.add("餐廳名稱:只能是中、英文字母、數字和_ , 且長度必需在1到20之間");
				}
				Integer phone_number = new Integer(req.getParameter(
						"phone_number").trim());

				String shop_address = req.getParameter("shop_address").trim();

				String web_url = req.getParameter("web_url").trim();

				Double subTotal = null;
				try {
					subTotal = new Double(req.getParameter("subTotal").trim());
				} catch (NumberFormatException e) {
					subTotal = 0.0;
					errorMsgs.add("請填數字ok?.");
				}

				Double mini_Price = null;
				try {
					mini_Price = new Double(req.getParameter("mini_Price")
							.trim());
				} catch (NumberFormatException e) {
					mini_Price = 0.0;
					errorMsgs.add("請填數字好嗎?.");
				}
				String deliveryOperationTime = req.getParameter("deliveryOperationTime").trim();

				Integer style_id = null;
				try {
					style_id = new Integer(req.getParameter("style_id").trim());
				} catch (NumberFormatException e) {
					style_id = 0;
					errorMsgs.add("餐廳類型為數字.");
				}

				StoreVO storeVO = new StoreVO();

				storeVO.setStore_name(store_name);
				storeVO.setPhone_number(phone_number);
				storeVO.setShop_address(shop_address);
				storeVO.setWeb_url(web_url);
				storeVO.setSubTotal(subTotal);
				storeVO.setMini_Price(mini_Price);
				storeVO.setDeliveryOperationTime(deliveryOperationTime);
				storeVO.setStyle_id(style_id);

				// Send the useback to the form , if there were errors.
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("storeVO", storeVO); // 含有輸入格式錯誤的storeVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/store/addStore.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				StoreService storeSrv = new StoreService();
				storeVO = storeSrv.addStore(store_name, phone_number,
						shop_address, web_url, subTotal, mini_Price,
						deliveryOperationTime, style_id);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/store/restaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllStore.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/addStore.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllStore.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer store_id = new Integer(req.getParameter("store_id"));

				/*************************** 2.開始刪除資料 ***************************************/
				StoreService storeSrv = new StoreService();
				storeSrv.deleteStore(store_id);
				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/store/restaurant.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("oops!刪除資料失敗" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/store/restaurant.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
