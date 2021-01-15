import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import Modify from '../page/user/Modify.vue'

// 포스트
import List from '../page/post/List.vue'

// Page Not Found 페이지
import PageNotFound from '../page/error/PageNotFound.vue'
Vue.use(Router) 
 
export default new Router({
  mode: 'history',
  routes: [   
    // 로그인/가입
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    {
      path: '/user/modify',
      name: constants.URL_TYPE.POST.MAIN,
      component: Modify,
    },
    // 포스트
    { 
      path: '/',
      name: constants.URL_TYPE.POST.MAIN,
      component: List,
    },
    {
      path: '*',
      redirect: '/404'
    },
    {
      path: '/404',
      component: PageNotFound
    }

  ]
})
