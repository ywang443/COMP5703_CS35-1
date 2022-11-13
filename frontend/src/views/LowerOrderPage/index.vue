<template>
  <div class="lower_order_page">
    <!-- 导航栏 -->
    <div>
      <van-nav-bar
        left-text="Booking Info"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <!-- 提交表单 -->
    <div class="submit_form">
      <van-form @submit="onSubmit">
        <!-- 餐厅信息 -->
        <div class="restaurant_info">
          <div class="left">
            <van-image
              width="7rem"
              height="9rem"
              fit="fill"
              :src="'http://43.140.198.220:7777/upload/' + initDta.img"
            />
          </div>
          <div class="right">
            <!-- 名称 -->
            <div class="restaurant_name">
              <h3>{{ initDta.name }}</h3>
            </div>
            <!-- 位置 -->
            <div class="restaurant_position">
              <van-icon name="guide-o" />
              {{ initDta.address }}
            </div>
            <!-- 用餐 -->
            <div class="have_meals">
              <div class="left">
                <div>Small Table</div>
                <div class="min_picture">
                  <van-image
                    width="30"
                    height="30"
                    :src="item"
                    v-for="(item, index) in facilitiesImg"
                    :key="index"
                  />
                </div>
              </div>
              <div class="right"><van-icon name="notes-o" /></div>
            </div>
          </div>
        </div>
        <!-- 预定信息 -->
        <div class="reserve">
          <!-- ***选择日期*** -->
          <div class="reserve_info">
            <div class="titel">Check-in</div>
            <div @click="displayDateChoice">
              {{ dateValue }}
              <van-icon name="arrow-down" />
            </div>
          </div>
          <!-- ***用餐时间*** -->
          <div class="reserve_info">
            <div class="titel">Period</div>
            <div>
              <van-popover
                v-model="showPopover"
                theme="dark"
                trigger="click"
                :actions="actions"
                @select="onSelect"
              >
                <template #reference>
                  {{ haveMealsTime }} hour

                  <van-icon name="arrow-down" />
                </template>
              </van-popover>
            </div>
          </div>
          <!-- 人数 -->
          <div class="reserve_info">
            <div class="titel">Guests</div>
            <div>
              <van-popover
                v-model="showPopoverGuests"
                theme="dark"
                trigger="click"
                :actions="actionsGuests"
                @select="onSelectGuests"
              >
                <template #reference>
                  {{ haveMealsGuests }} Guests

                  <van-icon name="arrow-down" />
                </template>
              </van-popover>
            </div>
          </div>
          <!-- 桌数 -->
          <div class="reserve_info">
            <div class="titel">Tables</div>
            <div>
              <van-popover
                v-model="showPopoverTables"
                theme="dark"
                trigger="click"
                :actions="actionsTables"
                @select="onSelectTables"
              >
                <template #reference>
                  {{ haveMealsTables }} small table
                  <van-icon name="arrow-down" />
                </template>
              </van-popover>
            </div>
          </div>
          <!-- ****** -->
        </div>
        <!-- 联系人信息 -->
        <div class="contacts_info">
          <!-- 标题 -->
          <div class="titel">
            <h4>Contact Details</h4>
            <van-icon name="notes-o" />
          </div>
          <!-- 名字 -->
          <div class="name">
            Mr/Lady:
            <input
              type="text"
              placeholder="Please enter  Name"
              v-model="name"
            />
          </div>
          <!-- 邮件 -->
          <div class="mail">
            Mail:
            <input
              type="text"
              placeholder="Please enter  Mail"
              v-model="mail"
            />
          </div>
          <!-- 电话 -->
          <div class="phone">
            Phone:
            <input
              type="text"
              placeholder="Please enter  Phone"
              v-model="phone"
            />
          </div>
        </div>
        <!-- 留言 -->
        <div class="message">
          <div>
            <input
              type="text"
              placeholder="Make spesial request"
              v-model="message"
            />
          </div>
          <div><van-icon name="notes-o" /></div>
        </div>
        <!-- 选择 -->
        <!-- 日期选择 -->
        <van-datetime-picker
          class="datetime_picker"
          v-model="currentDate"
          type="datehour"
          title="选择年月日小时"
          v-show="isShowDate"
          @cancel="cancelDate"
          @confirm="choiceDate"
        />
        <!-- ******* -->
        <div class="button" style="margin: 16px">
          <van-button round block type="info" native-type="submit">
            Book
          </van-button>
        </div>
      </van-form>
    </div>
  </div>
</template>

