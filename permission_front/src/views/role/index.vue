<template>
    <div class="app-container">
        <el-button type="primary" @click="handleAddRole">新增角色</el-button>
        <el-table :data="rolesList" style="width: 100%;margin-top:30px;" border>
            <el-table-column align="center" label="角色编号" width="220">
                <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column align="center" label="角色代码" width="220">
                <template slot-scope="scope">
                    {{ scope.row.name }}
                </template>
            </el-table-column>
            <el-table-column align="center" label="角色描述">
                <template slot-scope="scope">{{ scope.row.namezh }}</template>
            </el-table-column>
            <el-table-column align="center" label="操作">
                <template slot-scope="scope">
                    <el-button type="primary" size="small" @click="handleEdit(scope)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'编辑角色':'新增角色'">
            <el-form :model="role" label-width="80px" label-position="left">
                <el-form-item label="角色名称">
                    <el-input v-model="name" placeholder="Role Name" />
                </el-form-item>
                <el-form-item label="角色描述">
                <el-input
                    v-model="namezh"
                    :autosize="{ minRows: 2, maxRows: 4}"
                    type="textarea"
                    placeholder="Role Description"
                />
                </el-form-item>
                <el-form-item label="Menus" v-if=" dialogType=='edit' ">
                <el-tree
                    ref="tree"
                    accordion
                    :data="menuData"
                    :props="defaultProps"
                    show-checkbox
                    node-key="id"
                    class="permission-tree"
                    @node-click="node_click"
                />
            </el-form-item>
        </el-form>
      <div style="text-align:right;">
        <el-button @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmRole">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import path from "path";
import { deepClone } from "@/utils";
import { allRole, getMenu, deleteRole, addRole, updateRole } from "@/api/role";
import{allMenu ,assignMenu,findbyrid} from "@/api/menu"
import { constants } from "crypto";
// import { getRoutes, getRoles, addRole, deleteRole, updateRole } from '@/api/role'

const defaultRole = {
    key: "",
    name: "",
    description: "",
    routes: []
};

