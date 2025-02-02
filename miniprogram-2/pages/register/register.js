Page({
  data: {
    phone: "",
    password: "",
    confirmPassword: "",
  },

  onPhoneInput(e) {
    this.setData({
      phone: e.detail.value,
    });
  },

  onPasswordInput(e) {
    this.setData({
      password: e.detail.value,
    });
  },

  onConfirmPasswordInput(e) {
    this.setData({
      confirmPassword: e.detail.value,
    });
  },

  onRegister() {
    const { phone, password, confirmPassword } = this.data;

    if (!phone || !password || !confirmPassword) {
      wx.showToast({
        title: "请填写完整信息",
        icon: "none",
      });
      return;
    }

    if (password.length < 6) {
      wx.showToast({
        title: "密码长度至少为6位",
        icon: "none",
      });
      return;
    }    

    if (password !== confirmPassword) {
      wx.showToast({
        title: "两次密码不一致",
        icon: "none",
      });
      return;
    }

    wx.request({
      url: "http://127.0.0.1:8080/api/register",
      method: "POST",
      data: {
        phone,
        password,
      },
      success: (response) => {
        if (response.data.success===true) {
          wx.showToast({
            title: "注册成功",
          });
          setTimeout(() => {
            wx.redirectTo({
                url: "/pages/index/index"
            });
          }, 1500); 
        } else {
          wx.showToast({
            title: response.data.message || "注册失败",
            icon: "none",
          });
        }
      },
      fail: () => {
        wx.showToast({
          title: "请求失败，请稍后再试",
          icon: "none",
        });
      },
    });
  },
});
