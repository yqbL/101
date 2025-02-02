Page({
  data: {
    medicineList: [], // 药品列表
    isLoading: true,
    loadError: false
  },
  onLoad(options) {
    const orderId = options.orderId;
    console.log('接收到的药单编号:', orderId);
    if (!orderId || orderId === "") {
      this.setData({
        isLoading: false,
        loadError: true
      });
      console.error('未获取到有效的药单编号参数');
      return;
    }
    this.getMedicineOrderById(orderId);
  },
  getMedicineOrderById(orderId) {
    wx.showLoading({
      title: '加载中',
    });
    wx.request({
      url: `http://127.0.0.1:8080/api/medicine-order/${orderId}`,
      method: 'GET',
      success: (res) => {
        wx.hideLoading();
        console.log('请求响应状态码:', res.statusCode);
        console.log('请求响应数据:', res.data);
        if (res.statusCode === 200 && res.data.success) {
          // 假设返回的数据结构中有 items 字段存储药品列表
          const medicineList = res.data.data.items || []; 
          this.setData({
            medicineList,
            isLoading: false,
            loadError: false
          });
        } else {
          this.setData({
            isLoading: false,
            loadError: true
          });
          console.error('获取药单详情失败，响应状态码:', res.statusCode);
        }
      },
      fail: (err) => {
        wx.hideLoading();
        this.setData({
          isLoading: false,
          loadError: true
        });
        console.error('获取药单详情失败:', err);
      }
    });
  }
});