export default {
  setInfoTotal(state, {total}){
    state.total =total
  },
  setCurrentSearchKeyWord(state, {currentSearchKeyWord}){
    state.currentSearchKeyWord=currentSearchKeyWord;
  },
  setCurrentInfoPage(state, {currentInfoPage}){
    state.currentInfoPage=currentInfoPage
  },
  setCurrentPageNum(state, {currentPageNum}){
    state.currentPageNum=currentPageNum
  }
}
