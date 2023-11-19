import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";


const app = createApp(App);

app.use(createPinia());
app.use(router);

app.component('VueDatePicker', VueDatePicker);

app.mount('#app')

