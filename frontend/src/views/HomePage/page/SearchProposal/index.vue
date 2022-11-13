<template>
  <div class="search_proposal">
    <!-- 搜索框 -->
    <van-cell-group>
      <van-field
        v-model.trim="searchValue"
        clearable
        left-icon="arrow-left"
        placeholder="Where are you going?"
        @click-left-icon="returnUpper"
        @keyup.enter="searchContent(searchValue)"
      />
    </van-cell-group>
    <!-- 定位 -->
    <LocationAssembly class="location_assembly"></LocationAssembly>
    <!-- 最近搜索 -->
    <RecentSearch
      :searchList="searchList"
      :hotList="hotList"
      @search-content="clickSearchContent"
      @popular-destinations="popularDestinations"
    ></RecentSearch>
  </div>
</template>

<script>
import LocationAssembly from '../../components/Location'
import RecentSearch from '../../components/RecentSearch'

import { Toast } from 'vant'
import { getSearchInfo } from '@/utils/user'
// import { searchInfo } from '../../utils/search'

export default {
  name: 'SearchProposal',
  components: { LocationAssembly, RecentSearch },
  data() {
    return {
      // 搜索框的值
      searchValue: '',
      // 搜索记录数据
      searchList: [],
      // 热门目的数据
      hotList: []
    }
  },
  created() {
    this.initSearchProposal()
    this.obtainSearchinfo()
  },
  methods: {
    // 获取搜索页信息
    obtainSearchinfo() {
      getSearchInfo().then(res => {
        // console.log(res)
        const { code, data, msg } = res.data
        if (code === 0) {
          this.hotList = data.hotPlaces
          // console.log(this.hotList, 123)
        } else {
          Toast.fail(msg)
        }
      })
    },
    // 点击热门目的地
    popularDestinations(value) {
      console.log(123, value)
      // this.$emit('popular-destinations', value)
      // Dialog.alert({
      //   message: '功能完善中~'
      // }).then(() => {
      //   // on close
      // })
      this.$router.push({
        path: '/home/popular',
        query: {
          value: value
        }
      })
    },
    // 点击搜索建议
    clickSearchContent(value) {
      if (value === '') {
        Toast.fail('请检查搜索内容！')
        return
      }

      this.$router.push({
        path: '/home/popular',
        query: {
          value: value
        }
      })
    },
    // 获取搜索建议
    initSearchProposal() {
      let a = localStorage.getItem('list')
      if (a === null) return
      // try-catch主要用于进行异常处理；
      // 如果程序运转正常，就执行try，否则执行catch
      try {
        // 定义一个函数来获取网页的数据，并转换字符串数组
        a = JSON.parse(a)
        // console.log(a)
        // 然后把储存的值赋给 this.list 展示
        this.$store.commit('ADD_SEARCH_LIST', a)
      } catch (error) {
        // 给一个默认值
        this.list = []
        // console.log(123)
      }
    },
    // 搜索内容
    searchContent(Value) {
      // console.log(Value, 123)
      // 判断是不是为空
      if (Value === '') {
        Toast.fail('请检查搜索内容！')
        return
      }

      this.$router.push({
        path: '/home/popular',
        query: {
          value: Value
        }
      })
      const list = this.$store.state.searchList
      // 添加输入内容到list数组里面的时候，先把输入的内容去除掉只剩一个空格
      let listData = JSON.parse(JSON.stringify(list))
      listData.unshift(Value.replace(/\s+/g, ' '))
      //   去重
      listData = [...new Set(listData)]
      if (listData.length > 5) {
        // slice索引位置获取新的数组，该方法不会修改原数组
        listData = listData.slice(0, 5)
      }
      this.$store.commit('ADD_SEARCH_LIST', listData)
      // 定义一个变量，JSON数据交换格式，stringify将JS对象转换为JSON字符串
      const listDataStr = JSON.stringify(listData)

      localStorage.setItem('list', listDataStr)

      // 提示
      Toast('搜索功能未完善~')
      // 搜索完清空搜索内容
      this.searchValue = ''
    },
    // 返回上一页
    returnUpper() {
      // console.log(123)
      this.$router.back()
    }
  }
}
</script>

<style lang="scss" scoped>
.search_proposal {
  padding: 10px;
  // 定位
  .location_assembly {
    margin: 10px 0;
  }
}
</style>
