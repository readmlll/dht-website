import Vue from 'vue'
import Vuex from 'vuex'
import state from './state'
import mutations from './mutations'
import actions from './actions'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
  state, // 状态对象  数据
  mutations, // 包含多个更新数据的对象
  actions, // 包含多个对事件回调函数的对象
  getters // 包含多个getter计算属性的对象
})
