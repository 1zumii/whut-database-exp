// pages/login/login.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager'
import { setUserToken } from '../../utils/userTokenManager'

Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		username: '',
		password: '',
	},
	handleInputUsernameChange: function (event) {
		this.setData({ username: event.detail.value });
	},
	handleInputPasswordChange: function (event) {
		this.setData({ password: event.detail.value });
	},
	handleLoginByWechat: function () {
		wx.getUserInfo({
			withCredentials: 'false',
			lang: 'zh_CN',
			// timeout:10000,
			success: (result) => {
				console.log(result)
			},
			fail: (error) => {
				console.log(error);
			}
		});
	},
	handleLoginByUser: function () {
		const { username, password } = this.data;
		if (this.validateFormValue()) {
			AaHostPost(
				"/login/loginByUser",
				{ username, password }
			).then((json) => {
				if (json.code === 0) {
					const {userId,isAdmin} = json.data;
					setUserToken({userId,isAdmin});
					wx.switchTab({
						url: '../checkIn/checkIn',
						fail: () => {
							Notify({
								type: "danger",
								message: "登录成功，但页面跳转失败"
							})
						}
					});
				} else{
					Notify({
						type: "danger",
						message: json.msg
					});
					throw json;
				}
			}).catch((error)=>{
				Notify({
					type:'danger',
					message:'登录失败'
				})
			});
		}
	},
	validateFormValue: function () {
		const { username, password } = this.data;
		if (!username) {
			Notify({
				type: "danger",
				message: "请输入用户名"
			});
			return false;
		}
		if (!password) {
			Notify({
				type: "danger",
				message: "请输入密码"
			});
			return false;
		}
		return true;
	},
	onGotUserInfo: function (value) {
		const { detail } = value;
		console.log("onGotUserInfo", detail);
	}
})