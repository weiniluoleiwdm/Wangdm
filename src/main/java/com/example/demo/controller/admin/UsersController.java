package com.example.demo.controller.admin;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.component.AppProperties;
import com.example.demo.model.User;
import com.example.demo.service.UnitService;
import com.example.demo.service.UserService;
import com.example.demo.util.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

/**  
* @Description: 用户增删改查
* @author wdm  
* @date 2018年4月13日  下午2:55:17
*/
@Controller
@RequestMapping("users")
public class UsersController {
	@Autowired private AppProperties pro;
	@Autowired private UserService service;
	@Autowired private UnitService unitService;
	
	@RequestMapping
    public String goList(@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,Map<String,Object> map) {
		PageHelper.startPage(pageNum,5);
		List<User> users = service.getUserList();
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		System.err.println(new Gson().toJson(pageInfo));
        map.put("msg", "Hello Thymeleaf");
        map.put("author", pro.getAuthor());
        map.put("bean", pro);
        map.put("data", pageInfo);
        map.put("html", "<div>This is an <em>HTML</em> text. <b>Enjoy yourself!</b></div>");
        return "system/users/userList";
    }
	/**跳转edit页面**/
	@RequestMapping("goEdit")
    public String goEdit(Map<String,Object> map) {
        map.put("unitList", this.unitService.getUnitList());
        return "system/users/userEdit";
    }
	/**信息保存**/
	@RequestMapping("doEdit")
	public String doEdit (HttpServletRequest request,User user){
		JsonResult jr=service.insertSelective(user);
		if("ok".equals(jr.getStatus())) {
			return "redirect:/users";
		}
		request.setAttribute("userBack", jr);
		return "system/users/userEdit";
	}
	
//	/**
//	 * 根据ID查询用户
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") String id){
//		JsonResult r = new JsonResult();
//		try {
//			User user = userService.selectByPrimaryKey(id);
//			r.setResult(user);
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	/**
//	 * 根据ID查询用户
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "annuser/{id}", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> getAnnUserById (@PathVariable(value = "id") String id){
//		JsonResult r = new JsonResult();
//		try {
//			User user = userService.annSelectByPrimaryKey(id);
//			r.setResult(user);
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	/**
//	 * 查询用户列表
//	 * @return
//	 */
//	@RequestMapping(value = "users", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> getUserList (@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
//		PageHelper.startPage(pageNum,5);
//		JsonResult r = new JsonResult();
//		try {
//			List<User> users = userService.getUserList();
//			PageInfo<User> pageInfo = new PageInfo<User>(users);
//			r.setResult(pageInfo);
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	@RequestMapping(value = "users1", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> getUserList1 (@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
//		PageHelper.startPage(pageNum,5);
//		JsonResult r = new JsonResult();
//		try {
//			List<User> users = userService.getUserList1();
//			PageInfo<User> pageInfo = new PageInfo<User>(users);
//			r.setResult(pageInfo);
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	/**
//	 * 查询有单位的用户列表列表
//	 * @return
//	 */
//	@RequestMapping(value = "usersdw", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> getUserDwList (@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
//		PageHelper.startPage(pageNum,5);
//		JsonResult r = new JsonResult();
//		try {
//			List<Map<String, Object>> users = userService.getUserDwList();
//			PageInfo<Map<String, Object>> pageInfo = new PageInfo<Map<String, Object>>(users);
//			r.setResult(pageInfo);
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	/**
//	 * 添加用户
//	 * @param user
//	 * @return
//	 */
//	/**
//	* @Description: 删除用户
//	* @author wdm  
//	* @date 2018年4月14日  上午10:53:45
//	 */
//	@RequestMapping(value = "deluser/{id}", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> deluser (@PathVariable(value = "id")String gid){
//		JsonResult r = new JsonResult();
//		try {
//		   userService.delete(gid);
//			r.setResult("删除成功!");
//			r.setStatus("ok");
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
//	/**
//	 * 添加用户
//	 * @param user
//	 * @return
//	 */
//	@RequestMapping(value = "adduser", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> annInsert (User user){
//		JsonResult r = new JsonResult();
//		System.err.println(user.toString());
//		try {
//			int orderId = userService.annInsert(user);
//			if (orderId < 0) {
//				r.setResult(orderId);
//				r.setStatus("fail");
//			} else {
//				r.setResult(orderId);
//				r.setStatus("ok");
//			}
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	/**
//	 * 修改用户信息
//	 * @Description: TODO
//	 * @author wdm  
//	 * @date 2018年6月25日  上午9:17:24
//	 * http://localhost/upduser?id=6F230F564092C365E050A8C0D90A1A70&loginname=修改
//	 */
//	@RequestMapping(value = "upduser", method = RequestMethod.GET)
//	public ResponseEntity<JsonResult> upduser (User user){
//		JsonResult r = new JsonResult();
//		System.err.println(user.toString());
//		try {
//			int orderId = userService.updateByPrimaryKeySelective(user);
//			if (orderId < 0) {
//				r.setResult(orderId);
//				r.setStatus("fail");
//			} else {
//				r.setResult(orderId);
//				r.setStatus("ok");
//			}
//		} catch (Exception e) {
//			r.setResult(e.getClass().getName() + ":" + e.getMessage());
//			r.setStatus("error");
//			e.printStackTrace();
//		}
//		return ResponseEntity.ok(r);
//	}
//	
	
	

}
