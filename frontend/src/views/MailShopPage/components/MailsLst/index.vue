<template>
  <div class="mail_list">
    <div
      class="mail_crad"
      :class="item.isShowState ? 'state_color' : ''"
      v-for="item in messageList"
      :key="item.id"
      @click="changeMailState(item)"
    >
      <div class="mail_box">
        <div class="left">
          <van-image :src="'http://43.140.198.220:7777/upload/' + item.img" />
        </div>
        <div class="right">
          <!-- 名称 -->
          <div class="name">{{ item.title }}</div>
          <!-- 信息 -->
          <div class="info">
            {{ item.content }}
          </div>
          <!-- 功能-->
          <div class="function">
            <div @click.stop="replyMail"><van-icon name="comment" /></div>
            <div @click.stop="collectionMail"><van-icon name="star" /></div>
            <div @click.stop="openMail"><van-icon name="weapp-nav" /></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant'
export default {
  name: 'MailsLst',
  props: {
    messageList: {
      type: Array
    }
  },

  methods: {
    // 展开
    openMail() {
      Toast('功能完善中~')
    },
    // 收藏
    collectionMail() {
      Toast.success('收藏成功~')
    },
    // 回复邮件
    replyMail() {
      Toast.success('回复成功~')
    },
    // 改变邮件状态
    changeMailState(index) {
      this.$emit('change-mail-state', index)
    }
  }
}
</script>

<style lang="scss" scoped>
.mail_list {
  overflow-x: hidden;
  .mail_crad {
    display: flex;
    padding: 10px;
    margin-bottom: 15px;
    border-radius: 10px;
    border-bottom: 1px solid rgb(161, 153, 153);
    .mail_box {
      display: flex;
    }
    .left {
      ::v-deep .van-image__img {
        width: 50px;
        height: 50px;
        border-radius: 100%;
      }
    }
    .right {
      margin-left: 20px;
      .name {
        font-size: 20px;
        font-weight: bold;
      }
      .info {
        margin: 15px 0;
        color: rgb(184, 167, 167);
        word-wrap: break-word;
        word-break: break-all;
        display: -webkit-box; //使用了flex，需要加
        overflow: hidden; //超出隐藏
        word-break: break-all; //纯英文、数字、中文
        text-overflow: ellipsis; //省略号
        -webkit-box-orient: vertical; //垂直
        -webkit-line-clamp: 5; //显示一行
        white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
      }
      .function {
        display: flex;
        div {
          margin-right: 20px;
          border: 1px solid;
          padding: 5px;
          border-radius: 100%;
        }
      }
    }
  }
}
.state_color {
  background-color: #6c5cd2;
}
</style>
