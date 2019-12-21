// pages/signin/signin.js
import { AaHostPost } from '../../utils/httpManager'
import { setUserToken } from '../../utils/userTokenManager'
import Notify from '../../dist/vant-weapp/notify/notify';

Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		//用户信息
		username: '',
		password: '',
		checkPassword: '',
		userInfo: null,
		//学生信息
		stuNum: '',
		name: '',
		gender: null,
		phone: '',
		classId: null,
		//展示用数据
		genderPopupVisible: false,
		genderMap: ['女', '男'],
		classPopupVisible: false,
		classDataMap: [],
		classNameMap: [],
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		this.fetchClassIdList();
	},
	//性别选项
	handleGenderChange: function (event) {
		this.setData(
			{ gender: event.detail.index },
			() => this.closeGenderPopup()
		);
	},
	showGenderPopup: function () {
		this.setData({ genderPopupVisible: true });
	},
	closeGenderPopup: function () {
		this.setData({ genderPopupVisible: false });
	},
	//班级选项
	fetchClassIdList: function () {
		AaHostPost('/login/getAllClasses').then((json) => {
			if (json.code === 0) {
				const { classList } = json.data;
				this.setData({
					classDataMap: classList,
					classNameMap: classList.map(e => e.className),
				});
			} else {
				throw json;
			}
		}).catch((error) => {
			Notify({ type: 'danger', message: '可选班级列表获取失败' });
			console.error(error);
		});
	},
	renderClassesList: function () {
		const { genderMap } = this.data;
		return genderMap.map((element) => {
			console.log(element);
			return element.className;
		});
	},
	flipClassIdPopup: function () {
		const { classPopupVisible } = this.data;
		this.setData({ classPopupVisible: !classPopupVisible });
	},
	handleClassIdChange: function (event) {
		const { index } = event.detail;
		const { classDataMap } = this.data;
		this.setData({ classId: classDataMap[index].id });
	},
	//用户名
	handleUsernameChange: function (event) {
		this.setData({ username: event.detail });
	},
	handleGotUserInfo: function (event) {
		const { userInfo } = event.detail;
		this.setData({
			username: userInfo.nickName,
			userInfo: userInfo
		});
	},
	//密码
	handlePasswordChange: function (event) {
		this.setData({ password: event.detail });
	},
	//确认密码
	handleCheckPasswordChange: function (event) {
		this.setData({ checkPassword: event.detail });
	},
	//学号
	handleStuNumChange: function (event) {
		this.setData({ stuNum: event.detail });
	},
	//姓名
	handleNameChange: function (event) {
		this.setData({ name: event.detail });
	},
	//电话
	handlePhoneChange: function (event) {
		this.setData({ phone: event.detail });
	},
	//提交
	handleSubmitButtonClick: function () {
		if (this.validateInfo()) {
			const {
				username, password, stuNum,
				name, gender, phone, classId,
			} = this.data;
			let parameters = {
				username, password, stuNum,
				name, gender, phone, classId,
			};
			if (this.data.userInfo) {
				parameters.avatar = this.data.userInfo.avatar;
			}
			AaHostPost(
				"/login/createNewStudent",
				{ ...parameters }
			).then((json) => {
				if (json.code === 0) {
					const {userId,isAdmin} = json.data;
					setUserToken({userId,isAdmin});
					wx.switchTab({
						url: '../checkIn/checkIn',
						fail: () => {
							Notify({
								type: "danger",
								message: "注册成功，但页面跳转失败"
							})
						}
					});
				} else
					throw json;
			}).catch((error) => {
				Notify({
					type: "danger",
					message: "学生用户创建失败"
				});
			})
		}
	},
	validateInfo: function () {
		//必填项校验
		const attributeList = [
			'username', 'password', 'checkPassword',
			'stuNum', 'name', 'gender', 'phone', 'classId'
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
		if (this.data.password !== this.data.checkPassword) {
			Notify({ type: 'danger', message: '两次密码不一致' });
			return false;
		}
		return true;
	}
})