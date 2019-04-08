import jsonp from 'jsonp'
import utils from './utils'
let baseUrl='http://149.28.151.77/search'

export default {
  getTotal (func) {

    setTimeout(function () {
        if (window.layer) {
          window.layer.closeAll()
        }
    },50*1000)

    jsonp(baseUrl + '/total',null,(err,data)=>{
      if (err){
        console.log(err)
      }else{
        func(data)
      }
    })
  },
  searchTitle(func,title,page){
    setTimeout(function () {
      if (window.layer) {
        window.layer.closeAll()
      }
    },50*1000)

    jsonp(baseUrl + '/title?q='+title+"&page="+page+"&size=10",null,(err,data)=>{
      if (err){
        console.log(err)
      }else{
        func(data)
      }
    })
  },
  formatMoneyNum(num){
    if (num===undefined || num===null)
      return 0
    num=num+"";
    let partNum=Math.floor(num.length/3);
    let preCount=num.length%3
    let count=0
    let res=[]
    for (;count<partNum;count++){
      res.push(num.substr(-3*(count+1),3))
    }
    res.push(num.substr(0,preCount))
    num=""
    count = res.length-1

    for (;count>=0;count--){
      num+=","+res[count]
    }
    while(num[0]===',')
      num=num.substr(1)
    return num
  }
}
