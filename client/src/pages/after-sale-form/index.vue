<template>
  <view class="page">
    <template v-if="isShowForm">
      <view class="title">{{
        [1, 2].includes(state)
          ? "来自项目 — " + formData.projectName
          : "新建售后评价单"
      }}</view>
      <nut-form
        :model-value="formData"
        :rules="{
          projectName: [{ required: true, message: '请填写项目名称' }],
          scNo: [{ required: true, message: '请填写sc编号' }],
          employeeName: [{ required: true, message: '请填写员工姓名' }],
        }"
        ref="ruleForm"
      >
        <!-- =========基本信息=========== -->
        <template v-if="[0, 2].includes(state)">
          <nut-form-item label="项目名称" prop="projectName">
            <nut-input
              class="nut-input-text"
              @blur="customBlurValidate('projectName')"
              v-model="formData.projectName"
              placeholder="请输入项目名称"
              :readonly="state > 1"
              type="text"
            />
          </nut-form-item>

          <nut-form-item label="SC编号" required prop="scNo">
            <nut-input
              class="nut-input-text"
              @blur="customBlurValidate('scNo')"
              v-model="formData.scNo"
              placeholder="请输入SC编号"
              :readonly="state > 1"
              type="text"
            />
          </nut-form-item>

          <nut-form-item
            label="设备数量"
            prop="deviceNum"
            :rules="[{ validator: customValidator, message: '必须输入数字' }]"
          >
            <nut-input
              class="nut-input-text"
              v-model="formData.deviceNum"
              placeholder="请输入设备数量"
              :readonly="state > 1"
              type="number"
            />
          </nut-form-item>

          <nut-form-item label="员工姓名" required prop="employeeName">
            <nut-input
              class="nut-input-text"
              @blur="customBlurValidate('employeeName')"
              v-model="formData.employeeName"
              placeholder="请输入员工姓名"
              :readonly="state > 1"
              type="text"
            />
          </nut-form-item>

          <nut-form-item
            label="人数"
            prop="userNum"
            :rules="[{ validator: customValidator, message: '必须输入数字' }]"
          >
            <nut-input
              class="nut-input-text"
              v-model="formData.userNum"
              placeholder="请输入人数"
              :readonly="state > 1"
              type="number"
            />
          </nut-form-item>
        </template>

        <nut-form-item label="备注">
          <nut-textarea
            v-model="formData.remark"
            placeholder="请输入备注"
            type="text"
            limit-show
            :readonly="state >= 1"
            max-length="500"
          />
        </nut-form-item>

        <!-- =========厂商评价=========== -->
        <template v-if="[1, 2].includes(state)">
          <nut-form-item label="服务态度">
            <nut-rate
              :readonly="state === 2"
              active-color="#FFC800"
              v-model="formData.serviceScore"
            />
          </nut-form-item>

          <nut-form-item label="响应速度">
            <nut-rate
              :readonly="state === 2"
              active-color="#FFC800"
              v-model="formData.responseScore"
            />
          </nut-form-item>

          <nut-form-item label="本次服务是否完成">
            <span v-if="state === 2">
              <span v-if="formData.serviceDone === 1">是</span>
              <span v-if="formData.serviceDone === 0">否</span>
              <!-- <span v-else>未填写</span> -->
            </span>
            <nut-radio-group
              v-else
              direction="horizontal"
              v-model="formData.serviceDone"
            >
              <nut-radio
                :disabled="state === 2"
                :label="1"
                shape="button"
                size="small"
                >是</nut-radio
              >
              <nut-radio
                :disabled="state === 2"
                :label="0"
                shape="button"
                size="small"
                >否</nut-radio
              >
            </nut-radio-group>
          </nut-form-item>

          <nut-form-item label="是否在规定时间内完成">
            <span v-if="state === 2">
              <span v-if="formData.isTimeComplete === 1">是</span>
              <span v-if="formData.isTimeComplete === 0">否</span>
              <!-- <span v-else>未填写</span> -->
            </span>
            <nut-radio-group
              v-else
              direction="horizontal"
              v-model="formData.isTimeComplete"
            >
              <nut-radio
                :disabled="state === 2"
                :label="1"
                shape="button"
                size="small"
                >是</nut-radio
              >
              <nut-radio
                :disabled="state === 2"
                :label="0"
                shape="button"
                size="small"
                >否</nut-radio
              >
            </nut-radio-group>
          </nut-form-item>

          <nut-form-item label="意见反馈及评价">
            <nut-textarea
              v-model="formData.feedback"
              placeholder="请输入意见反馈及评价"
              type="text"
              limit-show
              :readonly="state === 2"
              max-length="500"
            />
          </nut-form-item>

          <nut-form-item
            label="联系电话"
            prop="contactPhone"
            required
            :rules="[
              { required: true, message: '请填写联系电话' },
              // { validator: asyncValidator, message: '电话格式不正确' },
            ]"
          >
            <nut-input
              class="nut-input-text"
              v-model="formData.contactPhone"
              placeholder="请输入联系电话"
              :readonly="state === 2"
              type="text"
            />
          </nut-form-item>

          <nut-form-item
            label="联系人签名"
            prop="commitUserSign"
            :rules="[{ required: true, message: '请签名' }]"
          >
          </nut-form-item>
          <nut-signature
            v-if="state !== 2"
            @confirm="confirm"
            @clear="clear"
          ></nut-signature>
          <img
            :src="formData.commitUserSign"
            class="demoSignUrl"
            v-if="formData.commitUserSign"
          />
        </template>

        <view style="padding-bottom: 20px">
          <nut-cell v-if="state !== 2">
            <nut-button block type="info" :loading="isLoading" @click="submit"
              >提交</nut-button
            >
          </nut-cell>
        </view>
      </nut-form>
    </template>

    <!-- <view class="success-page"> -->
      <view class="success-page" v-else>
      <Checked size="100" color="#07c160"></Checked>
      <view>
        {{ state == 1 ? "感谢您的评价" : "创建完成，点击右上角立即分享" }}
      </view>
      <view style="width: 100%; padding: 100px 16px; box-sizing: border-box">
        <nut-button block type="info" @click="backList">确定</nut-button>
      </view>
    </view>

    <nut-toast
      :duration="0"
      :msg="toastState.msg"
      v-model:visible="toastState.show"
      :type="toastState.type"
      :cover="toastState.cover"
    />
  </view>
