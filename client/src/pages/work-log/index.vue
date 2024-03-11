<template>
  <view class="page">
    <template v-if="isShowForm">
      <view class="title">{{
        [1, 2].includes(state)
          ? "来自项目 — " + formData.projectName
          : "新建工作日报"
      }}</view>
      <nut-form
        :model-value="formData"
        :rules="{
          projectName: [{ required: true, message: '请填写项目名称' }],
          something: [{ required: true, message: '请填写处理事项' }],
          scNo: [{ required: true, message: '请填写sc编号' }],
          employeeName: [{ required: true, message: '请填写员工姓名' }],
        }"
        ref="ruleForm"
      >
        <!-- =========基本信息=========== -->

        <nut-form-item label="项目名称" prop="projectName">
          <nut-input
            class="nut-input-text"
            @blur="customBlurValidate('projectName')"
            v-model="formData.projectName"
            placeholder="请输入项目名称"
            :readonly="basicEdit"
            type="text"
          />
        </nut-form-item>

        <nut-form-item label="客户名称" prop="customName">
          <nut-input
            class="nut-input-text"
            @blur="customBlurValidate('customName')"
            v-model="formData.customName"
            placeholder="请输入客户名称"
            :readonly="basicEdit"
            type="text"
          />
        </nut-form-item>

        <nut-form-item label="SC编号" required prop="scNo">
          <nut-input
            class="nut-input-text"
            @blur="customBlurValidate('scNo')"
            v-model="formData.scNo"
            placeholder="请输入SC编号"
            :readonly="basicEdit"
            type="text"
          />
        </nut-form-item>

        <nut-form-item label="员工姓名" required prop="employeeName">
          <nut-input
            class="nut-input-text"
            @blur="customBlurValidate('employeeName')"
            v-model="formData.employeeName"
            placeholder="请输入员工姓名"
            :readonly="basicEdit"
            type="text"
          />
        </nut-form-item>

        <nut-form-item label="处理事项" prop="something">
          <nut-input
            class="nut-input-text"
            v-model="formData.something"
            placeholder="请输入处理事项"
            :readonly="basicEdit"
            type="text"
          />
        </nut-form-item>

        <nut-form-item label="备注">
          <nut-textarea
            v-model="formData.remark"
            placeholder="请输入备注"
            :limit-show="!basicEdit"
            :readonly="basicEdit"
            max-length="500"
          />
        </nut-form-item>

        <!-- 工作内容 -->
        <nut-form-item label="工作内容"> </nut-form-item>
        <nut-form-item
          v-for="(item, index) in workContent"
          :key="index"
          :label="item.day"
        >
          <nut-textarea
            v-model="item.value"
            placeholder="请输入"
            max-length="500"
            :readonly="item.day != dayjs().format('YYYY-MM-DD')"
          />
        </nut-form-item>

        <view style="padding: 0 20px 20px" v-if="able === 'e' && state !== 2">
          <nut-row :gutter="10">
            <nut-col :span="basicEditSpan">
              <nut-button block plain type="info" @click="add">添加</nut-button>
            </nut-col>
            <nut-col :span="basicEditSpan">
              <nut-button
                block
                type="info"
                :loading="isLoading"
                @click="submit(basicEdit ? 1 : 0)"
                >{{ basicEdit ? "保存" : "提交" }}</nut-button
              >
            </nut-col>
            <nut-col :span="basicEditSpan" v-if="basicEdit">
              <nut-button block type="primary" @click="finish"
                >结束工作</nut-button
              >
            </nut-col>
          </nut-row>
        </view>
      </nut-form>
    </template>

    <nut-toast
      :duration="0"
      :msg="toastState.msg"
      v-model:visible="toastState.show"
      :type="toastState.type"
      :cover="toastState.cover"
    />

    <nut-notify
      @click="onClick"
      @closed="onClosed"
      v-model:visible="baseState.state.show"
      :msg="baseState.state.desc"
    />

    <nut-dialog
      content="已结束当前工作，是否新建评价单"
      v-model:visible="visible2"
    >
      <template #footer>
        <nut-row :gutter="10">
          <nut-col :span="12">
            <nut-button block plain size="small" type="info" @click="onCancel"
              >取消</nut-button
            >
          </nut-col>

          <nut-col :span="12">
            <nut-button block size="small" type="info" @click="onOk"
              >确定</nut-button
            >
          </nut-col>
        </nut-row>
      </template>
    </nut-dialog>
  </view>
</template>
<script setup>
import { ref, reactive, onMounted } from "vue";
import Taro, { useShareAppMessage, useLoad } from "@tarojs/taro";
import { getDailyReportDetail, addOrUpdateReport } from "../../api";
import * as dayjs from "dayjs";

const $instance = Taro.getCurrentInstance();

const formData = reactive({
  projectName: "", // 项目名称
  customName: "", //客户名称
  scNo: "", //sc编号
  employeeName: "", //员工姓名
  something: "", //处理事项
  remark: "", //备注
  // 工作内容
  // state: 0, //状态
});

