<template>
  <div style="height: 100%" class="back">

    <TopNav/>
    <div style="height: 160px;"></div>
    <div class="row row-allwidth">
      <div class="col-md-12 search-title">
        SEARCH
      </div>
    </div>

    <BigSearchRow />

    <div class="row row-allwidth">
      <div class="col-md-12 no-left-padding info-sum">
        已经收录 {{total}} 条数据
      </div>
    </div>

    <div class="row row-allwidth">
      <div class="col-md-12 bottom-copyright col-xs-12">
        Copyright © 2019 Readm 联系我们
      </div>
    </div>
  </div>


</template>

<script>
  import TopNav from "../components/TopNav.vue"
  import BigSearchRow from "../components/BigSearchRow";
  import {mapState,mapGetters,mapActions} from 'vuex'

  export default {
    name: 'index',
    components: {
      BigSearchRow,
      TopNav
    },
    computed:{
      ...mapState(['total'])
    },
    data(){
      return {

      }
    },
    methods:{
      ...mapActions(['setInfoTotal'])
    },
    mounted(){
      console.log(this.setInfoTotal())
    }
  }
</script>

<style>
  .back {
    width: 100%;
    height: 100%;
    background-image: url(../assets/back.jpg);
    background-size: 100% 100%;
  }

  /**
         * 搜索框
         */
  .search-title {
    color: white;
    font-size: 40px;
    text-align: center;
    font-weight: 900;
    margin-bottom: 16px;
  }


  .info-sum {
    opacity: 0.8;
    color: white;
    text-align: center;
    margin-top: 60px;
    font-size: 20px;
    font-weight: 900;
    margin-left: 20px;
    text-shadow: -1px -1px white, 1px 1px #333
  }
</style>
