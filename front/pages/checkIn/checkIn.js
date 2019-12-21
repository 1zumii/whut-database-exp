// pages/checkIn/checkIn.js
import { getUserToken } from '../../utils/userTokenManager'
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager'

const checkStatus = {
	notNeed: 0,  //没课
	notYet: 1,   //还没打卡
	checked: 2,  //打卡了
};

Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		//state
		courseNow: "形式与政策",
		isCheckin: -1,
		//display
		buttonDisable: false,
		buttonLoading: false,
		buttonText: "签 到"
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const { userId } = getUserToken();
		if (!userId && userId !== 0) {
			//未登录
			wx.reLaunch({
				url: "../login/login",
			});
		} else {
			//已登录
			this.fetchCourseInfo();
		}
	},
	//获取当前时间，本同学的课
	fetchCourseInfo: function () {

	}
})