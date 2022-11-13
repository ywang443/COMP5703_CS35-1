<template>
  <div class="map_assembly">
    <!-- 搜索框 -->
    <div class="search_frame">
      <van-cell-group>
        <van-field
          v-model="value"
          clearable
          left-icon="arrow-left"
          placeholder="Search destionation..."
          @click-left-icon="returnUpper"
          @keyup.enter="searchContent(value)"
        />
      </van-cell-group>
    </div>
    <!-- 地图展示 -->
    <div id="container"></div>
  </div>
</template>

<script>
import { Toast } from 'vant'
export default {
  name: 'MapAssembly',
  data() {
    return {
      value: '' // 输入框的值
    }
  },
  created() {
    this.initLocation()
  },
  mounted() {
    this.init()
  },
  watch: {
    // ' $store.state.location'() {
    //   console.log(123)
    //   this.init()
    // }
  },
  methods: {
    // 搜索内容
    searchContent(value) {
      Toast('功能正在完善中~')
      this.value = ''
    },
    // 点击返回上一页
    returnUpper() {
      this.$router.back()
    },
    // 定位
    initLocation() {
      Toast.loading({
        message: '定位中...',
        duration: 5000
      })
      console.log(1)
      const key = 'WQLBZ-ZVZC4-VBAU6-DULCQ-TMV3T-4WFYY'
      const geolocation = new window.qq.maps.Geolocation(key, 'restaurant')
      // console.log(geolocation)
      // console.log(geolocation.getLocation)
      geolocation.getLocation(
        data => {
          // console.log('定位成功', data)
          Toast('定位成功')
          this.locationData = `${data.nation}-${data.province}-${data.city}`
          this.$store.commit('GET_LOCATION', data)
        },
        err => {
          console.error('定位失败：', err)
          // this.$store.commit('SET_USER_POSITION', {})
          Toast({
            message: '定位失败',
            duration: 1500
          })
        },
        { timeout: 5000 }
      )
    },
    // 初始化地图
    init() {
      console.log(this.$store.state.location, 2)
      this.$nextTick(() => {
        const { lat, lng } = this.$store.state.location
        const mapPositioning = new window.qq.maps.Map(
          document.getElementById('container'),
          {
            center: new window.qq.maps.LatLng(lat, lng), // 地图的中心地理坐标。
            zoom: 8 // 地图的中心地理坐标。
          }
        )
        console.log(mapPositioning)
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.map_assembly {
  width: 100%;
  height: 100%;
  // 搜索 框
  .search_frame {
    z-index: 9999;
  }
  // 地图
  #container {
    width: 100%;
    height: 100%;
  }
}
</style>
