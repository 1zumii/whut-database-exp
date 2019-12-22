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
		isCheckin: -1,
		courseInfo: null,
		//display
		buttonDisable: false,
		buttonLoading: false,
		buttonText: "签 到",
		courseNow: "数据库系统原理E",
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
		this.setData({buttonLoading:true});
		AaHostPost(
			'/check-in/getCourseInfoByUser',
			{ userId, studentId, nowTimestamp }
		).then((json) => {
			if(json.code === 0){
				switch(json.data.isCheckin){
					case checkStatus.notNeed:
						//没课
						this.setData({
							courseInfo:json.data.courseInfo,
							isCheckin:json.data.isCheckin,
							buttonLoading:false,
							courseNow: "没 课",
							buttonDisable:true,
							buttonText: "休 息"
						});
						break;
					case checkStatus.notYet:
						//有课，还没打卡
						this.setData({
							courseInfo:json.data.courseInfo,
							isCheckin:json.data.isCheckin,
							buttonLoading:false,
							courseNow: json.data.courseInfo.courseName,
							buttonDisable:false,
							buttonText:"签 到"
						});
						break;
					case checkStatus.checked:
						//有课，打卡了
						this.setData({
							courseInfo:json.data.courseInfo,
							isCheckin:json.data.isCheckin,
							buttonLoading:false,
							courseNow: json.data.courseInfo.courseName,
							buttonDisable:false,
							buttonText:"已 签"
						});
						break;
				}
			}else {
				Notify({
					type:"danger",
					message:json.msg
				});
				throw json;
			}
		}).catch((error) => {
			console.error(error);
		})
	},
	//按钮点击
	handleCheckButtonClick: function () {

	}
})