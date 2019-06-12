var student = {
		template : `
		
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
		    
		     /*学号*/
		    <el-table-column
		      prop="id"
		      label="学号"
		      width="120">
		    </el-table-column>
		    
		     /*姓名*/
		    <el-table-column
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
		    
		    <el-table-column
		      prop="address"
		      label="地址"
		      show-overflow-tooltip>
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
			  <el-button type="text" @click="open"></el-button>
		  </el-table>
			`,
			 data: function(){
				 return {
					 tableData: [],
				        multipleSelection: [],
				        search: '',
				        filtersData:[{text: '2016-05-01', value: '2016-05-01'}, {text: '2016-05-02', value: '2016-05-02'}, {text: '2016-05-03', value: '2016-05-03'}, {text: '2016-05-04', value: '2016-05-04'}]
				      }
		    },
		    methods: {
		        handleSelectionChange(val) {
		          this.multipleSelection = val;
		        },
		        filterHandler(value, row, column) {
		            const property = column['property'];
		            return row[property] === value;
		        },
		        handleEdit(index, row) {
		            console.log(index, row);
		            this.open();
		        },
		          handleDelete(index, row) {
		            console.log(index, row);
		        },
		        open() {
		            this.$alert('<div style="color:rebeccapurple">我是 componentC</div>', '学生信息修改', {
		              dangerouslyUseHTMLString: true
		            });
		        },
		        loadStudents(){
		        	axios.get("/student/student").then(res=>{ //res 是返回对象
						res = res.data;
//						console.log(res);
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
		    	this.loadStudents();
		      }
}