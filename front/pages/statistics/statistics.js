// pages/statistics/statistics.js
import Notify from '../../dist/vant-weapp/notify/notify';
import { AaHostPost } from '../../utils/httpManager';
import { getUserToken } from '../../utils/userTokenManager';

Page({

	/**
	 * 页面的初始数据
	 */
	data: {
		isAdmin: null,
		userId: null,
		//display
		records: [],
	},

	//生命周期函数--监听页面加载
	onLoad: function (options) {
		const user = getUserToken();
		const { isAdmin, userId } = user;
		this.setData({ isAdmin, userId });

		this.fetchRecords();
	},
	onShow: function(){
		this.fetchRecords();
	},
	onPullDownRefresh: function () {
		this.fetchRecords();
	},
	//获取打卡记录
	fetchRecords: function () {
		const { isAdmin, userId } = this.data;
		AaHostPost(
			'/statistics/query-studentRecords',
			{ isAdmin, userId }
		).then((json) => {
			if (json.code === 0) {
				this.setData({
					records: json.data.records.map(
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
})