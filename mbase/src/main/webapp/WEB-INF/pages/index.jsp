<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>article</title>
<style>
form {
	margin: 0;
}

textarea {
	display: block;
}
</style>
<link rel="stylesheet"
	href="static/css/editor/themes/default/default.css">
<script charset="utf-8" src="static/js/kindeditor-4.1.9/kindeditor.js"></script>
<script charset="utf-8" src="static/js/kindeditor-4.1.9/lang/zh_CN.js"></script>
<script charset="utf-8" src="static/js/jquery-1.10.1.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[name="content"]', {
			resizeType : 1,
			themeType : 'simple',
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			allowFileManager : true,
			uploadJson : 'editor/upload',
            fileManagerJson : 'editor/manager',
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
</script>
</head>
<body>
	<a href="">Mbase</a>
	<img src="static/img/edit.gif">
	<br>
	<form action="post" method="POST">
		<input type="text" name="title">
		<textarea name="content"
			style="width: 700px; height: 200px; visibility: hidden; display: none;">KindEditor</textarea>
		<input type="submit" value="提交" />
	</form>





<form action="editor/upload" method="POST" enctype="multipart/form-data">
	<input type="file" name="ff" />		<input type="submit" value="提交" />
	
</form>
</body>
</html>
