<template>
  <div class="app-container">
    <div class="filter-container" style="margin-bottom:1%" gutter="20">
      用户名
      <el-input v-model="username" clearable style="width: 10%; margin-right:1%" class="filter-item" />姓名
      <el-input v-model="name" clearable style="width: 10%; margin-right:1%" class="filter-item" />手机
      <el-input
        v-model="telephone"
        clearable
        style="width: 10%; margin-right:1%"
        class="filter-item"
      />
      <el-button
        class="filter-item"
        type="primary"
        icon="el-icon-search"
        style="margin-left:1%;float:right;margin-right:5%"
        @click="getAllUser"
      >搜索</el-button>
    </div>
    <el-button type="primary" @click="handleAddUser">新增用户</el-button>

    <el-table :data="userList" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="用户编号" width="220">
        <template slot-scope="scope">{{ scope.row.id }}</template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="220">
        <template slot-scope="scope">{{ scope.row.username }}</template>
      </el-table-column>
      <el-table-column align="center" label="姓名" width="220">
        <template slot-scope="scope">{{ scope.row.name }}</template>
      </el-table-column>
      <!-- <el-table-column align="header-center" label="用户电话">
        <template slot-scope="scope">{{ scope.row.phone }}</template>
      </el-table-column>-->
      <el-table-column align="center" label="手机">
        <template slot-scope="scope">{{ scope.row.telephone }}</template>
      </el-table-column>

      <!-- <el-table-column align="header-center" label="用户地址">
        <template slot-scope="scope">{{ scope.row.address }}</template>
      </el-table-column>-->
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          
          <el-button type="primary" size="small" @click="handleEditUser(scope)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
          <el-button type="warning" size="small" @click="handleAllocate(scope)">分配角色</el-button>
          <el-button type="success" size="small" @click="ResetPas(scope)">重置密码</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
      :visible.sync="dialogVisible"
      :title="dialogType==='edit'?'编辑用户':'新增用户'"
      @close="dialogVisible=false;getAllUser()"
    >
      <el-form
        label-width="80px"
        label-position="left"
        :model="curUser"
        :rules="curUserRule"
        ref="curUser"
      >
        <!-- <el-form-item label="角色编号">
          <el-input v-model="id" placeholder="Role Name" />
        </el-form-item>-->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="curUser.name" />
        </el-form-item>
        <!-- <el-form-item label="用户电话">
          <el-input v-model="curUser.phone" />
        </el-form-item>-->
        <el-form-item label="手机" prop="telephone">
          <el-input v-model="curUser.telephone" maxlength="11" />
        </el-form-item>
        <!-- <el-form-item label="用户地址">
          <el-input v-model="curUser.address" />
        </el-form-item>-->
        <!-- <el-form-item label="是否启用">
          <el-radio v-model="curUser.enabled" label="1">是否启用</el-radio>
        </el-form-item>-->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="curUser.username" />
        </el-form-item>
        <el-form-item label="密码" v-if="dialogType!='edit'">
          <el-input v-model="curUser.password" show-password />
          <!-- <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>-->
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="curUser.remark" />
        </el-form-item>

        <!-- <el-form-item label="Menus" v-if=" dialogType=='edit' ">
          <el-tree
            ref="tree"
            :data="routesData"
            :props="defaultProps"
            show-checkbox
            node-key="id"
            class="permission-tree"
          />
        </el-form-item>-->
      </el-form>
      <div style="text-align:right;">
        <el-button @click="dialogVisible=false;getAllUser()">取消</el-button>
        <el-button type="primary" @click="confirmUser">确认</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="allocateVisible">
      <el-form label-width="80px" label-position="left">
        <el-form-item label="请选择角色" label-width="110px">
          <el-tree ref="tree" :data="allRoles" show-checkbox node-key="id" class="permission-tree" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button @click="allocateVisible=false; getAllUser()">取消</el-button>
        <el-button type="primary" @click="confirmAllocate">确认</el-button>
      </div>
    </el-dialog>
    <div style="text-align:center">
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="page"
        :limit.sync="limit"
        @pagination="getAllUser"
      />
    </div>
  </div>
</template>

<script>
import path from "path";
import { deepClone } from "@/utils";
import {
  allMenu,
  getMenu,
  deleteRole,
  addRole,
  updateRole,
  assignMenu,
  getUser,
  addUser,
  deleteUser,
  updateUser,
  getRoles,
  resetPass,
  getUserPage
} from "@/api/user";
import {
  allRole,
  assignRole
} from "@/api/role";
import Pagination from "@/components/Pagination";
// import { getConstant } from "@/api/basic";
import { constants } from "crypto";
// import { getRoutes, getRoles, addRole, deleteRole, updateRole } from '@/api/role'

const defaultUser = {
  name: "",
  phone: "",
  telephone: "",
  address: "",
  enabled: true,
  username: "",
  password: "",
  remark: ""
};

