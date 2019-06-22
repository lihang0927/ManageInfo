var student = {
		template : `
		<div>
			<el-button type="primary" icon="el-icon-edit" class="addButton" @click="AddStudent">增加学生</el-button>
			<el-table
		    ref="filterTable"
		     border
		     stripe
		    :data="pageData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
		    tooltip-effect="dark"
		    style="width: 100%"
		    @selection-change="handleSelectionChange">
		    
		    <el-table-column
		      type="selection"
		      width="55">
		    </el-table-column>
		    
		     /*学号*/
		    <el-table-column
		      fixed="left"
		      prop="no"
		      label="学号"
		      width="120">
		    </el-table-column>
		    
		     /*姓名*/
		    <el-table-column
		      fixed="left"
		      prop="name"
		      label="姓名"
		      width="120">
		    </el-table-column>
		    
		    /*性别*/
		    <el-table-column
		      prop="sex"
		      label="性别"
		      width="120">
		    </el-table-column>
		    
		    /*出生日期*/
		    <el-table-column
		      label="出生日期"
		      width="120"
		      prop="birth"
		      column-key="birth"
		      :filters="filtersData"
		      :filter-method="filterHandler"
		      filter-placement="bottom-end"
		      >
		      <template slot-scope="scope">{{ scope.row.birth }}</template>
		    </el-table-column>
		    
		    /*入学年份*/
		    <el-table-column
		      prop="grade"
		      label="入学年份"
		      width="120">
		    </el-table-column>
//		    
//		     /*联系电话*/
//		    <el-table-column
//		      prop="phone"
//		      label="联系电话"
//		      width="120">
//		    </el-table-column>
		    
		    <el-table-column
		      prop="address"
		      label="地址"
		      show-overflow-tooltip>
		    </el-table-column>
		    
		    /*学院*/
		     <el-table-column
		      prop="college.name"
		      label="学院"
		      width="120">
		    </el-table-column>
		    
		     /*专业*/
		     <el-table-column
		      prop="major.name"
		      label="专业"
		      width="120">
		    </el-table-column>
		    
		     <el-table-column
		     	  fixed="right"
			      align="right">
			      <template slot="header" slot-scope="scope">
			        <el-input
			          v-model="search"
			          size="mini"
			          placeholder="输入关键字搜索"/>
			      </template>
			      
			      <template slot-scope="scope">
			        <el-button
			          size="mini"
			          @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
			        <el-button
			          size="mini"
			          type="danger"
			          @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
			      </template>
			  </el-table-column>
			  <el-button type="text" ></el-button>
			  
		  </el-table>
		  
		
	    
	    <el-pagination
	      @size-change="handleSizeChange"
	      @current-change="handleCurrentChange"
	      :current-page.sync="currentPage"
	      :page-size="pagesize"
	       background
	      layout="total,prev, pager, next, jumper"
	      :total="allnum">
	    </el-pagination>
		
		<el-dialog title="学生信息" :visible.sync="dialogFormVisible">
		  <el-form :model="form">
			<el-form-item label="学号" :label-width="formLabelWidth">
		      <el-input v-model="form.no" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    <el-form-item label="姓名" :label-width="formLabelWidth">
		      <el-input v-model="form.name" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    <el-form-item label="性别" :label-width="formLabelWidth">
		      <el-select v-model="form.sex" placeholder="请选择活动区域">
		        <el-option label="Male"  value="Male"></el-option>
		        <el-option label="FeMale"  value="FeMale"></el-option>
		      </el-select>
		    </el-form-item>
		    
		    <el-form-item label="出生日期" :label-width="formLabelWidth">
		    	 <el-date-picker
			      v-model="form.birth"
			      type="date"
			      placeholder="选择日期">
			    </el-date-picker>
		    </el-form-item>
		    
		    <el-form-item label="入学年份" :label-width="formLabelWidth">
				<el-date-picker
			      v-model="form.grade"
			      type="year"
			      placeholder="选择年">
			    </el-date-picker>
			</el-form-item>
			 
			 <el-form-item label="所属学院" :label-width="formLabelWidth">
			     <el-select v-model="form.college.id" placeholder="请选择学院" @change="collegeChange">
			        <el-option v-for="(item,index) in collegeData" :key="item.value" :label="item.name"  v-bind:value="item.id"></el-option>
			      </el-select>
		    </el-form-item>
		    
		    <el-form-item label="所属专业" :label-width="formLabelWidth">
				<el-select v-model="form.major.id" placeholder="请选择专业" >
			        <el-option v-for="(item,index) in majorData" :key="item.value" :label="item.name"  v-bind:value="item.id"></el-option>
			      </el-select>
		    </el-form-item>
			
			<el-form-item label="联系电话" :label-width="formLabelWidth">
		      <el-input v-model="form.phone" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    <el-form-item label="联系地址" :label-width="formLabelWidth">
		      <el-input v-model="form.address" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="dialogFormVisible = false">取 消</el-button>
		    <el-button type="primary" @click="submitChange">确 定</el-button>
		</div>
		</el-dialog>
		
		</div>
			`,
			 data: function(){
				 return {
					 tableData: [],
					 pageData:[],
					 collegeData:[],
					 majorData:[],
				        multipleSelection: [],
				        /*分页是否打开*/
				        hidevalue:false,
				        currentPage:1,
				        allnum:4,
				        /*每一页的数量*/
				        pagesize:3,
				        search: '',
				         /*弹框是否打开*/
				        dialogFormVisible: false,
				        form: {
				        	id:'',
				            name: '',
				            no: '',
				            sex: '',
				            birth: '',
				            grade: '',
				            phone:'',
				            address:'',
				            college:{'id':1},
				            major:{'id':1,'college':{'id':1}}
				        },
				        /*状态为0：修改 状态为1：添加*/
				        status:0,
				        formLabelWidth: '120px',
				        filtersData:[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}],
				       
				 }
		    },
		    methods: {
		    	submitChange(){
		    		console.log(this.status);
		    		this.dialogFormVisible = false;

		    		/*如果未添加*/
		    		if(this.status==1){
		    			/*加载学院、专业信息 动态显示*/
		    			axios.post('/student/student',this.form).then(res=>{
		    				res = res.data;
//		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadStudents();
		    				}else{
		    					  alert(res.msg);
		    				}
		    			}).catch(err=>{
		    				console.error(err);
		    				alert(res.msg);
		    			});
		    		}
		    		/*为修改*/
		    		else{
		    			axios.put('/student/student',this.form).then(res=>{
		    				res = res.data;
//		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadStudents();
		    				}else{
		    					  alert(res.msg);
		    				}
		    			}).catch(err=>{
		    				console.error(err);
		    				alert(res.msg);
		    			});
		    		}
		    	},
		        handleSelectionChange(val) {
		          this.multipleSelection = val;
		        },
		        filterHandler(value, row, column) {
		            const property = column['property'];
		            return row[property] === value;
		        },
		        handleEdit(index, row) {
		            //console.log(index, row);
		            if(this.dialogFormVisible==false){
		    			this.dialogFormVisible=true;
		    			this.form=this.pageData[index];
		    			
		    			/*加载学院、专业信息 动态显示*/
		    			this.loadColleges();
		    			this.loadMajors();
		    		}
		        },
		        handleDelete(index, row) {
		            console.log(index, row);
		            console.log(row.id);
		            axios.delete('/student/student/'+row.id).then(res=>{
		            	  console.log(res);
					       res = res.data;
					       if(res.result){
					         this.loadStudents();
					         this.$notify({
					             title: '成功',
					             message: '成功删除学生',
					             type: 'success'
					           });
					       }
					     }).catch(err=>{
					       console.log(err);
					       alert('网络请求异常，请重试!');
					     });
		        },
		        collegeChange(value){
		        	console.log(value);
		        	console.log(this.majorData);
		        	var filterMajor=this.majorData.filter(function(d){
		        		return d.college.id==value;
		        	})
//		        	console.log(filterMajor);
		        	this.majorData=filterMajor
		        },
