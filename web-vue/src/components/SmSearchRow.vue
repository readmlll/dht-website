<template>
  <div class="row row-allwidth top-search">
    <div class="col-md-4 col-md-offset-0 col-xs-6 col-xs-offset-2 no-right-padding">
      <input @keyup.enter="KeyUpEnter" type="text" v-model="searchKeyWord" class="form-control search-input" placeholder="What would you like?...">
    </div>
    <div class="col-md-2  col-xs-2 no-left-padding">
      <button @click="searchClick" type="button" class="search-bn btn btn-default top-search-bn">Find It</button>
    </div>

    <div class="col-md-1 col-md-offset-3 col-xs-1 col-xs-offset-1 back-bn-col">
      <button @click="goback" type="button" class="fa fa-mail-reply back-bn"></button>
    </div>
  </div>
</template>

<script>
    import {mapState,mapGetters,mapActions} from 'vuex'
    export default {
      name: "SmSearchRow",
      data(){
        return {
          currentSearchKeyWordBack:''
        }
      },
      computed:{
        searchKeyWord:{
          get(){
            return this.currentSearchKeyWord
          },
          set(newValue){
            this.currentSearchKeyWordBack=newValue
          }
        },
        ...mapState(['currentInfoPage','currentSearchKeyWord'])
      },
      methods:{
        KeyUpEnter(){
          this.searchClick()
          this.$router.push({name:'search'})
        },
        searchClick(){
          this.currentSearchKeyWordBack=this.currentSearchKeyWordBack.trim()
          if (this.currentSearchKeyWordBack==="")
            return false

          this.searchTitle({title:this.currentSearchKeyWordBack,page:1})
          this.$router.push({name:'search'})

        },
        goback(){
          this.$router.go(-1)
        },
        ...mapActions(['setCurrentSearchKeyWord','searchTitle'])
      }
    }
</script>

<style scoped>

  .top-search {
    background: rgb(248, 248, 248);
    padding: 12px;
    padding-left: none;
    border-bottom: 1px solid rgb(231, 231, 231);
  }

  .top-search-bn {
    color: black;
    margin-left: 10px;
  }


  .bt-search-pack ul {
    margin-top: 16px;
  }
  .bt-search-pack ul:last-child{
    margin-bottom: 20px;
  }

  .bt-search-pack ul div:nth-child(n){
    margin-top: 10px;
  }


  .bt-search-pack ul div a{
    margin-bottom: 2px;
  }

</style>
