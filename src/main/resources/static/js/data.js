// 进入admin.html需要请求数据类型classes和 teacheres不需要参数
//admin.js修改url
var classes = [{
    class_id: "class_id1",
    class_name: "网络工程"
}, {
    class_id: "class_id2",
    class_name: "信息安全"
}];
var teacheres = {
    'total': 2,
    'rows': [{
        teacher_name: '董守玲',
        teacher_id: "111222333444",
        password: "123456",
        class_name: '网络工程'
    }, {
        teacher_name: '贺小箭',
        teacher_id: "222333444555",
        password: "123456",
        class_name: '网络工程'
    }]
};
//----------------------------

// admin.js 的52行 修改url post 请求添加班级
//post提交数据如： {"class_id":"CS-101","class_name":"计算机科学与技术"}

//----------------------------
// 根据参数[class_id]请求 班级学生信息 student_dg.js 修改url
var data = {
    'total': 2,
    'rows': [{
        student_name: '廖晓娟',
        student_id: "201430560243",
        password: "123456",
        class_name: '网络工程'
    }, {
        student_name: '要利娇',
        student_id: "201430560241",
        password: "123456",
        class_name: '网络工程'
    }]
};
//当请求班级没有成员的时候
var data = {
    'total': 0,
    'rows': []
};
//----------------------------
//  嘤嘤嘤嘤 datagrid.js 比较多 写在文档
