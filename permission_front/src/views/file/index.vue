<template>
  <div class="app-container">
    <div class="filter-container" style="margin-bottom:1%" gutter="20">
    <el-button type="primary" @click="handleAddFile">新增文件</el-button>
    <!-- <el-button type="primary" @click="handleVideo">高拍仪拍摄</el-button> -->
    <el-table :data="files" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="文件名称" width="300">
        <template slot-scope="scope">{{ scope.row.fileName }}</template>
      </el-table-column>
      <el-table-column align="center" label="文件类别" width="300">
        <template slot-scope="scope">{{ scope.row.fileTypeName }}</template>
      </el-table-column>
      <el-table-column align="center" label="文件备注" width="600">
        <template slot-scope="scope">{{ scope.row.fileNote }}</template>
      </el-table-column>

      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">修改 </el-button>
          <el-button type="success" size="small" @click="download(scope)">下载</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-left:75%; margin-top:5%" v-if="type=='check'">
      <el-button type="primary" @click="pass">审核通过</el-button>
      <el-button type="danger" @click="nopass">审核不通过</el-button>
    </div>

    

    <el-dialog :visible.sync="uploaddialogVisible" :title="上传材料" width="40%" @close="materialNote = ''">
      <el-upload
        class="upload-demo"
        ref="upload"
        action="123"
        :auto-upload="false"
        :on-preview="handlePreview"
        :on-remove="handleRemove"
        :before-remove="beforeRemove"
        :before-upload="beforeUpload"
        :limit="1"
        :on-exceed="handleExceed"
        :file-list="fileList"
        :on-change="handleChange"
        style="margin-bottom:3%"
      >
        <el-button type="primary">选择文件</el-button>
        
      </el-upload>
      文件名称
      <el-input v-model="materialName" style="width:100%;margin-bottom:3%"></el-input>
      文件类型
      <el-select v-model="materialTypeName" @change="getTypeCode" style="width:100%;margin-bottom:3%">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          
          >
        </el-option>
      </el-select>
      文件备注
      <el-input type="textarea" :rows="5" placeholder="请输入内容" v-model="materialNote" ></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="uploaddialogVisible = false; materialNote=''">取 消</el-button>
        <el-button size="small" type="primary" @click="submitUpload()">确 定</el-button>
      </div>
    </el-dialog>



  

    <el-dialog :visible.sync="editdialogVisible" :title="修改入职材料" width="30%">
      文件名称<el-input v-model="file.fileName" style="margin-bottom:2%"/>
      文件类型
      <el-select v-model="file.fileTypeName" @change="getTypeCode1" style="width:100%;margin-bottom:3%">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
          
          >
        </el-option>
      </el-select>
      文件备注<el-input type="textarea" :rows="5" v-model="file.fileNote"/>
        <el-button size="small" @click="editdialogVisible = false; filename=''" style="margin-left:70%;margin-top:2%">取 消</el-button>
        <el-button size="small" type="primary" @click="confirmEdit()">确 定</el-button>
    </el-dialog>


    

    

  </div>
  </div>
</template>

<script>

