<%@page language="java" pageEncoding="UTF-8"%>

<link href="${contentPath}static/styles/login/settingTabs.css" rel="stylesheet" />
<div id="settingPage">
    <el-dialog :visible.sync="settingDialog.show" class="self-dialog">
        <el-tabs v-model="activeName">
            <!---标题-->
            <el-tab-pane :disabled="true">
                <span slot="label"><i class="el-icon-setting"></i> 个人信息管理</span>
            </el-tab-pane>
            <!--选项卡-->
            <el-tab-pane label="基础设置" name="second">
                <el-form label-width="80px">
                      <!-- <el-form-item label="头像:">
                            <el-upload class="avatar-uploader"action="" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                                <img v-if="pane1.imageUrl" :src="pane1.imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                        </el-form-item> -->
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="手机号:">
                                <el-input v-model="perSon.model.phone" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="公司名称:">
                                <el-input v-model="perSon.model.companyName" auto-complete="off" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="真实姓名:">
                                <el-input v-model="perSon.model.realName" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户类型:">
                                <el-input v-model="perSon.model.userTypeName" auto-complete="off" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="用户邮箱:">
                                <el-input v-model="perSon.model.email" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="QQ号码:">
                                <el-input v-model="perSon.model.qq" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="手机号码:">
                                <el-input v-model="perSon.model.phone" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="座机号码:">
                                <el-input v-model="perSon.model.telPhone" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="身份证号:">
                                <el-input v-model="perSon.model.PIN" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="入职日期:">
                                <el-input v-model="perSon.model.entryDate" auto-complete="off" :disabled="true"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
                <el-row>
                    <el-col :span="6" :offset="18">
                        <div class="dialog-footer">
                            <el-button v-on:click="settingDialog.show = false">取 消</el-button>
                            <el-button type="primary" v-on:click="UpdatePerson()">确 定</el-button>
                        </div>
                    </el-col>
                </el-row>
            </el-tab-pane>

            <el-tab-pane label="修改密码" name="third1">
                <el-form label-width="80px">
                    <el-row>
                        <el-col :span="20" :offset="3">
                            <el-form-item label="原密码:">
                                <el-input v-model="password.model.passWord" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="20" :offset="3">
                            <el-form-item label="新密码:">
                                <el-input v-model="password.model.NewPwd" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="20" :offset="3">
                            <el-form-item label="确认密码:">
                                <el-input v-model="password.model.SurePwd" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="6" :offset="18">
                            <div class="dialog-footer">
                                <el-button v-on:click="settingDialog.show = false">取 消</el-button>
                                <el-button type="primary" v-on:click="UpdatePwd()">确 定</el-button>
                            </div>
                        </el-col>
                    </el-row>
                </el-form>
            </el-tab-pane>

            <el-tab-pane label="供应商权限" name="third2">
                <el-table :data="SuppliersData.model">
                    <el-table-column prop="orgUnitNumber" label="组织机构代码"></el-table-column>
                    <el-table-column prop="companyName" label="供应商公司"></el-table-column>
                    <el-table-column prop="contactTypeName" label="公司供销关系"></el-table-column>
                    <el-table-column prop="createTime" label="配置人"></el-table-column>
                </el-table>
            </el-tab-pane>

            <el-tab-pane label="销售商权限" name="third3">
                <el-table :data="CustomersData.model">
                    <el-table-column prop="orgUnitNumber" label="组织机构代码"></el-table-column>
                    <el-table-column prop="companyName" label="销售商公司"></el-table-column>
                    <el-table-column prop="contactTypeName" label="公司供销关系"></el-table-column>
                    <el-table-column prop="createTime" label="配置人"></el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="子公司权限" name="third5">
                <el-table :data="UserCompany.model">
                    <el-table-column prop="orgUnitNumber" label="机构编号"></el-table-column>
                    <el-table-column prop="companyName" label="公司名称"></el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane label="菜单权限" name="third4">
                <el-tree :data="UserMenu.model" :props="UserMenu.defaultProps"></el-tree>
            </el-tab-pane>
        </el-tabs>
    </el-dialog>
