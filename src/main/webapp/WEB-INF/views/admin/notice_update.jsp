<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table class="table">
	<tr>
		<td class="text-center"><h3>공지 목록</h3></td>
	</tr>
</table>
<form action="/admin/notice_update_ok" method="post" enctype="multipart/form-data">
<table class="table">
	<tbody>
		<tr>
			<th width="15%" class="success">공지 종류</th>
			<td width="85%">
				<select name="type" class="input-sm">
					<option ${vo.type=='일반공지'?'selected':'' }>일반공지</option>
					<option ${vo.type=='이벤트공지'?'selected':'' }>이벤트공지</option>
					<option ${vo.type=='여행공지'?'selected':'' }>여행공지</option>
					<option ${vo.type=='날씨공지'?'selected':'' }>날씨공지</option>
					<option ${vo.type=='추천공지'?'selected':'' }>추천공지</option>
				</select>
			</td>
		</tr>
		<tr>
			<th width="15%" class="success">제목</th>
			<td width="85%">
				<input type="text" name="subject" size="60" class="input-sm" required value="${vo.subject }">
				<input type="hidden" value="${vo.no }" name="no">
			</td>
		</tr>
		<tr>
			<th width="15%" class="success">내용</th>
			<td width="85%">
				<textarea rows="10" cols="60" name="content" style="resize: none;" required="required">${vo.content }</textarea>
			</td>
		</tr>
		<%-- <tr>
			<th width="15%" class="success">첨부파일</th>
			<td width="85%">
				<input type="file" name="files" size="20" class="input-sm" multiple="multiple" accept="image/*">
				
			</td>
		</tr> --%>
		<tr>
			<td class="text-center" colspan="2">
				<button type="submit" class="btn-sm btn-warning">등록</button>
				<button type="submit" class="btn-sm btn-warning" onclick="javascript:history.back()">취소</button>
			</td>
		</tr>
		
	</tbody>
</table>
</form>
</body>
</html>