<template>
  <div id="app" style="height: 100%">
    <router-view></router-view>
  </div>


</template>

<script>

  export default {
    name: 'app',
    components: {
    }
  }
</script>

<style>
  @import "../static/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css";
  @import "../static/lib/font-awesome-4.7.0/css/font-awesome.min.css";
  @import "../static/css/common.css";
  body{
    min-width: 430px;
  }
</style>
