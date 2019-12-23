import request from '@/utils/request'

export function allRole() {
    return request({
        url: '/role/findAll',
        method: 'get',
    })
}

export function addRole(data) {
    return request({
        url: '/role/add',
        method: 'post',
        data: data,
    })
}

export function deleteRole(id) {
    return request({
        url: '/role/delete',
        method: 'delete',
        params: {
            id: id
        }
    })
}

export function updateRole(data) {
    return request({
        url: '/role/update',
        method: 'post',
        data: data
    })
}
export function assignRole(id, list) {
    return request({
        url: '/roleuser/assign',
        method: 'post',
        data: list,
        params: {
            id: id
        }
    })
}