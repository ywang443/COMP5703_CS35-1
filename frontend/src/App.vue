<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="scss">
#app {
  width: 100%;
  height: 100vh;
  // overflow-x: auto;
  // overflow-y: hidden;
  overflow: hidden;
  margin: 0;
  padding: 0;
  background-color: white;
}
body {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
</style>
