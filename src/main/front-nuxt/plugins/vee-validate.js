import Vue from "vue";
import VeeValidate, { Validator } from "vee-validate";
import ko from "vee-validate/dist/locale/ko";

Vue.use(VeeValidate, {
  inject: false
});

Validator.localize("ko", ko);
