export default defineAppConfig({
  pages: [
    'pages/index/index',
    // 'pages/my/my',
    'pages/after-sale/index',
    'pages/after-sale-form/index',
  ],
  window: {
    backgroundTextStyle: 'light',
    navigationBarBackgroundColor: '#fff',
    navigationBarTitleText: 'WeChat',
    navigationBarTextStyle: 'black'
  },
  // "tabBar": {
  //   "list": [{
  //     "pagePath": "pages/index/index",
  //     "selectedIconPath": 'assets/home_fill (1).png',
  //     "iconPath": 'assets/home_fill.png',
  //     "text": "服务大厅"
  //   }, 
  //   {
  //     "pagePath": "pages/my/my",
  //     "selectedIconPath": 'assets/my_fill.png',
  //     "iconPath": 'assets/my_fill (1).png',
  //     "text": "我的"
  //   }
  // ]
  // },
})