</template>
<script setup>
import { ref, reactive, onMounted } from "vue";
import { Checked } from "@nutui/icons-vue-taro";
import Taro, { useShareAppMessage } from "@tarojs/taro";
import { getEvaluateDetail, addEvaluate, feedback } from "../../api";
import { getBase64ByFilePath } from "../../utils/base64";

const $instance = Taro.getCurrentInstance();

const formData = reactive({
  projectName: "", // 项目名称
  scNo: "", //sc编号
  deviceNum: "", //设备数量
  employeeName: "", //员工姓名
  userNum: "", //人数
  remark: "", //备注
  serviceScore: "", //服务态度
  responseScore: "", //响应速度
  isTimeComplete: "", //是否在规定时间内完成 0-否，1-是
  feedback: "", //意见及反馈
  commitUserSign: "", //提交人签名
  contactPhone: "", //联系方式
  serviceDone: "", //本次服务是否完成 0-否，1-完成
  // state: 0, //状态 1-待评价，2-评价完成
});

const ruleForm = ref(null);
const isLoading = ref(false);
const isShowForm = ref(true);
const state = ref(0);
const demoSignUrl = ref("");
const itemId = ref(0);

const toastState = reactive({
  msg: "toast",
  type: "text",
  show: false,
  cover: false,
});

