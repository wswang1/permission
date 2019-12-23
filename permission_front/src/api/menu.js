import request from '@/utils/request'

export function allMenu() {
    return request({
        url: '/menu/findall',
        method: 'get',
    })
}

export function assignMenu(roleId, menuList) {
    return request({
        url: '/menurole/assign',
        method: 'post',
        params: {
            roleId: roleId
        },
        data: menuList
    })
}

export function findbyrid(rid) {
    return request({
        url: '/menu/findbyrid',
        method: 'get',
        params: {
            rid: rid
        }
    })
}

export function updateMenu(menu) {
    return request({
        url: '/menu/update',
        method: 'post',
        data: menu
    })
}

export function deleteMenu(id) {
    return request({
        url: '/menu/delete',
        method: 'delete',
        params: {
            id: id
        }
    })
}

export function addMenu(data) {
    return request({
        url: '/menu/add',
        method: 'post',
        data: data
    })
}