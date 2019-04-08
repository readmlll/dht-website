import api from "../api/data-api"
import utils from "../api/utils"

export default {
  setInfoTotal ({commit}) {
    api.getTotal(function(total){
      utils.tip()
      total=api.formatMoneyNum(total)
      commit('setInfoTotal', {total})
      utils.closeTip()
    })
  },
  searchTitle({commit},{title,page}){
    utils.tip()
    api.searchTitle(function (data) {
      commit('setCurrentPageNum', {currentPageNum:page})
      commit('setCurrentInfoPage', {currentInfoPage:data})
      commit('setCurrentSearchKeyWord', {currentSearchKeyWord:title})
      utils.closeTip()
    },title,page)
  },
  setCurrentSearchKeyWord({commit},{currentSearchKeyWord}){
    commit('setCurrentSearchKeyWord', {currentSearchKeyWord})
  }
}
