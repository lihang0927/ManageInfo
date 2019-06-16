var Score = {
		template : `
		<div>
			<el-button type="primary" icon="el-icon-edit" class="addButton" @click="AddStudent">增加选修</el-button>
			<el-table
		    ref="filterTable"
		    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
		    tooltip-effect="dark"
		    style="width: 100%"
		    @selection-change="handleSelectionChange">
		    
		    <el-table-column
		      type="selection"
		      width="55">
		    </el-table-column>
		    
		     /*选修id*/
		    <el-table-column
		      prop="id"
		      label="选修id"
		      width="120">
		    </el-table-column>
		    
		     /*课程名*/
		    <el-table-column
		      prop="course.name"
		      label="课程名"
		      width="120">
		    </el-table-column>
		    
		    /*开课学院*/
		    <el-table-column
		      prop="course.college.name"
		      label="开课学院"
		      width="120">
		    </el-table-column>
		    
		    /*学生学号*/
		    <el-table-column
		      prop="student.no"
		      label="学生学号"
		      width="120">
		    </el-table-column>
		    
		    /*学生姓名*/
		    <el-table-column
		      prop="student.name"
		      label="学生姓名"
		      width="120">
		    </el-table-column>
		    
		     /*分数 */
		     <el-table-column
		      prop="score"
		      label="分数"
		      width="120">
		    </el-table-column>
		    
		     <el-table-column
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
		
		
		<el-dialog title="选修信息" :visible.sync="dialogFormVisible">
		  <el-form :model="form">
		  
		<el-form-item label="学号" :label-width="formLabelWidth">
	      <el-input v-model="form.student.no" autocomplete="off"></el-input>
	    </el-form-item>
	    
	    <el-form-item label="姓名" :label-width="formLabelWidth">
	      <el-input v-model="form.student.name" autocomplete="off"></el-input>
	    </el-form-item>

			
		<el-form-item label="课程" :label-width="formLabelWidth">
		     <el-select v-model="form.course.id" placeholder="请选择课程">
		        <el-option label="数据结构"  value="1"></el-option>
		        <el-option label="数据库"  value="2"></el-option>
		        <el-option label="C语言"  value="3"></el-option>
		      </el-select>
	    </el-form-item>
		    
	    <el-form-item label="分数" :label-width="formLabelWidth">
		      <el-input v-model="form.score" autocomplete="off"></el-input>
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
				        multipleSelection: [],
				        search: '',
				         /*弹框是否打开*/
				        dialogFormVisible: false,
				        form: {
				        	id:'',
				            score: '',
				            course:{'id':1,'name':''},
				            student:{'id':1,'no':'','name':''}
				        },
				        /*状态为0：修改 状态为1：添加*/
				        status:0,
				        formLabelWidth: '120px',
				        filtersData:[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]
				      }
		    },
		    methods: {
		    	submitChange(){
		    		console.log(this.status);
		    		this.dialogFormVisible = false;

		    		/*如果未添加*/
		    		if(this.status==1){
		    			console.log(this.form);
		    			axios.post('/coursestudent/addCourseStudent',this.form).then(res=>{
		    				res = res.data;
//		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadCourseStudents();
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
		    			axios.put('/coursestudent/putCourseStudent',this.form).then(res=>{
		    				res = res.data;
//		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadCourseStudents();
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
		    			this.form=this.tableData[index];
		    		}
		        },
		        handleDelete(index, row) {
		            console.log(index, row);
		            console.log(row.id);
		            axios.delete('/coursestudent/deleteCourseStudent/'+row.id).then(res=>{
		            	  console.log(res);
					       res = res.data;
					       if(res.result){
					         this.loadCourseStudents();
					       }
					       alert(res.msg);   //显示提示信息
					     }).catch(err=>{
					       console.log(err);
					       alert('网络请求异常，请重试!');
					     });
		        },
		        AddStudent(){
		        	 if(this.dialogFormVisible==false){
		    			this.dialogFormVisible=true;
		    			this.form={
					        	id:'',
					            score: '',
					            course:{'id':1,'name':''},
					            student:{'id':1,'no':'','name':''}
					          };
		    			this.status=1;
			    	}
		        },
		        loadCourseStudents(){
		        	axios.get("/coursestudent/allCourseStudent").then(res=>{ //res 是返回对象
		        		//console.log(res);
						res = res.data;
						//console.log(res);
						if(res.result === true){
							this.tableData = res.rows;
						}else{
							alter(res.msg);   //显示查询错误
						}
					}).catch(err=>{
						console.log(err);
					});
		        }
		      },
		    mounted: function(){
		    	this.loadCourseStudents();
		      }
}