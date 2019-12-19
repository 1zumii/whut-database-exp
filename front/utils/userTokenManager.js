export const getUserToken = () => {
    let userId = wx.getStorageSync("userId");
    return userId?userId:null;
};

export const setUserToken = (userId) => {
    wx.setStorageSync("userId", userId);
};

export const clearUserToken = () => {
    wx.removeStorageSync("userId");
}