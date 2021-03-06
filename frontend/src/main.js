import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// 부트스트랩 적용 ************************** //
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
// Install BootstrapVue
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)
// ***************************************** //

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App }, 
  template: '<App/>'
})
