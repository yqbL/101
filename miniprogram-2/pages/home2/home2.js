// home2.js
Page({
    data: {
        listData: [],
        isLoading: true,
        loadError: false
    },

    onLoad() {
        wx.showLoading({
            title: '加载中',
        });
        wx.request({
            url: 'http://127.0.0.1:8080/api/medicine-lists',
            method: 'GET',
            timeout: 5000, // 设置超时时间为 5 秒
            success: (res) => {
                wx.hideLoading();
                console.log('请求响应状态码:', res.statusCode);
                console.log('请求响应数据:', res.data);
                if (res.statusCode === 200 && res.data.success) {
                    this.setData({
                        listData: res.data.data,
                        isLoading: false,
                        loadError: false
                    });
                    console.log('赋值后的 listData:', this.data.listData); // 新增日志，确认赋值情况
                } else {
                    this.setData({
                        isLoading: false,
                        loadError: true
                    });
                    console.error('获取药单列表失败，响应状态码:', res.statusCode);
                }
            },
            fail: (err) => {
                wx.hideLoading();
                this.setData({
                    isLoading: false,
                    loadError: true
                });
                console.error('获取药单列表失败:', err);
            }
        });
    },

    handleOrderNumberTap(e) {
        const orderId = e.currentTarget.dataset.orderId;
        console.log('点击的药单编号:', orderId);
        if (orderId) {
            wx.navigateTo({
                url: `/pages/home1/home1?orderId=${orderId}`
            });
        } else {
            console.error('未获取到有效的药单编号');
        }
    }
});