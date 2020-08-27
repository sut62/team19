import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import '@mdi/font/css/materialdesignicons.css'
import VueSimpleAlert from "vue-simple-alert";

Vue.use(VueSimpleAlert);
Vue.config.productionTip = false

new Vue({
  vuetify,
  router,
  render: h => h(App)
}).$mount('#app')
