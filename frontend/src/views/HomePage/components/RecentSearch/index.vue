<template>
  <div class="recent_search">
    <!-- 记录 -->
    <div class="search_record">
      <!-- 标题 -->
      <h5>Recent Search</h5>
      <!-- 搜索记录 -->
      <div class="record">
        <div
          class="record_text"
          v-for="(item, index) in $store.state.searchList"
          :key="index"
          @click="searchContent(item)"
        >
          <van-icon name="clock-o" />
          {{ item }}
        </div>
      </div>
    </div>
    <!-- 热门 -->
    <div>
      <!-- 标题 -->
      <h5>Popular Destinations</h5>
      <!-- 搜索记录 -->
      <div class="hot_record">
        <span
          v-for="(item, index) in hotList"
          :key="index"
          @click="popularDestinations(item)"
        >
          {{ item }}
        </span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RecentSearch',
  props: {
    searchList: {
      type: Array
    },
    hotList: {
      type: Array
    }
  },
  methods: {
    // 点击热门目的地
    popularDestinations(value) {
      //  console.log(123, value)
      this.$emit('popular-destinations', value)
    },
    // 点击搜索建议
    searchContent(value) {
      console.log(123, value)
      this.$emit('search-content', value)
    }
  }
}
</script>

<style lang="scss" scoped>
.recent_search {
  // 搜索记录
  .search_record {
    padding-bottom: 20px;
    border-bottom: 1px solid #e6e8ec;
    .record {
      // 搜索文本
      .record_text {
      }
      .van-icon {
        margin: 5px 0;
        margin-right: 10px;
      }
    }
  }
  // 热门
  .hot_record {
    display: flex;
    flex-wrap: wrap;
    span {
      padding: 5px;
      border-radius: 10px;
      margin-bottom: 10px;
      margin-right: 10px;
      background-color: #f1f2f3;
    }
  }
}
</style>
