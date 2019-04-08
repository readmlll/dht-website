<template>
  <div class="container container-allwidth all-height" style="min-height: 100%;">
    <SmTopNav />
    <SmSearchRow />



    <div class="row  row-allwidth" style="min-height: calc(100% - 142px); padding-top: 40px; padding-left: 20px;">
      <div class="col-md-7 col-md-offset-2">
        <h2>使用Readm-DHT前必读</h2>
        <br>
        <p style="text-indent: 0">Readm提醒您：在使用Readm磁力搜索引擎（以下简称Readm）前，请您务必仔细阅读并透彻理解本声明。您可以选择不使用Readm，但如果您使用Readm，您的使用行为将被视为对本声明全部内容的认可。</p>

      </div>
      <div class="col-md-7 col-md-offset-2">
        <ul style="margin-left: 20px;">
          <li>鉴于Readm以非人工检索方式、根据您键入的关键字自动生成到第三方资源的磁力链接，一切因使用Readm而可能遭致的意外、疏忽、侵权及其造成的损失（包括因下载资源而感染电脑病毒等），Readm对其概不负责，亦不承担任何法律责任。任何通过使用Readm而搜索链接到的资源均系他人制作或提供，您可能从该资源中获得资讯及享用服务，Readm对其合法性概不负责，亦不承担任何法律责任。</li>
          <li>Readm搜索结果是根据您键入的关键字自动搜索并生成的，不代表Readm赞成搜索到的资源中的内容或立场。</li>
          <li>您应该对使用搜索引擎的结果自行承担风险。Readm不做任何形式的保证：不保证搜索结果满足您的要求，不保证搜索服务不中断，不保证搜索结果的安全性、正确性、及时性、合法性。因网络状况、通讯线路、第三方网站等任何原因而导致您不能正常使用Readm，Readm不承担任何法律责任。</li>
          <li>Readm尊重并保护所有使用Readm用户的个人隐私权，未保存任何个人资料。Readm提醒您：您在使用搜索引擎时输入的关键字将不被认为是您的个人隐私资料。</li>
          <li>Readm没有存储任何种子文件，下载链接由第三方存储站点提供。但是如果任何单位或个人认为通过Readm搜索链接到的资源可能涉嫌侵犯其信息网络传播权等合法权利，应该及时向Readm提出权利通知，并提供身份证明、权属证明及详细侵权情况证明。Readm在收到上述文件后，将会尽快协助屏蔽本站的相关内容。</li>
          <li>Readm在努力屏蔽一切不良信息，但是DHT网络中的资源是实时变化的，请您在发现不良信息时及时反馈给我们。反馈方式：填写
            <a href="#"target="_blank">在线问卷</a>或发邮件至readmlll@aliyun.com，谢谢！</li>
        </ul>
      </div>
    </div> <!--  row-->

    <div class="back"></div>

    <div class="row row-allwidth">

    </div>


    <Allright />

  </div>
</template>

<script>
  import SmTopNav from "@/components/SmTopNav"
  import SmSearchRow from "@/components/SmSearchRow"
  import Allright from "@/components/Allright"
  export default {
    name: "statement",
    components: {
      SmTopNav,
      SmSearchRow,
      Allright
    },
    data(){
      return {

      }
    },
    methods:{

    },
    computed:{
    },
    mounted() {
    },
    watch:{

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


  .bt-search-pack ul {
    margin-top: 16px;
  }

  .bt-search-pack ul:last-child {
    margin-bottom: 20px;
  }

  .bt-search-pack ul div:nth-child(n) {
    margin-top: 10px;
  }


  .bt-search-pack ul div a {
    margin-bottom: 2px;
  }



  .border-eee {
    border: 1px solid #eee;
  }

  .all-height {
    height: calc(100% - 200px);
  }
</style>
