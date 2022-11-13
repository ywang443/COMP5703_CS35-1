<template>
  <div class="location">
    <van-icon name="aim" />
    {{ locationData }}
  </div>
</template>

<script>
import { Toast } from 'vant'

export default {
  name: 'LocationAssembly',
  data() {
    return {
      locationData: '123'
    }
  },
  created() {
    this.initLocation()
  },
  // watch: {
  //   '$store.state.loading'() {
  //     this.initData()
  //   }
  // },
  methods: {
    // async initData() {
    //   console.log(2)
    //   this.locationData = await this.$store.state.location
    // },
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
    }
  }
}
</script>

<style lang="scss" scoped>
.location {
  // width: 80%;
  padding-bottom: 20px;
  border-bottom: 1px solid #e6e8ec;
}
</style>