const ruleForm = ref(null);
const isLoading = ref(false);
const isShowForm = ref(true);
const state = ref(0);
const itemId = ref(0);
const able = ref("r");
const basicEdit = ref(false);
const basicEditSpan = ref(12);

const workContent = ref([]);

// 吐司
const toastState = reactive({
  msg: "toast",
  type: "text",
  show: false,
  cover: false,
});

// 通知
const onClosed = () => console.log("closed");
const onClick = () => console.log("click");
const baseState = {
  state: reactive({
    show: false,
    desc: "基础用法",
  }),
  methods: {
    cellClick(msg = "基础用法") {
      baseState.state.desc = msg;
      baseState.state.show = true;
    },
  },
};

// 对话框
const visible2 = ref(false);
const onCancel = () => {
  visible2.value = false;

  Taro.navigateTo({
    url: "/pages/work-log/index",
  });
};
const onOk = () => {
  visible2.value = false;

  const { projectName, customName, scNo, employeeName, something } = formData;
  const params = `j=w&able=e&p=${projectName}&c=${customName}&sc=${scNo}&e=${employeeName}&s=${something}`;

  Taro.navigateTo({
    url: "/pages/after-sale-form/index?" + params,
  });
};

onMounted(() => {
  if ($instance.router.params.type === "detail") {
    basicEdit.value = true;
    basicEditSpan.value = 8;
    detail($instance.router.params.id);
  } else {
    add()
  }

  if ($instance.router.params.able === "e") {
    able.value = "e";
  } else {
    able.value = "r";
  }
});

// useShareAppMessage(() => {
//   return {
//     title: "邀请您评价-尚展售后服务评价平台",
//     path: `/pages/after-sale-form/index?id=${itemId.value}&type=detail&able=e`,
//   };
// });

// 详情
const detail = async (ids) => {
  try {
    openToast("loading", "加载中", true);
    const { code, data } = await getDailyReportDetail(ids);
    if (code === 200) {
      formData.scNo = data.scNo;
      formData.projectName = data.projectName;
      formData.employeeName = data.employeeName;
      formData.something = data.something;
      formData.remark = data.remark || "";
      formData.customName = data.customName;
      state.value = data.status;
      workContent.value = JSON.parse(data.workContent || "[]");

      itemId.value = data.id;
    }
  } catch (error) {
    console.log(error);
  } finally {
    toastState.show = false;
  }
};

// 添加
const add = () => {
  const now = dayjs().format("YYYY-MM-DD");
  if (workContent.value.find((item) => item.day === now)) {
    return baseState.methods.cellClick(
      "请勿重复添加日期,直接在当前日期编辑内容后保存"
    );
  }

  workContent.value.push({
    day: now,
    value: "",
  });
};

// 提交
const submit = (status) => {
  ruleForm.value.validate().then(({ valid, errors }) => {
    if (valid) {
      isLoading.value = true;
      openToast("loading", "加载中", true);

      let params = {
        projectName: formData.projectName,
        scNo: formData.scNo,
        employeeName: formData.employeeName,
        remark: formData.remark,
        something: formData.something,
        customName: formData.customName,
        status,
      };

      // 日报内容
      if (workContent.value.length) {
        params.workContent = JSON.stringify(workContent.value);
      } else {
        params.workContent = "[]";
      }

      // 保存更新
      if ($instance.router.params.type === "detail") {
        params.id = itemId.value;
      }

      addOrUpdateReport(params).then(
        ({ code, data }) => {
          if (code === 200 && data) {
            itemId.value = data;
          }
          isLoading.value = false;
          // toastState.show = false;
          const msgMap = {
            0: "提交成功",
            1: "保存成功",
            2: "结束成功",
          };
          openToast("success", msgMap[status]);

          setTimeout(() => {
            toastState.show = false;
          }, 1000);
          detail(itemId.value);
        },
        (err) => {
          console.log(err);
        }
      );
    } else {
      console.log("error submit!!", errors);
    }
  });
};

const finish = () => {
  submit(2);
  visible2.value = true;
};

// 失去焦点校验
const customBlurValidate = (prop) => {
  ruleForm.value.validate(prop).then(({ valid, errors }) => {
    if (valid) {
      console.log("success", formData);
    } else {
      console.log("error submit!!", errors);
    }
  });
};

const openToast = (type, msg, cover = false) => {
  toastState.show = true;
  toastState.msg = msg;
  toastState.type = type;
  toastState.cover = cover;
};

const backList = () => {
  Taro.navigateTo({
    url: "/pages/after-sale/index",
  });
};
</script>

<style lang="scss">
.title {
  color: rgb(144, 156, 164);
  font-size: 0.8rem;
  font-weight: 400;
  margin-bottom: 0.5rem;
  padding: 0 0.5rem;
  padding-top: 1.5rem;
}

.success-page {
  overflow: hidden;
  padding: 200px 0;
  height: 100vh;
  display: flex;
  gap: 50px;
  flex-direction: column;
  align-items: center;
}

.nut-textarea__textarea__readonly {
  padding: 0;
}
</style>
