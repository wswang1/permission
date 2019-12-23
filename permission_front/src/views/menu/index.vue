<template>
  <div class="app-container">
    <el-button type="primary" @click="handleAddRole" style="margin-bottom:2%">新增菜单</el-button>
    <div style="display:flex">
      <el-tree
        ref="tree"
        :data="routes"
        @node-click="handleNodeClick"
        :expand-on-click-node="false"
        node-key="id"
        :key="treekey"
        class="permission-tree"
        style="width:20%"
      />

      <el-dialog :visible.sync="dialogVisible" :title="'新增菜单'">
        <el-form label-width="230px" label-position="left">
          <!-- <el-form-item label="角色编号">
          <el-input v-model="id" placeholder="Role Name" />
          </el-form-item>-->
          <!-- <el-form-item label="id">
            <el-input v-model="id" />
          </el-form-item> -->
          <!-- <el-form-item label="url">
            <el-input v-model="url" />
          </el-form-item> -->
          <el-form-item label="菜单路径">
            <el-input v-model="path" />
          </el-form-item>
          <!-- <el-form-item label="component">
            <el-input v-model="component" />
          </el-form-item> -->
          <el-form-item label="菜单名称">
            <el-input v-model="name" />
          </el-form-item>
          <el-form-item label="菜单图标">
            <el-input v-model="iconcls" />
          </el-form-item>

          <el-form-item label="上级菜单ID(若为一级菜单请填写0)">
            <el-input v-model="parentid" />
          </el-form-item>
        </el-form>
        <div style="text-align:right;">
          <el-button  @click="dialogVisible=false">取消</el-button>
          <el-button type="primary" @click="add">确定</el-button>
        </div>
      </el-dialog>

      <el-form label-width="80px" label-position="left" style="position:relative; left:10%">
        <el-form-item label="菜单ID">
          <el-input v-model="id" :disabled="true" />
        </el-form-item>
        <!-- <el-form-item label="url">
          <el-input v-model="url" :disabled="disable" />
        </el-form-item>-->
        <el-form-item label="菜单路径">
          <el-input v-model="path" :disabled="disable" />
        </el-form-item>
        <!-- <el-form-item label="component">
          <el-input v-model="component" :disabled="disable" />
        </el-form-item>-->
        <el-form-item label="菜单名称">
          <el-input v-model="name" :disabled="disable" />
        </el-form-item>
        <el-form-item label="菜单图标">
          <el-input v-model="iconcls" :disabled="disable" />
        </el-form-item>
        <el-form-item label="父节点ID">
          <el-input v-model="parentid" :disabled="disable" />
        </el-form-item>
        <div style="text-align:right;">
          <el-button type="warning" @click="handleEdit">编辑</el-button>
          <el-button type="danger" @click="handledelete">删除</el-button>
          <el-button type="primary" @click="confirmEdit">确认</el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>
     

<script>
import {
  allRole,
  getMenu,
  deleteRole,
  addRole,
  updateRole,
  assignMenu
} from "@/api/user";

import { allMenu, addMenu, updateMenu, deleteMenu } from "@/api/menu";

