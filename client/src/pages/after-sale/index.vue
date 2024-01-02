<template>
  <view>
    <nut-sticky>
      <nut-searchbar
        v-model="value"
        @search="search"
        placeholder="请输入SC编号"
      >
        <template #rightout>
          <span @tap="search">搜索</span>
        </template>
      </nut-searchbar>

      <nut-cell
        :showIcon="true"
        title="选择日期范围"
        @click="openSwitch('isVisible')"
      >
        <template #desc>
          <span class="date-text">
            <span class="date-span">
              {{
                datestate.date && datestate.date[0]
                  ? `${datestate.date[0]}至${datestate.date[1]}`
                  : "请选择"
              }}
            </span>

            <span class="close" @tap.stop="clearDateText">
              <CircleClose v-show="datestate.date.length" />
            </span>
          </span>
        </template>
      </nut-cell>
      <nut-calendar
        v-model:visible="datestate.isVisible"
        :default-value="datestate.date"
        ref="calendar"
        type="range"
        :start-date="`2023-01-01`"
        @close="closeSwitch('isVisible')"
        @choose="setChooseValue"
        :is-auto-back-fill="true"
      >
      </nut-calendar>
    </nut-sticky>

    <view style="padding: 0 10px" v-if="dataList.length">
      <nut-cell-group>
        <nut-cell
          v-for="item in dataList"
          is-link
          :key="item.id"
          @tap="goToDetail(item)"
        >
          <template #desc>
            <span>{{ item.employeeName }}</span>
          </template>
          <template #title>
            <custem-listitem :itemData="item" />
          </template>
        </nut-cell>
      </nut-cell-group>
    </view>
    <nut-empty v-else description="无数据"></nut-empty>

    <nut-toast
      :duration="0"
      :msg="toastState.msg"
      v-model:visible="toastState.show"
      :type="toastState.type"
      :cover="toastState.cover"
    />
  </view>
</template>

<script>
import { ref, reactive, onMounted, nextTick } from "vue";
import { getEvaluateList } from "../../api";
import custemListitem from "../../components/custem-listitem";
import Taro, { usePullDownRefresh, useReachBottom } from "@tarojs/taro";
import { CircleClose } from "@nutui/icons-vue-taro";
import * as dayjs from "dayjs";

export default {
  components: {
    custemListitem,
    CircleClose,
  },

  setup() {
    const value = ref("");
    const dataList = ref([]);
    const calendar = ref(null);

    const datestate = reactive({
      date: [],
      isVisible: false,
    });

    const state = reactive({
      startTime: "",
      endTime: "",
      total: 0,
      pageNum: 1,
      pageSize: 10,
    });

    const toastState = reactive({
      msg: "toast",
      type: "text",
      show: false,
      cover: false,
    });

    const openSwitch = (param) => {
      datestate[`${param}`] = true;
      const now = dayjs().format("YYYY-MM-DD");
      console.log(datestate.date.length);
      if (!datestate.date.length) {
        nextTick(() => calendar.value.scrollToDate(now));
      }
    };
    const closeSwitch = (param) => {
      datestate[`${param}`] = false;
    };
    const clearDateText = () => {
      datestate.date = [];
      state.startTime = "";
      state.endTime = "";
    };
    const setChooseValue = (param) => {
      datestate.date = [...[param[0][3], param[1][3]]];
      if (datestate.date.length === 2) {
        state.startTime = datestate.date[0];
        state.endTime = datestate.date[1];
      } else {
        state.startTime = "";
        state.endTime = "";
      }
    };

    // 详情
    const goToDetail = (item) => {
      Taro.navigateTo({
        url: `/pages/after-sale-form/index?id=${item.id}&type=detail`,
      });
    };

    // 加载
    const openToast = (type, msg, cover = false) => {
      toastState.show = true;
      toastState.msg = msg;
      toastState.type = type;
      toastState.cover = cover;
    };

    const search = () => {
      getList(1, value.value);
    };

    const getList = async (offset, scNo = "") => {
      try {
        // if (state.total === dataList.value.lengxth && state.total !== 0) return;

        Taro.showNavigationBarLoading();
        openToast("loading", "加载中", true);
        const { code, data, msg } = await getEvaluateList({
          scNo,
          startTime: state.startTime,
          endTime: state.endTime,
          pageNum: state.pageNum,
          pageSize: state.pageSize,
        });
        if (code === 200) {
          state.total = data.total;
          dataList.value = data.records;
        }
        // 关闭刷新动画
        if (offset === 0) {
          setTimeout(() => {
            Taro.stopPullDownRefresh();
          }, 700);
        }
        setTimeout(() => {
          Taro.hideNavigationBarLoading();
        }, 600);
      } catch (error) {
        console.log(error);
      } finally {
        toastState.show = false;
      }
    };

    onMounted(() => {
      getList(1);
    });

    // 下拉刷新时的回调
    usePullDownRefresh(() => {
      state.pageSize = 10;
      getList(0);
    });

    // 上拉触底时的回调。
    useReachBottom(() => {
      state.pageSize += 10;
      getList(1);
    });

    return {
      value,
      state,
      search,
      goToDetail,
      dataList,
      toastState,
      datestate,
      openSwitch,
      closeSwitch,
      setChooseValue,
      clearDateText,
      calendar,
    };
  },
};
</script>

<style lang="scss">
.h5-view.nut-menu__bar {
  box-shadow: none;
}

.nut-cell__value {
  flex: initial;
}

.nut-sticky .h5-view.nut-cell {
  box-shadow: none;
  margin: 0;

  .date-text {
    display: flex;
    align-items: center;

    .date-span {
      height: 50px;
    }
  }

  .close {
    color: #666;
    margin-left: 10px;
  }
}
</style>