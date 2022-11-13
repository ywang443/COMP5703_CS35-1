<template>
  <div class="mail_page">
    <h2>Inbox</h2>
    <!-- 分类 -->
    <div class="classification">
      <span
        :class="[activeIndex === index ? 'span_color' : '']"
        v-for="(item, index) in classificationData"
        :key="index"
        @click="choiceClassification(index)"
      >
        {{ item }}
      </span>
    </div>
    <!-- <h1>这是没有数据看到的</h1> -->
    <!-- 内容 -->
    <div class="content" v-if="mailList.length === 0">
      <!-- 图片 -->
      <div>
        <van-image :src="imailImg" />
      </div>
      <div><h2>No unread mesaages</h2></div>
      <div>
        When you contact a host or send a reservation request, you'll see your
        messages here. .
      </div>
      <div></div>
    </div>
    <!-- <h1>这是有数据看到的</h1> -->
    <!-- 邮件列表 -->
    <div class="mail_list" v-else>
      <MailList :mailList="mailList" @see-mail="seeMail"></MailList>
    </div>
    <!-- *** -->
  </div>
</template>

<script>
import MailList from './components/MailList'
import { Toast } from 'vant'
import { postUserMail } from '@/utils/user'
export default {
  name: 'MailPage',
  components: {
    MailList
  },
  data() {
    return {
      // 定义分类标签数据
      classificationData: ['All', 'Booking', 'Promo', 'Up'],
      // 分类key
      activeIndex: 0,
      //  海报
      imailImg: require('@/assets/image/25mail.png'),
      // 邮件列表数据
      mailList: [
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12JuneYeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: true // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: true // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12JuneYeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: true // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: true // 控制已读还是未读状态
        // },
        // {
        //   titel: 'Payment Succes', // 标题
        //   info: ' Yeay! Your payment for Kraton Hotel Yogyakarta with a stay date of 12June...', // 信息
        //   isColorShow: false // 控制已读还是未读状态
        // }
      ]
    }
  },
  created() {
    this.obtainUserMailList()
  },
  methods: {
    // 获取用户邮件列表
    obtainUserMailList() {
      const type = {
        type: this.activeIndex + 1
      }
      postUserMail(type).then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.mailList = data.mails
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 查看邮件
    seeMail(id) {
      console.log(id)
      // this.mailList[id].isColorShow = false
      this.$router.push({
        path: '/mail/info',
        query: {
          id: id
        }
      })
    },
    // 点击选择分类
    choiceClassification(index) {
      // console.log(index)
      this.activeIndex = index
      this.obtainUserMailList()
      // Toast.fail('暂无数据！')
    }
  }
}
</script>

<style lang="scss" scoped>
.mail_page {
  padding: 10px 10px 50px 10px;
  // 内容
  .content {
    text-align: center;
    div:nth-child(1) {
      margin-top: 30px;
    }
  }
  // 分类
  .classification {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: space-around;
    margin: 10px 0;

    span {
      //   margin-top: 10px;
      border-radius: 15px;
      padding: 7px;
      background-color: #fdfdff;
    }
    .span_color {
      //  background-color: #d3ceef;
      border: 1px solid #5140c1;
      box-shadow: 3px 3px 10px #d3ceef;
    }
  }
}
</style>
