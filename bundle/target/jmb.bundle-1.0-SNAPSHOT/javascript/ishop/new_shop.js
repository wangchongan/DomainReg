$().ready(function(){
    var rootPath = f_getRootPath();
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="shopDTO.introDetail"]', {
			afterCreate : function() {

			}
		});
		prettyPrint();
	});
});

//是否有实体店
function f_hasRealShop(obj) {
	if ($(obj).val() == 'YES') {
		$("#hasRealShopTR").show();
	} else {
		$("#hasRealShopTR").hide();
	}
}