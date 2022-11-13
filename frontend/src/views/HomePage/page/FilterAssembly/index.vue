<template>
  <div class="filter_assembly">
    <!-- 导航栏 -->
    <div>
      <van-nav-bar
        left-text="Filter"
        right-text="Clear filter"
        left-arrow
        @click-left="onClickLeft"
      />
    </div>
    <!-- 表单 -->
    <div class="form_verification">
      <van-form>
        <!-- ***1*** -->
        <h5>Cuisines</h5>
        <!-- ****** -->
        <van-field name="checkbox" label="African">
          <template #input>
            <van-checkbox v-model="formData.checkbox1" shape="square" />
          </template>
        </van-field>
        <!-- ****** -->
        <van-field name="checkbox" label="AfternoonTea">
          <template #input>
            <van-checkbox v-model="formData.checkbox2" shape="square" />
          </template>
        </van-field>
        <!-- ****** -->
        <van-field name="checkbox" label="American">
          <template #input>
            <van-checkbox v-model="formData.checkbox3" shape="square" />
          </template>
        </van-field>
        <!-- ****** -->
        <van-field name="checkbox" label="Asian">
          <template #input>
            <van-checkbox v-model="formData.checkbox4" shape="square" />
          </template>
        </van-field>
        <!-- ****** -->
        <div class="show_all" @click="isShowAction">Show all</div>
        <!-- ***价格范围*** -->
        <div class="price_range">
          <h5>Price range</h5>
          <div class="range">
            <div class="money_text">
              ${{ formData.sliderValue[0] }} - ${{ formData.sliderValue[1] }}
            </div>
            <van-slider
              v-model="formData.sliderValue"
              range
              @change="onChange"
            />
          </div>
        </div>
        <!-- ***设施和特征*** -->
        <div class="facilities">
          <h5>Facilities & Features</h5>
          <!-- ****** -->
          <van-field name="checkbox" label="Free Wifi">
            <template #input>
              <van-checkbox v-model="formData.checkbox10" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="OutdoorSeating">
            <template #input>
              <van-checkbox v-model="formData.checkbox11" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="Free Wifi">
            <template #input>
              <van-checkbox v-model="formData.checkbox12" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <div class="show_all" @click="isShowAction2">Show all</div>
          <!-- ****** -->
        </div>
        <!-- **用餐场景** -->
        <div class="dining_scenes">
          <h5>Dining Scenes</h5>
          <!-- ****** -->
          <van-field name="checkbox" label="Couple dating">
            <template #input>
              <van-checkbox v-model="formData.checkbox10" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="Birthday party">
            <template #input>
              <van-checkbox v-model="formData.checkbox11" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="Business banquet">
            <template #input>
              <van-checkbox v-model="formData.checkbox12" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <div class="show_all" @click="isShowAction">Show all</div>
          <!-- ****** -->
        </div>
        <!-- **评级星** -->
        <div>
          <h5>Rating Star</h5>
          <div class="rating_star">
            <span>
              <van-icon name="star" />
              5 stars
            </span>
            <span>
              <van-icon name="star" />
              4 stars
            </span>
            <span>
              <van-icon name="star" />
              3 stars
            </span>
            <span>
              <van-icon name="star" />
              2 stars
            </span>
            <span>
              <van-icon name="star" />
              1 stars
            </span>
          </div>
        </div>
        <!-- ***座椅选项*** -->
        <div>
          <h5>Seating Options</h5>
          <!-- ****** -->
          <van-field name="checkbox" label="Small Table (1-4 person)">
            <template #input>
              <van-checkbox v-model="formData.checkbox10" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="Medium Table (5-8 person)">
            <template #input>
              <van-checkbox v-model="formData.checkbox11" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
          <van-field name="checkbox" label="L _ounge">
            <template #input>
              <van-checkbox v-model="formData.checkbox12" shape="square" />
            </template>
          </van-field>
          <!-- ****** -->
        </div>
        <!-- ****** -->
      </van-form>
      <!-- 展示组件 -->
      <div class="action_sheet">
        <van-action-sheet v-model="isShow" title="Cuisines">
          <!-- ***1**** -->
          <div class="all_from">
            <div>African</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox1"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***2**** -->
          <div class="all_from">
            <div>AfternoonTea</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox2"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ****3*** -->
          <div class="all_from">
            <div>American</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox3"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***4**** -->
          <div class="all_from">
            <div>Asian</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox4"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ****5*** -->
          <div class="all_from">
            <div>Australian</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox5"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***6**** -->
          <div class="all_from">
            <div>Bar</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox6"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***7**** -->
          <div class="all_from">
            <div>Bistro</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox7"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***8**** -->
          <div class="all_from">
            <div>Brazilian</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox8"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ***9**** -->
          <div class="all_from">
            <div>Brewery</div>
            <div>
              <van-checkbox
                v-model="formData.checkbox9"
                shape="square"
              ></van-checkbox>
            </div>
          </div>
          <!-- ******* -->
          <!-- ******* -->
        </van-action-sheet>
      </div>
      <!-- 展示 2 -->
      <div class="action_sheet2">
        <van-action-sheet v-model="isShow2">
          <!-- ***1**** -->
          <div>
            <h5>Where do you wanna to stay?</h5>
            <van-cell-group>
              <van-field
                v-model="formData.settleValue1"
                right-icon="search"
                placeholder="Search location"
              />
            </van-cell-group>
          </div>
          <!-- ***2**** -->
          <div>
            <h5>Check-in</h5>
            <van-cell-group>
              <van-field
                v-model="formData.dateValue1"
                right-icon="notes-o"
                placeholder="Select date"
                @click-right-icon="choiceSettleDate"
              />
            </van-cell-group>
            <!-- 选择日期 -->
            <van-calendar v-model="dateShow" @confirm="onConfirm" />
          </div>
          <!-- ***3**** -->
          <div>
            <h5>WCheck-out</h5>
            <van-cell-group>
              <van-field
                v-model="formData.leaveDateValue1"
                right-icon="newspaper-o"
                placeholder="Select date"
                @click-right-icon="choiceEndDate"
              />
            </van-cell-group>
            <!-- 选择日期 -->
            <van-calendar v-model="dateShow2" @confirm="onConfirm2" />
          </div>
          <!-- ***房间和客人**** -->
          <div>
            <h5>Room & Guest</h5>
            <!-- ***** -->
            <div class="adults">
              <div class="left">
                <div>Adults</div>
                <div class="left_adults">Ages 13 or above</div>
              </div>
              <div class="rigtn">
                <van-stepper v-model="formData.adultValue" />
              </div>
            </div>
            <!-- ***** -->
            <div class="adults">
              <div class="left">
                <div>Child</div>
                <div class="left_adults">Age 2-12</div>
              </div>
              <div class="rigtn">
                <van-stepper v-model="formData.adultValue2" />
              </div>
            </div>
            <!-- ***** -->
            <div class="adults">
              <div class="left">
                <div>Room</div>
                <!-- <div class="left_adults">Ages 13 or above</div> -->
              </div>
              <div class="rigtn">
                <van-stepper v-model="formData.adultValue3" />
              </div>
            </div>
            <!-- ***** -->
          </div>
          <!-- ***2**** -->

          <!-- ******* -->
        </van-action-sheet>
      </div>
    </div>
    <!-- 提交框 -->
    <div class="submit_apply">
      <van-button type="info" @click="onSubmit">Apply</van-button>
    </div>
  </div>
