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
		    
		    <el-table-column
		      label="入学日期"
		      width="120"
		      prop="date"
		      column-key="date"
		      :filters="filtersData"
		      :filter-method="filterHandler"
		      filter-placement="bottom-end"
		      >
		      <template slot-scope="scope">{{ scope.row.date }}</template>
		    </el-table-column>
		    
		    <el-table-column
		      prop="name"
		      label="姓名"
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
		  </el-table>
			`,
			 data: function(){
				 return {
					 tableData: [{
				          date: '2016-05-02',
				          name: '王小虎',
				          address: '上海市普陀区金沙江路 1518 弄',
				          tag: '家'
				        }, {
				          date: '2016-05-04',
				          name: '李宁',
				          address: '上海市普陀区金沙江路 1517 弄',
				          tag: '公司'
				        }, {
				          date: '2016-05-01',
				          name: '王小虎',
				          address: '上海市普陀区金沙江路 1519 弄',
				          tag: '家'
				        }, {
				          date: '2016-05-03',
				          name: '王小虎',
				          address: '上海市普陀区金沙江路 1516 弄',
				          tag: '公司'
				        }],
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
		        },
		          handleDelete(index, row) {
		            console.log(index, row);
		        }
		      }
}