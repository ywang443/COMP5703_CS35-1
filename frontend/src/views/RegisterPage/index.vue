<template>
  <div class="register_page">
    <!-- 导航 -->
    <div>
      <van-nav-bar
        :title="title"
        left-text="返回"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <!-- 用户注册 -->
    <div class="register_user">
      <van-form validate-first @failed="onFailed" @submit="submitRegister">
        <!-- 账号 -->
        <van-cell-group>
          <van-field
            v-model="account"
            label="account"
            name="account"
            placeholder="Please enter account"
            :rules="[{ pattern, message: 'Please check the content' }]"
          />
        </van-cell-group>
        <!-- 密码 -->
        <van-cell-group>
          <van-field
            v-model="password"
            label="password"
            name="password"
            placeholder="Please enter password"
            :rules="[{ pattern, message: 'Please check the content' }]"
          />
        </van-cell-group>
        <!-- 邮箱 -->
        <van-cell-group>
          <van-field
            v-model="mailbox"
            label="mailbox"
            name="mailbox"
            placeholder="Please enter mailbox"
            :rules="[{ pattern, message: 'Please check the content' }]"
          />
        </van-cell-group>
        <!-- 商家注册 -->
        <div class="register_shop" v-if="$route.query.type !== 'user'">
          <!-- 餐厅名称 -->
          <van-cell-group>
            <van-field
              v-model="restaurantName"
              label="restaurantName"
              name="restaurantName"
              placeholder="Please enter restaurantName"
              :rules="[{ pattern, message: 'Please check the content' }]"
            />
          </van-cell-group>
          <!-- 餐厅电话 -->
          <van-cell-group>
            <van-field
              v-model="restaurantPhone"
              label="restaurantPhone"
              name="restaurantPhone"
              placeholder="Please enter restaurantPhone"
              :rules="[{ pattern, message: 'Please check the content' }]"
            />
          </van-cell-group>
          <!-- 餐厅地址 -->
          <van-cell-group>
            <van-field
              v-model="restaurantAddress"
              label="restaurantAddress"
              name="restaurantAddress"
              placeholder="Please enter restaurantAddress"
              :rules="[{ pattern, message: 'Please check the content' }]"
            />
          </van-cell-group>
          <!-- 餐厅简介 -->
          <van-cell-group>
            <van-field
              v-model="restaurantInfo"
              rows="1"
              autosize
              label="restaurantInfo"
              type="restaurantInfo"
              placeholder="restaurantInfo"
              :rules="[{ pattern, message: 'Please check the content' }]"
            />
          </van-cell-group>
          <!-- 餐厅图片 -->
          <van-cell-group>
            <van-field name="restaurantImg" label="restaurantImg">
              <template #input>
                <van-uploader
                  v-model="imgB"
                  :max-count="1"
                  :after-read="afterReadB"
                />
              </template>
            </van-field>
          </van-cell-group>
        </div>
        <!-- 头像 -->
        <van-cell-group>
          <van-field name="portrait" label="portrait">
            <template #input>
              <van-uploader
                v-model="imgA"
                :max-count="1"
                :after-read="afterReadA"
              />
            </template>
          </van-field>
        </van-cell-group>
        <!-- 注册 -->
        <div class="register_button">
          <van-button type="info" native-type="submitRegister">
            register
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { registerShop, registerUser } from '@/utils/login'
import { Toast } from 'vant'
export default {
  name: 'RegisterPage',
  data() {
    return {
      account: '', // 账号
      password: '', // 密码
      mailbox: '', // 邮箱
      restaurantName: '', // 餐厅名称
      restaurantPhone: '', // 餐厅电话
      restaurantAddress: '', // 餐厅地址
      restaurantInfo: '', // 餐厅简介
      restaurantImg: '', // 餐厅图片
      userImg: '', // 用户头像
      imgA: [], // 头像图片暂存
      imgB: [], // 餐厅图片暂存
      pattern: /^[\s\S]*.*[^\s][\s\S]*$/ // 正则效验非空
    }
  },
  computed: {
    title() {
      if (this.$route.query.type === 'user') {
        return '注册用户'
      } else {
        return '注册商家'
      }
    }
  },
  methods: {
    // 上传头像
    afterReadA(file) {
      // 此时可以自行将文件上传至服务器
      console.log(file)
      // const data = {
      //   file: file.content
      // }
      const formData = new FormData()
      formData.append('file', file.file)
      axios
        .post('http://43.140.198.220:7777/uploadimg', formData, {
          // 因为我们上传了图片,因此需要单独执行请求头的Content-Type
          headers: {
            // 表示上传的是文件,而不是普通的表单数据
            'Content-Type': 'multipart/form-data'
          }
        })
        .then(res => {
          console.log(res)
          const { code, data, msg } = res.data
          if (code === 0) {
            this.userImg = data.url
          } else {
            Toast.fail(msg)
          }
        })
    },
    // 上传餐厅图片
    afterReadB(file) {
      // 此时可以自行将文件上传至服务器
      console.log(file.file)

      const formData = new FormData()
      formData.append('file', file.file)
      axios
        .post('http://43.140.198.220:7777/uploadimg', formData, {
          // 因为我们上传了图片,因此需要单独执行请求头的Content-Type
          headers: {
            // 表示上传的是文件,而不是普通的表单数据
            'Content-Type': 'multipart/form-data'
          }
        })
        .then(res => {
          console.log(res)
          const { code, data, msg } = res.data
          if (code === 0) {
            this.restaurantImg = data.url
          } else {
            Toast.fail(msg)
          }
        })
    },
    // 验证不通过
    onFailed() {
      console.log('不通过')
    },
    // 注册
    submitRegister() {
      if (this.$route.query.type === 'user') {
        const user = {
          type: this.$route.query.type, // 注册类型
          account: this.account, // 账号
          password: this.password, // 密码
          mailbox: this.mailbox, // 邮箱
          userImg: this.userImg // 用户头像
        }
        registerUser(user).then(res => {
          console.log(res)
          const { code, msg } = res.data
          if (code === 0) {
            Toast.success(msg)
            this.$router.push({ path: '/' })
          } else {
            Toast.fail(msg)
          }
        })
        console.log(1)
      } else {
        const shop = {
          type: this.$route.query.type, // 注册类型
          account: this.account, // 账号
          password: this.password, // 密码
          mailBox: this.mailbox, // 邮箱
          shopName: this.restaurantName, // 餐厅名称
          shopPhone: this.restaurantPhone, // 餐厅电话
          shopAddress: this.restaurantAddress, // 餐厅地址
          shopDescription: this.restaurantInfo, // 餐厅简介
          shopImg: this.restaurantImg, // 餐厅图片
          userImg: this.userImg // 用户头像
        }
        registerShop(shop).then(res => {
          console.log(res)
          const { code, msg } = res.data
          if (code === 0) {
            Toast.success(msg)
            this.$router.push({ path: '/' })
          } else {
            Toast.fail(msg)
          }
        })
        console.log(2)
      }

      // console.log(shop)
    },
    // 返回
    onClickLeft() {
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.register_page {
  height: 100%;
  overflow-y: auto;
  // 用户
  .register_user {
    padding: 10px;
  }
  // 注册
  .register_button {
    margin-top: 30px;
    // padding: 10px;
    padding-bottom: 50px;
    text-align: center;
    .van-button--normal {
      width: 50%;
      border-radius: 20px;
    }
  }
}
</style>
