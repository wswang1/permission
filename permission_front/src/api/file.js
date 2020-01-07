import request from '@/utils/request_9998'

export function addFile(data) {
    return request({
        url: '/file/add',
        method: 'post',
        data: data
    })
}

export function deleteFile(id) {
    return request({
        url: '/file/delete',
        method: 'delete',
        params: {
            id: id
        }
    })
}

export function findFile(username) {
    return request({
        url: '/file/find',
        method: 'get',
        params: {
            username: username
        }
    })
}

export function updateFile(file) {
    return request({
        url: '/file/update',
        method: 'post',
        params: {
            id: file.id,
            fileName: file.fileName,
            fileTypeName: file.fileTypeName,
            fileTypeCode: file.fileTypeCode,
            fileNote: file.fileNote,
        }
    })
}