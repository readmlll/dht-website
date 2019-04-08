<template>
  <div class="container container-allwidth all-height">
    <SmTopNav />
    <SmSearchRow />

    <div class="row  row-allwidth">
      <div class="col-md-12 col-md-offset-1 col-xs-10 col-xs-offset-1 search-hint">
        为您找到 {{api.formatMoneyNum(currentInfoPage.totalElements)}} 条相关结果 , 共{{currentInfoPage.totalPages}}页 , 当前第{{currentPageNum}}页。
      </div>
    </div>

    <div class="row  row-allwidth" style="height: calc(100% - 65px);">

      <div class="col-md-6 col-md-offset-1 col-xs-10 col-xs-offset-1 border-eee">
        <li class="bt-search-pack">

          <ul v-for="(info,index) in currentInfoPage.content">
            <div class="row row-allwidth ft-size-16">
              <a class="col-md-12" :href="'/#/info/'+index">{{info.fileTitle}}</a>
            </div>
            <div class="row row-allwidth ft-size-13">
              <div class="col-md-3 col-xs-3 fa fa-cloud-download"><a class="search-pack-bt-a" :href="'magnet:?xt=urn:btih:'+info.infoHash">磁力</a></div>
              <div class="col-md-3 col-xs-3">大小: {{info.fileSize}}</div>
              <div class="col-md-3 col-xs-3">文件个数: {{info.fileNum}}</div>
              <div class="col-md-3 col-xs-3">收录时间: {{info.time}}</div>
            </div>
          </ul>


        </li>
      </div>

      <div class="col-md-3 col-md-offset-1 col-xs-10 col-xs-offset-1 border-eee">
        <div class="statement">
          本站是一个磁力链接搜索引擎，可以从DHT网络获取到当前的活跃资源，用于DHT网络的学习，分析和研究。内容是本站根据您的指令自动搜索的结果，不代表本站赞成资源的内容或立场，资源的版权归种子制作者所有。
          更多内容请查看声明。
        </div>
      </div>
    </div> <!--  row-->

    <div class="back"></div>

    <div class="row row-allwidth">
      <nav aria-label="..." class="col-md-10 col-md-offset-1 col-xs-10 col-xs-offset-1">

        <ul class="pagination" @click="gotoPage">

          <li  v-bind:id="'page-'+pageNum"
               v-for="pageNum in utils.pageNumNavGenerate(currentInfoPage.number+1,currentInfoPage.totalPages,5)"
               v-if="pageNum==currentPageNum"
          class="active" >
            <span class="sr-only" style="height: auto;width: auto;margin: 0;">
              {{pageNum}}
            </span>
          </li>

          <li  v-else v-bind:id="'page-'+pageNum">
            <span class="disabled">
            {{pageNum}}
            </span>
          </li>
        </ul>
      </nav>
    </div>


  <Allright />

  </div>
</template>

<script>
  import SmTopNav from "@/components/SmTopNav"
  import SmSearchRow from "@/components/SmSearchRow"
  import Allright from "@/components/Allright"
  import {mapState,mapGetters,mapActions} from 'vuex'
  import api from '../api/data-api'
  import utils from '../api/utils'


    export default {
      name: "SearchPage.vue",
      components: {
        SmTopNav,
        SmSearchRow,
        Allright
      },
      data(){
        return {
          api,
          utils,
          tip: ' <p id="tip">请稍后，正在加载中...</p>'
        }
      },
      methods:{
        gotoPage(ev){

          console.log(ev.target.parentNode)
          let page=ev.target.parentNode.id.toString().split("-")[1]
          if (page ===undefined || page === null)
              return false

          if (page==='首页')
              page = 1
          if (page==='尾页')
              page = this.currentInfoPage.totalPages
          if (page==='<')
              page = this.currentInfoPage.number
          if (page==='>')
              page = this.currentInfoPage.number+2


          if (this.currentPageNum!=page)
          {
            this.searchTitle({title:this.currentSearchKeyWord,page})
            document.body.scrollTop=0
          }
        },
        ...mapActions(['searchTitle'])
      },
      computed:{
        ...mapState(['currentInfoPage','currentSearchKeyWord','currentPageNum'])
      },
      mounted() {
        console.log(this.currentInfoPage)
      },
      watch:{
        currentInfoPage:{//深度监听，可监听到对象、数组的变化
          deep:true,
          handler (nv) {
          }
        }
      }
    }
</script>

<style scoped>
  .back {
    width: 100%;
    height: 100%;
    background: white;
    opacity: 0.8;
    z-index: -10;
    position: fixed;
    top: 0;
  }

  .search-hint {
    color: rgba(0, 0, 0, .5);
    padding: 10px;
  }

  .bt-search-pack {
    list-style: none;
  }

  .bt-search-pack ul {
    margin-top: 16px;
  }

  .bt-search-pack ul:last-child {
    margin-bottom: 20px;
  }

  .bt-search-pack ul div:nth-child(n) {
    margin-top: 10px;
  }

  .search-pack-bt-a {
    color: black;
    margin-left: 4px;
  }

  .bt-search-pack ul div a {
    margin-bottom: 2px;
  }

  .statement {
    padding-top: 12px;
    padding-bottom: 12px;
  }

  .border-eee {
    border: 1px solid #eee;
  }

  .all-height {
    height: calc(100% - 200px);
  }
</style>
