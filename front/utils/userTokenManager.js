export const getUserToken = () => {
    let userId = wx.getStorageSync("user");
    return userId?userId:null;
};

export const setUserToken = (user) => {
    wx.setStorageSync("user", user);
};

export const clearUserToken = () => {
    wx.removeStorageSync("user");
}