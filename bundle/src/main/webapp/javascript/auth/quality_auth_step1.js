var alreadyCheckedService = "";

function f_findServicesByFatherId(serviceId) {
	$.getJSON(f_getRootPath() + "/services/find_services_by_father_id.action?fatherId=" + serviceId, function(data){
		var errorFlag = data.errorFlag;
		if (errorFlag == 0) {
			var serviceHTML = "";
			$.each(data.resultObj, function(i,item){
				var html = "";
				if (alreadyCheckedService.indexOf(item.id + ",") == -1) {
					html ="<input type='checkbox' id='service_" + item.id + "' value='" + item.id + "' onclick=\"f_selectService(" + item.id + ",'" + item.serviceName + "')\"><label for='service_" + item.id + "'>" + item.serviceName + "</label></input> ";
				} else {
					html ="<input type='checkbox' id='service_" + item.id + "' value='" + item.id + "' onclick=\"f_selectService(" + item.id + ",'" + item.serviceName + "')\" checked='checked'><label for='service_" + item.id + "'>" + item.serviceName + "</label></input> ";
				}
				serviceHTML = serviceHTML + html;
			});
			$("#servicesShow").html(serviceHTML);
		}
	});
}

/**
 * 选择服务项目
 * @param id
 * @param name
 */
function f_selectService(id, name) {
	//文本框的已选项目
	var ss = $("#selectedServices").val();
	//新增服务
	if (f_isChecked($("#service_" + id))) {
		var newSelected = "<span id='selectedSpan_" + id + "' style='margin-left:5px;'>" + name + "</span>";
		$("#selectedServicesTD").append(newSelected);
		alreadyCheckedService = alreadyCheckedService + id + ",";
		$("#selectedServices").val(ss + id + ",");
	} else { //取消服务
		$("#selectedSpan_" + id).remove();
		alreadyCheckedService = alreadyCheckedService.replaceAll(id + ",", "", true);
		ss = ss.replaceAll(id + ",", "", true);
		$("#selectedServices").val(ss);
	}
}