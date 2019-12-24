// pages/statistics/statistics.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager';
import { getUserToken } from '../../utils/userTokenManager';

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		isAdmin: getUserToken().isAdmin,
		userId: null,
		courseId: 1,
		//display
		recordsCourse: [],
		recordsStudent: [],
		courseList: [],
	},

	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const user = getUserToken();
		const { isAdmin, userId } = user;
		this.setData(
			{ isAdmin, userId },
			()=>{
				if(this.data.isAdmin){
					this.fetchAllCourses();
				}
			}
		);

		this.fetchRecords();
	},
	onShow: function(){
		if(isAdmin){
			this.fetchAllCourses();
		}
		this.fetchRecords();
	},
	onPullDownRefresh: function () {
		this.fetchRecords();
	},
	//获取所有课程
	fetchAllCourses:function(){
		AaHostPost(
			'/course-manage/queryAllCourses'
		).then((json)=>{
			if(json.code === 0){
				this.setData({
					courseList:json.data.courseList.map(
						e => {
							return {
								text:e.courseName,
								value:e.id
							}
						}
					)
				})
			}else {
				Notify({
					type:"warning",
					message:json.msg
				});
				throw json;
			}
		}).catch((e)=>{
			console.error(e)
		})
	},
	//获取打卡记录
	fetchRecords: function () {
		const { isAdmin, userId, courseId } = this.data;
		if(isAdmin){
			AaHostPost(
				'/statistics/query-courseRecords',
				{ courseId }
			).then((json) => {
				if (json.code === 0) {
					this.setData({
						recordsStudent:json.data.records.map(
							e => {
								const { time, student } = e;
								let res = time.match(/(.+)T(.+)\.000\+0000/);
								return { time: `${res[1]}\t${res[2]}`, student };
							}
						)
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
		}else {
			AaHostPost(
				'/statistics/query-studentRecords',
				{ userId }
			).then((json) => {
				if (json.code === 0) {
					this.setData({
						recordsCourse: json.data.records.map(
							e => {
								const { time, course } = e;
								let res = time.match(/(.+)T(.+)\.000\+0000/);
								return { time: `${res[1]}\t${res[2]}`, course };
							}
						)
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
		}
	},
	//查看其他课程的考勤记录
	handleCourseIdChange:function(e){
		this.setData({
			courseId:e.detail
		},()=>{
			this.fetchRecords()
		})
	}
})