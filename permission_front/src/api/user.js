import request from '@/utils/request'

export function login(userName, passWord) {
    return request({
        url: '/user/login',
        method: 'post',
        params: {
            userName: userName,
            passWord: passWord,
        }
    })
}

export function getUserPage(pageSize, pageNum, username, name, telephone) {
    return request({
        url: '/user/findall',
        method: 'get',
        params: {
            pageSize: pageSize,
            pageNum: pageNum,
            username: username,
            name: name,
            telephone: telephone,
        }
    })
}

export function addUser(data) {
    return request({
        url: '/user/add',
        method: 'post',
        data: data
    })
}

export function updateUser(data) {
    return request({
        url: '/user/update',
        method: 'post',
        data: data
    })
}

export function deleteUser(id) {
    return request({
        url: '/user/delete',
        method: 'delete',
        params: {
            id: id
        }
    })
}

export function resetPass(id, passWord) {
    return request({
        url: '/user/resetpass',
        method: 'post',
        params: {
            newPass: passWord,
            id: id
        }
    })
}

export function getRoles(id) {
    return request({
        url: '/user/getRole',
        method: 'get',
        params: {
            id: id
        }
    })
}



export function getInfo(token) {
    return request({
        url: '/user/getInfo',
        method: 'get',
        params: {
            token: token
        }
    })
}

// export function logout() {
//   return request({
//     url: '/user/logout',
//     method: 'post'
//   })
// }