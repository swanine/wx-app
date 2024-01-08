<template>
  <view class="page index">
    <nut-swiper
      :init-page="page"
      :loop="true"
      auto-play="3000"
      direction="vertical"
      height="180"
      :pagination-visible="true"
      style="height: 180px"
    >
      <nut-swiper-item>
        <img src="http://www.szmold.cn/PicFile/1508480829.jpg" alt="" />
      </nut-swiper-item>
      <nut-swiper-item>
        <img src="http://www.szmold.cn/PicFile/1508481684.jpg" alt="" />
      </nut-swiper-item>
      <nut-swiper-item>
        <img src="http://www.szmold.cn/PicFile/1511494135.jpg" alt="" />
      </nut-swiper-item>
      <nut-swiper-item>
        <img src="http://www.szmold.cn/PicFile/1511422289.jpg" alt="" />
      </nut-swiper-item>
    </nut-swiper>

    <view class="card">
      <nut-cell>
        <template #title>
          <span class="title" style="color: #111">基础服务</span>

          <view class="feature">
            <view class="item" @tap="goToAfterSaleForm">
              <img :src="p1" />
              <view class="item-name">售后单</view>
            </view>

            <view class="item" @tap="goToAfterSale">
              <img :src="p2" />
              <view class="item-name">售后查询</view>
            </view>
          </view>
        </template>
      </nut-cell>

      <!-- <view class="title">基础服务</view>
    
    </view> -->
    </view>

    <view class="card">
      <nut-cell-group>
        <nut-cell>
          <template #title>
            <span class="title" style="color: #111">联系我们</span>
          </template>
        </nut-cell>
        <nut-cell title="客服热线" desc="0551-66391071"></nut-cell>
        <nut-cell title="Email" desc="shangwu_2005@vip.163.com"></nut-cell>
        <nut-cell
          title="公司地址"
          desc="安徽省合肥市双凤工业区金福路22号"
        ></nut-cell>
      </nut-cell-group>
    </view>
  </view>
</template>

<script>
import { reactive, toRefs } from "vue";
import Taro from "@tarojs/taro";
import p1 from "../../assets/icon_ojo2eobsn7/dengji.svg";
import p2 from "../../assets/icon_ojo2eobsn7/baogaosousuo.svg";

export default {
  name: "Index",
  components: {},
  setup() {
    const state = reactive({
      msg: "欢迎使用 NutUI4.0 开发小程",
      msg2: "你成功了～666",
      type: "text",
      page: 2,
      show: false,
      cover: false,
    });

    const tabSwitch = (item, index) => {
      console.log(item, index);
      if (index === 1) {
        Taro.navigateTo({
          url: "/pages/my/my",
        });
      }
    };

    const goToAfterSale = () => {
      Taro.navigateTo({
        url: "/pages/after-sale/index",
      });
    };

    const goToAfterSaleForm = () => {
      Taro.navigateTo({
        url: "/pages/after-sale-form/index",
      });
    };

    return {
      ...toRefs(state),
      tabSwitch,
      goToAfterSale,
      goToAfterSaleForm,
      p1,
      p2,
    };
  },
};
</script>

<style lang="scss">
.index {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
.nut-swiper-item {
  line-height: 180px;
}
.nut-swiper-item img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.card {
  margin: 16px 24px;
  background-color: #fff;
  border-radius: 12px;

  .title {
    font-size: 32px;
    font-weight: 600;
  }

  .feature {
    display: flex;
    flex-wrap: wrap;
    row-gap: 30px;
    padding: 30px 0;

    .item {
      display: flex;
      flex-basis: 25%;
      flex-direction: column;
      align-items: center;
    }

    img {
      width: 100px;
      height: 100px;
    }

    .item-name {
      font-size: 24px;
    }
  }
}
</style>
