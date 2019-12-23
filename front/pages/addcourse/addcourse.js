// pages/addcourse/addcourse.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager';

Page({
	/**
	 * 页面的初始数据
	 */
	data: {
		//state
		newCourseName: '',
		newTeacher: '',
		dayIndex: 2,
		courseIndex: 1,
		//display
		dayIndexMap: [
			{ value: 1, text: '星期天' },
			{ value: 2, text: '星期一' },
			{ value: 3, text: '星期二' },
			{ value: 4, text: '星期三' },
			{ value: 5, text: '星期四' },
			{ value: 6, text: '星期五' },
			{ value: 7, text: '星期六' }
		],
		courseIndexMap: [
			{ value: 1, text: '第一节' },
			{ value: 2, text: '第二节' },
		],
	},

	//生命周期函数--监听页面加载
	onLoad: function (options) {

	},
	//生命周期函数--监听页面显示
	onShow: function () {

	},
	//课程名称
	handleCourseNameChange:function(event){
		this.setData({newCourseName:event.detail});
	},
	//任课老师
	handleTeacherChange:function(event){
		this.setData({newTeacher:event.detail});
	},
	//上课时间
	handleDayIndexSelectChange: function (event) {
		this.setData({ dayIndex: event.detail });
	},
	hanleCourseIndexSelectChange: function (event) {
		this.setData({ courseIndex: event.detail });
	},
	//提交
	onSubmit: function () {
		if (this.validateForm()) {
			AaHostPost(
				'/setting/add-course',
				{
					courseName: this.data.newCourseName,
					teacher: this.data.newTeacher,
					dayIndex: this.data.dayIndex,
					courseIndex: this.data.courseIndex
				}
			).then((json) => {
				if (json.code === 0) {
					Notify({
						type: "success",
						message: "课程创建成功"
					});
					wx.navigateBack({ delta: 1 });
				} else {
					Notify({
						type: "warning",
						message: json.msg
					});
					throw json;
				}
			}).catch((error) => {
				console.error(error);
			})
		}
	},
	validateForm: function () {
		const { newCourseName, newTeacher } = this.data;
		if (!newCourseName) {
			Notify({
				type: "warning",
				message: "课程名称不能为空"
			});
			return false;
		}
		if (!newTeacher) {
			Notify({
				type: "warning",
				message: "任课老师不能为空"
			});
			return false;
		}
		return true;
	}
})