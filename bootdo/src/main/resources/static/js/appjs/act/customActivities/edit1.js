// $().ready(function () {
//
// });
// $('#signupForm1').onsubmit(update1())
//
// $.validator.setDefaults({
// 	submitHandler : function() {
// 		update1();
// 	}
// });
function update1() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/activiti/customActivities/update",
        data: $('#signupForm1').serialize(),// 你的formid
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}


// var openUser = function(){
// 	layer.open({
// 		type:2,
// 		title:"选择人员",
// 		area : [ '300px', '450px' ],
// 		content:"/sys/user/treeView"
// 	})
// }
//
// function loadUser(userIds,userNames){
// 	$("#candidateUser").val(userIds);
// 	$("#candidateUserName").val(userNames);
// }