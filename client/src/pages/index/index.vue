<template>
  <view class="page index">
    <nut-swiper
      :init-page="page"
      :loop="true"
      auto-play="3000"
      height="180"
      :pagination-visible="true"
      style="height: 180px"
    >
      <nut-swiper-item v-for="item in swiperImgUrl" :key="item">
        <img :src="item" />
      </nut-swiper-item>
    </nut-swiper>

    <view class="card">
      <nut-cell>
        <template #title>
          <span class="title" style="color: #111">基础服务</span>

          <view class="feature">
            <view
              class="item"
              v-for="icon in iconList"
              :key="icon.label"
              @tap="goToPath(icon.path)"
            >
              <img :src="icon.img" />
              <view class="item-name">{{ icon.label }}</view>
            </view>
          </view>
        </template>
      </nut-cell>
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
import Taro from "@tarojs/taro";
import p1 from "../../assets/icon_ojo2eobsn7/shouhoudan.svg";
import p2 from "../../assets/icon_ojo2eobsn7/shouhouchaxun.svg";
import p3 from "../../assets/icon_ojo2eobsn7/gongzuoribao.svg";
import p4 from "../../assets/icon_ojo2eobsn7/ribaochaxun.svg";

export default {
  name: "Index",
  setup() {
    const swiperImgUrl = [
      "http://www.szmold.cn/PicFile/1508480829.jpg",
      "http://www.szmold.cn/PicFile/1508481684.jpg",
      "http://www.szmold.cn/PicFile/1511494135.jpg",
      "http://www.szmold.cn/PicFile/1511422289.jpg",
    ];

    const iconList = [
      {
        label: "售后单",
        img: p1,
        path: "after-sale-form/index?able=e",
      },
      {
        label: "售后查询",
        img: p2,
        path: "after-sale/index",
      },
      {
        label: "工作日报",
        img: p3,
        path: "work-log/index?able=e",
      },
      {
        label: "日报查询",
        img: p4,
        path: "work-log-search/index",
      },
    ];

    const goToPath = (path) => {
      Taro.navigateTo({
        url: "/pages/" + path,
      });
    };

    return {
      swiperImgUrl,
      iconList,
      goToPath,
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