//		        handleChange(value) {
//		            console.log(value);
//		         },
		        handleSizeChange(val){
		        	console.log(val);
		        },
		        /*页面修改数据*/
		        handleCurrentChange(val){
//		        	console.log(val);
		        	/*开始数据 页数*每一页的数量*/
		        	let st=this.pagesize*(val-1);
		        	let et=st+this.pagesize;
		        	this.pageData=this.tableData.slice(st,et);
		        },
		        AddStudent(){
		        	 if(this.dialogFormVisible==false){
		    			this.dialogFormVisible=true;
		    			this.form={
					            name: '',
					            no: '',
					            sex: '',
					            birth: '',
					            grade: '',
					            phone:'',
					            address:'',
					            college:{'id':'','name':''},
					            major:{'id':'',name:'','college':{'id':'',name:''}}
					    };
		    			this.status=1;
		    			this.loadColleges();
		    			this.loadMajors();
			    	}
		        },
		        loadStudents(){
		        	axios.get("/student/student").then(res=>{ //res 是返回对象
		        		//console.log(res);
						res = res.data;
						var returnData=res.rows;
						var len=returnData.length;
						
						if(res.result === true){
							this.tableData = res.rows;
							/*修改表格显示数据 与总的页数*/
							this.pageData=this.tableData.slice(0,Math.min(this.pagesize,len+1));
							this.allnum=len;
//							console.log(this.tableData);
//							console.log(this.pageData);
						}else{
							alter(res.msg);   //显示查询错误
						}
					}).catch(err=>{
						console.log(err);
					});
		        },
		        /*查询学院信息 更新添加*/
		        loadColleges(){
		        	axios.get("/college/college").then(res=>{ //res 是返回对象
						res = res.data;
						
						if(res.result === true){
							this.collegeData = res.rows;
						}else{
							alter(res.msg);   //显示查询错误
						}
					}).catch(err=>{
						console.log(err);
					});
		        },
		        /*查询专业信息 更新添加*/
		        loadMajors(){
		        	axios.get("/major/major").then(res=>{ //res 是返回对象
						res = res.data;
						//console.log(res);
						if(res.result === true){
							this.majorData = res.rows;
						}else{
							alter(res.msg);   //显示查询错误
						}
					}).catch(err=>{
						console.log(err);
					});
		        }
		      },
		    mounted: function(){
		    	this.loadStudents();
		      }
}