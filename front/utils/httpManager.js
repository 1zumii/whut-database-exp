const root = "http://localhost:9975"

export const AaHostPost = (url,data={}) => {
    console.log('send: ',root+url,'\t',data);
    return new Promise((resolve,reject)=>{
      wx.request({
        url:root+url,
        method:'POST',
        data:data,
        dataType: 'json',
        header: {
          'content-type': 'application/json'
        },
        success:(res)=>{
          console.log('fetch: ',res)
          resolve(res.data)
        },
        fail:(error)=>{
          console.error('error: ',root+url)
          reject(error)
        }
      })
    });
};