import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style
import { getToken } from '@/utils/auth' // get token from cookie
import getPageTitle from '@/utils/get-page-title'
import Layout from '@/layout'

NProgress.configure({ showSpinner: false }) // NProgress Configuration

const whiteList = ['/login'] // no redirect whitelist

router.beforeEach(async(to, from, next) => {
    // start progress bar
    NProgress.start()

    // set page title
    document.title = getPageTitle(to.meta.title)

    // determine whether the user has logged in
    const hasToken = getToken()
        // console.log(hasToken)
    if (hasToken) {
        if (to.path === '/login') {
            // if is logged in, redirect to the home page
            next({ path: '/' })
            NProgress.done()
        } else {
            // console.log(this)
            const hasGetUserInfo = store.getters.name
            if (hasGetUserInfo) {
                console.log(hasGetUserInfo)
                console.log(router.options.routes)
                console.log(to.path)
                next()
            } else {
                try {
                    // get user info
                    let routes = await store.dispatch('user/getInfo')

                    let routes1 = generateTree(routes, "")
                        // console.log(routes1)
                    routes = formatRoutes(routes1)
                    router.options.routes = routes
                    router.addRoutes(routes)
                    console.log(routes)
                        // next()
                        // next({...to })
                    next({...to, replace: true })
                } catch (error) {
                    // remove token and go to login page to re-login
                    await store.dispatch('user/resetToken')
                    Message.error(error || 'Has Error')
                    next(`/login?redirect=${to.path}`)
                    NProgress.done()
                }
            }
            // console.log(router.options.routes)
            // console.log(789)
            // next()
        }
    } else {
        /* has no token*/

        if (whiteList.indexOf(to.path) !== -1) {
            // in the free login whitelist, go directly
            next()
        } else {
            // other pages that do not have permission to access are redirected to the login page.
            next(`/login?redirect=${to.path}`)
            NProgress.done()
        }
    }
})




export const formatRoutes = (routes) => {

    // let routes1 = generateTree(routes, "")
    // console.log(routes1)
    // routes = routes1
    let fmRoutes = [];
    var index = 0;
    routes.forEach(router => {
        index++;
        let {
            path,
            component,
            name,
            meta,
            hidden,
            icon,
            children
        } = router;

        if (children && children instanceof Array) {
            // console.log(children)
            children = formatRoutes(children);
            // console.log(children)
        }

        let fmRouter = {
            path: path,
            component(resolve) {

                require(['./views/' + component + '.vue'], resolve)

            },

            name: name,
            hidden: hidden,
            meta: { title: name, icon: icon },
            children: children,

        };

        // console.log(fmRouter)

        if (fmRouter.children.length > 0) {
            fmRouter.component = Layout
        } else {

            delete fmRouter["children"];
        }
        fmRoutes.push(fmRouter);
        //console.log(fmRouter)
    })
    return fmRoutes;
}

router.afterEach(() => {
    // finish progress bar
    NProgress.done()
})

function generateTree(treeData, parentid) {
    var treeArr = [];
    for (var i = 0; i < treeData.length; i++) {
        var node = treeData[i];
        if (node.parentid == parentid) {
            var newNode = { id: node.id, name: node.name, path: node.path, icon: node.icon, component: node.component, children: generateTree(treeData, node.id) };
            treeArr.push(newNode);
        }
    }
    return treeArr;
}