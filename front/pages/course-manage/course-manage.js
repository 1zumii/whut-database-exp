// pages/course-manage/course-manage.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager';

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		courseList:null,
	},
	//生命周期函数--监听页面加载
	onLoad: function (options) {
		this.fetchAllCourses()
	},
	//生命周期函数--监听页面显示
	onShow: function () {
		this.fetchAllCourses();
	},
	fetchAllCourses:function(){
		AaHostPost(
			'/course-manage/queryAllCourses'
		).then((json)=>{
			if(json.code === 0){
				this.setData({courseList:json.data.courseList})
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
	navigateToCourseModify:function(e){
		const {id} = e.currentTarget.dataset;
		wx.navigateTo({
			url: `../course-modify/course-modify?id=${id}`,
			fail: ()=>{
				Notify({
					type:"warning",
					message:"课程详情页跳转失败"
				});
			},
		});
	}
})