export default {
    data() {
        return {
            id: '',
            name: '',
            namezh: '',
            checkedlist: [],
            raw: [],
            role: Object.assign({}, defaultRole),
            routes: [],
            rolesList: [],
            dialogVisible: false,
            dialogType: "new",
            checkStrictly: false,
            defaultProps: {
                children: "children",
                label: "label"
            },
            menuData:{}
        };
    },
    computed: {
        routesData() {
            return this.routes;
        }
    },
    created() {
        // Mock: get all routes and roles list from server

        this.getAllMenu();
        this.getRoles();
    },
    methods: {
        node_click(data){
            console.log(data)
        },
        async getRoutes() {
            allMenu()
                .then(response => {
                    this.raw = response.data;
                    // console.log(this.raw);
                    this.routes = this.generateRoutes(response.data);

                    // console.log(this.routes);
                })
                .catch(error => {
                    reject(error);
                });
        },
        async getRoles() {
            allRole()
                .then(response => {
                    this.rolesList = response.data;
                })
                .catch(error => {
                    reject(error);
                });
        },

        getAllMenu(){
            allMenu().then(response=>{
                this.menuData = this.generateTree(response.data,0)
                console.log(this.menuData)
            })
        },

        


        generateTree(treeData,parentid){
            var treeArr=[];
            for(var i=0;i<treeData.length;i++){ 
                var node=treeData[i];
                if(node.parentid==parentid ){ 
                    var newNode={id:node.id,label:node.name,children:this.generateTree(treeData,node.id)};
                    treeArr.push(newNode);              
                }
            }
            return treeArr;
        },


        // Reshape the routes structure so that it looks the same as the sidebar
        generateRoutes(routes, basePath = "/") {
            const res = [];

            for (let route of routes) {

                // skip some route
                if (route.hidden) {
                    continue;
                }

                const onlyOneShowingChild = this.onlyOneShowingChild(
                    route.children,
                    route
                );

                // if (route.children && onlyOneShowingChild && !route.alwaysShow) {
                //   route = onlyOneShowingChild
                // }
                // console.log(route)
                // console.log(route.path)
                const data = {
                    path: route.path,
                    title: route.name,
                    isenabled: route.isenabled,
                    id: route.id
                };

                // recursive child routes
                if (route.children) {
                    data.children = this.generateRoutes(route.children, "");
                }
                res.push(data);
            }

            return res;
        },

        generateArr(routes) {
            let data = [];
            routes.forEach(route => {
                // console.log(route)
                if (route.isenabled) {
                    this.checkedlist.push(route.path)
                    data.push(route);
                }

                if (route.children) {
                    const temp = this.generateArr(route.children);
                    if (temp.length > 0) {
                        data = [...data, ...temp];
                    }
                }
            });
            //  console.log(data)
            return data;
        },
        handleAddRole() {
            this.role = Object.assign({}, defaultRole);
            if (this.$refs.tree) {
                this.$refs.tree.setCheckedNodes([]);
            }
            this.name = this.namezh = ""
            this.dialogType = "new";
            this.dialogVisible = true;
        },
        handleEdit(scope) {
            this.dialogType = "edit";
            this.dialogVisible = true;
            this.checkStrictly = true;
            this.name = scope.row.name;
            this.namezh = scope.row.namezh;
            this.role = scope.row.id;
            this.$nextTick(() => {
                this.$refs.tree.setCheckedKeys([]);
            })
            // this.$refs.tree.setChecked([],true,false)
            findbyrid(this.role).then(response=>{
                for(let item in response.data){
                    console.log(response.data[item])
                    this.$nextTick(() => {
                        this.$refs.tree.setChecked(response.data[item].id,true,false)
                    })
                }
            })

            // console.log(this.role)
            // getMenu(this.role)
            //     .then(response => {
            //         // console.log(response);
            //         this.$nextTick(() => {
            //             const routes = this.generateRoutes(response.data);

            //             this.$refs.tree.setCheckedNodes(this.generateArr(routes));

            //             // set checked state of a node not affects its father and child nodes
            //             this.checkStrictly = false;
            //         });
            //     })
            //     .catch(error => {
            //         reject(error);
            //     });
        },
        handleDelete({ $index, row }) {
            this.$confirm("是否确定删除本角色", "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                .then(async () => {
                    
                    
                    await deleteRole(row.id).then(response=>{
                        if(response.result==true){
                            this.getRoles()
                            this.$message({
                                type: "success",
                                message: "删除成功"
                            });
                        }
                        else{
                            this.$message.error(response.message)
                        }
                    })
                    // this.rolesList.splice($index, 1);
                    
                })
                .catch(err => {
                    console.error(err);
                });
        },
        // generateTree(routes, basePath = "/", checkedKeys) {
        //     const res = [];

        //     for (const route of routes) {
        //         const routePath = path.resolve(basePath, route.path);

        //         // recursive child routes
        //         if (route.children) {
        //             route.children = this.generateTree(
        //                 route.children,
        //                 routePath,
        //                 checkedKeys
        //             );
        //         }

        //         if (
        //             checkedKeys.includes(routePath) ||
        //             (route.children && route.children.length >= 1)
        //         ) {
        //             res.push(route);
        //         }
        //     }
        //     return res;
        // },


        async confirmRole(scope) {
            const isEdit = this.dialogType === "edit";

            // const checkedKeys = this.$refs.tree.getCheckedKeys();
            // var r1 = this.generateTree(
            //   deepClone(this.raw),
            //   "/",
            //   checkedKeys
            // );
            // const checkedKeys = this.$refs.tree.getCheckedKeys();
            // console.log(checkedKeys)
            // console.log(this.raw)

            if (isEdit) {
                const checkedNodes = this.$refs.tree.getCheckedNodes(false,true);
                console.log(checkedNodes)
                // console.log(checkedKeys)
                console.log(scope)
                let data1={
                    id:this.role,
                    name:this.name,
                    namezh:this.namezh
                }
                
                await updateRole(data1).then(response=>{
                   if(response.result==true){
                       this.$message.success("成功更新角色信息")
                   }
                   else{
                       this.$message.error(response.message)
                   }
                })
                // let data = {
                //     menuIds: checkedKeys
                // }
                let checkedKeys=[]
                for(let item in checkedNodes){
                    checkedKeys.push(checkedNodes[item].id)
                }
                console.log(checkedKeys)
                assignMenu(this.role,checkedKeys).then(response=>{
                    if(response.result == true){
                        this.$message.success("成功更新角色权限")
                        this.dialogVisible =false
                    }
                    else{
                        this.$message.error(response.message)
                    }
                })



                // // await assignMenu(this.role, checkedKeys);
                // this.getRoles()


            } else {
                let data={
                    name:this.name,
                    namezh:this.namezh,
                }
                await addRole(data).then(response=>{
                    if(response.result==true){
                        this.$message.success("您已经成功添加角色")
                        this.getRoles();
                        this.dialogVisible = false
                    }
                    else{
                        this.$message.error(response.message);
                    }
                })


            }

            // const { description, key, name } = this.role;
            // this.dialogVisible = false;
            // this.$notify({
            //   title: "Success",
            //   dangerouslyUseHTMLString: true,
            //   message: `
            //       <div>Role Key: ${key}</div>
            //       <div>Role Nmae: ${name}</div>
            //       <div>Description: ${description}</div>
            //     `,
            //   type: "success"
            // });
        },
        // reference: src/view/layout/components/Sidebar/SidebarItem.vue
        onlyOneShowingChild(children = [], parent) {
            let onlyOneChild = null;
            const showingChildren = children.filter(item => !item.hidden);

            // When there is only one child route, the child route is displayed by default
            if (showingChildren.length === 1) {
                onlyOneChild = showingChildren[0];
                onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path);
                return onlyOneChild;
            }

            // Show parent if there are no child route to display
            if (showingChildren.length === 0) {
                onlyOneChild = { ...parent, path: "", noShowingChildren: true };
                return onlyOneChild;
            }

            return false;
        },



    }
};
</script>

<style lang="scss" scoped>
.app-container {
    .roles-table {
        margin-top: 30px;
    }
    .permission-tree {
        margin-bottom: 30px;
    }
}
</style>