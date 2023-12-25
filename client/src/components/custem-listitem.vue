<template>
  <view>
    <view style="font-size: 12px; color: #999999">{{ itemData.scNo }}</view>
    <view style="font-size: 16px;font-weight: 600; color: #333; margin-bottom: 4px">{{
      itemData.projectName
    }}</view>
    <nut-tag color="#E9E9E9" textColor="#999999" v-if="itemData.state === 1"
      >待评价</nut-tag
    >
    <nut-tag type="primary" v-if="itemData.state === 2">完成评价</nut-tag>
    <nut-tag
      type="success"
      v-if="itemData.state === 2 && itemData.serviceScore > 4"
      >好评</nut-tag
    >
    <nut-tag
      type="danger"
      v-if="itemData.state === 2 && itemData.serviceScore < 2"
      >差评</nut-tag
    >
    <view style="font-size: 12px; color: #999999">{{
      dayjs(itemData.commitTime).format("YYYY-MM-DD HH:mm:ss")
    }}</view>
  </view>
</template>

<script>
import * as dayjs from "dayjs";

export default {
  props: {
    itemData: {
      type: Object,
      default: () => ({
        projectName: "项目名称",
        serviceScore: 5,
        state: 1,
        commitTime: "2023-12-14",
      }),
    },
  },
  setup() {
    return {
      dayjs,
    };
  },
};
</script>

<style lang="scss">
.nut-tag + .nut-tag {
  margin-left: 5px;
}
</style>