export default {
  data() {
    return {
      raw: [],
      routes: [],
      dialogVisible: false,
      id: "",
      url: "",
      path: "",
      component: "",
      name: "",
      iconcls: "",
      keepalive: "",
      requireauth: "",
      parentid: "",
      enabled: "",
      data: "",
      disable: true,
      treekey: ""
    };
  },
  computed: {
    routesData() {
      return this.routes;
    }
  },
  created() {
    // Mock: get all routes and roles list from server

    this.getRoutes();
  },

  methods: {
    renderTree() {
      this.treeKey = +new Date();
    },

    handledelete() {
        if(this.id==""){
            this.$message.error("请先选择菜单")
            return
        }
      this.$confirm("确认删除菜单\'  "+ this.name+ "  \'?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        
        await deleteMenu(this.id).then(response=>{
            if(response.result==true){
                this.$message.success("您已经成功删除目录")
                this.getRoutes();
            }
            else{
                this.$message.error(response.message)
            }
        })
      });
      
    },
    handleAddRole() {
      this.dialogVisible = true;
      this.id = "";
      this.url = "";
      this.path = "";
      this.component = "";
      this.name = "";
      this.iconcls = "";
      this.parentid = "";
    },

    handleEdit() {
      this.disable = !this.disable;
    },

    confirmEdit() {
        let data={
            id:this.id,
            path : this.path,
            icon :this.iconcls,
            name:this.name,
            parentid:this.parentid
        }
      updateMenu(data).then(response=>{
          if(response.result == true){
              this.$message.success("更新成功")
              this.getRoutes()
          }
          else{
              this.$message.error(response.message)
          }
      })
      this.disable = true;
    },
    handleNodeClick(data) {
      this.disable = true;
      this.data = data;
      let rt = this.chooseRoutes(this.raw, this.data.id);
      this.id = rt.id;
      this.url = rt.url;
      this.path = rt.path;
      this.componemt = rt.component;
      this.name = rt.name;
      this.iconcls = rt.icon;
      this.parentid = rt.parentid;
    },
    onlyOneShowingChild(children = [], parent) {
      let onlyOneChild = null;
      const showingChildren = children.filter(item => !item.hidden);

      // When there is only one child route, the child route is displayed by default
      // if (showingChildren.length === 1) {
      //   onlyOneChild = showingChildren[0];
      //   console.log(onlyOneChild)
      //   onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path);
      //   return onlyOneChild;
      // }

      // Show parent if there are no child route to display
      if (showingChildren.length === 0) {
        onlyOneChild = { ...parent, path: "", noShowingChildren: true };
        return onlyOneChild;
      }

      return false;
    },

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
          label: route.name,
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

    chooseRoutes(routes, id) {
      //   const res = [];

      for (let route of routes) {
        // skip some route
        if (route.hidden) {
          continue;
        }

        const onlyOneShowingChild = this.onlyOneShowingChild(
          route.children,
          route
        );
        if (route.id == id) {
          return route;
        }
        // if (route.children && onlyOneShowingChild && !route.alwaysShow) {
        //   route = onlyOneShowingChild
        // }
        // console.log(route)
        // console.log(route.path)
        //console.log(route.id, id);

        // recursive child routes
        if (route.children && route.children.length > 0) {
          // console.log(route.children)
          let rt = this.chooseRoutes(route.children, id);
          if (rt != null) {
            return rt;
          }
        }
        // res.push(data);
      }

      return null;
    },

    async getRoutes() {
      allMenu()
        .then(response => {
          this.raw = response.data;
          let treeRoute = this.generateTree(this.raw,"")
          console.log(treeRoute);
          this.routes = this.generateRoutes(treeRoute);
            console.log(routes)
          // console.log(this.routes);
        })
        .catch(error => {
          console.log(error);
        });
    },

    generateTree(treeData, parentid) {
      var treeArr = [];
      for (var i = 0; i < treeData.length; i++) {
        var node = treeData[i];
        if (node.parentid == parentid) {
          var newNode = {
            id: node.id,
            name: node.name,
            path: node.path,
            icon: node.icon,
            component: node.component,
            children: this.generateTree(treeData, node.id)
          };
          treeArr.push(newNode);
        }
      }
      return treeArr;
    },

    add() {
        let data ={
            path:this.path,
            icon:this.iconcls,
            name:this.name,
            parentid:this.parentid,
            enabled:true,
            component:"test1/test1-2"
        }
      addMenu(data).then(response => {
        if(response.result == true){
            this.$message.success("添加成功")
            this.getRoutes()
            this.renderTree();
            this.dialogVisible = false;
        }
        else{
            this.$message.error(response.message)
        }
      });
      
    }
  }
};
</script>

<style lang="scss" scoped>
.el-form .el-select {
  width: 100%;
}
</style>

