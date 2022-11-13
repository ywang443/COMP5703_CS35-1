<template>
  <div class="classification">
    <div
      class="classification_content"
      v-for="item in classificationData"
      :key="item.id"
      @click="enterClassification(item.id, item.name)"
    >
      <div class="logo">
        <van-image width="50" height="50" :src="item.img" />
      </div>
      <div class="classification_name">{{ item.name }}</div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ClassiFication',
  props: {
    classificationData: {
      type: Array
    }
  },
  methods: {
    enterClassification(id, name) {
      // console.log(id, 123)
      this.$emit('enter-classification', id, name)
    }
  }
}
</script>

<style lang="scss" scoped>
.classification {
  // border: 1px solid;
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-around;
  background-color: white;
  border-radius: 15px;
  padding: 5px;
  .classification_content {
    width: 15%;
    // border: 1px solid;
    .logo {
      ::v-deep .van-image__img {
        border-radius: 50px;
      }
    }
    .classification_name {
      width: 100%;
      text-align: center;
      font-weight: bold;
      overflow: hidden;
      // 省略号
      text-overflow: ellipsis;
      /*弹性伸缩盒子模型显示*/
      display: -webkit-box;
      /*限制在一个块元素显示文本的行数*/
      -webkit-line-clamp: 1; /*2行*/
      /*设置或检索伸缩盒对象子元素的排列方式*/
      -webkit-box-orient: vertical;
    }
  }
}
</style>
