// pages/course-modify/course-modify.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager';

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		//state
		courseName: '',
		teacher: '',
		dayIndex: 2,
		courseIndex: 1,
		selected: ["1"],
		//display
		titleText: '课程名称',
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
		activeNames: [],
		all: [],
	},
	/**
	 * 生命周期函数--监听页面加载
	 */
	onLoad: function (options) {
		const courseId = (Number)(options.id);
		this.fetchCourseInfo(courseId)
	},
	/**
	 * 生命周期函数--监听页面显示
	 */
	onShow: function () {

	},
	fetchCourseInfo: function (courseId) {
		AaHostPost(
			'/course-manage/query-courseAllInfo',
			{ courseId }
		).then((json) => {
			if (json.code === 0) {
				const { courseInfo, all, selected } = json.data;
				this.setData({
					titleText: courseInfo.courseName,
					courseName: courseInfo.courseName,
					teacher: courseInfo.teacher,
					dayIndex: courseInfo.dayIndex,
					courseIndex: courseInfo.courseIndex,
					selected:selected.map(e => String(e.studentId)),
					all:all,
				})
			} else {
				Notify({
					type: 'warning',
					message: json.msg
				});
				throw json;
			}
		}).catch((e) => {
			console.error(e);
		})
	},
	//上课时间
	handleDayIndexSelectChange: function (event) {
		this.setData({ dayIndex: event.detail });
	},
	hanleCourseIndexSelectChange: function (event) {
		this.setData({ courseIndex: event.detail });
	},
	//课程名称
	handleCourseNameChange: function (event) {
		this.setData({ courseName: event.detail });
	},
	//任课老师
	handleTeacherChange: function (event) {
		this.setData({ teacher: event.detail });
	},
	//折叠框
	onCollapseChange: function (event) {
		this.setData({ activeNames: event.detail });
	},
	//学生列表
	onCheckBoxGroupChange:function(event) {
		console.log(event.detail);
		this.setData({selected: event.detail});
	}
})