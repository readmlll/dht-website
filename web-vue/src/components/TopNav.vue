<template>
  <div>
    <div class="row row-allwidth nav-row">
      <div class="col-md-4 col-sm-4 col-xs-4  nav-left">
        <router-link to='/'><a href="javascript:;" class="nav-a">Readm-DHT</a></router-link>
      </div>
      <div class="col-md-1 col-md-offset-5 col-sm-1 col-sm-offset-5 col-xs-2 col-xs-offset-4  col-sm-1 col-sm-offset-5
				col-xs-1 col-xs-offset-5  nav-rigth right-first">
        <a href="https://github.com/readmlll" target="_blank" class="nav-a">Github</a>
      </div>
      <div class="col-md-1 col-sm-1 col-xs-1 col-xs-offset-1 nav-rigth margin-left">
        <a href="javascript:;" class="nav-a">Help</a>
      </div>
      <div class="col-md-1 col-sm-1  col-xs-1 col-xs-offset-1  nav-rigth margin-left auto-width">
        <a href="javascript:;" @click="gotoStatement" class="nav-a">声明</a>
      </div>
    </div>
  </div>
</template>

<script>
    export default {
        name: "TopNav",
      methods:{
        gotoStatement(){
          this.$router.push({'name':'statement'})
        }
      }
    }
</script>

<style scoped>
  .margin-left{
    margin-left: 1%;
  }

  @media (min-width: 0px) and (max-width: 768px) {
    .margin-left{
      margin-left: 3%;
    }
    .right-first{
      margin-right: 8px;
    }
  }

  @media (min-width: 768px) and (max-width: 992px) {
    .margin-left{
      margin-left: 2%;
      margin-right: -2%;
    }
  }
  @media (min-width: 992px)  {
    .margin-left{
      margin-right: -2%;
    }
    .auto-width{
      width: auto;
    }
  }
</style>
