Page({
  data: {
    phone: '', // 存储输入的手机号/账号
    password: '', // 存储输入的密码
  },

  // 处理手机号/账号输入
  handlePhoneInput(e) {
    this.setData({
      phone: e.detail.value,
    });
  },

  // 处理密码输入
  handlePasswordInput(e) {
    this.setData({
      password: e.detail.value,
    });
  },

  // 登录按钮点击事件
  handleLogin() {
    const { phone, password } = this.data;

    if (!phone || !password) {
      wx.showToast({
        title: '请输入完整信息',
        icon: 'none',
      });
      return;
    }

    // 发起登录请求
  wx.request({
    url: 'http://127.0.0.1:8080/api/login', // 本地开发接口
      method: 'POST',
      header: {
        'Content-Type': 'application/json',
      },
      data: {
        phone, 
        password,
      },
      success: (res) => {
        // wx.hideLoading();
        // res.data.code === 200
        if (res.data.success === true) {

          wx.showToast({
            title: '登录成功',
            icon: 'success',
          });

          // 跳转到首页
          wx.navigateTo({
            url: '/pages/home1/home1',
          });

        }  
        else {
          wx.showToast({
            title: res.data.message || '登录失败',
            icon: 'none',
          });
        }
      },
      fail: () => {
        wx.hideLoading();
        wx.showToast({
          title: '网络错误，请稍后再试',
          icon: 'none',
        });
      },
    });

    
  },

  // 注册按钮点击事件
  handleRegister() {
    wx.navigateTo({
      url: '/pages/register/register',
    });
  },

  // 忘记密码按钮点击事件
  handleForgetPassword() {
    wx.navigateTo({
      url: '/pages/forgetPassword/forgetPassword',
    });
  },

  // 微信快捷登录事件
  handleWechatLogin(e) {
    if (e.detail.userInfo) {
      wx.showToast({
        title: '微信登录成功',
        icon: 'success',
      });
      // 可以将用户信息存储或处理
      console.log('用户信息:', e.detail.userInfo);
      // 跳转到首页或其他页面
      wx.navigateTo({
        url: '/pages/home/home',
      });
    } else {
      wx.showToast({
        title: '微信登录失败',
        icon: 'none',
      });
    }
  },
});
