<template>
  <div class="login">
    <div>
      <van-image :src="image" />
    </div>
    <!-- 登录信息 -->
    <div class="login_info">
      <h2>Restaurant reservation system</h2>
      <!-- 账号 -->
      <div>
        <van-cell-group>
          <van-field
            v-model="value"
            label="Account "
            placeholder="请输入账号"
          />
        </van-cell-group>
      </div>
      <!-- 密码 -->
      <div>
        <van-cell-group>
          <van-field
            v-model="password"
            type="password"
            label="Password"
            placeholder="请输入密码"
          />
        </van-cell-group>
      </div>
      <!-- 验证码 -->
      <div>
        <van-field
          v-model="verificationCode"
          center
          clearable
          placeholder="Enter the verification code"
        >
          <template #button>
            <!-- <van-button size="small" type="primary">发送验证码</van-button> -->
            <SIdentify
              :identifyCode="identifyCode"
              @click.native="refreshIdentifyCode"
            ></SIdentify>
          </template>
        </van-field>
      </div>
      <!-- 注册 -->
      <div class="register">
        <div @click="jumpRegister('user')">
          <van-icon name="contact" />
          RegisterUser
        </div>
        <div @click="jumpRegister('shop')">
          <van-icon name="shop-collect-o" />
          RegisterShop
        </div>
      </div>
      <!-- 商家登录 -->
      <div>
        <van-checkbox v-model="checked">I am Shop</van-checkbox>
      </div>
      <!-- 登录 -->
      <van-button type="info" @click="loginSubmit">Login</van-button>
    </div>
  </div>
</template>

<script>
import SIdentify from './components/SCanvas'

import { Toast } from 'vant' // 轻提示

import { registerLogin } from '@/utils/login'
export default {
  name: 'LoginPage',
  components: {
    SIdentify
  },
  data() {
    return {
      value: 'admin', // 账号
      password: '123456', // 密码
      verificationCode: '', // 验证码
      image: require('@/assets/image/diancan.png'),
      identifyCode: '', // 密码登录图形验证码
      identifyCodes: '1234567890abcdefghizklmnopqrstuvwxyz', // 生成图形验证码的依据
      checked: false, // 是否商家登录
      path: '/welcome' // 登录路由地址
    }
  },

  methods: {
    // 跳转注册页面
    jumpRegister(type) {
      // console.log(type)
      this.$router.push({
        path: '/register',
        query: {
          type
        }
      })
      // console.log(123)
    },
    // 点击登录
    loginSubmit() {
      if (this.checked) {
        this.path = '/welcome/shop'
      }
      // const data = `account=${this.value}&password=${this.password}`
      const data = {
        name: this.value,
        password: this.password
      }

      if (
        this.value !== '' &&
        this.password !== '' &&
        this.identifyCode === this.verificationCode
      ) {
        // *******生成token* 一般是后端生成，这里我在前端生成给你们*****
        // const Base64 = require('js-base64').Base64
        // const secretKey = 'Xiaoming' // 秘钥
        // let token = `${this.value}-${this.password}||${secretKey}`
        // token = Base64.encode(token) // 加密
        // token = this.$md5(token) // 二次加密
        // localStorage.setItem('token', token) // 把token存到本地缓存当中  setItem添加
        //  console.log(token)
        // ****************
        // console.log('登录')
        // 点击登录，像后台发起请求
        registerLogin(data).then(res => {
          console.log(res)
          const { code, data, msg } = res.data
          if (code === 0) {
            const { token, userInfo } = data
            localStorage.setItem('token', token) // 把token存到本地缓存当中  setItem添加
            this.$store.commit('USER_INFO', userInfo)
            Toast.success('登录成功')
            this.$router.push({
              path: this.path
            })
          } else {
            Toast.fail(msg)
          }
        })
      } else {
        Toast.fail('登录失败，请检查登录信息！')
      }
      // console.log(123)
    },
    // 刷新验证码
    refreshIdentifyCode() {
      this.identifyCode = ''
      this.makeIdentifyCode(4)
      console.log(123)
    },
    // 生成4位数的随机验证码
    makeIdentifyCode(l) {
      const random = Math.floor(
        (Math.random() + Math.floor(Math.random() * 9 + 1)) *
          Math.pow(10, l - 1)
      )
      this.identifyCode = random + ''
      // console.log(this.identifyCode)
    }
  },
  mounted() {
    // 初始化验证码
    this.identifyCode = ''
    this.makeIdentifyCode(4)
  }
}
</script>

<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  // 登录信息
  .login_info {
    padding: 15px;
    h2 {
      text-align: center;
    }
    .van-button {
      margin-top: 30px;
      width: 100%;
    }
  }
  // 注册
  .register {
    width: 90%;
    margin: 10px auto;
    padding-bottom: 20px;
    display: flex;
    justify-content: space-between;
    color: rgb(106, 107, 108);
    font-size: 13px;
    .van-icon {
      margin-right: 10px;
    }
  }
}
</style>
