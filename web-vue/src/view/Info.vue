<template>
    <div class="container container-allwidth all-height" >
      <SmTopNav />
      <SmSearchRow />

      <div style="height: 30px;">
      </div>

      <div class="row  row-allwidth" style="height: calc(100% - 130px)" >

        <div class="col-md-7 col-md-offset-1 col-xs-10 col-xs-offset-1">

          <div class="panel panel-default info-conten-panel ">
            <div class="panel-heading info-content-head ">
              <h3>基础信息</h3>
            </div>
            <div class="panel-body info-content">
              <p>资源名称:&nbsp;<span>{{currentHashInfo.fileTitle.toString().trim()}}</span></p>
              <p>文件大小:&nbsp;&nbsp;<span>{{currentHashInfo.fileSize}}</span></p>
              <p>创建日期:&nbsp;&nbsp;<span>{{currentHashInfo.time}}</span></p>
              <p>磁力链接:&nbsp;&nbsp;<span><a :href="'magnet:?xt=urn:btih:'+currentHashInfo.infoHash">magnet:?xt=urn:btih:{{currentHashInfo.infoHash}}</a></span></p>
              <p>文件数量:&nbsp;&nbsp;<span>{{currentHashInfo.fileSize}}</span></p>
            </div>
          </div>

          <div class="panel panel-default info-conten-panel ">
            <div class="panel-heading info-content-head ">
              <h3>文件列表</h3>
            </div>
            <div class="panel-body info-content">
              <ul class="list-group">
                <li v-for="fileName in currentHashInfo.fileLIst" class="list-group-item list-group-item-self">{{fileName}}</li>
              </ul>
            </div>
          </div>

        </div>

        <div class="col-md-2 col-md-offset-1 col-xs-10 col-xs-offset-1 border-eee">
          <div class="statement">
            本站是一个磁力链接搜索引擎，可以从DHT网络获取到当前的活跃资源，用于DHT网络的学习，分析和研究。内容是本站根据您的指令自动搜索的结果，不代表本站赞成资源的内容或立场，资源的版权归种子制作者所有。
            更多内容请查看声明。
          </div>
        </div>


      </div>

      <div class="back"></div>

      <Allright />
    </div>
</template>

<script>
  import SmTopNav from "@/components/SmTopNav"
  import SmSearchRow from "@/components/SmSearchRow"
  import Allright from "@/components/Allright"
  import {mapState,mapGetters,mapActions} from 'vuex'

    export default {
        name: "info",
      components: {
        SmTopNav,
        SmSearchRow,
        Allright
      },
      computed:{
        currentHashInfo(){
          return this.currentInfoPage.content[this.$route.params.index]
        },
        ...mapState(['currentInfoPage'])
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

  .statement{
    padding-top: 12px;
    padding-bottom: 12px;
  }

  .border-eee{
    border: 1px solid #eee;
  }

  .panel-heading h3{
    margin: 0;
    font-size: 15px;
    font-weight: 900;
  }
  p{
    word-wrap:break-word ;
  }

  .list-group-item-self{
    padding: 3px 2px 3px 6px;
  }

  .all-height{
    height:calc(100% - 49px);
  }
</style>
