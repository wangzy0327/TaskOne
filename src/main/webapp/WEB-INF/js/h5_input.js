$(document).ready(function(){
    $("button").click(function(){
        var url = $("#url").val()
        console.log(url);
        $.ajax({
            url:"/urlSubmit",
            type:"POST",
            dataType:"json",
            contentType:"application/json;charset=UTF-8",
            // 向后端传递的数据
            data:JSON.stringify({
                "url":url
            }),
            success:function(result) {
                <!-- 处理后端返回的数据 -->
                // var message= JSON.stringify(result);
                // $("#select-box").html("查询成功" + message);
                var message= JSON.stringify(result);
                if(message && message['success']){
                    var data = message['data'];
                    if(!data)
                        $("#select-box").html("查询成功");
                    else{
                        $("#select-box").html(message['error']);
                    }
                }else{
                    console.log(message);
                }
            },
            error:function(result){
                $("#select-box").html("查询失败");
            }
        });
    });
});
// $(function(){
//     // var result = "这是段落的一些文本";
//     $("button").click(function(){
//         console.log("11111111");
//         $.ajax({url:"text1.txt",success:function(result){
//             $("#div1").html(result);
//         }});
//         console.log("22222222");
//     });
// });