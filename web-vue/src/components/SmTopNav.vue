<template>
  <div class="row row-allwidth nav-row">
    <div class="col-md-4 col-sm-4 col-xs-4  nav-left">
      <router-link to='/'><a href="javascript:;" class="nav-a">Readm-DHT</a></router-link>
    </div>

    <div class="col-md-1 col-md-offset-5 col-sm-1 col-sm-offset-5
				col-xs-1 col-xs-offset-4 nav-rigth">
      <a href="https://github.com/readmlll" target="_blank" class="nav-a">Github</a>
    </div>
    <div class="col-md-1 col-sm-1 col-xs-1 nav-rigth help-xs">
      <a href="javascript:;" class="nav-a">Help</a>
    </div>
    <div class="col-md-1 col-sm-1  col-xs-1 nav-rigth">
      <a href="javascript:;"  @click="gotoStatement" class="nav-a">声明</a>
    </div>
  </div>
</template>

<script>
    export default {
        name: "SmTopNav",
      methods:{
        gotoStatement(){
          this.$router.push({'name':'statement'})
        }
      }
    }
</script>

<style scoped>

  .nav-row {
    height: 37px;
    background: black;
    opacity: 0.4;
    line-height: 35px;
  }

  @media (min-width: 0px) and (max-width: 768px) {
    .help-xs{
      width: auto;
      margin-left: 4%;
      margin-right: 2%;
    }
  }


</style>
