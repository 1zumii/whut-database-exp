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
		courseNow: "数据库系统原理E",
		isCheckin: -1,
		//display
		buttonDisable: false,
		buttonLoading: false,
		buttonText: "签 到"
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const user = getUserToken();
		if (!user) {
			//未登录
			wx.reLaunch({
				url: "../login/login",
			});
		} else {
			//已登录
			this.fetchCourseInfo();
		}
	},
	//生命周期函数--监听页面显示
	onShow: function (options) {
		console.log("onShow", new Date().getTime());
		if (getUserToken()) {
			this.fetchCourseInfo();
		}
	},
	//页面相关事件处理函数--监听用户下拉动作
	onPullDownRefresh: function () {
		console.log("onPullDownRefresh", new Date().getTime());
		if (getUserToken()) {
			this.fetchCourseInfo();
		}
	},
	//获取当前时间，本同学的课
	fetchCourseInfo: function () {
		const nowTimestamp = new Date().getTime();
		const { userId, studentInfo } = getUserToken();
		const { studentId } = studentInfo;
		AaHostPost(
			'/check-in/getCourseInfoByUser',
			{ userId, studentId, nowTimestamp }
		).then((json) => {

		}).catch((error) => {

		})
	},
	//按钮点击
	handleCheckButtonClick: function () {

	}
})