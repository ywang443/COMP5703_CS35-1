<template>
  <div class="mail_shop_page">
    <h1>Inbox</h1>
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
    <!-- 列表 -->
    <div class="mails_lst">
      <MailsLst
        :messageList="mailData[activeIndex]"
        @change-mail-state="changeMailState"
        v-if="mailData[activeIndex].length !== 0"
      ></MailsLst>
      <van-empty description="暂无数据哦~" v-else />
    </div>
  </div>
</template>

<script>
import { postMailStatus, postShopMail } from '@/utils/shop'
import MailsLst from './components/MailsLst'
import { Toast } from 'vant'
export default {
  name: 'MailShopPage',
  components: { MailsLst },
  data() {
    return {
      // 定义分类标签数据
      classificationData: ['message', 'notification', 'comments'],
      // 分类key
      activeIndex: 0,
      // 列表数据
      mailData: {
        0: [
          // {
          //   img: require('@/assets/image/20220911180102.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // }
        ],
        1: [
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/20220911180102.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // }
        ],
        2: [
          // {
          //   img: require('@/assets/image/20220911180102.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting1.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // },
          // {
          //   img: require('@/assets/image/canting2.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting3.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: false // 状态
          // },
          // {
          //   img: require('@/assets/image/canting4.jpg'), // 头像
          //   title: 'Xiaoming', // 标题
          //   info: 'MailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLstMailsLst', // 信息
          //   isShowState: true // 状态
          // }
        ]
      }
    }
  },
  created() {
    this.obtainShopMail()
  },
  methods: {
    // 获取商家邮件信息
    obtainShopMail() {
      const type = {
        type: this.activeIndex + 1
      }
      postShopMail(type).then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.mailData[this.activeIndex] = data.mails
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 改变邮件状态
    changeMailState(item) {
      console.log(item)
      const data = {
        id: item.id,
        isRead: 1
      }
      postMailStatus(data).then(res => {
        console.log(res)
        const { code, msg } = res.data
        if (code === 0) {
          Toast.success(msg)
        } else {
          Toast.fail(msg)
        }
      })
      // console.log(this.mailData[0][0])
      // if (this.activeIndex === 0) {
      //   this.mailData[this.activeIndex][id].isShowState = false
      // } else if (this.activeIndex === 1) {
      //   this.mailData[this.activeIndex][id].isShowState = false
      // } else {
      //   this.mailData[this.activeIndex][id].isShowState = false
      // }
    },
    // 点击选择分类
    choiceClassification(index) {
      // console.log(index)
      this.activeIndex = index
      this.obtainShopMail()
    }
  }
}
</script>

<style lang="scss" scoped>
.mail_shop_page {
  margin-bottom: 100px;
  // 列表
  .mails_lst {
    padding: 10px;
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
