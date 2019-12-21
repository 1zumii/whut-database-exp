export const getUserToken = () => {
    let userId = wx.getStorageSync("user");
    return userId?userId:null;
};

export const setUserToken = (userId) => {
    wx.setStorageSync("user", userId);
};

export const clearUserToken = () => {
    wx.removeStorageSync("user");
}