var buzzList = [];
var users = [];

function getBuzzList(buzzString) {
	var buzzList = [];
	if ((url == "") || (url == "?")) return;
	var parameters = url.substr(url.indexOf('?') + 1).split('&');
	for (var i = 0; i < parameters.length; i++) {
		//parameters[i]: buzz=buzz_name,sentiment@;@user1_name,user1_text,user1_image@,@user2_name...
		buzzList[i] = (parameters[i].split('='))[1];
	}
}

function marker_htmlMaker(store_detail) {
	var htmlString = "";
	htmlString = htmlString + "<div align='left' class='info'>";
	htmlString = htmlString + "<img src='" + store_detail[3] + "' width='64' height='64' /><br />";
	htmlString = htmlString + "<a class='normal_font'>Store Name</a><br />";
	htmlString = htmlString + "<a class='result_title_font'>" + unescape(store_detail[0]) + "</a><br />";
	htmlString = htmlString + "<a class='normal_font' href='#' onClick=\"window.open(\'" + store_detail[2] + "\')\">Sale Title & Link</a><br />";
	htmlString = htmlString + "<a class='result_title_font'>" + unescape(store_detail[1]) + "</a><br />";
	htmlString = htmlString + "<a class='normal_font'>Expire Date</a><br />";
	htmlString = htmlString + "<a class='result_title_font'>" + unescape(store_detail[5]) + "</a><br />";
	htmlString = htmlString + "<a class='normal_font'>Address</a><br />";
	htmlString = htmlString + "<a class='result_title_font'>" + unescape(store_detail[6]) + "</a><br />";
	htmlString = htmlString + "<a class='normal_font'>Phone</a><br />";
	htmlString = htmlString + "<a class='result_title_font'>" + unescape(store_detail[7]) + "</a><br />";
	htmlString = htmlString + "</div>";
	
	return htmlString;
}