export default {
  components: { Pagination },
  data() {
    return {
      curUser: {},
      userList: [],
      dialogVisible: false,
      dialogType: "new",
      allocateVisible: false,
      allRoles: [],
      scope: null,
      username:"",
      name:"",
      telephone:"",
      page: 1,
      limit: 10,
      total: 0,
      curUserRule: {
        name: { required: true, message: "请输入内容", trigger: "blur" },
        telephone: { required: true, message: "请输入内容", trigger: "blur" },
        username: { required: true, message: "请输入内容", trigger: "blur" }
      },
      password: "789789"
    };
  },
  computed: {},
  created() {
    // Mock: get all routes and roles list from server
    this.curUser = Object.assign({}, defaultUser);
    this.getAllUser();

    // getConstant("ResetPassword").then(response => {
    //   this.password = response.data;
    // });
  },
  methods: {
    getAllUser() {
      getUserPage(this.limit, this.page,this.username,this.name,this.telephone).then(response => {
        console.log(response);
        this.userList = response.data.list;
        this.total = response.data.total;
      });
    },

    ResetPas(scope) {
      this.$confirm("确认重置用户密码吗?", "提示", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      }).then(async () => {
        // console.log(scope.row.username);
        resetPass(scope.row.id,this.password).then(response => {
          console.log(response);
          if (response.result == true) {
            this.$message({
              type: "success",
              message: "您已经成功重置密码，新密码为" + this.password
            });
          } else {
            this.$message({
              type: "error",
              message: response.message
            });
          }
        });
      });
    },

    async handleAllocate(scope) {
      this.allocateVisible = true;
      this.allRoles = [];
      var userRoles = [];
      this.scope = scope;
      await allRole().then(response => {
        for (var i = 0; i < response.data.length; i++) {
          let item = {
            label: response.data[i].name,
            id: response.data[i].id
          };
          this.allRoles.push(item);
        }
      });
      await getRoles(scope.row.id).then(response => {
          console.log(response)
        for (var i = 0; i < response.data.length; i++) {
            userRoles.push(response.data[i].rid);
        }
        this.$refs.tree.setCheckedKeys(userRoles);
        console.log(response.data, userRoles);
      });
    },

    async confirmAllocate() {
      console.log(this.scope);
      const checkedKeys = this.$refs.tree.getCheckedKeys();
      await assignRole(this.scope.row.id, checkedKeys).then(response=>{
          if(response.result==true){
              this.$message.success("分配成功")
          }
          else{
              this.$message.error(response.message)
          }
      })
      this.scope = null;
      this.allocateVisible = false;
    },
    handleAddUser() {
      this.curUser = Object.assign({}, defaultUser);
      this.curUser.password = this.password;
      this.dialogVisible = true;
      this.dialogType = "new";
    },
    handleEditUser(scope) {
      this.curUser = scope.row;
      console.log(this.curUser);
      this.dialogVisible = true;
      this.dialogType = "edit";
    },

    handleDelete(scope) {
      this.$confirm("确认删除用户吗?", "提示", {
        confirmButtonText: "确认",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(async () => {
          await deleteUser(scope.row.id).then(response => {
            this.getAllUser();
            if (response.result == true) {
              this.$message({
                type: "success",
                message: "删除成功!"
              });
            } else {
              this.$message({
                type: "error",
                message: response.message
              });
            }
          });

          // this.rolesList.splice($index, 1);
        })
        .catch(err => {
          console.error(err);
        });
    },

    async confirmUser() {
      if (this.dialogType == "new") {
        let valid1;
        this.$refs.curUser.validate(valid => {
          if (valid == false) {
            valid1 = valid;
            this.$message({
              type: "error",
              message: "您填写的信息不完整"
            });
            return;
          }
        });
        if (valid1 == false) {
          return;
        }
        await addUser(this.curUser).then(response => {
          console.log(response);
          if (response.success == false) {
            this.$message({
              type: "error",
              message: response.message
            });
          }

          if (response.result == true) {
            this.$message({
              type: "success",
              message: "您已成功添加用户"
            });
            this.getAllUser();
            this.dialogVisible = false;
          }
        });
      } else {
        let valid1;

        this.$refs.curUser.validate(valid => {
          valid1 = valid;
          if (valid == false) {
            // valid1 = valid;
            this.$message({
              type: "error",
              message: "您填写的信息不完整"
            });
            return;
          }
        });
        if (valid1 == false) {
          return;
        }
        console.log(valid1);
        await updateUser(this.curUser).then(response => {
          console.log(response);
          if (response.result == false) {
            this.$message({
              type: "error",
              message: response.message
            });
          }

          if (response.result == true) {
            this.$message({
              type: "success",
              message: "您已成功更新用户"
            });
            this.getAllUser();
            this.dialogVisible = false;
          }

          // console.log(this.routes);
        });
        this.getAllUser();
        // this.dialogVisible = false;
      }
    }
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