import{
  addFile,
  findFile,
  updateFile,
  deleteFile,
}from "@/api/file"
export default {
  data() {
    return {
      fileList: [],
      filedialogVisible: false,
      fileMember: {
        beginTime: "",
        endTime: "",
        schoolName: "",
        degree: "",
        major: ""
      },
      videodialogVisible: false,
      iDeviceCount: 0,
      iMainCamIdx: 0,
      iMainResIdx: 0,
      iSubCamIdx: 0,
      iSubResIdx: 0,
      bAutoCrop: false,
      bShowVideoInfo: false,
      iRotateIdx: 0,
      bUseSubCam: false,
      cacheImage: [],
      imagedialogVisible: false,
      item: {},
      
      uploaddialogVisible: false,
      files: [],
      materialNote:"",
      editdialogVisible:false,
      file:{},
      options:[{
          value: '图书',
          label: '图书',
          code:"01"
        }, {
          value: '杂志',
          label: '杂志',
          code:"02"
        }, {
          value: '文档',
          label: '文档',
          code:"03"
        }, {
          value: '照片',
          label: '照片',
          code:"04"
        }],
      materialTypeName:"",
      materialTypeCode:"",
      timeStamp:"",
      pdfUploaddialogVisible:false,
      materialName:"",
      pdfpath:"",
      pdfCache:[],
      pdfdialogVisible:false
    };
  },

  created() {
    this.empNo = this.$route.query.id;
    this.type = this.$route.query.type;

    this.fetchData();
  },

  methods: {
    fetchData() {
      findFile(localStorage.getItem("username")).then(response => {
        this.files = response.data;
        console.log(this.files)
      });
    },


    

    getTypeCode(val){
      var obj = {};
      obj = this.options.find(item => {
        return item.value === val;
      });
      console.log(val)
      this.materialTypeCode = obj.code;
     
    },


    getTypeCode1(val){
      var obj = {};
      obj = this.options.find(item => {
        return item.value === val;
      });
      console.log(val)
      this.file.fileTypeCode = obj.code;
    },

    handleDelete(scope){
      
      this.$confirm("是否确定删除文件", "警告", {
          confirmButtonText: "确认",
          cancelButtonText: "取消",
          type: "warning"
        })
          .then(async () => {
            
            deleteFile(scope.row.id).then(response=>{
            if(response.result == true){
              this.$message.success("删除成功")
              this.fetchData()
            }
            else{
              this.$message.error(response.message)
            }
          })
          })
          .catch(err => {
            console.error(err);
          });

    },


    handleAddFile() {
      this.uploaddialogVisible = true;
    },

    handleVideo() {
      this.videodialogVisible = true;
    },

    handleUpload() {
      this.uploaddialogVisible = true;
      this.filedialogVisible = false;
    },

    handleRemove(file, fileList) {

      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.error("只能选择1个文件!");
    },

    handleChange(file, fileList){
      this.materialName = file.name
    },

    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },

    beforeUpload(file) {
      

      var that = this;

      console.log(this.materialName)
      let fd = new FormData();
      fd.append("file1", file);

      fd.append("fileName",this.materialName)
      fd.append("fileNote", this.materialNote)
      fd.append("fileTypeCode",this.materialTypeCode)
      fd.append("fileTypeName",this.materialTypeName)
      fd.append("username",localStorage.getItem("username"))
      console.log(fd.get("materialNote"));
      if(this.materialTypeCode==""){
        this.$message.error("请选择文件类型")
        return
      }
      if(this.materialName==""){
        this.$message.error("请填写文件名称")
        return
      }
      addFile(fd).then(response => {
        console.log(response)
        if (response.result == true) {
          console.log(response)
          this.$message.success("上传成功");
          this.uploaddialogVisible = false;
          this.fetchData();
          this.materialNote = ""
        } else {
          this.$message.error(response.message);
        }
      });
    },

    submitUpload() {
      var that = this;
      setTimeout(function() {
        if (that.$refs.upload.$children[0].fileList.length == 1) {
          that.$refs.upload.submit();
        } else {
          that.$message({
            type: "error",
            showClose: true,
            duration: 3000,
            message: "请选择文件!"
          });
        }
      }, 100);
    },

    

    handleEdit(scope) {
      this.editdialogVisible = true
      this.file = JSON.parse(JSON.stringify(scope.row))
    },

    confirmEdit(){
      // this.file.modifier = sessionStorage.getItem("user")
      // this.file.modififerId = sessionStorage.getItem("userid")
      if(this.file.materialName==""){
        this.$message.error("请填写文件名")
        return
      }
      updateFile(this.file).then(response=>{
        if(response.result == true){
          this.$message.success("更新成功")
          this.fetchData()
          this.editdialogVisible = false
        }
        else{
          this.$message.error(response.message)
        }
      })
    },

    

    download(scope) {
      

      window.open("http://47.98.146.179/" + scope.row.filePath+"?attname="+scope.row.fileName);
    },

    
  }
};
</script>