<script>
import { Toast } from 'vant'
import { getBuyRestaurant, postOrderAdd } from '@/utils/user'
export default {
  name: 'LowerOrderPage',
  data() {
    return {
      initDta: {}, // 餐厅
      restaurantImg: require('@/assets/image/canting1.jpg'), // 餐厅图片
      restaurantName: '', // 餐厅名称
      restaurantAddress: '', // 餐厅地址
      facilitiesImg: [
        require('@/assets/image/wifi.png'),
        require('@/assets/image/canju.png')
      ], // 设施
      isShowDate: false, // 控制选择日期显示隐藏
      currentDate: new Date(), // 当前时间
      dateValue: '', // 选择的时间
      haveMealsTime: '1', // 用餐时间
      haveMealsGuests: '1', // 用餐人数
      haveMealsTables: '1', // 用餐桌数
      showPopover: false, // 选择用餐时间显示隐藏
      showPopoverGuests: false, // 选择用餐人数显示隐藏
      showPopoverTables: false, // 选择用餐桌数显示隐藏
      name: '', // 用户名
      mail: '', // 邮件
      phone: '', // 电话
      message: '', // 留言
      id: 1, // 餐厅id
      actions: [
        // 用餐时间选项
        { text: '1' },
        { text: '2' },
        { text: '3' },
        { text: '4' },
        { text: '5' },
        { text: '6' }
      ],
      actionsGuests: [
        // 用餐人数选项
        { text: '1' },
        { text: '5' },
        { text: '10' },
        { text: '15' },
        { text: '20' }
      ],
      actionsTables: [
        // 用餐人数选项
        { text: '1' },
        { text: '2' },
        { text: '3' },
        { text: '4' },
        { text: '5' }
      ]
    }
  },
  created() {
    this.initDate()
    this.obtainRestaurantInfo()
  },
  methods: {
    // 获取下单页餐厅信息
    obtainRestaurantInfo() {
      const id = `id=${this.$route.query.id}`
      getBuyRestaurant(id).then(res => {
        console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.initDta = data.shopInfo
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 选择用餐时间
    onSelect(action, index) {
      this.haveMealsTime = action.text
    },
    // 选择用餐z桌数
    onSelectTables(action, index) {
      this.haveMealsTables = action.text
    },
    // 选择用餐人数
    onSelectGuests(action, index) {
      this.haveMealsGuests = action.text
    },
    // 显示日期选择
    displayDateChoice() {
      this.isShowDate = true
    },
    // 获取当前时间
    initDate() {
      const date = new Date()
      const y = date.getFullYear()
      let m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      h = h < 10 ? '0' + h : h
      this.dateValue = y + '-' + m + '-' + d + '-' + h + ':00'
    },
    // 取消选择日期
    cancelDate() {
      //   console.log('取消')
      this.isShowDate = false
      //   console.log('取消1')
    },
    //  确定选择日期
    choiceDate(value) {
      this.isShowDate = false
      //   console.log(value)
      // 对取到选择时间，进行转换格式
      const date = new Date(value)
      const y = date.getFullYear()
      let m = date.getMonth() + 1
      m = m < 10 ? '0' + m : m
      let d = date.getDate()
      d = d < 10 ? '0' + d : d
      let h = date.getHours()
      h = h < 10 ? '0' + h : h
      this.dateValue = y + '-' + m + '-' + d + '-' + h + ':00'

      //   console.log(this.dateValue)
    },
    // 下单
    onSubmit() {
      const data = {
        // restaurantImg: this.restaurantImg,
        // restaurantName: this.restaurantName,
        // restaurantAddress: this.restaurantAddress,
        dateValue: this.dateValue,
        haveMealsTime: this.haveMealsTime,
        haveMealsGuests: this.haveMealsGuests,
        haveMealsTables: this.haveMealsTables,
        name: this.name,
        mail: this.mail,
        phone: this.phone,
        message: this.message,
        shopId: this.id
      }
      console.log(data)
      postOrderAdd(data).then(res => {
        console.log(res)
        const { code, msg } = res.data
        if (code === 0) {
          Toast.success(msg)
          this.$router.push({ path: '/search' })
        } else {
          Toast.fail(msg)
        }
      })
      Toast.success('预定成功~')
      // this.$router.push({
      //   path: '/search'
      // })
    },
    // 返回上一页
    onClickLeft() {
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.lower_order_page {
  width: 100%;
  height: 100%;
  overflow-x: auto;
  // 提交表单
  .submit_form {
    padding: 10px;
    // 留言
    .message {
      display: flex;
      justify-content: space-between;
      align-items: center;
      margin: 15px 0;
      padding-bottom: 15px;
      border-bottom: 1px solid #cdd4db;
      input {
        border-bottom: 1px dotted #000;
        border-top: 0px solid;
        border-left: 0px solid;
        border-right: 0px solid;
      }
    }
    // 底部
    .button {
      .van-button--round {
        margin-top: 70px;
      }
    }
    // 联系人信息
    .contacts_info {
      margin: 15px 0;
      padding-bottom: 15px;
      border-bottom: 1px solid #cdd4db;
      input {
        border-bottom: 1px dotted #000;
        border-top: 0px solid;
        border-left: 0px solid;
        border-right: 0px solid;
      }
      .titel {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }
    // 预定信息
    .reserve {
      margin: 15px 0;
      padding-bottom: 15px;
      border-bottom: 1px solid #cdd4db;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      .reserve_info {
        width: 45%;
        margin: 10px 0;
        .titel {
          font-weight: bold;
        }
      }
    }
    // 餐厅信息
    .restaurant_info {
      width: 100%;
      display: flex;
      align-items: center;
      .left {
        width: 30%;

        ::v-deep .van-image__img {
          border-radius: 10px;
        }
      }
      .right {
        width: 65%;
        margin-left: 20px;
        div {
          margin: 5px 0;
        }
        // 位置
        .restaurant_position {
          color: #595c68;
          .van-icon {
            color: red;
          }
        }
        // 小图片
        .min_picture {
          .van-image {
            margin-right: 10px;
          }
        }
        // 用餐
        .have_meals {
          display: flex;
          align-items: center;
          .left {
            width: 80%;
          }
          .right {
            width: 10%;
          }
        }
        // 名称
        .restaurant_name {
          h3 {
            line-height: 0px;
          }
        }
      }
    }
  }
}
.datetime_picker {
  width: 100%;
  z-index: 999;
  position: fixed;
  bottom: 0;
  right: 0;
}
</style>
