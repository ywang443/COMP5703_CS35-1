package com.ezeat.common.util;

import com.ezeat.constant.statusCode.impl.AppCode;
import com.ezeat.exception.ApiException;
import com.ezeat.vo.TokenVo;
import com.google.gson.Gson;
import lombok.Data;
import lombok.experimental.Accessors;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static java.lang.System.currentTimeMillis;
import static org.apache.commons.codec.digest.DigestUtils.sha1;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * 返回结果
 *
 * @author ZhouHua
 */
@Data
@Accessors(chain = true)
public class TokenUtils {
    // token公钥
    private static String tokenId = "D146458A1067856E";
    // token私钥
    private static String tokenKey = "E9772C6C18E585BB";

    /**
     * 生成token
     *
     * @param userId 用户id
     * @param shopId 店铺id
     * @return
     */
    public static String makeToken(Integer userId, Integer shopId) {

        // 时间戳
        long timestamp = currentTimeMillis();
        // 秘文
       String sign = sha256Hex(timestamp + userId + tokenKey + shopId).toString();
        // 构建token对象
        TokenVo tokenVo = new TokenVo();
        tokenVo.setTimestamp(timestamp);
        tokenVo.setUserId(userId);
        tokenVo.setShopId(shopId);
        tokenVo.setSign(sign);

        // token对象转json字符串
        Gson gson = new Gson();
        String tokenJson = gson.toJson(tokenVo);

        // json字符串 用base64编码 就成了token字符串
        Base64.Encoder encoder = Base64.getEncoder();
        String token = encoder.encodeToString(tokenJson.getBytes(StandardCharsets.UTF_8));
//        System.out.println("token:" + token);
//        Boolean aBoolean = verifyToken(token);
        return token;
    }

    /**
     * 验证token
     *
     * @param token
     * @return
     **/
    public static Boolean verifyToken(String token) {
        TokenVo tokenVo = tokenToObject(token);
//        try {
        long timestamp = tokenVo.getTimestamp();
        if (timestamp == 0) {
            throw new ApiException(AppCode.TOKEN_ERROR, "token不完整t");
        }
        int userId = tokenVo.getUserId();
        if (userId < 0) {
            throw new ApiException(AppCode.TOKEN_ERROR, "token不完整u");
        }
        int shopId = tokenVo.getShopId();
        if (shopId <= -1) {
            throw new ApiException(AppCode.TOKEN_ERROR, "token不完整s");
        }
        String signFe = tokenVo.getSign();
        // 秘文
        String sign = sha256Hex(timestamp + userId + tokenKey + shopId).toString();
        // 判断秘文是否相等
        if (sign.equals(signFe)) {
            return true;
        }
        throw new ApiException(AppCode.TOKEN_ERROR, "token验证失败");
//        } catch (Exception e) {
//            throw new ApiException(AppCode.TOKEN_ERROR, "token验证失败");
//        }
    }

    /**
     * token字符串转token对象
     *
     * @param token
     * @return
     */
    public static TokenVo tokenToObject(String token) {
        try {
            // 对字符串进行解码
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] decode = decoder.decode(token);
            String tokenJson = new String(decode);

            Gson gson = new Gson();
            // token对象
            TokenVo tokenVo = gson.fromJson(tokenJson, TokenVo.class);
            return tokenVo;
        } catch (Exception e) {
            throw new ApiException(AppCode.TOKEN_ERROR, "token解码错误");
        }


    }

}
