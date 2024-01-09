<template>
  <view> count: {{ count }} </view>
</template>

<script>
import { onMounted, ref } from "vue";
import Taro from "@tarojs/taro";

export default {
  setup() {
    const count = ref(9)

    onMounted(() => {

    });

    return {
      count
    };
  },
};
</script>

<style>
</style>