package sydml.mybatis.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sydml.common.api.dto.LoginInfo;
import com.sydml.common.api.dto.UserDTO;
import com.sydml.common.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sydml.mybatis.base.ResultMap;
import sydml.mybatis.dao.UserMapper;
import sydml.mybatis.dto.RequestInfo;
import sydml.mybatis.dto.User;
import sydml.mybatis.service.IUserLoginService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yuming-Liu
 * 日期： 2019-03-18
 * 时间： 19:51
 */
@RestController
@RequestMapping("get-all")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IUserLoginService loginServie;

    @GetMapping
    public List<ResultMap> getAll() {
        long id = Thread.currentThread().getId();
        System.out.println("getAll:" + id);
        List<ResultMap> resultMap = userMapper.queryAll();
        List<User> result = JsonUtil.decodeArrayJson(JsonUtil.encodeJson(resultMap), User.class);
        return resultMap;
    }

    @GetMapping("/users-page-detail")
    public PageInfo lists(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo result = PageInfo.of(userMapper.queryAllPageDetail());
        return result;
    }

    @GetMapping("/users-page")
    public Page<User> users(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<User> result = userMapper.queryAllPage();
        return result;
    }

    @PostMapping
    @ResponseBody
    public String save(@RequestBody RequestInfo info) {
        System.out.println(JsonUtil.encodeJson(info));
        String s = JsonUtil.encodeJson(info);
        s += writeSuccessResponse();
        return s;
    }

    private String writeSuccessResponse() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("message", "登录成功，欢迎使用");
        map.put("data", null);
        return JsonUtil.encodeJson(map);
    }

    @PostMapping("login")
    public void login(@RequestBody(required = true) LoginInfo loginInfo) {
        loginServie.login(loginInfo);
    }

    @PostMapping("save")
    public void save(@RequestBody(required = true) UserDTO userDTO) {
        loginServie.save(userDTO);
    }

    @GetMapping("find-by-username")
    @ResponseBody
    public UserDTO findByUsername(@RequestParam(value = "username") String username) {
        UserDTO userDTO = loginServie.findByUsername(username);
        return userDTO;
    }

    @GetMapping("find-by-id")
    @ResponseBody
    public UserDTO findById(@RequestParam(value = "id") Long id) {
        UserDTO userDTO = loginServie.findById(id);
        return userDTO;
    }
}