</div>
<script>
    var settingPage = new Vue({
        el: "#settingPage",
        data: {
            activeName: 'second',
            //供应商model
            SuppliersData: {
                model: []
            },
            //销售商客户model
            CustomersData: {
                model: []
            },
            //修改密码model
            password: {
                model: {}
            },
            //修改个人信息model
            perSon: {
                imageUrl: '',
                model: {}
            },
            //用户菜单权限
            UserMenu: {
                model: [],
                defaultProps: {
                    children: 'chindren',
                    label: 'menuName'
                }
            },
            //用户子公司权限
            UserCompany: {
                model: []
            },
            settingDialog: {
                show: false
            }
        },
        methods: {
            handleAvatarSuccess(res, file) {
                this.perSon.imageUrl = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            //修改个人信息
            UpdatePerson() {
                $.ajax({
                    url: "/Person/UpdateUserData",
                    data: settingPage.perSon.model,
                    dataType: "text",
                    type: 'POST',
                    success: function (result) {
                        if (result == "") {
                            settingPage.settingDialog.show = false; //关闭弾罩层
                            Vue.prototype.$notify({ title: '成功', message: "操作成功！", type: 'success' });
                        } else {
                            settingPage.settingDialog.show = false; //关闭弾罩层
                            Vue.prototype.$notify({ title: '警告', message: result, type: 'warning' });
                        }
                    },
                    error: function () {
                        Vue.prototype.$notify.error({ title: '错误', message: "保存失败!!!" });
                    }
                });
            },
            //修改密码
            UpdatePwd() {
                var NewPwd = settingPage.password.model.NewPwd;
                var SurePwd = settingPage.password.model.SurePwd;
                if (NewPwd != SurePwd) {
                    return Vue.prototype.$notify({ title: '警告', message: "俩次密码输入的不一样呀", type: 'warning' });
                }
                $.ajax({
                    url: "/Person/UpdatePassword",
                    data: settingPage.password.model,
                    dataType: "text",
                    success: function (result) {
                        if (result == "") {
                            settingPage.settingDialog.show = false; //关闭弾罩层
                            Vue.prototype.$notify({ title: '成功', message: "操作成功！", type: 'success' });
                        } else {
                            settingPage.settingDialog.show = false; //关闭弾罩层
                            Vue.prototype.$notify({ title: '警告', message: result, type: 'warning' });
                        }
                    },
                    error: function () {
                        Vue.prototype.$notify.error({ title: '错误', message: "保存失败!!!" });
                    }
                });
            },
            //点击头像触发该事件
            loadDataPage() {
                this.password.model = {};
                //弹出选项卡
                this.settingDialog.show = true;
                //获取个人资料
                $.ajax({
                    url: "/Person/GetUserDataList",
                    dataType: "json",//返回值类型（默认是JSON）
                    success: function (result) {
                        settingPage.perSon.model = result;
                    }
                });
                //获取该用户关联的子公司列表
                $.ajax({
                    url: "/Person/GetUserCompanyList",
                    dataType: "json",//返回值类型（默认是JSON）
                    success: function (result) {
                        settingPage.UserCompany.model = result;
                    }
                });
                //获取该用户关联的供应商列表
                $.ajax({
                    url: "/Person/GetUserSupplierList",
                    dataType: "json",//返回值类型（默认是JSON）
                    success: function (result) {
                        settingPage.SuppliersData.model = result;
                    }
                });
                //获取该用户关联的客户列表
                $.ajax({
                    url: "/Person/GetUserCustomerList",
                    dataType: "json",//返回值类型（默认是JSON）
                    success: function (result) {
                        settingPage.CustomersData.model = result;
                    }
                });
                //获取该用户关联的权限列表
                $.ajax({
                    url: "/Person/GetUserMenuList",
                    dataType: "json",//返回值类型（默认是JSON）
                    success: function (result) {
                        settingPage.UserMenu.model = result;
                    }
                });
            }
        }
    });

</script>