</template>

<script>
// import AllCuisines from '@/views/HomePage/components/AllCuisines'
import { Toast } from 'vant'

export default {
  name: 'FilterAssembly',
  //   components: { AllCuisines },
  data() {
    return {
      // 展示框
      isShow: false,
      isShow2: false,
      // 选择日期
      dateShow: false,
      dateShow2: false,

      // 表单数据
      formData: {
        checkbox1: true,
        checkbox2: false,
        checkbox3: true,
        checkbox4: false,
        checkbox5: true,
        checkbox6: false,
        checkbox7: true,
        checkbox8: false,
        checkbox9: false,
        checkbox10: false,
        checkbox11: false,
        checkbox12: true,
        // 滑块范围
        sliderValue: [10, 80],
        //   入驻
        settleValue1: '',
        // 日期
        dateValue1: '',
        // 离开日期
        leaveDateValue1: '',
        // 成人数量
        adultValue: '',
        // 儿童数量
        adultValue2: '',
        // 房间数量
        adultValue3: ''
      }
    }
  },
  methods: {
    // 获取回调参数
    formatDate(date) {
      return `${date.getMonth() + 1}/${date.getDate()}`
    },
    // 日期回调
    onConfirm(date) {
      this.dateShow = false
      this.formData.dateValue1 = this.formatDate(date)
      console.log(this.formatDate(date))
    },
    // 日期回调
    onConfirm2(date2) {
      this.dateShow2 = false
      this.formData.leaveDateValue1 = this.formatDate(date2)
      console.log(this.formatDate(date2))
    },
    // 选择离开日期隐藏显示
    choiceEndDate() {
      if (this.dateShow2) {
        this.dateShow2 = false
      } else {
        this.dateShow2 = true
      }
    },
    // 选择入住日期隐藏显示
    choiceSettleDate() {
      if (this.dateShow) {
        this.dateShow = false
      } else {
        this.dateShow = true
      }
    },
    // 滑块范围值
    onChange(value) {
      console.log(value)
    },
    // 隐藏 展示
    isShowAction() {
      if (this.isShow) {
        this.isShow = false
      } else {
        this.isShow = true
      }
    },
    // 隐藏 展示
    isShowAction2() {
      if (this.isShow) {
        this.isShow2 = false
      } else {
        this.isShow2 = true
      }
    },
    // 表单
    onSubmit() {
      //   console.log(123)
      Toast.success('提交申请成功!')
      this.$router.push({ path: '/home/all' })
    },
    // 点击返回上一页
    onClickLeft() {
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.filter_assembly {
  height: 100%;
  //   overflow-x: auto;
  padding: 10px;
  display: flex;
  flex-direction: column;
  // 表单
  .form_verification {
    // height: 100%;
    flex: 1;
    overflow-x: auto;
    // 评级星
    .rating_star {
      display: flex;
      flex-direction: row;
      flex-wrap: wrap;
      justify-content: space-around;
      span {
        margin: 5px 0;
        background-color: #f1f2f3;
        padding: 15px 20px;
        border-radius: 10px;
        .van-icon {
          color: yellow;
          margin-right: 5px;
        }
      }
    }
    // 价格范围
    .price_range {
      padding-bottom: 10px;
      .money_text {
        margin-bottom: 25px;
        color: #595c68;
      }
    }
    // 房间和客人
    .adults {
      //   margin: 5px 0;
      display: flex;
      justify-content: space-between;
      flex-direction: row;
      align-items: center;
      .left_adults {
        color: #4f4f65;
        font-size: 13px;
      }
    }
    .adults:nth-child(3) {
      //   background-color: yellow;
      margin: 20px 0;
      border-top: 1px solid;
      border-bottom: 1px solid;
      padding: 20px 0;
    }
  }
  // 提交框
  .submit_apply {
    border-top: 1px solid;
    background-color: #ffffff;
    padding: 30px;

    display: flex;
    justify-content: space-around;

    .van-button--normal {
      width: 80%;

      border-radius: 15px;
    }
  }
  // 展示组件
  .action_sheet {
    .all_from {
      margin: 20px 0;
      display: flex;
      justify-content: space-between;
      padding: 10px 20px;
    }
  }
  // 展示2
  .action_sheet2 {
    width: 100%;
    // padding: 10px 20px;
    .van-action-sheet {
      width: 88%;
      padding: 15px 25px;
      .van-cell {
        border: 1px solid;
        border-radius: 15px;
      }
    }
  }
  // 导航栏
  .van-nav-bar {
    height: 50px;
    ::v-deep .van-icon {
      color: #000;
    }
    ::v-deep .van-nav-bar__text {
      color: #000;
    }
  }
  // 全部显示
  .show_all {
    font-weight: bold;
    color: #161bb4;
    font-size: 13px;
    margin: 10px 0;
  }
}
::v-deep .van-checkbox__icon {
  margin-left: 100px;
}
::v-deep .van-field__label {
  width: 50%;
}
</style>
