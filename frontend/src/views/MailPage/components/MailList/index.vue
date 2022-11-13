<template>
  <div>
    <div
      class="mail_list"
      :class="item.isRead ? 'unread_color' : ''"
      v-for="item in mailList"
      :key="item.id"
      @click="seeMail(item.id)"
    >
      <!-- 上 -->
      <div class="upper">
        <!-- 标题 -->
        <div class="titel">
          <h3>{{ item.title }}</h3>
        </div>
        <!-- May -->
        <div>May 12</div>
      </div>
      <!-- 下 -->
      <div class="lower">
        <div class="information">
          {{ item.content }}
        </div>
        <div class="tips" v-show="item.isRead">●</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MailList',
  props: {
    mailList: {
      type: Array
    }
  },
  methods: {
    // 查看邮件
    seeMail(id) {
      this.$emit('see-mail', id)
    }
  }
}
</script>

<style lang="scss" scoped>
.mail_list {
  margin-bottom: 10px;
  border-radius: 10px;
  padding: 10px;
  padding-bottom: 20px;
  border-bottom: 1px solid rgb(179, 169, 169);
  // 上
  .upper {
    display: flex;
    justify-content: space-between;
    align-items: center;
    .titel {
    }
  }
  // 下
  .lower {
    display: flex;
    justify-content: space-between;
    align-items: center;
    // 信息
    .information {
      width: 80%;
      display: -webkit-box; //使用了flex，需要加
      overflow: hidden; //超出隐藏
      word-break: break-all; //纯英文、数字、中文
      text-overflow: ellipsis; //省略号
      -webkit-box-orient: vertical; //垂直
      -webkit-line-clamp: 3; //显示一行
      white-space: pre-line; //合并空白符序列，但是保留换行符。(如果要使用这个垂直和显示几行的代码也要写)
    }
    // 提示
    .tips {
      font-size: 30px;
      color: red;
    }
  }
}
// 未读背景颜色
.unread_color {
  background-color: #bbbbc5;
}
</style>
