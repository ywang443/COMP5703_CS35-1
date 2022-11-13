package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.PasswordUtils;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.constant.statusCode.impl.ResultCode;
import com.ezeat.entity.Appointments;
import com.ezeat.entity.Collections;
import com.ezeat.entity.Shops;
import com.ezeat.service.*;
import com.ezeat.validator.*;
import com.ezeat.vo.ResultVo;
import com.ezeat.mapper.UsersMapper;
import com.ezeat.entity.Users;
import com.ezeat.vo.TokenVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ezeat.common.util.TokenUtils.makeToken;

@Slf4j
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private HotsService hotsService;

    @Autowired
    private ShopsService shopsService;

    @Autowired
    private AppointmentsService appointmentsService;

    @Autowired
    private CarouselsService carouselsService;

    @Autowired
    private CollectionsService collectionsService;

    @Autowired
    private TablesService tablesService;


    @Override
    public ResultVo login(LoginVd loginVd) {

        // 通过用户名来获取用户信息
        Users user = usersMapper.selectOne(new QueryWrapper<Users>().eq("name", loginVd.getName()));
        if (user == null) {
            return new ResultVo(ResultCode.USER_NOT_EXIST);
        }

        // 验证用户密码
        // 加密前端传过来的密码，用AES算法加密
//        String key = "0123456789abcdef"; // 128 bit key
//        String iv = "fedcba9876543210"; // 16 bytes IV
        // iv和key都要和php的一样
//        String passwordMd5 = AESCipherUtils.encrypt(key, iv, loginVd.getPassword());

        String passwordAES = PasswordUtils.encode(loginVd.getPassword());
//        String passwordMd5 = DigestUtils.md5Hex(loginVd.getPassword());

        // 数据库中的密码(用AES算法加密之后储存的)
        String passwordDb = user.getPassword();

        // 登陆成功
        if (passwordAES.contentEquals(passwordDb)) {
            QueryWrapper<Users> queryWrapper = new QueryWrapper();
            queryWrapper.select("id", "img", "name", "email").eq("id", user.getId());
            List<Map<String, Object>> maps = usersMapper.selectMaps(queryWrapper);
            // 数据库中的用户信息
            Map<String, Object> userDb = maps.get(0);

            int shopId = 0;
            // 查找餐厅表，看看有没有属于当前用户的餐厅
            QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
            shopsQueryWrapper.eq("user_id", userDb.get("id"));
            Shops shop = shopsService.getOne(shopsQueryWrapper);
            if (shop != null) {
                shopId = shop.getId();
            }

            // 生成token
            String token = makeToken(user.getId(), shopId);
            // 构建返回给前端的数据
            HashMap data = new HashMap<>();
            data.put("token", token);
            data.put("userInfo", userDb);
            return new ResultVo(ResultCode.SUCCESS, data);
        }
        // 登陆失败
        return new ResultVo(ResultCode.FAILED, "密码错误");
    }

    public ResultVo getHomeData() {
        // 轮播图列表
        List carousels = carouselsService.getAll();
        // 热门店铺
        List hots = hotsService.getAll();
        // 店铺排行(按评分)
        List shopRankings = shopsService.getHots(5);
        // 构建返回给前端的数据
        HashMap<String, Object> resHashMap = new HashMap();
        resHashMap.put("carousels", carousels);
        resHashMap.put("shopHots", hots);
        resHashMap.put("shopRankings", shopRankings);

        return new ResultVo(resHashMap, "获取数据成功~");
    }

    @Override
    public Users findUserById(Long userId) {
        return null;
    }

    @Override
    public Users modifyUserById(Users users) {
        return null;
    }

    @Override
    public Users findUserByUsername(String name) {
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        name = name.trim();
        wrapper.eq("name", name);
        Users users = usersMapper.selectOne(wrapper);
        return users;
    }

    @Override
    public Boolean verifyPassword(String passwordDb, String passwordFe) {
        // 前端传过来的密码，用md5加密
        String passwordMd5 = DigestUtils.md5Hex(passwordFe);
        // 判断加密后的密码跟数据库中的密码是否一致
        if (passwordMd5 == passwordDb) {
            return true;
        }
        return false;
    }

    @Override
    public Users findUserByEmail(String email) {
        return null;
    }

    @Override
    public ResultVo getShopDetail(int id) {
        Shops shopDetail = shopsService.getById(id);
        HashMap<String, Object> resHashMap = new HashMap();
        resHashMap.put("shopDetail", shopDetail);
        if (shopDetail == null) {
            return new ResultVo(ResultCode.NOT_FOUND);

        }
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getHomeRanking() {
        int count = shopsService.count();
        List hots = shopsService.getHots(count);
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("shopRankingAll", hots);
        return new ResultVo(resHashMap);

    }

    @Override
    public ResultVo orderLower(int shopId) {
        QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();

        shopsQueryWrapper.select("id", "name", "img", "phone", "address");
        shopsQueryWrapper.eq("id", shopId);
        Map<String, Object> shopInfoMap = shopsService.getMap(shopsQueryWrapper);
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("shopInfo", shopInfoMap);
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo searchShop(String value) {
        // 返回给前端的数据
        HashMap<String, Object> resHashMap = new HashMap<>();

        QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
        shopsQueryWrapper.select("id", "name", "img", "description", "address", "rate", "average");
        shopsQueryWrapper.like("name", value);
        List<Map<String, Object>> shopMaps = shopsService.listMaps(shopsQueryWrapper);
        resHashMap.put("shopList", shopMaps);

        QueryWrapper<Collections> collectionsQueryWrapper = new QueryWrapper<>();
//        collectionsQueryWrapper.eq("user_:=")


        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo searchInfo() {
        List hotPlaces = shopsService.getHotsPlaces(10);
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("hotPlaces", hotPlaces);
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo orderAdd(AppointmentAddVd appointmentAddVd) {
        // 解析token
        String token = appointmentAddVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        try {
            // 构造新的预定对象
            Appointments appointment = new Appointments();
            appointment.setUserId(tokenVo.getUserId());
            appointment.setTableId(0);
            appointment.setShopId(appointmentAddVd.getShopId());
            appointment.setName(appointmentAddVd.getName());
            appointment.setDate(appointmentAddVd.getDateValue());
            appointment.setQuantity(appointmentAddVd.getHaveMealsGuests());
            appointment.setTables(appointmentAddVd.getHaveMealsTables());
            appointment.setName(appointmentAddVd.getName());
            appointment.setMail(appointmentAddVd.getMail());
            appointment.setPhone(appointmentAddVd.getPhone());
            System.out.println("phone: " + appointmentAddVd.getPhone());
            appointment.setMemo(appointmentAddVd.getMessage());

            // 是否保存成功
            boolean isSaved = appointmentsService.save(appointment);
            if (isSaved) {
                return new ResultVo("新建订单成功");
            }
            return new ResultVo(ResultCode.FAILED, "新建订单失败");
        } catch (Exception e) {
            return new ResultVo(ResultCode.FAILED.getCode(), "新建订单失败", e.getMessage());
        }
    }

    @Override
    public ResultVo registerUser(UserRegisterVd userRegisterVd) {
        // 从users表中查找用户
        QueryWrapper<Users> usersQueryWrapper = new QueryWrapper<>();
        usersQueryWrapper.eq("name", userRegisterVd.getAccount());
        Users userDb = usersMapper.selectOne(usersQueryWrapper);

        // 用户已存在
        if (userDb != null) {
            return new ResultVo(ResultCode.USER_IS_EXIST);
        }

        // 加密后的密码
        String encodedPassword = PasswordUtils.encode(userRegisterVd.getPassword());

        Users user = new Users();
        user.setName(userRegisterVd.getAccount());
        user.setEmail(userRegisterVd.getMailbox());
        user.setPassword(encodedPassword);
        user.setImg(userRegisterVd.getUserImg());

        HashMap<String, Object> reHashMap = new HashMap<>();
        try {
            int insertCount = usersMapper.insert(user);
            if (insertCount == 0) {
                return new ResultVo(ResultCode.INSERT_ERROR, "注册失败");
            }
            reHashMap.put("id", user.getId());
            return new ResultVo(reHashMap, "注册成功");
        } catch (Exception e) {
            return new ResultVo(ResultCode.FAILED.getCode(), "注册失败");
        }

    }

    @Override
    public ResultVo registerShop(ShopRegisterVd shopRegisterVd) {
        // 用户注册
        UserRegisterVd userRegisterVd = new UserRegisterVd();
        userRegisterVd.setAccount(shopRegisterVd.getAccount());
        userRegisterVd.setPassword(shopRegisterVd.getPassword());
        userRegisterVd.setMailbox(shopRegisterVd.getMailBox());
        userRegisterVd.setUserImg(shopRegisterVd.getUserImg());
        ResultVo resultVo = registerUser(userRegisterVd);
        Object data = resultVo.getData();
        // 注册失败
        if (data == null) {
            return resultVo;
        }
        HashMap dataHashMap = (HashMap<String, Object>) data;
        Integer userId = (Integer) dataHashMap.get("id");

        Shops shop = new Shops();
        shop.setUserId(userId);
        shop.setName(shopRegisterVd.getShopName());
        shop.setAddress(shopRegisterVd.getShopAddress());
        shop.setDescription(shopRegisterVd.getShopDescription());
        shop.setImg(shopRegisterVd.getShopImg());
        shop.setPhone(shopRegisterVd.getShopPhone());
        boolean isSaveSuccess = shopsService.save(shop);
        // 插入成功
        if (isSaveSuccess) {
            return new ResultVo("注册成功");
        }
        // 插入失败，删除刚刚注册的用户账号
        usersMapper.deleteById(userId);
        return new ResultVo(ResultCode.FAILED, "注册失败");
    }

}
