/*
 * author:deji.xu
 * 2016-01-24
 */
(function($) {

	var RowOfLi = function(ele, opt) {
		this.$element = ele;
		this.options = $.extend({}, this.defaults, opt);
	};
	RowOfLi.defaults = {
		'marginLeft' : '5px',
		'lineHeight' : '80px'
	};
	RowOfLi.prototype.toRow = function() {
		var marginLeft = this.options.marginLeft;
		var lineHeight = this.options.lineHeight;
		var cssvar = {};
		for(var name in this.options){
			if( name=== 'lineHeight' || name==='marginLeft') continue;
//			names += '\''+name+'\'' + ": " +'\''+ this.options[name] +'\''+ ", ";
			cssvar[name]=this.options[name];
		}
//		alert(JSON.stringify(cssvar));
		return this.$element.each(function() {
			var $this = $(this);
			/*$this.css({
				'position' : 'relative'
			});*/
			$this.find("li").css({
				'position' : 'relative',
				'display' : 'inline',
				'margin-left' : marginLeft,
				'line-height' : lineHeight,
				'cursor' : 'pointer'/*,
				'float':'left'*/
			});
			$this.find("li").css(cssvar);
		});
	};
	$.fn.liToRow = function(options) {
		var rowOfLi = new RowOfLi(this, options);
		return rowOfLi.toRow();
	};

	var CheckboxChange = function(ele) {
		this.$element = ele;
	};
	CheckboxChange.prototype = {
		selectAll : function() {
			return this.$element.each(function(index){
				var $this = $(this);
				var $allChk = $this.find("thead :checkbox");
				var $tr = $this.find("tbody>tr");
				var className = "checkbox_change_"+index;
				$tr.find("td:first :checkbox").addClass(className);
				
				$allChk.on("click",function(){
					$tr.find("td:first :checkbox").prop("checked",this.checked);
				});
				$("."+className).on("click",function(){
					$allChk.prop("checked",$("."+className).length == $("."+className+":checked").length ? true : false);
				});
				
			});	
		}
	};
	$.fn.checkboxSelectAll = function() {
		var checkboxChange = new CheckboxChange(this);
		return checkboxChange.selectAll();
	};

})(jQuery);