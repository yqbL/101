Page({
  data: {
    userInfo: {
      userId: "李梅香",
    },
  },

  handleForgetPassword() {
    wx.showToast({
      title: "忘记密码功能暂未实现",
      icon: "none",
    });
  },

  handleLogout() {
    wx.showToast({
      title: "已退出登录",
      icon: "success",
    });
    // TODO: 实现实际退出逻辑
  },
});
