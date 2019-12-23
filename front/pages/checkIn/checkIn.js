// pages/checkIn/checkIn.js
import { getUserToken } from '../../utils/userTokenManager'
import Notify from '../../dist/vant-weapp/notify/notify'
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
		courseNow: "休 息",
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const user = getUserToken();
		if (!user) {
			//未登录
			wx.reLaunch({
				url: "../login/login",
			});
		} else if (!user.isAdmin) {
			//已登录
			this.fetchCourseInfo();
		}
	},
	//生命周期函数--监听页面显示
	onShow: function (options) {
		console.log("onShow", new Date().getTime());
		const user = getUserToken();
		if (user && !user.isAdmin) {
			this.fetchCourseInfo();
		}
	},
	//页面相关事件处理函数--监听用户下拉动作
	onPullDownRefresh: function () {
		console.log("onPullDownRefresh", new Date().getTime());
		const user = getUserToken();
		if (user && !user.isAdmin) {
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
			if (json.code === 0) {
				switch (json.data.isCheckin) {
					case checkStatus.notNeed:
						//没课
						this.setData({
							courseInfo: json.data.courseInfo,
							isCheckin: json.data.isCheckin,
							courseNow: "没 课",
							buttonDisable: true,
							buttonText: "休 息"
						});
						break;
					case checkStatus.notYet:
						//有课，还没打卡
						this.setData({
							courseInfo: json.data.courseInfo,
							isCheckin: json.data.isCheckin,
							courseNow: json.data.courseInfo.courseName,
							buttonDisable: false,
							buttonText: "签 到"
						});
						break;
					case checkStatus.checked:
						//有课，打卡了
						this.setData({
							courseInfo: json.data.courseInfo,
							isCheckin: json.data.isCheckin,
							courseNow: json.data.courseInfo.courseName,
							buttonDisable: true,
							buttonText: "已 签"
						});
						break;
				}
			} else {
				Notify({
					type: "danger",
					message: json.msg
				});
				throw json;
			}
		}).catch((error) => {
			console.error(error);
		})
	},
	//按钮点击，打卡签到
	handleCheckButtonClick: function () {
		const { userId } = getUserToken();
		const parameters = {
			courseId: this.data.courseInfo.id,
			userId: userId,
			nowTimestamp: new Date().getTime()
		};
		this.setData({ buttonLoading: true });
		AaHostPost(
			"/check-in/checkInByUser",
			{ ...parameters }
		).then((json) => {
			if (json.code === 0) {
				this.setData({
					isCheckin: checkStatus.checked,
					buttonDisable: true,
					buttonText: "已 签",
					buttonLoading: false
				});
			} else {
				Notify({
					type: "danger",
					message: json.msg
				});
				throw json;
			}
		}).catch((error) => {
			console.error(error);
		})
	}
})