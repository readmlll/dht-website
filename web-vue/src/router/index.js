import Vue from 'vue'
import Router from 'vue-router'
//import HelloWorld from '@/components/HelloWorld'
import App from "../App"
import Search from "../view/Search"
import Index from "../view/Index"
import Info from "../view/Info"
import Statement from '../view/Statement'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/index.html',
    },
    {
      path: '/search.html',
      name: 'search',
      component: Search
    },
    {
      path: '/index.html',
      name: 'index',
      component: Index
    },
    {
      path: '/info/:index',
      name: 'info',
      component: Info
    },
    {
      path: '/statement.html',
      name: 'statement',
      component: Statement
    }
  ]
})
