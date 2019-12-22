// pages/setting/setting.js
import { AaHostPost } from '../../utils/httpManager'
import { getUserToken, setUserToken, clearUserToken } from '../../utils/userTokenManager'
import Notify from '../../dist/vant-weapp/notify/notify';
Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		//state
		isAdmin: getUserToken().isAdmin,
		newUsername: getUserToken().userInfo.username,
		newPassword: getUserToken().userInfo.password,
		newCheckPassword: '',
		//display
		userInfo: getUserToken().userInfo,
		updateUserInfoVisible: false,
		courseListVisible: false,
		courseList: null,
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const user = getUserToken()
		this.setData({
			isAdmin: user.isAdmin,
			userInfo: user.userInfo
		});
		this.fetchStudentCourseList();
	},
	//生命周期函数--页面出现在前台时执行
	onShow: function () {
		const user = getUserToken()
		this.setData({
			isAdmin: user.isAdmin,
			userInfo: user.userInfo
		});
		this.fetchStudentCourseList();
	},
	//点击注销
	handleLogoutClick: function () {
		wx.reLaunch({
			url: "../login/login",
			success: (result) => {
				clearUserToken();
				Notify({
					type: "success",
					message: "注销成功"
				});
			},
			fail: () => {
				Notify({
					type: "warning",
					message: "注销失败"
				});
			},
		});
	},
	//获取学生已经选的课
	fetchStudentCourseList:function(){
		const {studentId} = getUserToken().studentInfo
		AaHostPost(
			'/setting/query-studentCourses',
			{studentId}
		).then((json)=>{
			if(json.code === 0){
				this.setData({
					courseList:json.data.courseList
				})
			}else {
				Notify({
					type:"warning",
					message:"学生课表获取失败"
				});
				throw json;
			}
		}).catch((error)=>{
			console.error(error);
		})
	},
	//用户信息修改popup
	flipUpdateUserInfoPopupVisible: function () {
		const { updateUserInfoVisible } = this.data;
		const { username, password } = getUserToken().userInfo;
		this.setData({
			updateUserInfoVisible: !updateUserInfoVisible,
			newUsername: username,
			newPassword: password,
			newCheckPassword: '',
		});
	},
	//提交用户信息修改
	submitNewUserInfo: function () {
		if (this.validateNewUserInfoValue()) {
			const user = getUserToken();
			const { id } = user.userInfo;
			const { newUsername, newPassword } = this.data;
			const parameters = {
				id,
				newUsername,
				newPassword
			};
			AaHostPost(
				"/setting/update-userInfo",
				{ ...parameters }
			).then((json) => {
				if (json.code === 0) {
					user.userInfo = json.data.user;
					setUserToken(user);
					Notify({
						type:'success',
						message:'用户信息修改成功'
					});
					this.flipUpdateUserInfoPopupVisible();
				}else {
					Notify({
						type:'warning',
						message:'用户信息修改失败'
					});
					throw json;
				}
			}).catch((error) => {
				console.error(error);
			})
		}
	},
	handleNewUsernameChange: function (event) {
		this.setData({ newUsername: event.detail });
	},
	handleNewPasswordChange: function (event) {
		this.setData({ newPassword: event.detail });
	},
	handleNewCheckPasswordChange: function (event) {
		this.setData({ newCheckPassword: event.detail });
	},
	//校验修改页面表单
	validateNewUserInfoValue: function () {
		const attributeList = [
			'newUsername', 'newPassword',
			'newCheckPassword'
		];
		for (let e of attributeList) {
			if (
				!this.data[e] &&
				this.data[e] !== 0
			) {
				Notify({ type: 'danger', message: `请检查是否遗漏 (${e})` });
				return false;
			}
		}
		if (this.data.newPassword !== this.data.newCheckPassword) {
			Notify({ type: 'danger', message: '两次密码不一致' });
			return false;
		}
		return true;
	},
	//学生课表popup
	flipCourseListPopupVisible: function () {
		const { courseListVisible } = this.data;
		this.setData({
			courseListVisible: !courseListVisible
		});
	}
})