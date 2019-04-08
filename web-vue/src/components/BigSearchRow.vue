<template>
  <div class="row row-allwidth">
    <div class="col-md-4 col-md-offset-4 col-xs-6 col-xs-offset-2 no-right-padding">
      <input @keyup.enter="KeyUpEnter" type="text" v-model="searchKeyWord" class="form-control search-input" placeholder="What would you like?...">
    </div>
    <div class="col-md-2 col-xs-2 no-left-padding">
      <router-link to='/search.html'><button @click="searchClick" type="button" class="search-bn btn btn-info">Find It</button></router-link>

    </div>
  </div>
</template>

<script>
  import {mapState,mapGetters,mapActions} from 'vuex'

    export default {
        name: "BigSearchRow",
        data(){
          return {
            searchKeyWord : ""
          }
        },
      methods:{
        KeyUpEnter(){
          this.searchClick()
          this.$router.push({name:'search'})
        },
        searchClick(){
          this.searchKeyWord=this.searchKeyWord.trim()
          if (this.searchKeyWord==="")
                return false

          this.searchTitle({title:this.searchKeyWord,page:1})
        },
        ...mapActions(['searchTitle'])
      }
    }
</script>

<style scoped>


  .search-input {
    height: 46px;
    padding-left: 20px;
  }

  .search-bn {
    height: 46px;
    font-size: 18px;
  }
</style>
