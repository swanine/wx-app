<template>
  <view> count: {{ count }} 
    <button open-type="getPhoneNumber" @getphonenumber="callback">按钮</button>
    <!-- <button @click="login">按钮</button> -->
  </view>
</template>

<script>
import { onMounted, ref } from "vue";
import Taro from "@tarojs/taro";
import { wxLogin, getUserPhone } from "../../api";

export default {
  setup() {
    const count = ref(9)

    onMounted(() => {
      
    });

    // const login = () => {
    //   Taro.login({
    //     success: function (res) {
    //       console.log(res);
    //       wxLogin(res.code).then(res => {
    //         console.log(res);
    //       })
    //     }
    //   })
    // }

    const callback = (e) => {
      console.log(e);
      if (e.detail) {
        getUserPhone(e.detail.code).then(res => {
          console.log(res);
        })
      }
    }

    return {
      count,
      callback,
      // login
    };
  },
};
</script>

<style>
</style>