onMounted(() => {
  console.log($instance.router.params);
  if ($instance.router.params.type === "detail") {
    detail($instance.router.params.id);
  }
});

useShareAppMessage((e) => {
  console.log(e);
  console.log(itemId.value);
  return {
    title: "邀请您评价-尚展售后服务评价平台",
    path: `/pages/after-sale-form/index?id=${itemId.value}&type=detail`,
  };
});

// 详情
const detail = async (ids) => {
  try {
    openToast("loading", "加载中", true);
    const { code, data, message } = await getEvaluateDetail(ids);
    if (code === 200) {
      formData.commitUserSign = data.commitUserSign;
      demoSignUrl.value = data.commitUserSign;
      formData.contactPhone = data.contactPhone;
      formData.deviceNum = data.deviceNum;
      formData.employeeName = data.employeeName;
      formData.feedback = data.feedback;
      formData.isTimeComplete = data.isTimeComplete;
      formData.projectName = data.projectName;
      formData.remark = data.remark;
      formData.responseScore = data.responseScore;
      formData.scNo = data.scNo;
      formData.serviceDone = data.serviceDone;
      formData.serviceScore = data.serviceScore;
      formData.userNum = data.userNum;
      state.value = data.state;
      itemId.value = data.id;
    }
  } catch (error) {
    console.log(error);
  } finally {
    toastState.show = false;
  }
};

// 提交
const submit = () => {
  ruleForm.value.validate().then(({ valid, errors }) => {
    if (valid) {
      isLoading.value = true;
      openToast("loading", "加载中", true);

      let fn = addEvaluate;

      let params = {
        projectName: formData.projectName,
        scNo: formData.scNo,
        deviceNum: formData.deviceNum,
        employeeName: formData.employeeName,
        userNum: formData.userNum,
        remark: formData.remark,
      };

      if (state.value === 1) {
        params = {
          commitUserSign: demoSignUrl.value,
          contactPhone: formData.contactPhone,
          feedback: formData.feedback,
          isTimeComplete: formData.isTimeComplete,
          responseScore: formData.responseScore,
          serviceDone: formData.serviceDone,
          serviceScore: formData.serviceScore,
        };
        params.id = itemId.value;
        fn = feedback;
      }

      fn(params).then(
        (res) => {
          if (res.data) {
            itemId.value = res.data;
          }
          console.log(res);
          isLoading.value = false;
          isShowForm.value = false;
          toastState.show = false;
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
// 函数校验
const customValidator = (val) => {
  if (val) {
    if (/^\d+$/.test(val)) {
      return Promise.resolve();
    } else {
      return Promise.reject("必须输入数字");
    }
  } else {
    return Promise.resolve();
  }
};

// Promise 异步校验
const asyncValidator = (val) => {
  const telReg = /^400(-?)[0-9]{7}$|^1\d{10}$|^0[0-9]{2,3}-[0-9]{7,8}$/;
  return new Promise((resolve, reject) => {
    showToast.loading("模拟异步验证中...");
    setTimeout(() => {
      showToast.hide();
      if (!val) {
        reject("请输入联系电话");
      } else if (!telReg.test(val)) {
        reject("联系电话格式不正确");
      } else {
        resolve("");
      }
    }, 1000);
  });
};

const openToast = (type, msg, cover = false) => {
  toastState.show = true;
  toastState.msg = msg;
  toastState.type = type;
  toastState.cover = cover;
};

// 签名
const confirm = (canvas, data) => {
  if (data === "") {
    return false;
  }
  formData.commitUserSign = data;
  getBase64ByFilePath(data).then(
    (res) => {
      demoSignUrl.value = res.base64Url;
      // console.log(res);
    },
    (err) => {
      console.log(err);
    }
  );
};

const clear = () => {
  demoSignUrl.value = "";
  formData.commitUserSign = "";
  console.log("清除事件");
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
  /* justify-content: center; */
  align-items: center;
}
</style>
