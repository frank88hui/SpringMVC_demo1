<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>

    <script type="text/javascript">
        function doUpload() {

            var formData = new FormData($("#itemForm")[0]);
            $.ajax({
                url: '${pageContext.request.contextPath }/ajaxUpload.action', /*这是处理文件上传的servlet*/
                type: 'POST',
                data: formData,
                async: false,
                cache: false,
                contentType: false,
                processData: false,
                success: function (data) {
                    if ($("#img").prop('src') == undefined) {
                        $("#picspan").html("<img src=/pic/"+data+" id=\"img\" width=100 height=100/><br/>")
                    } else {
                        $("#img").prop('src', "/pic/" + data);
                    }
                    $("#picpath").val(data);
                },
                error: function (returndata) {
                    alert(returndata);
                }
            });

        }
    </script>

</head>
<body>
<!-- 上传图片是需要指定属性 enctype="multipart/form-data" -->
<!-- <form id="itemForm" action="" method="post" enctype="multipart/form-data"> -->
<form id="itemForm" action="${pageContext.request.contextPath }/updateitem.action" method="post"
      enctype="multipart/form-data">
    <input type="hidden" name="id" value="${item.id }"/> 修改商品信息：
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" value="${item.name }"/></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="price" value="${item.price }"/></td>
        </tr>

        <tr>
            <td>商品生产日期</td>
            <td><input type="text" name="createtime"
                       value="<fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
        </tr>

        <tr>
            <td>商品图片</td>
            <td>
                <span id="picspan">
                <c:if test="${item.pic !=null}">
                    <img src="/pic/${item.pic}" id="img" width=100 height=100/>
                    <br/>
                </c:if>
                </span>
                <input type="text" id="picpath" name="pic" value="${item.pic}" hidden="hidden"/>
                <input type="file" name="pictureFile" onchange="doUpload()"/>
            </td>
        </tr>

        <tr>
            <td>商品简介</td>
            <td><textarea rows="3" cols="30" name="detail">${item.detail }</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="提交"/>
            </td>
        </tr>
    </table>

</form>
</body>

</html>