var teachClass = {
		template : `
		<div>
			<el-button type="primary" icon="el-icon-edit" class="addButton" @click="AddCourseTeacher">增加授课</el-button>
			<el-table
		    ref="filterTable"
		    :data="tableData.filter(data => !search || data.teacher.name.toLowerCase().includes(search.toLowerCase()))"
		    tooltip-effect="dark"
		    style="width: 100%"
		    @selection-change="handleSelectionChange">
		    
		    <el-table-column
		      type="selection"
		      width="55">
		    </el-table-column>
		    
		     /*课程号*/
		    <el-table-column
		      prop="id"
		      label="编号"
		      width="120">
		    </el-table-column>
		    
		     <el-table-column
		      label="教师"
		      width="180">
		      <template slot-scope="scope">
		        <el-popover trigger="hover" placement="top">
		          <p>姓名: {{ scope.row.teacher.name }}</p>
		          <p>出生日期: {{ scope.row.teacher.birth }}</p>
		          <p>所属学院: {{ scope.row.teacher.college.name }}</p>
		          <p>职称: {{ scope.row.teacher.position }}</p>
		          <div slot="reference" class="name-wrapper">
		            <el-tag size="medium">{{ scope.row.teacher.name }}</el-tag>
		          </div>
		        </el-popover>
		      </template>
		    </el-table-column>
		    
		     <el-table-column
		      label="课程"
		      width="180">
		      <template slot-scope="scope">
		        <el-popover trigger="hover" placement="top">
		          <p>课程名: {{ scope.row.course.name }}</p>
		          <p>学分: {{ scope.row.course.credit }}</p>
		          <p>所属学院: {{ scope.row.course.college.name }}</p>
		          <div slot="reference" class="name-wrapper">
		            <el-tag size="medium">{{ scope.row.course.name }}</el-tag>
		          </div>
		        </el-popover>
		      </template>
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
		
		
		<el-dialog title="授课信息" :visible.sync="dialogFormVisible">
		  <el-form :model="form">
	
		    <el-form-item label="教师名" :label-width="formLabelWidth">
		      <el-input v-model="form.teacher.name" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    <el-form-item label="教师职称" :label-width="formLabelWidth">
			     <el-select v-model="form.teacher.position" placeholder="请选择教师职称">
			        <el-option label="教授"  value="教授"></el-option>
			        <el-option label="副教授"  value="副教授"></el-option>
			        <el-option label="讲师"  value="讲师"></el-option>
			      </el-select>
		    </el-form-item>
		    
		    <el-form-item label="出生日期" :label-width="formLabelWidth">
		    	 <el-date-picker
			      v-model="form.teacher.birth"
			      type="date"
			      placeholder="选择日期">
			    </el-date-picker>
		    </el-form-item>
		    
		    <el-form-item label="教师所属学院" :label-width="formLabelWidth">
			     <el-select v-model="form.teacher.college.name" placeholder="请选择所属学院">
			        <el-option label="计算机学院"  value="1"></el-option>
			        <el-option label="外国语学院"  value="2"></el-option>
			        <el-option label="土木学院"  value="3"></el-option>
			         <el-option label="材料学院"  value="4"></el-option>
			      </el-select>
		    </el-form-item>
		    
		    <el-form-item label="课程名" :label-width="formLabelWidth">
		      <el-input v-model="form.course.name" autocomplete="off"></el-input>
		    </el-form-item>
		    
		    <el-form-item label="学分" :label-width="formLabelWidth">
			     <el-select v-model="form.course.credit" placeholder="请选择学分">
			        <el-option label="1"  value="1"></el-option>
			        <el-option label="2"  value="2"></el-option>
			        <el-option label="3"  value="3"></el-option>
			         <el-option label="4"  value="4"></el-option>
			      </el-select>
		    </el-form-item>
		    
		    <el-form-item label="课程所属学院" :label-width="formLabelWidth">
			     <el-select v-model="form.course.college.name" placeholder="请选择所属学院">
			        <el-option label="计算机学院"  value="1"></el-option>
			        <el-option label="外国语学院"  value="2"></el-option>
			        <el-option label="土木学院"  value="3"></el-option>
			         <el-option label="材料学院"  value="4"></el-option>
			      </el-select>
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
				            teacher:{'id':'','name':'','position':'','birth':'','college':{'id':'','name':''}},
				            course:{'id':'','name':'','credit': '','college':{'id':'','name':''}}
				        },
				        /*状态为0：修改 状态为1：添加*/
				        status:0,
				        formLabelWidth: '120px',
				}
		    },
		    methods: {
		    	submitChange(){
		    		console.log(this.status);
		    		this.dialogFormVisible = false;

		    		/*如果未添加*/
		    		if(this.status==1){
		    			console.log(this.form);
		    			axios.post('/courseteacher/addCourseTeacher',this.form).then(res=>{
		    				res = res.data;
//		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadCourseTeacher();
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
		    			console.log(this.form);
		    			axios.put('/courseteacher/putCourseTeacher',this.form).then(res=>{
		    				res = res.data;
		    				console.log(res);
		    				if(res.result === true) {
		    					// 成功后刷新列表
		    					this.loadCourseTeacher();
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
		            axios.delete('/courseteacher/deleteCourseTeacher/'+row.id).then(res=>{
		            	  console.log(res);
					       res = res.data;
					       if(res.result){
					         this.loadCourseTeacher();
					       }
					       alert(res.msg);   //显示提示信息
					     }).catch(err=>{
					       console.log(err);
					       alert('网络请求异常，请重试!');
					     });
		        },
		        AddCourseTeacher(){
		        	 if(this.dialogFormVisible==false){
		    			this.dialogFormVisible=true;
		    			this.form={
				        	id:'',
				            teacher:{'id':'','name':'','position':'','birth':'','college':{'id':'','name':''}},
				            course:{'id':'','name':'','credit': '','college':{'id':'','name':''}}
				        };
		    			this.status=1;
			    	}
		        },
		        loadCourseTeacher(){
		        	axios.get("/courseteacher/allCourseTeacher").then(res=>{ //res 是返回对象
						res = res.data;
						console.log(res);
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
		    	this.loadCourseTeacher();
		      }
}