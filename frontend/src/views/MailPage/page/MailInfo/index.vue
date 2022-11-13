<template>
  <div class="mail_info">
    <!-- 导航栏 -->
    <div><van-nav-bar left-arrow @click-left="onClickLeft" /></div>
    <!-- 内容 -->
    <div class="content">
      <!-- 图标 -->
      <div class="icon" v-if="maillIfoData.type === 1">
        <van-icon name="checked" />
      </div>
      <div class="fail" v-else><van-icon name="clear" /></div>
      <!-- 标题 -->
      <div class="titel">
        <h2>{{ maillIfoData.title }}</h2>
      </div>
      <!-- 信息 -->
      <div class="info">
        {{ maillIfoData.content }}
      </div>
    </div>
    <!-- 底部 -->
    <div class="button">
      <van-button type="info" @click="jumpWish">Go To My Booking</van-button>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant'

import { postUserMailInfo } from '@/utils/user'
export default {
  name: 'MailInfo',
  data() {
    return {
      maillIfoData: {
        // titel: '123123123', // 标题
        // info: 'asdawdad', // 信息
        // reserveStatus: true, // 预定成功或者失败
        // id: 1 // 邮件id
      }
    }
  },
  created() {
    this.obtainMailDetailed()
  },
  methods: {
    // 获取邮件信息
    obtainMailDetailed() {
      const id = {
        id: this.$route.query.id
      }
      postUserMailInfo(id).then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.maillIfoData = data.mailDetail
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 跳转心愿单
    jumpWish() {
      this.$router.push({ path: '/like' })
    },
    // 返回上一页
    onClickLeft() {
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.mail_info {
  width: 100%;
  // 底部
  .button {
    width: 100%;
    position: fixed;
    bottom: 0;
    // right: 10%;
    left: calc(50% - 60px);
    margin-bottom: 20px;
    .van-button--normal {
      border-radius: 15px;
    }
  }
  // 内容
  .content {
    text-align: center;

    // 信息
    .info {
      width: 80%;
      margin: 0 auto;
    }
    .titel {
      width: 80%;
      margin: 0 auto;
    }
    // 图标
    .icon {
      font-size: 200px;
      color: rgba(127, 222, 10, 0.808);
    }
    .fail{
      font-size: 200px;
      color: rgba(240, 14, 14, 0.808);
    }
  }
}
</style>
